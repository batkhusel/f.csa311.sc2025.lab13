package AndrewWebServices;

import java.util.HashMap;
import java.util.Map;

public class InMemoryDatabase extends Database {
    private Map<String, Integer> users = new HashMap<>();

    public void addUser(String accountName, int password) {
        users.put(accountName, password);
    }

    @Override
    public int getPassword(String accountName) {
        return users.getOrDefault(accountName, 0);
    }
}
