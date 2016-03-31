import org.apache.ignite.Ignition;

public class Server {
    public static void main(String[] args) {
        String env = System.getProperty("env");

        if (env == null || env.isEmpty())
            throw new IllegalArgumentException();

        Ignition.start("ignite-" + env + ".xml");
    }
}
