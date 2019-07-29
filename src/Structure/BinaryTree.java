package Structure;

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

    //todo 删除指定节点
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




}
