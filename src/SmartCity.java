import java.util.HashMap;
import java.util.Map;

public class SmartCity {

    private Map<String, User> users;
    private Map<String, Tip> tipsByID;

    public SmartCity() {
        this.users = new HashMap<>();
        this.tipsByID = new HashMap<>();
    }
}
