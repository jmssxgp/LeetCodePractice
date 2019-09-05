/**
 * @author xgp
 * @version 1.0
 * @date 2019/9/5 19:51
 */
public class TrieNode {
    private int R = 26;
    private TrieNode[] nodes;
    boolean isEnd;

    public TrieNode(){
        nodes = new TrieNode[R];
    }

    public boolean containsKey(char ch){
        return nodes[ch-'a']!=null;
    }

    public TrieNode get(char ch){
        return nodes[ch-'a'];
    }

    public void put(char ch, TrieNode t){
        nodes[ch-'a'] =t;
    }
    public void setEnd(){
        isEnd = true;
    }

    public boolean isEnd(){
        return isEnd;
    }
}
