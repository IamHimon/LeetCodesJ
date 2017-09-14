package com.company.Offers.Chapter4;

import com.company.BasedClass.TreeNode;
import com.company.Offers.Chapter2.Array;
import org.omg.CORBA.OBJ_ADAPTER;

import java.util.*;

/**
 * Created by himon on 17-8-25.
 */
public class Common {

    /*面试题27, 二叉树的镜像*/
    //1.层次遍历实现
    private static TreeNode Mirror(TreeNode root) {
        //鲁棒性
        if (root == null)
            return null;
        if (root.right == null || root.left == null)
            return null;

        for (int i = 1; i <= TreeNode.getTreeHeight(root); i++) {
            mirrorRecursively(root, i);
        }
        TreeNode.printTree(root);
        return root;
    }

    private static void mirrorRecursively(TreeNode root, int level) {
        if (root == null || level < 1)
            return;

        if (level == 1) {
            TreeNode temp = root.right;
            root.right = root.left;
            root.left = temp;
            return;
        }
        mirrorRecursively(root.left, level - 1);
        mirrorRecursively(root.right, level - 1);
    }

    //2.前序遍历来实现
    private static void pre_mirror(TreeNode root) {
        //鲁棒性
        if (root == null)
            return;
        if (root.right == null || root.left == null)
            return;
        TreeNode temp = root.right;
        root.right = root.left;
        root.left = temp;

        if (root.left != null)
            pre_mirror(root.left);
        if (root.right != null)
            pre_mirror(root.right);
    }

    /*面试题28,对称的二叉树*/
    private static boolean isSymmetrical(TreeNode root) {
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

    private static List<Object> mirror_order(TreeNode root, List<Object> result) {
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
        if (root1 == null && root2 == null)
            return true;
        if (root1 == null || root2 == null)
            return false;
        if (root1.val != root2.val)
            return false;
        return isSymmetrical_1(root1.right, root2.left) && isSymmetrical_1(root1.left, root2.right);
    }

    /*面试题29,顺时针打印矩阵*/
    private static void clockwise_print(int[][] num, int width, int height, int start_x, int start_y) {
        if (width <= 0 || height <= 0)
            return;
        //如果是一排,直接输出
        if (width == 1 || height == 1) {
            for (int i = start_x; i < width + start_x; i++) {
                for (int j = start_y; j < height + start_y; j++) {
                    System.out.println(num[i][j]);
                }
            }
            return;
        }
        //否则,输出四周
        //打印第一行
        for (int i = 0; i < width - 1; i++) {
            System.out.println(num[start_y][i + start_x]);
        }
        //打印最后一列
        for (int j = 0; j < height - 1; j++) {
            System.out.println(num[j + start_y][width - 1 + start_x]);
        }
        //打印最后一行
        for (int i = width - 1 + start_x; i > start_x; i--) {
            System.out.println(num[height - 1 + start_y][i]);
        }
        //打印第一列
        for (int j = height - 1 + start_y; j > start_y; j--) {
            System.out.println(num[j][start_x]);
        }
        clockwise_print(num, width - 2, height - 2, start_x + 1, start_y + 1);
    }

    /*面试题30,包含min函数的栈*/
    static Stack<Integer> m_data = new Stack();
    static Stack<Integer> m_min = new Stack();

    private static void push(Integer value) {
        m_data.push(value);
//        Integer peek_value = m_data.peek();
//        m_min.push(peek_value < value ? peek_value : value);
        if (m_min.empty() && value < m_data.peek())
            m_min.push(value);
        else
            m_min.push(m_min.peek());
    }

    private static Integer pop() {
        if (!m_data.empty() && !m_min.empty()) {
            m_min.pop();
            return m_data.pop();
        }
        return 0;
    }

    private static Integer min() {
        if (!m_min.empty() && !m_data.empty()) {
            return m_min.pop();
        }
        return 0;
    }

    /*面试题31,栈的压入,弹出序列
    * 利用一个辅助栈来完成.
    * 1.如果下一个弹出数字刚好是栈顶元素,则直接弹出.
    * 2.如果下一个弹出数字P不是栈顶元素,且push_seq中还有数字,则继续讲push_seq中的数字压入栈中,直到压入的是数字P.
    * 3.如果所有数字都已经压入栈中,还没有找到P,则返回false;
    * */
    private static boolean isPopOrder(int[] push_seq, int[] pop_seq) {
        if (push_seq == null || pop_seq == null)
            return false;
        Stack<Integer> s1 = new Stack();
        Stack<Integer> s2 = new Stack();
        int i = 0; //push_seq
        int p = 0;
        while (p < pop_seq.length) {
            if (!s1.empty() && s1.peek() == pop_seq[p]) {
                s2.push(s1.pop());
                p++;
            } else if (i < push_seq.length) {
                while (i < push_seq.length && push_seq[i] != pop_seq[p])
                    s1.push(push_seq[i++]);

                if (i > push_seq.length - 1)
                    return false;
                else
                    s1.push(push_seq[i++]);
            } else if (s1.peek() != pop_seq[p]) {
                return false;
            }
        }
        return true;
    }

    /*面试题32,从上到下打印二叉树*/
    private static void levelPrintTree1(TreeNode root) {
        if (root == null)
            return;
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        while (!queue.isEmpty()) {
            System.out.print(queue.peek().val + " ");
            if (queue.peek().left != null) {
                queue.add(queue.peek().left);
            }
            if (queue.peek().right != null) {
                queue.add(queue.peek().right);
            }
            queue.remove();
        }
    }

    /*面试题32,分行从上向下打印二叉树*/
    private static void levelPrintTree2(TreeNode root) {
        if (root == null)
            return;
        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);
        int current_floor_count;
        int next_floor_count = 1;

        while (!queue.isEmpty()) {
            current_floor_count = next_floor_count;
            next_floor_count = 0;
            for (int i = 0; i < current_floor_count; i++) {
                TreeNode cNode = queue.poll();
                System.out.print(cNode.val + " ");
                if (cNode.left != null) {
                    queue.add(cNode.left);
                    next_floor_count++;
                }
                if (cNode.right != null) {
                    queue.add(cNode.right);
                    next_floor_count++;
                }
            }
            System.out.println();
        }
    }

    /*面试题33,之字打印二叉树*/
    private static void levelPrintTree3(TreeNode root) {
        if (root == null)
            return;

        Stack<TreeNode> s1 = new Stack();
        Stack<TreeNode> s2 = new Stack();
        Stack<TreeNode> s3 = new Stack<>();

        TreeNode p = root;
        int level = 1;
        s2.push(root);
        while (!s1.isEmpty() || !s2.isEmpty()) {

            //清空s2,S2付给s1(为了保证顺序,先把s2点保存到一个栈s3中,然后再将s3点放到s1中,这样才能保证s1,s2顺序不变,)
            while (!s2.isEmpty())
                s3.push(s2.pop());
            while (!s3.isEmpty())
                s1.push(s3.pop());

            while (!s1.isEmpty()) {
                TreeNode temp = s1.pop();
                System.out.println(temp.val);
                if (level % 2 == 0) {
                    if (temp.right != null)
                        s2.push(temp.right);
                    if (temp.left != null)
                        s2.push(temp.left);
                } else {
                    if (temp.left != null)
                        s2.push(temp.left);
                    if (temp.right != null)
                        s2.push(temp.right);
                }
            }
            level++;
        }
    }

    /*面试题33,二叉搜索树的后序遍历序列*/
    private static boolean verifySequenceOfBST(int[] seq, int start, int end) {
        if (seq.length == 0)
            return true;
        boolean right_is = true;
        int i = start;
        for (; i < end - 1; i++) {
            if (seq[i] > seq[end - 1])
                break;
        }
//        System.out.println(i);

        for (int j = i; j < end - 1; j++) {
            if (seq[j] < seq[end - 1]) {
                right_is = false;
                break;
            }
        }
        if (start < i - 1)
            return right_is && verifySequenceOfBST(seq, start, i - 1);
        else if (i < end - 1)
            return right_is && verifySequenceOfBST(seq, i, end - 1);
        else
            return true;
    }

    /*面试题34,二叉树中和为某一值的路径*/
    private static boolean findPath(TreeNode root, int value) {
        if (root == null)
            return false;
        ArrayList path = new ArrayList<TreeNode>();

//        Stack<TreeNode> path = new Stack<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pNode = root;
        while (!stack.isEmpty() || pNode != null) {
            if (pNode != null) {
                path.add(pNode);
//                System.out.println(pNode.val);
                stack.push(pNode);
                if (pNode.right == null && pNode.left == null)
                    System.out.println(sum(path));
                pNode = pNode.left;

            } else {
                pNode = stack.pop();
                if (!path.isEmpty()&&pNode.right==null)
                    path.remove(path.size() - 1);
                pNode = pNode.right;
            }
        }
//        System.out.println(path);

        return true;
    }

    private static int sum(ArrayList<TreeNode> path) {
        int sum = 0;
        for (TreeNode p : path)
            sum += (Integer)p.val;
        return sum;
    }

    private static ArrayList<TreeNode> path = new ArrayList<>();
    private static void findPath1(TreeNode root, int expectedSum) {
        if (root == null)
            return;
        path.add(root);
        if (sum(path)==expectedSum&& root.right==null&& root.left==null){
            System.out.println(sum(path));
            for (TreeNode t:path)
                System.out.print(t.val+" ");
            System.out.println();
        }

        if (root.left!=null)
            findPath1(root.left, expectedSum);
        if (root.right!=null)
            findPath1(root.right, expectedSum);

        if (!path.isEmpty())
            path.remove(path.size() - 1);
    }

    /*面试题35,复杂链表复制*/
    


    public static void main(String[] args) {
//        Object[] vals1 = new Object[]{8, 8, 7, 9, 2, null, null, null, null, 4, 7};
//        Object[] vals2 = new Object[]{8, 8, 2, 3, 4, 6, 7};
        Object[] vals2 = new Object[]{8, 6, 10, 5, 7, 9, 11};
//        Object[] vals3 = new Object[]{7, 7, 7, 7, 7, 7, 7};
//        TreeNode tree1 = new TreeNode().createTree(vals1);
        TreeNode tree2 = new TreeNode().createTree(vals2);
        findPath1(tree2.getRoot(), 21);
//        TreeNode tree3 = new TreeNode().createTree(vals3);

//        hasSubTree(tree1.getRoot(), tree2.getRoot());
//        mirorRecursively(tree2.getRoot(), 1);
//        System.out.println();
//        mirorRecursively(tree2.getRoot(), 2);
//        Mirror(tree2.getRoot());
//        pre_mirror(tree2.getRoot());
//        TreeNode.printTree(tree2.getRoot());
//        System.out.println(isSymmetrical(tree3.getRoot()));
//        System.out.println(isSymmetrical_1(tree3.getRoot()));

//        int[][] num = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
//        clockwise_print(num, 3, 3, 0, 0);
//        clockwise_print(num, 4, 4, 0, 0);
//        int[] push_order = new int[]{1, 2, 3, 4, 5};
//        int[] pop_order1 = new int[]{4, 5, 3, 2, 1};
//        int[] pop_order2 = new int[]{4, 3, 5, 1, 2};
//        System.out.println(isPopOrder(push_order, pop_order1));

//        levelPrintTree1(tree2.getRoot());
//        levelPrintTree3(tree2.getRoot());


//        System.out.println(verifySequenceOfBST(new int[]{5, 7, 6, 9, 11, 10, 8}, 0, 7));
//        System.out.println(verifySequenceOfBST(new int[]{7,4,6,5}, 0, 4));

    }
}
