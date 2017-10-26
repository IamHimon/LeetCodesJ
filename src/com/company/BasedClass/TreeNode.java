package com.company.BasedClass;

import com.company.Main;
import com.company.Offers.Chapter2.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static com.company.Offers.Chapter2.Tree.GetNext;


/**
 * Created by �� on 2017/7/17.
 * binary tree
 */
public class TreeNode {
    public int sign = 0;
    public Object val;
    public TreeNode left;
    public TreeNode right;
    public ArrayList<TreeNode> nodes = new ArrayList<>();
    public TreeNode root;
    public TreeNode parent;
    public int childCount;

    public TreeNode() {
    }

    public TreeNode(Object x) {
        val = x;
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
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

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getRoot() {
        if (nodes.size() != 0)
            root = nodes.get(0);
        else
            root = null;
        return root;
    }

    /*create a binary tree, the input is Object list, which can contain null values*/
    public TreeNode createTree(Object[] vals) {
        for (Object obj : vals) {
            if (obj != null)
                nodes.add(new TreeNode(obj));
            else
                nodes.add(null);
        }
        // the last Parent may have no right child ,so process the former lastParent-1 parents firstly.
        for (int i = 0; i < nodes.size() / 2 - 1; i++) {
            nodes.get(i).left = nodes.get(i * 2 + 1);
            nodes.get(i).right = nodes.get(i * 2 + 2);
        }
        //process the last parent separately.
        int lastParentIndex = nodes.size() / 2 - 1;
        nodes.get(lastParentIndex).left = nodes.get(lastParentIndex * 2 + 1);
        if (nodes.size() % 2 != 0)
            nodes.get(lastParentIndex).right = nodes.get(lastParentIndex * 2 + 2);
        return this;
    }

    public TreeNode createTree(ArrayList<Object> vals) {
        for (Object obj : vals) {
            if (obj != null)
                nodes.add(new TreeNode(obj));
            else
                nodes.add(null);
        }
        // the last Parent may have no right child ,so process the former lastParent-1 parents firstly.
        for (int i = 0; i < nodes.size() / 2 - 1; i++) {
            nodes.get(i).left = nodes.get(i * 2 + 1);
            nodes.get(i).right = nodes.get(i * 2 + 2);
        }
        //process the last parent separately.
        int lastParentIndex = nodes.size() / 2 - 1;
        nodes.get(lastParentIndex).left = nodes.get(lastParentIndex * 2 + 1);
        if (nodes.size() % 2 != 0)
            nodes.get(lastParentIndex).right = nodes.get(lastParentIndex * 2 + 2);
        return this;
    }


    public static int getTreeHeight(TreeNode node) {
        int height1;
        int height2;
        if (node == null)
            return 0;
        height1 = getTreeHeight(node.left);
        height2 = getTreeHeight(node.right);
        return (height1 > height2) ? height1 + 1 : height2 + 1;
    }

    /* preorder on binary tree, using recursive way*/
    public static List<Object> preOrder(TreeNode root, List<Object> result) {
        if (root == null) {
            result.add(null);
            return result;
        }
        result.add(root.val);
        preOrder(root.left, result);
        preOrder(root.right, result);
        return result;
    }


    /*preorder on binary tree , using circulate way
    * */
    public ArrayList<Object> preOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        ArrayList<Object> result = new ArrayList<>();
        TreeNode p = root;

        while (!stack.empty() || p != null) {
            while (p != null) {
                result.add(p.val);
                stack.push(p);
                p = p.left;
            }
            if (!stack.empty()) {
                p = stack.peek();
                stack.pop();
                p = p.right;
            }
        }
        return result;
    }

    /*inorder on binary tree, using recursive way*/
    public ArrayList<Object> inOrder(TreeNode root, ArrayList<Object> result) {
        if (root == null)
            return result;
        inOrder(root.left, result);
        result.add(root.val);
        inOrder(root.right, result);
        return result;
    }

    private void getLeaves(TreeNode p, ArrayList leaf) {
        if (p != null && p.left == null && p.right == null) {
            System.out.print(p.val.toString() + "");
            leaf.add(p);
            getLeaves(p.left, leaf);
            getLeaves(p.right, leaf);
        }
    }

    /*inorder on binary tree, using circulate way*/
    public List<Object> inOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Object> result = new ArrayList<>();
        while (!stack.empty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            if (!stack.empty()) {
                root = stack.peek();
                result.add(root.val);
                stack.pop();
                root = root.right;
            }
        }
        return result;
    }

    //后序遍历
    public List<Object> postOrder(TreeNode root, List<Object> result) {
        if (root == null) {
            return result;
        }
        postOrder(root.left, result);
        postOrder(root.right, result);
        result.add(root.val);
        return result;
    }

    //层次遍历

    /*构造父节点指针
    * 思路：给节点赋一个属性：sign表示有没有给他安排过parent，
    * 每次都把左子树加入到栈中，对过程中的某个点同样策略一直到最左端，
    * 到达叶子节点，转到右子树，如当前节点右子树非空，并且是未处理的(asign==0)，则把右子树加入到栈中
    *先从栈中取出当前节点root，如果当前节点的左子树或者右子树是空的或者他们非空但是是处理过的，则处理这个root点(setParent,setSign)，他的parent就是剩下栈的peek()，
    *
    *如果当前节点右子树非空，并且右子树已经处理过，则将下一个当前节点赋值为当前节点父节点的右节点,
    * 否则正常往当前节点的右子树走(root=root.right)
    * */
    public static TreeNode buildParent(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode parent = new TreeNode();
        while (!stack.empty() || root != null) {
            /*向左到底，把非空且sign为0的节点加入到栈中，没有root.sign==0会死循环*/
            while (root != null && root.sign == 0) {
                stack.push(root);
                root = root.left;
            }

            if (!stack.empty()) {
                root = stack.peek();//当前节点

                /*如当前节点右子树非空，并且是未处理的(asign==0)，则把右子树加入到栈中*/
                if (root.right != null && root.right.sign == 0)
                    root = root.right;
                else {
                    /*当当前节点是未处理的，并且当前节点为叶子节点，或者右子树为空，则pop栈顶元素（当前元素），
                    *并且取出parent节点，设置当前节点的父节点和sign值。
                    */
                   /* if (root.sign == 0 && (root.right == null && root.left == null) || (root.right != null)){
                        stack.pop();
                        if (stack.empty()) //最后一个节点（根节点）还是会进到这里，但是一次栈中只有他自己了，pop之后为空则结束程序。
                            break;
                        parent = stack.peek();
                        root.setParent(parent);
                        root.setSign(1);
                    }else {
                        stack.pop();
                    }*/

                    stack.pop();
                    if (stack.empty()) //最后一个节点（根节点）还是会进到这里，但是一次栈中只有他自己了，pop之后为空则结束程序。
                        break;
                    parent = stack.peek();
                    root.setParent(parent);
                    root.setSign(1);

                    /*如果当前节点右子树非空，并且右子树已经处理过，则将下一个当前节点赋值为当前节点父节点的右节点*/
                    if (root.right != null && root.right.sign == 1) {
                        root = parent.right;
                    } else {
                        root = root.right;
                    }
                }
            }
        }
        return root;
    }

    public static void printTree(TreeNode treeNode) {
        if (treeNode == null)
            return;
        for (int i = 0; i <= getTreeHeight(treeNode); i++) {
            printLevel(treeNode, i);
            System.out.println();
        }
    }

    public static void printLevel(TreeNode root, int level) {
        if (root == null || level < 1)
            return;
        if (level == 1) {
            System.out.print(root.val + " ");
            return;
        }
        printLevel(root.left, level - 1);
        printLevel(root.right, level - 1);
    }

    //判断是不是平衡二叉树
    private static boolean isBalance = true;

    public static int getPostDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getPostDepth(root.left);
        int right = getPostDepth(root.right);
        if (Math.abs(left - right) > 1)
            isBalance = false;
        return left > right ? (left + 1) : (right + 1);
    }


    public static void main(String[] args) throws Exception {
        Object[] vals = new Object[]{1, 2, 3, 4, 5, 6, null, 7};
        TreeNode tree = new TreeNode().createTree(vals);
//        System.out.println(tree.getRoot());

//        printTree(tree.getRoot());


//        printTree(tree.getRoot());

        getPostDepth(tree.getRoot());
        System.out.println(isBalance);
//        buildParent(tree.getRoot());


//        for (TreeNode node:tree.nodes){
//            if (node!=null&&node.parent!=null)
//                System.out.println(node.parent.val);
//        }

//        System.out.println(tree.nodes.get(3).val);
//        System.out.println(GetNext(tree.nodes.get(3)).val);


//        List<Object> result1 = new ArrayList<>();
//        result1 = tree.preOrder(tree.getRoot());
//        System.out.println(result1);


//        List<Object> result2 = tree.inOrder(tree.getRoot());
//        System.out.println(result2);
//
//        List<Object> result3 = new ArrayList<>();
//        result3 = tree.postOrder(tree.getRoot(), result3);
//        System.out.println(result3);
//
//        TreeNode root = Tree.reBuildTree3(result1, result2);
//
//        assert root != null;
//        List<Object> rootRestlt = root.inOrder(root);
//        System.out.println(rootRestlt);


    }

}
