import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] t ={1,3,2};
        solution.nextPermutation(t);
        for (int i:t
             ) {
            System.out.println(i);
        }
    }
}
