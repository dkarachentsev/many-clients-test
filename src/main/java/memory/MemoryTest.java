package memory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteDataStreamer;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheMemoryMode;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.cache.affinity.rendezvous.RendezvousAffinityFunction;
import org.apache.ignite.configuration.CacheConfiguration;

public class MemoryTest {
    private static final String FILE = "wf_test.csv";

    private static final String CACHE = "CmplTradeHist";

    private static final Field[] FIELDS;

    static {
        FIELDS = CmplTradeHist.class.getDeclaredFields();

        Arrays.sort(FIELDS, new Comparator<Field>() {
            @Override public int compare(Field f1, Field f2) {
                return f1.getName().compareTo(f2.getName());
            }
        });
    }

    public static void main(String[] args) throws Exception {
        if (args.length >= 2 && "--generate".equals(args[0])) {
            generate(Integer.parseInt(args[1]));

            return;
        }

        String env = System.getProperty("env");

        if (env == null || env.isEmpty())
            throw new IllegalArgumentException();

        Ignition.setClientMode(true);

        try (Ignite ignite = Ignition.start("ignite-" + env + ".xml")) {
            CacheConfiguration<String, CmplTradeHist> ccfg = new CacheConfiguration<>(CACHE);

            ccfg.setMemoryMode(CacheMemoryMode.OFFHEAP_TIERED);
            ccfg.setOffHeapMaxMemory(400 * 1024L * 1024L * 1024L);
            ccfg.setAffinity(new RendezvousAffinityFunction(false, 256));
            ccfg.setCacheMode(CacheMode.PARTITIONED);
            ccfg.setBackups(1);
            ccfg.setIndexedTypes(String.class, CmplTradeHist.class);
            ccfg.setStatisticsEnabled(true);

            IgniteCache<String, CmplTradeHist> cache = ignite.getOrCreateCache(ccfg);

            load(ignite);

            System.out.println(cache.size());
            System.out.println(cache.metrics().getOffHeapAllocatedSize());
        }
    }

    private static void generate(int size) throws IOException {
        System.out.println("Generating data...");

        try (FileWriter writer = new FileWriter(FILE)) {
            for (int i = 0; i < size; i++) {
                StringBuilder builder = new StringBuilder();

                for (Field field : FIELDS)
                    builder.append(field.getName()).append('-').append(i).append(',');

                String row = builder.deleteCharAt(builder.length() - 1).append('\n').toString();

                writer.write(row);

                if (i > 0 && i % 10000 == 0)
                    System.out.println(i);
            }

            writer.flush();
        }
    }

    private static void load(Ignite ignite) throws IOException, IllegalAccessException {
        System.out.println("Loading data...");

        try (IgniteDataStreamer<String, CmplTradeHist> streamer = ignite.dataStreamer(CACHE)) {
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
                String line;

                int cnt = 0;

                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");

                    CmplTradeHist val = new CmplTradeHist();

                    for (int i = 0; i < parts.length; i++)
                        FIELDS[i].set(val, parts[i]);

                    streamer.addData(val.getCacheKey(), val);

                    if (++cnt % 10000 == 0)
                        System.out.println(cnt);
                }
            }
        }
    }
}
