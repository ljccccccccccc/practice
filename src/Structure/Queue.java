package Structure;

public class Queue {
    private int[] queueArray;
    private int maxSize;
    public int front;  //头部取出
    public int rear; //尾部插入
    private int length;

    public Queue (int maxSize) {
        this.maxSize = maxSize;
        queueArray = new int[maxSize];
        front = 0;
        rear = -1;
        length = 0;
    }

    //插入
    public void insert (int target) throws Exception{
        if(isFull()){
            throw new Exception("队列已满！不能继续插入！");
        }else{
            if(rear == maxSize - 1 ){
                rear = -1;
            }else{
                queueArray[++rear] = target;
                length++;
            }
        }
    }

    //删除
    public int remomve () throws Exception{
        if(isEmpty()){
            throw new Exception("队列为空，不能进行移除操作！");
        }else{
            int el = queueArray[front++];
            //如果头达到数组尾部最大值，那么把头转到队列开始
            if(front == maxSize){
                front = 0;
            }
            length --;
            return el;
        }
    }

    //查看队头
    private int head () throws Exception{
        if(isEmpty()){
            throw new Exception("队列为空！");
        }
        return queueArray[front];
    }

    public int size() {
        return length;
    }

    private boolean isEmpty() {
        return (length == 0 );
    }

    private boolean isFull(){
        return (length == maxSize);
    }

    public static void main (String[] args){
        Queue queue = new Queue(8);
        try{
            queue.insert(7);
            queue.insert(30);
            queue.insert(93);
            queue.insert(41);
            System.out.println(queue.head());
            System.out.println(queue.remomve());
            System.out.println(queue.head());
            System.out.println(queue.remomve());
            System.out.println(queue.head());
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
