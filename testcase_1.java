import java.util.*;

public class testcase_1 {
    public static void main(String[] args) {
        // Input JSON-like data (hardcoded for this example)
        Map<String, Object> data = new HashMap<>();

        Map<String, Integer> keys = new HashMap<>();
        keys.put("n", 4);
        keys.put("k", 3);
        data.put("keys", keys);

        data.put("1", Map.of("base", "10", "value", "4"));
        data.put("2", Map.of("base", "2", "value", "111"));
        data.put("3", Map.of("base", "10", "value", "12"));
        data.put("6", Map.of("base", "4", "value", "213"));

        // Extract n and k
        int n = ((Map<String, Integer>) data.get("keys")).get("n");
        int k = ((Map<String, Integer>) data.get("keys")).get("k");
        int m = k - 1; // degree of polynomial

        // Convert roots to base-10
        Map<Integer, Integer> roots = new HashMap<>();
        for (String key : data.keySet()) {
            if (key.equals("keys")) continue;
            Map<String, String> val = (Map<String, String>) data.get(key);
            int base = Integer.parseInt(val.get("base"));
            String value = val.get("value");
            int decimal = Integer.parseInt(value, base);
            roots.put(Integer.parseInt(key), decimal);
        }

        // Print results
        System.out.println("Roots in base-10: " + roots);
        System.out.println("n (roots provided): " + n);
        System.out.println("k (minimum required): " + k);
        System.out.println("Polynomial degree (m): " + m);

        if (n >= k) {
            System.out.println("✅ Enough roots to determine coefficients.");
        } else {
            System.out.println("❌ Not enough roots to determine coefficients.");
        }
    }
}
