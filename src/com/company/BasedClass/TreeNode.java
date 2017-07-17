package com.company.BasedClass;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

/**
 * Created by �� on 2017/7/17.
 * binary tree
 */
public class TreeNode {
    public Object val;
    public TreeNode left;
    public TreeNode right;
    public ArrayList<TreeNode> nodes = new ArrayList<>();
    public TreeNode root;

    public TreeNode() {
    }

    public TreeNode(Object x) {
        val = x;
    }

    public Object getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }


    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRoot() {
        if (nodes.size()!=0)
            root = nodes.get(0);
        else
            root = null;
        return root;
    }

    /*����������������һ��Object����null������֧Ϊ��*/
    public TreeNode createTree(Object[] vals) {
        for (Object obj:vals){
            if (obj!=null)
                nodes.add(new TreeNode(obj));
            else
                nodes.add(null);
        }
        // ��ǰlastParentIndex-1�����ڵ㰴�ո��ڵ��뺢�ӽڵ�����ֹ�ϵ����������
        for (int i=0;i<nodes.size()/2-1;i++){
            nodes.get(i).left = nodes.get(i*2+1);
            nodes.get(i).right = nodes.get(i*2+2);
        }
        //���һ��parent����û��right����������
        int lastParentIndex = nodes.size()/2-1;
        nodes.get(lastParentIndex).left = nodes.get(lastParentIndex * 2 + 1);
        if(nodes.size()%2!=0)
            nodes.get(lastParentIndex).right = nodes.get(lastParentIndex * 2 + 2);
        return this;
    }


    public int getTreeHeight(TreeNode node){
        int height1;
        int height2;
        if (node==null)
            return 0;
        height1 = getTreeHeight(node.left);
        height2 = getTreeHeight(node.right);
        return (height1 > height2)?height1+1:height2+1;
    }

    /*�ݹ鷨 ǰ�����������*/
    public ArrayList<Object> preOrder(TreeNode root,ArrayList<Object> result){
        if (root==null){
            return result;
        }
        result.add(root.val);
        preOrder(root.left, result);
        preOrder(root.right, result);
        return result;
    }


    /*������ ǰ�����������
    *
    * */
    public ArrayList<Object> preOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        ArrayList<Object> result = new ArrayList<>();
        TreeNode p = root;

        while (!stack.empty()||p!=null){
            while (p!=null) {
                result.add(p.val);
                stack.push(p);
                p = p.left;
            }
            if (!stack.empty()){
                p = stack.peek();  //��ȡ��ǰ�ڵ�
                stack.pop();    //��pop
                p = p.right;
            }
        }
        return result;
    }

    /*�ݹ飬�������*/
    public ArrayList<Object> inOrder(TreeNode root,ArrayList<Object> result){
        if (root == null)
            return result;
        inOrder(root.left, result);
        result.add(root.val);
        inOrder(root.right, result);
        return result;
    }

    /*�������������*/
    public ArrayList<Object> inOrder(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        ArrayList<Object> result = new ArrayList<>();
        while (!stack.empty()||root!=null){
            while (root!=null) {
                stack.push(root);
                root = root.left;
            }
            while (!stack.empty()){
                root = stack.peek();
                result.add(root.val);
                stack.pop();
                root = root.right;
            }
        }
        return result;
    }



    public static void main(String[] args) {
        Object[] vals = new Object[]{1, 2, 3, 4, 5, 6, 7};
        TreeNode tree = new TreeNode().createTree(vals);
//        ArrayList<Object> result1 = new ArrayList<>();
//        result1 = tree.preOrder(tree.getRoot(),result1);
//        result1 = tree.inOrder(tree.getRoot(), result1);
//        System.out.println(result1);
//
        ArrayList<Object> result2 = tree.inOrder(tree.getRoot());
        System.out.println(result2);




//        System.out.println(tree.nodes.get(0).left.val);
//        System.out.println(tree.nodes.get(0).right.val);
//        System.out.println(tree.getTreeHeight(tree.nodes.get(0)));
//        System.out.println(Math.ceil(Math.log(tree.getTreeHeight(tree.nodes.get(0)))/Math.log(2)));
//
//
//        System.out.println("/");
//        System.out.println("\\");
    }

}