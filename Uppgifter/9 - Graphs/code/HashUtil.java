public class HashUtil {
    public static int hash(String name, int mod) {
        int hash = 7;
        for (int i = 0; i < name.length(); i++) {
            hash = (hash * 31 + name.charAt(i)) % mod;
        }
        return hash;
    }
}
