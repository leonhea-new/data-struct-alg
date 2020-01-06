package personal.ha;


import sun.text.normalizer.UCharacter;

import java.util.*;

public class ACTireTree {

    public ACNode rootNode = new ACNode('/', -1);

    public void addString(String str) {
        char[] chars= str.toCharArray();
        ACNode currentNode = rootNode;
        for (char c: chars) {
            if (! currentNode.subNodes.containsKey(c)) {
                currentNode.addSubNode(c);
            }
            currentNode = currentNode.subNodes.get(c);
        }
        currentNode.isEnd = true;
    }

    public void genNext() {
        Queue<ACNode> toBeProcessed = new LinkedList<>();
//        for (Map.Entry<Character, ACNode> entry : rootNode.subNodes.entrySet()) {
//            entry.getValue().next = rootNode;
//        }
//        toBeProcessed.addAll(rootNode.subNodes.values());
        toBeProcessed.add(rootNode);
        ACNode currentParentNode;
        while(toBeProcessed.peek() != null) {
            currentParentNode = toBeProcessed.poll();
            for (Map.Entry<Character, ACNode> entry : currentParentNode.subNodes.entrySet()) {
                ACNode parentNextNode = currentParentNode.next;
                ACNode currentNode = entry.getValue();
                Character currentChar = entry.getKey();
                while (parentNextNode != null && (!parentNextNode.subNodes.containsKey(currentChar) ) ) {
                    parentNextNode = parentNextNode.next;
                }
                if (  parentNextNode == null ) currentNode.next = rootNode;
                else currentNode.next = parentNextNode.subNodes.get(currentChar);
                toBeProcessed.add(currentNode);
            }
        }
    }

    public void match(String str) {
        char[] chars= str.toCharArray();
        ACNode currentNode = rootNode;
        for (int i=0; i<chars.length; i++) {
            while( ! currentNode.subNodes.containsKey(chars[i]) && currentNode.next != null) {
                currentNode = currentNode.next;
            }
            if (currentNode.subNodes.containsKey(chars[i])) {
                currentNode = currentNode.subNodes.get(chars[i]);
            }
            if (currentNode.isEnd) {
                System.out.print("mathch: "+ (i -currentNode.depth) + "->" + i + ": ");
                for (int j = i-currentNode.depth; j<=i; j++) System.out.print(chars[j]);
                System.out.print("\n");
            }
        }

    }

    @Override
    public String toString() {
        Queue<ACNode> currentLevel = new LinkedList<>();
        Queue<ACNode> nextLevel = new LinkedList<>();
        currentLevel.add(rootNode);
        StringBuilder result = new StringBuilder();
        ACNode currentNode;
        while(true) {
            while (currentLevel.peek() != null) {
                currentNode = currentLevel.poll();
                result.append(currentNode);
                result.append("_");
                result.append(currentNode.depth);
                result.append(" ");
                nextLevel.addAll(currentNode.subNodes.values());
            }
            result.append("\n");
            if (nextLevel.size() > 0) {
                currentLevel = nextLevel;
                nextLevel = new LinkedList<>();
            } else {
                break;
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        ACTireTree tree = new ACTireTree();
        tree.addString("abc");
        tree.addString("abcdabc");
        tree.addString("abcdefa");
        tree.genNext();
        System.out.println(tree);
        tree.match("efadabcabcdabceabcdefa");
    }
}


class ACNode {

    Character data;
    HashMap<Character, ACNode> subNodes;
    ACNode next;
    int depth = -1;
    boolean isEnd = false;

    public ACNode(char c, int depth) {
        data = c;
        subNodes = new HashMap<>();
        this.depth = depth;
    }

    public void addSubNode(char c) {
        subNodes.put(c, new ACNode(c, depth + 1));
    }

    @Override
    public String toString(){
        if ( next == null) return data.toString()+ "(null)";
        return data.toString() + "(" + next.data + "," + isEnd + ")";
    }



}