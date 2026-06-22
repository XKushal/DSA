package Target;
import java.util.ArrayList;
import java.util.List;

//Time: O(n)
//Space: O(n)

// Longest Path With Different Adjacent Characters
public class LongestPath {

    int maxlen = 1; //tracks the global maximum length
    
    public int longestPath(int[] parent, String s){
        int n = parent.length;

        //build adjancency list for the tree
        List<List<Integer>> tree = new ArrayList<>();
        for(int i = 0; i < n; i++) tree.add(new ArrayList<>()); //This builds the tree using the parent array.
        /* parent = [-1, 0, 0, 1, 1]
            0 is root
            1 -> child of 0
            2 -> child of 0
            3 -> child of 1
            4 -> child of 1
        */
        for(int i = 1; i < n; i++) tree.get(parent[i]).add(i); //add child to parent list 

        //start dfs from the root node (index 0)
        dfs(0, tree, s);
        return maxlen;
    }

    //DFS returns the longest valid path starting from this node
    private int dfs(int node, List<List<Integer>> tree, String s){
        int longest = 0, secondLongest = 0; //track two longest child path
        
        //visit each child of the current node
        for (int child: tree.get(node)){
            int children = dfs(child, tree, s);

            //only consider if child's character is different from current node's character
            if(s.charAt(child) != s.charAt(node)){
                if(children > longest){
                    secondLongest = longest;
                    longest = children;
                }else if(children > secondLongest){
                    secondLongest = children;
                } 
            }
        }

        //update the global maximum path length
        maxlen = Math.max(maxlen, longest + secondLongest +1);

        //return the longest path including this node
        return longest + 1;
    }

    public static void main(String[] args) {
        LongestPath m = new LongestPath();

        int[] parent = {-1,0,0,0};
        String s = "aabc";

        System.out.println("Longest Path With Different Adjacent Characters: " + m.longestPath(parent, s));
    }
    
}
