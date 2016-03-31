import java.util.Random;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;

public class Client {
    public static void main(String[] args) {
        String env = System.getProperty("env");

        if (env == null || env.isEmpty())
            throw new IllegalArgumentException();

        Random rnd = new Random();

        while (true) {
            Ignition.setClientMode(true);

            try (Ignite ignite = Ignition.start("ignite-" + env + ".xml")) {
                IgniteCache<Key, Value> cache = ignite.cache("test-cache");

                int idx = rnd.nextInt(5_000_000);

                if (rnd.nextDouble() > 0.5)
                    cache.put(new Key(idx), new Value(idx));
                else {
                    Value val = cache.get(new Key(idx));

                    if (val != null && val.index() != idx)
                        throw new AssertionError();
                }
            }
        }
    }
}
