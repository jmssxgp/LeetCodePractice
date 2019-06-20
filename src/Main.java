import java.util.List;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<Integer> l = solution.grayCode(3);
        for (int i:l
             ) {
            System.out.println(i);
        }
    }
}
