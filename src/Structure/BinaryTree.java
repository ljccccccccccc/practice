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
    public Node insert (Node node) {
        if(root == null){
            root = node;
        }

        Node cur = root;
    }

}
