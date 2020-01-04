package tjs.bc.testdemo.tree;
 
import lombok.Data;
import lombok.ToString;
 
/**
 * @project: testdemo
 * @author: SJT
 * @date: 2019/2/22
 * @desc:
 */
@Data
@ToString
public class TreeNode {
 
    private TreeNode leftChild;
 
    private TreeNode rightChild;
 
    private int val;
 
    private boolean isDelete;
 
    public TreeNode(){}
    public TreeNode(int val){
        this.val = val;
    }
}