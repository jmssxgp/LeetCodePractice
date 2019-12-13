import java.util.*;

/**
 * contest double 12 B
 */
class Leaderboard {
    Map<Integer, Integer> map;
    List<Integer> list;
    public Leaderboard() {
        map = new HashMap<>();
    }
    
    public void addScore(int playerId, int score) {
        if (!map.containsKey(playerId))
            map.put(playerId, score);
        else {
            map.put(playerId, score+map.get(playerId));
        }
    }
    
    public int top(int K) {
        list = new ArrayList<>(map.values());
        list.sort(Comparator.comparingInt(a -> -a));
        int sum = 0;
        for (int i = 0; i < K; i++) {
            sum+=list.get(i);
        }
        return sum;
    }
    
    public void reset(int playerId) {
        map.remove(playerId);
    }
}