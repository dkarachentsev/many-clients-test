import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;

public class Client {
    private static final int CLIENTS = 100;

    public static void main(String[] args) {
        final String env = System.getProperty("env");

        if (env == null || env.isEmpty())
            throw new IllegalArgumentException();

        ExecutorService exec = Executors.newFixedThreadPool(CLIENTS);

        for (int i = 0; i < CLIENTS; i++) {
            final String name = "ignite-" + i;

            exec.submit(new Runnable() {
                @Override public void run() {
                    try {
                        Random rnd = new Random();

                        IgniteConfiguration cfg = Ignition.loadSpringBean("ignite-" + env + ".xml", "ignite.cfg");

                        cfg.setGridName(name);
                        cfg.setClientMode(true);

                        setClientCfg(cfg);

                        try (Ignite ignite = Ignition.start(cfg)) {
                            IgniteCache<Key, Value> cache = ignite.cache("test-cache");

                            while (true) {
                                int idx = rnd.nextInt(5_000_000);

                                if (rnd.nextDouble() > 0.5)
                                    cache.put(new Key(idx), new Value(idx));
                                else {
                                    Value val = cache.get(new Key(idx));

                                    if (val != null && val.index() != idx)
                                        throw new AssertionError();
                                }

                                Thread.sleep(1000);
                            }
                        }
                    }
                    catch (Throwable t) {
                        System.err.println("== Fatal error: " + t.getMessage());
                        t.printStackTrace();
                    }
                }
            });
        }
    }

    private static void setClientCfg(IgniteConfiguration cfg) {
        cfg.setSystemThreadPoolSize(2);
        cfg.setPublicThreadPoolSize(2);
        cfg.setConnectorConfiguration(null);
    }
}
