import java.math.BigInteger;
import java.util.*;
import java.util.regex.*;

public class Main {
    public static void main(String[] args) {
        // Put your JSON input here as a string
        String json = "{\n" +
                "\"keys\": { \"n\": 10, \"k\": 7 },\n" +
                "\"1\": { \"base\": \"6\", \"value\": \"13444211440455345511\" },\n" +
                "\"2\": { \"base\": \"15\", \"value\": \"aed7015a346d635\" },\n" +
                "\"3\": { \"base\": \"15\", \"value\": \"6aeeb69631c227c\" },\n" +
                "\"4\": { \"base\": \"16\", \"value\": \"e1b5e05623d881f\" },\n" +
                "\"5\": { \"base\": \"8\", \"value\": \"316034514573652620673\" },\n" +
                "\"6\": { \"base\": \"3\", \"value\": \"2122212201122002221120200210011020220200\" },\n" +
                "\"7\": { \"base\": \"3\", \"value\": \"20120221122211000100210021102001201112121\" },\n" +
                "\"8\": { \"base\": \"6\", \"value\": \"20220554335330240002224253\" },\n" +
                "\"9\": { \"base\": \"12\", \"value\": \"45153788322a1255483\" },\n" +
                "\"10\": { \"base\": \"7\", \"value\": \"1101613130313526312514143\" }\n" +
                "}";

        Pattern entryPat = Pattern.compile("\"(\\d+)\"\\s*:\\s*\\{([^}]*)\\}");
        Pattern basePat  = Pattern.compile("\"base\"\\s*:\\s*\"([^\"]+)\"");
        Pattern valuePat = Pattern.compile("\"value\"\\s*:\\s*\"([^\"]+)\"");

        Matcher m = entryPat.matcher(json);
        TreeMap<Integer, BigInteger> roots = new TreeMap<>();
        while (m.find()) {
            String keyStr = m.group(1);
            String content = m.group(2);
            Matcher bm = basePat.matcher(content);
            Matcher vm = valuePat.matcher(content);
            if (bm.find() && vm.find()) {
                int key = Integer.parseInt(keyStr);
                int base = Integer.parseInt(bm.group(1));
                String value = vm.group(1);
                try {
                    BigInteger dec = new BigInteger(value, base);
                    roots.put(key, dec);
                } catch (Exception ignored) {}
            }
        }

        // print output in same style as your first testcase
        boolean first = true;
        for (BigInteger v : roots.values()) {
            if (!first) System.out.print(" ");
            System.out.print(v);
            first = false;
        }
    }
}
