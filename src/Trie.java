/**
 * @author xgp
 * @version 1.0
 * @date 2019/9/5 19:56
 */
public class Trie {
    private TrieNode root;

    public Trie(){
        root = new TrieNode();
    }

    public void insert(String word){
        TrieNode node = root;
        for (int i=0; i<word.length();i++){
            char currentChar = word.charAt(i);
            if (!root.containsKey(currentChar)){
                node.put(currentChar, new TrieNode()); //不存在则创建新的节点
            }
            node = node.get(currentChar);     //移动到新节点或已存在的下一节点，节点所在位置已经保存了字符信息
        }
        node.setEnd();
    }

    private TrieNode searchPrefix(String word){
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (node.containsKey(currentChar)){
                node = node.get(currentChar);
            }else {
                return null;
            }
        }
        return node;
    }

    public boolean search(String word){
        TrieNode node = searchPrefix(word);
        return node!=null&&node.isEnd();
    }

    public boolean startsWith(String prefix){
        TrieNode node = searchPrefix(prefix);
        return node!=null;
    }
}
