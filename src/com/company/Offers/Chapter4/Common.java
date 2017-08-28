package com.company.Offers.Chapter4;

import com.company.BasedClass.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by himon on 17-8-25.
 */
public class Common {

    /*面试题27, 二叉树的镜像*/
    //1.层次遍历实现
    private static TreeNode Mirror(TreeNode root){
        //鲁棒性
        if (root == null)
            return null;
        if (root.right == null || root.left == null)
            return null;

        for (int i=1;i<= TreeNode.getTreeHeight(root);i++){
            mirrorRecursively(root, i);
        }
        TreeNode.printTree(root);
        return root;
    }

    private static void mirrorRecursively(TreeNode root, int level){
        if (root == null || level<1)
            return ;

        if (level == 1){
            TreeNode temp = root.right;
            root.right = root.left;
            root.left = temp;
            return ;
        }
        mirrorRecursively(root.left, level - 1);
        mirrorRecursively(root.right, level - 1);
    }

    //2.前序遍历来实现
    private static void pre_mirror(TreeNode root){
        //鲁棒性
        if (root == null)
            return ;
        if (root.right == null || root.left == null)
            return ;
        TreeNode temp = root.right;
        root.right = root.left;
        root.left = temp;

        if (root.left != null)
            pre_mirror(root.left);
        if (root.right != null)
            pre_mirror(root.right);
    }

    /*面试题28,对称的二叉树*/
    private static boolean isSymmetrical(TreeNode root){
        if (root == null)
            return false;
        List<Object> pre_order = new ArrayList<>();
        List<Object> mirror_order = new ArrayList<>();
        pre_order = TreeNode.preOrder(root, pre_order);
        System.out.println(pre_order.toString());
        mirror_order = mirror_order(root, mirror_order);
        System.out.println(mirror_order.toString());
        return pre_order.equals(mirror_order);
    }

    private static List<Object> mirror_order(TreeNode root, List<Object> result){
        if (root == null) {
            result.add(null);
            return result;
        }
        result.add(root.val);
        mirror_order(root.right, result);
        mirror_order(root.left, result);
        return result;
    }
    //<<剑指offer>>上的解法
    private static boolean isSymmetrical_1(TreeNode root) {
        return isSymmetrical_1(root, root);
    }

    private static boolean isSymmetrical_1(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2==null)
            return true;
        if (root1 == null || root2==null)
            return false;
        if (root1.val != root2.val)
            return false;
        return isSymmetrical_1(root1.right, root2.left) && isSymmetrical_1(root1.left, root2.right);
    }


    public static void main(String[] args) {
        Object[] vals1 = new Object[]{8, 8, 7, 9, 2, null, null, null, null, 4, 7};
        Object[] vals2 = new Object[]{8, 8, 2, 3,4,6,7};
        Object[] vals3 = new Object[]{7,7,7,7,7,7,7};
        TreeNode tree1 = new TreeNode().createTree(vals1);
        TreeNode tree2 = new TreeNode().createTree(vals2);
        TreeNode tree3 = new TreeNode().createTree(vals3);

//        hasSubTree(tree1.getRoot(), tree2.getRoot());
//        mirorRecursively(tree2.getRoot(), 1);
//        System.out.println();
//        mirorRecursively(tree2.getRoot(), 2);
//        Mirror(tree2.getRoot());
//        pre_mirror(tree2.getRoot());
//        TreeNode.printTree(tree2.getRoot());
        System.out.println(isSymmetrical(tree3.getRoot()));
        System.out.println(isSymmetrical_1(tree3.getRoot()));

    }
}
