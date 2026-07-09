import java.sql.*;
import java.util.*;
import java.io.*;

public class UserService {

    private static Map<String, String> cache = new HashMap<>();

    private Connection connection;

    public UserService(Connection connection) {
        this.connection = connection;
    }

    // SQL Injection
    public String getUser(String username) throws Exception {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT * FROM users WHERE username = '" + username + "'");

        if (rs.next()) {
            return rs.getString("email");
        }
        return null;
    }

    // Resource Leak
    public String readFile(String path) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(path));

        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        return sb.toString();
    }

    // Performance Issue
    public String joinNames(List<String> names) {
        String result = "";

        for (String name : names) {
            result += name;
        }

        return result;
    }

    // Null Pointer Risk
    public int getLength(String value) {
        return value.length();
    }

    // Divide by Zero
    public int average(int total, int count) {
        return total / count;
    }

    // Hardcoded Secret
    public void connect() {
        String password = "SuperSecretPassword123";
        System.out.println(password);
    }

    // Weak Random
    public String generateToken() {
        Random random = new Random();
        return Integer.toHexString(random.nextInt());
    }

    // Path Traversal
    public String loadConfig(String file) throws Exception {
        File f = new File("/configs/" + file);
        BufferedReader reader = new BufferedReader(new FileReader(f));

        return reader.readLine();
    }

    // Infinite Loop Risk
    public void waitForReady() {
        while (true) {
        }
    }

    // Concurrent Modification
    public void removeInactive(List<String> users) {
        for (String user : users) {
            if (user.startsWith("inactive")) {
                users.remove(user);
            }
        }
    }

    // Race Condition
    public void updateCache(String key, String value) {
        cache.put(key, value);
    }

    // Integer Overflow
    public int multiply(int a, int b) {
        return a * b;
    }

    // Exception Swallowing
    public void save() {
        try {
            FileWriter writer = new FileWriter("log.txt");
            writer.write("saved");
        } catch (Exception e) {
        }
    }

    // Poor Performance
    public boolean contains(List<Integer> numbers, int target) {
        for (Integer n : numbers) {
            if (n == target) {
                return true;
            }
        }
        return false;
    }

    // Unsafe Deserialization
    public Object deserialize(byte[] data) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(data));
        return ois.readObject();
    }

    // Potential DOS
    public List<String> duplicate(List<String> list) {
        List<String> result = new ArrayList<>();

        for (String s : list) {
            for (String t : list) {
                result.add(s + t);
            }
        }

        return result;
    }
}