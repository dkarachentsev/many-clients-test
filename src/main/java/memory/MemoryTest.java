package memory;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteDataStreamer;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheMemoryMode;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.cache.affinity.rendezvous.RendezvousAffinityFunction;
import org.apache.ignite.configuration.CacheConfiguration;

public class MemoryTest {
    private static final int THREADS = Runtime.getRuntime().availableProcessors() * 2;

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
//        if (2 > 1) {
//            generate(Integer.parseInt(args[0]));
//
//            return;
//        }

        int size = Integer.parseInt(args[0]);

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

            load(ignite, size);

            System.out.println("Cache size: " + cache.size());
        }
    }

    private static void generate(final int size) throws IOException, InterruptedException {
        System.out.println("Generating data...");

        try (FileWriter writer = new FileWriter("wf_test.csv")) {
            for (int i = 0; i < size; i++) {
                StringBuilder builder = new StringBuilder();

                for (Field field : FIELDS)
                    builder.append("abcdef").append(',');

                String row = builder.deleteCharAt(builder.length() - 1).append('\n').toString();

                writer.write(row);

                if (i > 0 && i % 10000 == 0)
                    System.out.println(i);

                if (i >= size)
                    break;
            }

            writer.flush();
        }
    }

    private static void load(final Ignite ignite, final int size) throws IllegalAccessException, InterruptedException {
        System.out.println("Loading data...");

        try (IgniteDataStreamer<String, CmplTradeHist> streamer = ignite.dataStreamer(CACHE)) {
            ExecutorService exec = Executors.newFixedThreadPool(THREADS);

            final AtomicInteger cnt = new AtomicInteger();

            for (int i = 0; i < THREADS; i++) {
                exec.submit(new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        try {
                            while (true) {
                                int idx = cnt.getAndIncrement();

                                if (idx >= size)
                                    break;

                                CmplTradeHist val = new CmplTradeHist();

                                for (Field field : FIELDS)
                                    field.set(val, "abcdef");

                                streamer.addData(val.getCacheKey() + idx, val);

                                if (idx > 0 && idx % 10000 == 0)
                                    System.out.println("Loaded: " + idx);
                            }
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }

                        return null;
                    }
                });
            }

            exec.shutdown();

            exec.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        }
    }
}
