package tjs.bc.testdemo.tree;
 
import lombok.Data;
import lombok.ToString;
 
import java.util.Stack;
 
/**
 * @project: testdemo
 * @author: SJT
 * @date: 2019/2/22
 * @desc:
 */
@ToString
@Data
public class BinaryTree {
 
    private TreeNode root;
 
    /**
    * @desc: 二叉树插入数据
    * @author: SJT
    * @date: 2019/2/22
    * @param: [val]
    * @return: void
    */
    public void insert(int val){
        TreeNode newNode = new TreeNode(val);
        if(root == null){
            root = newNode;
            root.setLeftChild(null);
            root.setRightChild(null);
        } else {
            TreeNode currentNode = root;
            TreeNode parentNode;
            while (true){
                //往右子节点放
                parentNode = currentNode;
                if(newNode.getVal() > currentNode.getVal()){
                    currentNode = currentNode.getRightChild();
                    if(currentNode == null){
                        parentNode.setRightChild(newNode);
                        return;
                    }
                }else {
                    //往左子节点放
                    currentNode = currentNode.getLeftChild();
                    if (currentNode == null){
                        parentNode.setLeftChild(newNode);
                        return;
                    }
                }
            }
        }
    }
 
    /**
    * @desc: 二叉树根据值查找
    * @author: SJT
    * @date: 2019/2/22
    * @param: [val]
    * @return: tjs.bc.testdemo.tree.TreeNode
    */
    public TreeNode find(int val) {
        TreeNode currentNode = root;
        if (currentNode != null) {
            while (currentNode.getVal() != val) {
                if (currentNode.getVal() > val) {
                    currentNode = currentNode.getLeftChild();
                } else {
                    currentNode = currentNode.getRightChild();
                }
                if (currentNode == null) {
                    return null;
                }
            }
            if (currentNode.isDelete()) {
                return null;
            }else {
                return currentNode;
            }
        } else {
            return null;
        }
    }
 
    /**
    * @desc: 前序遍历递归算法访问
    * @author: SJT
    * @date: 2019/2/22
    * @param: [root]
    * @return: void
    */
    public void preOrder(TreeNode root){
        if(root == null) {
            return;
        }
        System.out.println(root.getVal());
        preOrder(root.getLeftChild());
        preOrder(root.getRightChild());
    }
 
    /**
    * @desc: 前序遍历迭代算法访问
    * @author: SJT
    * @date: 2019/2/23
    * @param: [root]
    * @return: void
    */
    public void _preOrder(TreeNode root){
        if(root != null){
            Stack<TreeNode> stack = new Stack<>();
            TreeNode currentNode = root;
            stack.push(currentNode);
            while (!stack.isEmpty()&&(currentNode = stack.pop())!= null){
                System.out.println(currentNode.getVal());
                if(currentNode.getRightChild() != null){
                    stack.push(currentNode.getRightChild());
                }
                if(currentNode.getLeftChild() != null){
                    stack.push(currentNode.getLeftChild());
                }
            }
        }
    }
 
    /**
    * @desc: 中序遍历递归访问
    * @author: SJT
    * @date: 2019/2/22
    * @param: [root]
    * @return: void
    */
    public void inOrder(TreeNode root){
        if(root == null){
            return;
        }
        inOrder(root.getLeftChild());
        System.out.println(root.getVal());
        inOrder(root.getRightChild());
    }
 
    /**
     * @desc: 中序遍历迭代算法访问
     * @author: SJT
     * @date: 2019/2/23
     * @param: [root]
     * @return: void
     */
    public void _inOrder(TreeNode root){
        if(root != null){
            Stack<TreeNode> stack = new Stack<>();
            TreeNode currentNode = root;
            while (!stack.isEmpty() || currentNode != null){
                while (currentNode != null){
                    stack.push(currentNode);
                    currentNode = currentNode.getLeftChild();
                }
                currentNode = stack.peek();
                stack.pop();
                System.out.println(currentNode.getVal());
                currentNode = currentNode.getRightChild();
            }
        }
    }
 
    /**
    * @desc: 后续递归遍历访问
    * @author: SJT
    * @date: 2019/2/22
    * @param: [root]
    * @return: void
    */
    public void postOrder(TreeNode root){
        if (root == null){
            return;
        }
        postOrder(root.getLeftChild());
        postOrder(root.getRightChild());
        System.out.println(root.getVal());
    }
 
    /**
     * @desc: 后续遍历迭代算法访问
     * @author: SJT
     * @date: 2019/2/23
     * @param: [root]
     * @return: void
     */
    public void _postOrder(TreeNode root){
        if(root != null){
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            Stack<Integer> vals = new Stack<>();
            TreeNode currentNode;
            while (!stack.isEmpty()){
                currentNode = stack.peek();
                stack.pop();
                vals.push(currentNode.getVal());
                if(currentNode.getLeftChild() != null){
                    stack.push(currentNode.getLeftChild());
                }
                if(currentNode.getRightChild() != null){
                    stack.push(currentNode.getRightChild());
                }
 
            }
            while (!vals.isEmpty()){
                System.out.println(vals.pop());
            }
        }
 
    }
 
    public static void main(String[] args) throws InterruptedException {
        BinaryTree tree = new BinaryTree();
        tree.insert(10);
        tree.insert(11);
        tree.insert(2);
        tree.insert(3);
        tree.insert(1);
        tree.insert(5);
        tree.insert(4);
        tree.insert(6);
        TreeNode node = tree.find(5);
        System.out.println(node.getVal());
        System.out.println("\n----先序递归遍历访问-----\n");
        tree.preOrder(tree.getRoot());
        System.out.println("\n----先序迭代遍历访问-----\n");
        tree._preOrder(tree.getRoot());
        System.out.println("\n----中序递归遍历访问-----\n");
        tree.inOrder(tree.getRoot());
        System.out.println("\n----中序迭代遍历访问-----\n");
        tree._inOrder(tree.getRoot());
        System.out.println("\n----后序递归遍历访问-----\n");
        tree.postOrder(tree.getRoot());
        System.out.println("\n----后序迭代遍历访问-----\n");
        tree._postOrder(tree.getRoot());
 
 
    }
}