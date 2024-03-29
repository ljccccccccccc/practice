package Structure;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.ArrayList;

public class BinaryTree {
    //树的节点类
    public class Node {
        int age;
        String name;
        Node leftChild;
        Node rightChild;

        public Node (int age, String name) {
            this.age = age;
            this.name = name;
        }

        //打印该节点信息
        public void displayNode() {
            System.out.println("name: "+name+", age: "+age);
        }
    }

    private Node root;

    //二叉树类
    public BinaryTree () {
        root = null;
    }

    //查找
    public Node find (int key) {
        Node cur = root;
        if(cur == null){
            return null;
        }

        while(cur.age != key){
            if(cur.age > key) {
                cur = cur.rightChild;
            }else{
                cur = cur.leftChild;
            }
            if(cur ==null){
                return null;//遍历所有的节点都找不到
            }
        }

        return cur;
    }


    //插入新节点
    public void insert (Node node) {
        if(root == null){
            root = node;
        }

        Node cur = root;
        while(true){
            //插入节点大于当前节点
            if(cur.age < node.age){
                if(cur.rightChild == null){
                    cur.rightChild = node;
                    return ;
                }else{
                    cur = cur.rightChild;
                }
            }else if(cur.age > node.age){
                if(cur.leftChild == null){
                    cur.leftChild = node;
                    return;
                }else{
                    cur = cur.leftChild;
                }
            }
        }
    }

    //删除指定节点
    public boolean delete (Node node) {
        if(root == null){
            //树为空
            return false;
        }
        boolean isLeftChild = true; //记录目标节点是否为父节点的左子节点
        Node cur = root; //初始化要删除的节点
        Node parent = null; //要删除节点的父节点

        //找到要删除的节点
        while(cur.age != node.age) {  //确定要删除的节点和它的父节点
            parent = cur;
            if(node.age < cur.age){  //目标节点小于当前节点,跳转到左子节点
                cur = cur.leftChild;
            }else{ //目标节点大于当前节点,跳转到右子节点
                cur = cur.rightChild;
                isLeftChild = false;
            }

            if(cur == null){
                //没有找到要删除的节点
                return false;
            }
        }

        if(cur.leftChild == null && cur.rightChild ==null){
            //目标节点无子节点
            if(cur == root){
                //删除的是根节点
                root = null;
            }else if(isLeftChild){
                cur.leftChild = null;
            }else{
                cur.rightChild = null;
            }
        }else if(cur.leftChild == null){  //只有一个右子节点
            if(cur == root){
                root = cur.rightChild;
            }else if(isLeftChild){ //如果要删除节点是父节点的左子节点,那么把当前节点的右子节点值赋给当前节点
                parent.leftChild = cur.rightChild;
                //cur = rightChild            //应该也可以这么写
            }else{
                parent.rightChild = cur.rightChild;
            }

            //todo 为什么上面这一段else if不能直接写成  cur = cur.rightChild 就不用分两种情况了啊
        }else if(cur.rightChild == null){   //只有一个左子节点
            if(cur == root){
                root = cur.leftChild;
            }else if(isLeftChild){
                cur = cur.leftChild;
            }else{
                cur = cur.rightChild;
            }

        }else{ // 有两个子节点
            //1.找到欲删除节点的中序后继
            Node successor = cur.rightChild;
            Node successorParent = null;

            while(successor.leftChild != null){
                successorParent = successor;
                successor = successor.leftChild;
            }
            //欲删除节点的右子节点就是它的后继，证明该后继无左子节点，则将以后继节点为根的子树上移即可
            if(successorParent == null){
                if(cur == root){
                    root = successor;
                    root.leftChild = successor.leftChild;
                }else if(isLeftChild){
                    parent.leftChild = successor;
                    successor.leftChild = cur.leftChild;
                }else{
                    parent.rightChild = successor;
                    successor.leftChild = cur.leftChild;
                }
            }else {
                //欲删除节点的中序后继不是它的右子节点
                successorParent.leftChild = successor.rightChild;
                successor.rightChild = cur.rightChild;
                if(cur == root){
                    root = successor;
                    root.leftChild = successor.leftChild;
                }else if(isLeftChild){
                    parent.leftChild = successor;
                    successor.leftChild = cur.leftChild;
                }else {
                    parent.rightChild = successor;
                    successor.leftChild = cur.leftChild;
                }
            }

        }
        return true;
    }

    //遍历
    public static final int PREORDER = 1; //前序遍历
    public static final int INORDER = 2;//中序遍历
    public static final int POSTORDER = 3;//中序遍历


    public void traverse (int type){
        switch (type) {
            case 1 :
                System.out.println("-----前序遍历-----");
                preorder(root);
                System.out.println();
                break;
            case 2 :
                System.out.println("-----中序遍历-----");
                inorder(root);
                System.out.println();
                break;
            case 3 :
                System.out.println("-----后序遍历-----");
                postorder(root);
                System.out.println();
                break;
        }
    }


    //前序遍历
    public void preorder (Node currentRoot) {
        //递归遍历  currentRoot 为NULL时 跳出递归
        if(currentRoot != null){
            System.out.println(currentRoot.age + "\t");
            preorder(currentRoot.leftChild);
            preorder(currentRoot.rightChild);
        }
    }

    //中序遍历
    public void inorder (Node currentRoot){
        if(currentRoot != null){
            //递归遍历
            inorder(currentRoot.leftChild);
            System.out.println(currentRoot.age + "\t");
            inorder(currentRoot.rightChild);
        }
    }

    //后序遍历
    public void postorder (Node currentRoot) {
        if(currentRoot != null){
            postorder(currentRoot.rightChild);
            postorder(currentRoot.leftChild);
            System.out.println(currentRoot.age + "\t");
        }
    }

    //最大深度
    private int getDepth(Node currentNode , int initDeep){
        int deep = initDeep;    //当前节点已到达深度
        int leftDeep = initDeep;
        int rightDeep = initDeep;

        if(currentNode.leftChild != null){//递归调用
            leftDeep = getDepth(currentNode.leftChild, deep + 1);
        }
        if(currentNode.rightChild != null){
            rightDeep = getDepth(currentNode.rightChild , deep+1);
        }
        return Math.max(leftDeep,rightDeep);
    }

    //获取树的最大深度
    public int getTreeDepth() {
        if(root == null){
            return 0;
        }
        return getDepth(root, 1);
    }

    //获取关键值最大的节点
    public Node getMax () {
        if(isEmpty()){
            return null;
        }
        Node cur = root;
        while(cur.rightChild != null){
            cur = cur.rightChild;
        }
        return cur;

    }

    //获取最小的节点
    public Node getMin () {
        if(isEmpty()){
            return null;
        }
        Node cur = root;
        while(cur.leftChild != null){
            cur = cur.leftChild;
        }
        return cur;
    }


    //以树的形式打印出该BTree
    public void displayTree () {
        int depth = getTreeDepth();
        ArrayList<Node> currentLayerNodes = new ArrayList<Node> ();
        currentLayerNodes.add(root);
        int layerIndex  =1 ;

        while(layerIndex <= depth){
            int nodeBlankNum = (int)Math.pow(2,depth - layerIndex)-1;
            for(int i = 0;i<currentLayerNodes.size();i++){
                Node node = currentLayerNodes.get(i);
                printBlank(nodeBlankNum);

                if(node == null){
                    System.out.print("*\t");
                }else{
                    System.out.print("* "+node.age+"\t");
                }

                printBlank(nodeBlankNum);
                System.out.print("*\t");//补齐空位
            }
            System.out.println();
            layerIndex++;
            currentLayerNodes = getALLNodeOfThisLayer(currentLayerNodes);
        }
    }


    //todo 这里没写完
    private ArrayList<Node> getALLNodeOfThisLayer(ArrayList<Node> parentNodes) {
        ArrayList list = new ArrayList<Node>();
        Node parentNode;
        for(int i = 0;i<parentNodes.size();i++){
            parentNode = (Node) parentNodes.get(i);
            if(parentNode != null){
                if (parentNode.leftChild != null){
                    //如果父节点有左子节点，加入集合
                    list.add(parentNode.leftChild);
                }else{
                    list.add(null);
                }

                if (parentNode.rightChild != null){
                    list.add(parentNode.rightChild);
                }else{
                    list.add(null);
                }
            }else{
                list.add(null);
                list.add(null);
            }
        }
        return list;
    }

    //空判断
    public boolean isEmpty () {
        return (root == null) ;
    }


    //打印出指定个数的空位
    private void printBlank (int num){
        for (int i = 0;i<num;i++){
            System.out.println("*\t");
        }
    }

    //判断是否为叶子节点
    public boolean isLeaf (Node node) {
        return (node.leftChild != null || node.rightChild != null);
    }

    //获取根节点
    public Node getRoot (){
        return root;
    }
}
