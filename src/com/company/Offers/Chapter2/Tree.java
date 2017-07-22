package com.company.Offers.Chapter2;

import com.company.BasedClass.TreeNode;

import java.util.List;

/**
 * Created by �� on 2017/7/17.
 */
public class Tree {

    /*给前序遍历序列和中序遍历序列，重建二叉树*/

    public static TreeNode reBuildTree(int startPreorder, int endPreorder, int startInorder, int endInorder,
                                       List<Object> preorder, List<Object> inorder) throws Exception {

        if (preorder.size()==0&&inorder.size()==0)
            return null;
        System.out.println("["+startPreorder+" - "+endPreorder+"]"+"  "+"["+startInorder+" - "+endInorder+"]");
        //前序遍历序列第一个数字是根节点的值
        Object rootValue = preorder.get(startPreorder);
        TreeNode root = new TreeNode(rootValue);
//        root.setVal((int)rootValue);
//        root.setLeft(null);
//        root.setRight(null);

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
            root.left = reBuildTree(startPreorder + 1, leftPreorderEnd, startInorder, rootInorder - 1, preorder, inorder);
        }
        if(leftLength < endPreorder - startPreorder) {
            root.right = reBuildTree(leftPreorderEnd + 1, endPreorder, rootInorder + 1, endInorder, preorder, inorder);
        }
        return root;
    }


    public static TreeNode reBuildTree3(List<Object> preorder, List<Object> inorder) throws Exception {

        if (preorder.size()==0&&inorder.size()==0)
            return null;

        int startPreorder = 0;
        int endPreorder = preorder.size()-1;
        int startInorder = 0;
        int endInorder = inorder.size()-1;
//        System.out.println(preorder);
//        System.out.println(inorder);
//        System.out.println("["+startPreorder+" - "+endPreorder+"]"+"  "+"["+startInorder+" - "+endInorder+"]");
        //前序遍历序列第一个数字是根节点的值
        Object rootValue = preorder.get(startPreorder);
        TreeNode root = new TreeNode(rootValue);
        root.setLeft(null);
        root.setRight(null);

        if (startPreorder == endPreorder){
            //当preorder和inorder都只有一个元素，并且相等时，返回
            if ((startInorder == endInorder)&&(preorder.get(startPreorder)==inorder.get(startInorder))) {
                return root;
            }else
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
            /*
            * 原来这边是：preorder = preorder.subList(startPreorder + 1, leftPreorderEnd+1);
            * subList()这个方法截取List的同时也改变了来时list，所以需要新建一个List在传入到递归函数中。            *
            * */
            List<Object> preorder1 = preorder.subList(startPreorder + 1, leftPreorderEnd+1);
            List<Object> inorder1 = inorder.subList(startInorder, rootInorder);
            root.left = reBuildTree3(preorder1, inorder1);
        }
        if(leftLength < endPreorder - startPreorder) {
            List<Object> preorder2 = preorder.subList(leftPreorderEnd + 1, endPreorder+1);
            List<Object> inorder2 = inorder.subList(rootInorder + 1, endInorder+1);

            root.right = reBuildTree3(preorder2, inorder2);
        }
        return root;
    }


    /*
    * 给定一个节点pNode，找出它在二叉树中序遍历的下一个节点：
    * 1.如果pNode有右子树，则右子树的最左子节点就是nNext
    * 2.如果pNode没有右子树，
    *   （1）如果是父节点的左孩子，则下一个节点就是他的父节点就是nNext
    *   （2）如果是父节点的右孩子，则上溯它所有的父节点，直达找到一个是他父节点左孩子的那个节点，则他的父节点就是下一个节点nNext
    * */
    public static TreeNode GetNext(TreeNode pNode) {
        if (pNode == null)
            return null;
        TreeNode pNext = new TreeNode();
        if (pNode.right != null) {
            TreeNode pRight = pNode.right;
            while (pRight.left != null) {
                pRight = pRight.left;
            }
            pNext = pRight;
        } else if (pNode.parent!=null) {
            TreeNode pCurrent = pNode;
            TreeNode pParent = pNode.parent;
            while (pParent!=null&&pCurrent==pParent.right){
                pCurrent = pParent;
                pParent = pParent.parent;
            }
            pNext = pParent;
        }
        return pNext;
    }


    public static void main(String_p[] args) {

    }
}
