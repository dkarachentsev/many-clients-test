import org.apache.ignite.Ignition;

public class Server {
    public static void main(String[] args) {
        Ignition.start("ignite.xml");
    }
}
