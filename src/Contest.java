import java.util.*;

/**
 * @author xgp
 * @version 1.0
 * @date 2019/9/2 9:42
 */

public class Contest {
    /**
     * contest 152 D
     * @param words
     * @param puzzles
     * @return
     */
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        List<Integer> l = new ArrayList<>();
        for (int i=0; i<puzzles.length;i++){
            l.add(0);
        }
        Map<Integer, Integer> map = new HashMap<>();
        int k;
        for (int i = 0; i < words.length; i++) {
            char[] word = words[i].toCharArray();
            k=0;
            for (int j=0;j<word.length;j++){
                k|=1<<word[j]-'a';
            }
            if (map.containsKey(k)){
                map.put(k,map.get(k)+1);
            }else {
                map.put(k,1);
            }
        }

        for (int i = 0; i < puzzles.length; i++) {
            char[] puzzle = puzzles[i].toCharArray();
            k=0;
            for (int j = 0; j < puzzle.length; j++) {
                k|=1<<puzzle[j]-'a';
            }
            for (int j = k; j!=0; j=j-1&k){
                int z = j>>>puzzle[0]-'a';
                if ((z&1)>0&&map.containsKey(j))
                    l.set(i,map.get(j)+l.get(i));
            }
        }
        return l;
    }


}
