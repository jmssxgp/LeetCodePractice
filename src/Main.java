import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "0109910";
        List<String> l = solution.restoreIpAddresses(s);
        for (String string:
             l) {
            System.out.println(string);
        }
    }
}
