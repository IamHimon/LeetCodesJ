package com.company.Offers;

import com.company.BasedClass.TreeNode;
import org.omg.CORBA.PUBLIC_MEMBER;

import javax.swing.plaf.PanelUI;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by 猛 on 2017/7/17.
 */
public class Tree {

    /*给前序遍历序列和中序遍历序列，重建二叉树*/
    public static TreeNode reBuildTree2(List<Object> n1, List<Object> n2) {
        TreeNode root = new TreeNode();
        if (n1.size()==0){
            return root;
        }
        root.setVal((int) n1.get(0));
        int leftLength = n2.indexOf(n1.get(0));
        System.out.println(leftLength);

        while (root.val!=null){
            int index_n2 = n2.indexOf(n1.get(0));
            System.out.println(n1.subList(1, index_n2 + 1));
            System.out.println(n2.subList(0, index_n2));
            root.left = reBuildTree2(n1.subList(1, index_n2 + 1), n2.subList(0, index_n2));
            System.out.println(n1.subList(index_n2 + 1, n1.size()));
            System.out.println(n2.subList(index_n2 + 1, n2.size()));
            root.right = reBuildTree2(n1.subList(index_n2 + 1, n1.size()), n2.subList(index_n2 + 1, n2.size()));
        }

        return root;
    }

    public static TreeNode reBuildTree(int startPreorder, int endPreorder, int startInorder, int endInorder,
                                       List<Object> preorder, List<Object> inorder) throws Exception {
        if (preorder.size()==0&&inorder.size()==0)
            return null;
        //前序遍历序列第一个数字是根节点的值
        Object rootValue = preorder.get(0);
        TreeNode root = new TreeNode();
        root.setVal((int)rootValue);
        root.setLeft(null);
        root.setRight(null);

        if (startPreorder == endPreorder){
            //当preorder和inorder都只有一个元素，并且相等时，返回
            if ((startInorder == endInorder)&&(preorder.get(startPreorder)==inorder.get(startInorder)))
                return root;
            else
                throw new Exception("Invalid input!");
        }

        //在中序遍历中找到根节点的值
        int rootInorder = inorder.indexOf(rootValue);

        if (rootInorder==endInorder&&inorder.get(rootInorder)!=rootValue)
            throw new Exception("Invalid input!");

        int leftLength = rootInorder - startInorder;
        int leftPreorderEnd = startPreorder + leftLength;

        //构建左子树
        if (leftLength>0) {
            root.left = reBuildTree(startPreorder+1, leftPreorderEnd, startInorder, rootInorder-1, preorder, inorder);
        }

        if(leftLength < endPreorder - startPreorder) {
            root.right = reBuildTree(leftPreorderEnd + 1, endPreorder, rootInorder + 1, endInorder, preorder, inorder);
        }
        return root;

    }

    public static void main(String[] args) {

    }
}
