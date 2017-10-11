package com.company;

import com.company.BasedClass.TreeNode;
import com.company.Offers.Chapter2.Array;
import com.company.Offers.Chapter2.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.company.Solu.Solution.*;

public class Main {

    public static int meituan_1(int N, int[] num, int V) {
        if (num.length != N)
            return 0;

        int[] W = new int[N + 1];
        W[0] = 0;
        for (int i = 1; i < N; i++)
            W[i] = 1;

        int[] C = new int[N + 1];
        C[0] = 0;
        for (int i = 1; i < N; i++) {
            C[i] = num[i - 1];
        }

        int max_sum = 0;
        for (int n : num) {
            max_sum += n;
        }

        int VV = V;
        for (int k = 1; k <= max_sum / VV; k++) {
            V = k * VV;
            int[][] f = new int[N + 1][V + 1];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= V; j++) {
                    f[i][j] = Integer.MIN_VALUE;
                }
            }
            for (int i = 1; i <= N; i++) {
                for (int j = C[i]; j <= V; j++) {
                    f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - C[i]] + W[i]);
                }
            }
            System.out.println(f[N][V]);
        }

        return 1;

    }

    private static TreeNode buildTree(TreeNode root, String[] nodes) {
        if (root == null) {
            TreeNode root1 = new TreeNode(nodes[0]);
            TreeNode left = new TreeNode(nodes[1]);
            TreeNode right = new TreeNode(nodes[2]);
            root1.setLeft(left);
            root1.setRight(right);
            return root1;
        }

        if (root.getLeft().val.equals(nodes[0])) {
            root.left = buildTree(root.left, nodes);
        }
        if (root.getRight().val.equals(nodes[1])) {
            root.right = buildTree(root.right, nodes);
        }

        return root;
    }

    //在一直树种寻找跟他相等叶子节点
    public static TreeNode preOrder(TreeNode root, String value) {
        if (root == null) {
            return null;
        }
        if (root.val.equals(value)) {
            return root;
        }
        preOrder(root.left, value);
        preOrder(root.right, value);
        return null;
    }

    private static void getLeaves(TreeNode p, ArrayList leaf) {
        if (p != null && p.left == null && p.right == null) {
            System.out.print(p.val + "");
            leaf.add(p);
            getLeaves(p.left, leaf);
            getLeaves(p.right, leaf);
        }
    }

    public static int childCount(TreeNode root) {
        if (root == null)
            return 0;
        if (root != null && root.left == null && root.right == null)
            return 1;
        return childCount(root.left) + childCount(root.right);
    }


    public static void main(String[] args) {

        int m = 6;
        int n = 2;
        String[][] nodes = new String[][]{{"A", "B", "C"}, {"C", "F", "*"}, {"B", "D", "E"}, {"D", "G", "*"}, {"E", "H", "I"}, {"E", "J", "K"}};

        ArrayList<Object> vals = new ArrayList<>();
        for (String s : nodes[0])
            vals.add(s);

        int i = 1;
        int count = 0;
        while (i < vals.size()) {
            boolean hasChild = false;
            for (int j = 0; j < m; j++) {
                if (nodes[j][0].equals(vals.get(i))) {
                    count++;
                    hasChild = true;
                    for (int z = 1; z <= n; z++)
                        vals.add(nodes[j][z]);
                }
            }
            if (!hasChild) {
                vals.add(null);
                vals.add(null);
            }

            if (count == m - 1)
                break;
            i++;
        }
        System.out.println(vals);

        TreeNode tree = new TreeNode().createTree(vals);
        TreeNode.printTree(tree.getRoot());

        int num = childCount(tree.getRoot());
        System.out.println(num);




/*        ArrayList<TreeNode> allNodes = new ArrayList<>();
        for (int i=0;i<m;i++){
            TreeNode tempNode = new TreeNode();
            for (int j=0;j<n;j++){


            }

        }*/

    }


}
