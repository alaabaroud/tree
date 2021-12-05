package tree;

import org.w3c.dom.Node;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

public class Tree {
    private  class Node {
        private  int value;
         Node right;
         Node left;

        public Node(int value) {
            this.value= value;
        }

        @Override
        public String  toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }

    private  Node root;


    public void insert(int value) {
        if (root == null) {

            root = new Node(value);
            return;
        }
var current  = root;
        while (true) {
            if (value< current.value) {
                if (current.left == null){
                    current.left = new Node(value);
                    break;
                }
                current = current.left;
            } else{
                if (current.right == null) {
                    current.right = new Node(value);
                    break;

                }
                    current = current.right;
            }
        }
    }

    // recursion
    public void preOrder() {
        preOrder(root);
    }
    private void preOrder(Node root) {
if (root == null)
    return;

        System.out.println(root.value);
        preOrder(root.left);
        preOrder(root.right);

    }



    public void inOrder() {
        inOrder(root);
    }
    private void inOrder(Node root) {
        if (root == null)
            return;
        inOrder(root.left);
        System.out.println(root);
        inOrder(root.right);
    }


    public void postOrder() {
        postOrder(root);
    }
     void postOrder(Node root) {
        if(root == null) {
            return;
        } else{
            postOrder(root.left);
            postOrder(root.right);
            System.out.println(root.value + "");


        }
    }


    // calculating the height of a tree :
    public int height() {
       return height(root);
    }

    private int height(Node root) {
        if (root.left == null && root.right == null) {
            return 0;
        } else
            return 1 + Math.max(height(root.left), height(root.right));

    }


    // min node value.  O(N)
    public int min() {
        return min(root);
    }
    public int min (Node root) {
        if (root.right == null && root.left == null){
            return root.value;
        }else {
            var leftMin = min(root.left);
            var rightMin = min(root.right);
            return Math.min(Math.min(leftMin, rightMin), root.value);
        }
    }

    // min node value O(log n) binary search

    public int minBS(){
        if (root == null) {
            throw new IllegalStateException();

        }else {
            var current = root;
            var last = current;
            while (current!= null) {
                last = current;
                current = current.left;
            }
            return last.value;
        }
    }


// max node value O(N)
    public int max() {
        return max(root);
    }
    private int max( Node root) {
        if (root.left == null && root.right == null){
            return root.value;
        }else {
            var leftMax = max(root.left);
            var rightMax = max(root.right);
            return Math.max(Math.max(leftMax, rightMax), root.value);
        }
    }



    //identical trees /// preorder becasue we are starting from the root.
    // bigO(N)
public boolean equal(Tree other) {
        return equal( root, other.root);
}
    private boolean equal(Node a, Node b) {
        if (a == null && b == null) {
            return true; // two empty trees
        }
        // identical trees
        if  (a!= null && b != null) {
            if (a.value == b.value && equal(a.left, b.left) && equal(a.right, b.right)) {
return true;
            }
        }
        // not identical
        return false;

    }

public  boolean IsMerror(Tree other){
        return IsMerror(root, other.root);
}
    public boolean IsMerror(Node a, Node b) {
        if (a == null && b == null) {
            return false;
        }else{
            if (a == null || b == null){
                return false;
            }

        }
        if(a != null && b !=null){
            if (a.value== b.value && IsMerror(a.left , b.right) &&  IsMerror(a.right, b.left)){
                return true;
            }
        }
return false;
    }
}


-------------------
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.right , root.left);
    }

    public boolean isSymmetric (TreeNode right, TreeNode left){
        if(left == null || right ==null) {
            return left == right;
        }

        if (left.val != right.val){
            return false;
        }
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);

    }
}


///////////////////

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null ) {
            return root2;
        }
        if (root2 == null ) {
            return root1;
        }

        root1.val += root2.val ;
        root1.left =mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees( root1.right, root2.right);
        return root1;

    }
//////////////////////////
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) {
            return root;
        }

        var  left = invertTree(root.left);
        var  right= invertTree(root.right);

        root.left = right;
        root.right = left;
        return root;
    }
}

*///////////
class Solution {
    public boolean isUnivalTree(TreeNode root) {
        if(root == null) return true;

        if (root.left != null) {
            if (root.left.val != root.val){
                return false;
            }
        }
        if (root.right != null) {
            if (root.right.val != root.val){
                return false;
            }
        }

        return isUnivalTree(root.left) && isUnivalTree(root.right);
    }
}
/////////////
sum of left leaves

class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;

        }else if ( root.left != null && root.left.left == null && root.left.right == null) {
            return root.left.val +  sumOfLeftLeaves(root.right);
        }else  {
            return  sumOfLeftLeaves(root.left) +  sumOfLeftLeaves(root.right);
        }



    }
}
//////////////////
path sum
class Solution {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }else if (root.left==null && root.right == null && targetSum-root.val==0) {
            return true;
        }else  {
            return hasPathSum(root.left, targetSum-root.val) ||  hasPathSum(root.right, targetSum-root.val);
        }
    }

}
///////////////////////'

class Solution {
    public int findSecondMinimumValue(TreeNode root) {
        if(root.left == null)   return -1;

        int left  = (root.left.val  == root.val) ? findSecondMinimumValue(root.left)  : root.left.val;
        int right = (root.right.val == root.val) ? findSecondMinimumValue(root.right) : root.right.val;

        if(left  == -1)         return right;
        if(right == -1)         return left;

        return Math.min(left, right);
    }
}
///////////////

class Solution {
    public int minDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return Math.min(left, right) +1;

    }

}
///////////
static Node secondMax(Node node) {

    Node prevNode=null; //2nd largest Element
    Node currNode=node;
    if(null == currNode)
        return prevNode;

    while(currNode.right != null){
        prevNode=currNode;
        currNode=currNode.right;
    }
    if(currNode.left != null){
        currNode=currNode.left;
        while(currNode.right != null){
            currNode=currNode.right;
        }
        prevNode=currNode;
    }

    return prevNode;



}

/// odd sum
public int oddSum(){
    return inOrdeTraverser(this.getRoot());
}
    private int inOrderTree=0;
    public int inOrdeTraverser(Node node){
        if (node !=null){
            if ((Integer)node.getValue() %2 !=0){
                inOrderTree =inOrderTree+ (Integer)node.getValue();
            }
            inOrdeTraverser(node.getLeft());
            inOrdeTraverser(node.getRight());
        }
        return inOrderTree;
    }


    // array to BST

    public Node sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    public Node helper(int[] nums, int left, int right) {
        if(left > right) return null;

        int m = left + (right - left) / 2;
        Node root = new Node(nums[m]);
        root.left = helper(nums, left, m - 1);
        root.right = helper(nums, m + 1, right);
        return root;
    }
// check if bst

    boolean isBST(Node<Integer> node)
    {
        if (node == null)
            return true;

        if (node.left != null && node.left.value > node.value)
            return false;

        if (node.right != null && node.right.value < node.value)
            return false;
        return     (isBST(node.left) && isBST(node.right) );
    }


    // breadth first
    public String breadthFirst() {
        Node node = this.root;
        List<Integer> breathFirstValues = new ArrayList<>();
        breathFirstValues.add((Integer) node.getValue());
        return breadthFirstTraverse(node, breathFirstValues);
    }

    private String breadthFirstTraverse(Node node, List<Integer> breathFirstValues) {
        if (node != null) {
            if (node.getLeft() != null) {
                breathFirstValues.add((Integer) node.getLeft().getValue());
            }
            if (node.getRight() != null) {

                breathFirstValues.add((Integer) node.getRight().getValue());
            }
            breadthFirstTraverse(node.getLeft(), breathFirstValues);
            breadthFirstTraverse(node.getRight(), breathFirstValues);
        }
        return breathFirstValues.toString();
    }

   
