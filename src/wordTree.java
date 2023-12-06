import java.util.*;
public class wordTree {
    public class treeNode{
        HashMap<Character, treeNode> children;
        char c;
        boolean isEndOfWord;
        public treeNode(char character) {
            c = character;
            children = new HashMap<>();
        }
        public treeNode() {
            children = new HashMap<>();
        }

        public void insert (String word) {
            if (word == null || word.isEmpty()) {
                return;
            }
            treeNode traverse = children.get(word.charAt(0));
            for (int i = 0; i < word.length(); ++ i) {
                if (i == 0) {
                    if (traverse == null) {
                        traverse = new treeNode(word.charAt(i));
                        children.put(word.charAt(i), traverse);
                    }
                }
                if (i != 0) {
                    if (traverse.children.get(word.charAt(i)) == null) {
                        treeNode ins = new treeNode(word.charAt(i));
                        traverse.children.put(word.charAt(i), ins);
                        traverse = ins;
                    } else {
                        traverse = traverse.children.get(word.charAt(i));
                    }
                }
            }
            traverse.isEndOfWord = true;
        }
    }
    treeNode root;
    ArrayList<String> wordList;
    public wordTree() {
        wordList = new ArrayList<>();
        root = new treeNode();
    }

    public void insert(String word) {
        wordList.add(word);
        root.insert(word);
    }

    public boolean isEmpty(treeNode root)
    {
        for (int i = 0; i < 26; i++) {
            char x = (char) ('a' + i);
            if (root.children.get(x) != null) {
                return false;
            }
        }
        return true;
    }

    public treeNode remove(treeNode root, String key, int depth)
    {
        if (root == null)
            return null;

        if (depth == key.length()) {

            if (root.isEndOfWord)
                root.isEndOfWord = false;

            if (isEmpty(root)) {
                root = null;
            }

            return root;
        }

        root.children.put(key.charAt(depth), remove(root.children.get(key.charAt(depth)), key, depth + 1));

        if (isEmpty(root) && root.isEndOfWord == false){
            root = null;
        }

        return root;
    }

    public void removeWord(String word) {
        root = remove(root, word, 0);
        wordList.remove(word);
    }
    public ArrayList<String> getWordList() {
        return wordList;
    }
    public void possibleWord(treeNode traverse, List<String> list, StringBuilder currentWord) {
        if (traverse.isEndOfWord) {
            list.add(currentWord.toString());
        }
        if (traverse.children == null || traverse.children.isEmpty()) {
            return;
        }
        for (treeNode child: traverse.children.values()) {
            possibleWord(child, list, currentWord.append(child.c));
            currentWord.deleteCharAt(currentWord.length() - 1);
        }
    }
    public List<String> autoSuggest(String text) {
        List<String> list = new ArrayList<>();
        StringBuilder currentWord = new StringBuilder();
        treeNode traverse = root;
        for (int i = 0; i < text.length(); ++ i) {
            traverse = traverse.children.get(text.charAt(i));
            if (traverse == null) {
                return list;
            }
            currentWord.append(text.charAt(i));
        }
        possibleWord(traverse, list, currentWord);
        return list;
    }

}