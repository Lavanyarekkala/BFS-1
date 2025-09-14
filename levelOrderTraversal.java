// Time Complexity : O(N) where N is the number of nodes in the binary tree
// Space Complexity : O(h) where h is the height of the binary tree
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

/**
 * Here, we are using BFS approach to traverse the binary tree level by level.
 * we take a snapshot of the size of the queue at each level and iterate until that size and add the children of each node to the queue. 
 * we also use the size to ensure that only nodes belonging to the same level are added to the temp list.
 * This size snapshot is necessary because as we add children to the queue, the size of the queue changes dynamically and it becomes difficult to determine where one level ends and the next begins and to group nodes by their levels.
 * The temp list is a grouping of nodes at the same level, which is then added to the result list.
 * 
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class levelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> result=new ArrayList<>();

        if(root==null) return result;

        Queue<TreeNode> q=new LinkedList<>();

        q.add(root);


        while(!q.isEmpty())
        {
            int size=q.size();
            List<Integer> temp=new ArrayList<>();

            for(int i=0;i<size;i++)
            {
                TreeNode currNode=q.poll();
                temp.add(currNode.val);
                
                if(currNode.left!=null)
                {
                    q.add(currNode.left);
                }
                if(currNode.right!=null)
                {
                    q.add(currNode.right);
                }
            }
            result.add(temp);

        }
        return result;
        
    }
}