package Structure;

public class DoublelyLinkList {


    public static class Link {

        public int age;
        public String name;
        public Link next;
        public Link previous;

        public Link (int age, String name) {
            this.age = age;
            this.name = name;
        }

        //打印该节点的信息
        public void displayLink(){
            System.out.println("name : "+name+ " , age: "+age);
        }
    }


    private  Link first;
    private  Link last;

    public  DoublelyLinkList () {
        first = null;
        last = null;
    }

    //插入到链表前端
    public void insertFirst (Link link) {
        if(isEmpty()) {
            last = link;
        }else{
            first.previous = link;
        }
        link.next = first;
        first = link;
    }

    //插入到链表末端
    public void insertLast (Link link) {
        if(isEmpty()){
            first = link;
        }else{
            last.next = link;
            link.previous = last;
        }
        last = link;
    }

    //删除第一个节点
    public Link deleteFirst() throws Exception{
        if(isEmpty()){
            throw new Exception("链表为空，不能执行删除操作！");
        }
            Link tmp = first;
            if(first.next == null){
                //只有一个元素
                last = null;
            }else{
                first.next.previous = null;
            }
            first = first.next;
            return tmp;

    }

    //删除最后一个节点
    public Link deleteLast () throws Exception{
        if(isEmpty()){
            throw new Exception("链表为空，不能执行删除操作！");
        }
            Link tmp = last;
            if(last.previous == null){
                //此时的链表只有一个元素
                first = null;
            }else{
                last.previous.next = null;
            }
            last = last.previous;
            return tmp;

    }

    //查找属性值为特定值的节点
    public Link find (int key){
        Link cur = first ;
        while(cur != null && cur.age !=  key){
            //有这么一个节点
            if(cur.next == null){//只有一个元素
                return null;
            }
            cur = cur.next; //一直查找
        }
        return cur;
    }

    //在指定节点之后插入，操作成功返回true，操作失败返回false
    public boolean insertAfter (Link link,Link node){
        Link target = find(link.age);
        boolean flag = true;
        if(target == null){
            flag = false;
        }else{
            if(target.next == null){//只有一个节点
                insertLast(node);
            }else{
                //至少有两个节点
                target.next.previous = node;
                node.next = target.next;
                //必须先执行上面两步
                target.next = node;
                node.previous = target;
            }
        }
        return flag;
    }

    //打印出所有链表
    public void display () {
        Link cur = first;
        while(cur != null){
            cur.displayLink();
            cur = cur.next;
        }
    }

    //空判断
    private boolean isEmpty() {
        return first == null;
    }

    public static void main (String[] args){
        try{
            DoublelyLinkList doublelyLinkList = new DoublelyLinkList();
            doublelyLinkList.insertFirst(new Link(12,"Miss"));
            doublelyLinkList.insertFirst(new Link(24,"Hello"));
            doublelyLinkList.insertFirst(new Link(5,"Fine"));
            doublelyLinkList.insertFirst(new Link(17,"Nice"));
            doublelyLinkList.display();
            System.out.println("--------------------------------------");
            doublelyLinkList.deleteLast();
            doublelyLinkList.display();
            System.out.println("--------------------------------------");
            doublelyLinkList.deleteFirst();
            doublelyLinkList.display();
            System.out.println("--------------------------------------");
            doublelyLinkList.insertLast(new Link(16,"QieZi"));
            doublelyLinkList.display();
            System.out.println("--------------------------------------");
            Link qieZi = doublelyLinkList.find(16);
            Link maoZi = new Link(17,"MaoZi");
            doublelyLinkList.insertAfter(qieZi,maoZi);
            doublelyLinkList.display();
        }catch (Exception e ){
            System.out.println(e);
        }


    }
}
