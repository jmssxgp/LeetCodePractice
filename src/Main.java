public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] t = {2, 0, 2, 1, 1, 0,1,2};
        solution.sortColors(t);
        for (int i = 0; i < 8; i++) {
            System.out.println(t[i]);
        }
    }
}
