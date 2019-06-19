public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] t = {1,1,1,1,1,1,1};
        int c = solution.removeDuplicates(t);
        for (int i = 0; i < c; i++) {
            System.out.println(t[i]);
        }
    }
}
