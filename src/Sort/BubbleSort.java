package Sort;

public class BubbleSort {

    private int [] array;

    public BubbleSort(int [] array){
        this.array = array;
    }

    //按顺序打印数组中的元素
    public void display(){
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]+"\t");
        }
        System.out.println();
    }

    //冒泡排序1 正常冒泡
    public void bubbleSort(){
        int temp;
        int len = array.length;

        for(int i=0;i<len-1;i++){  //外层循环：每循环一次就确定了一个相对最大元素
            for(int j=1;j<len-i;j++){  //内层循环：有i个元素已经排好，根据i确定本次的比较次数
                if(array[j-1]>array[j]){  //如果前一位大于后一位，交换位置
                    temp = array[j-1];
                    array[j-1] = array[j];
                    array[j] = temp;
                }
            }
            System.out.print("第"+(i+1)+"轮排序结果：");
            display();
        }
    }

    //冒泡排序2 如果没有交换就停止
    public void bubbleSort2() {
        int tmp ;
        int counter = 1;
        int len = array.length;

        for(int i = 0;i<len-1;i++){
            boolean flag = false; //判断标志位
            for(int j = 1;j < len-i;j++){
                if(array[j] < array[j-1]){
                    tmp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = tmp;

                    if(!flag) flag = true; //如果内部有数据交换，则置为true
                }
            }
            System.out.println("第"+counter+++"轮排序已完成！");
            display();
            if(!flag) break; //如果没有数据交换就退出
        }

    }

    //冒泡排序3 记录冒泡的位置
    public void bubbleSort3() {
        int tmp;
        int counter = 1;
        int endPoint = array.length - 1;

        while(endPoint > 0){
            int pos = 1;
            for(int j = 1;j<= endPoint;j++){
                if (array[j-1] > array[j]){
                    tmp = array[j-1];
                    array[j-1] = array[j];
                    array[j] = tmp;

                    pos = j;   //记录当前下标
                }
            }
            endPoint = pos-1; //下一轮排序只针对小于pos的点进行排序，大于等于pos已经排序完成
            System.out.println("第"+ counter++ +"轮排序已完成");
            display();
        }
    }

    //双指针冒泡排序
    public void bubbleSort4() {
        int temp;
        int low = 0;
        int high = array.length - 1;
        int counter = 1;
        while (low < high) {

            for (int i = low; i < high; ++i) {   //正向冒泡，确定最大值
                if (array[i] > array[i + 1]) {  //如果前一位大于后一位，交换位置
                    temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                }
            }
            --high;

            for (int j = high; j > low; --j) {   //反向冒泡，确定最小值
                if (array[j] < array[j - 1]) {  //如果前一位大于后一位，交换位置
                    temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
            ++low;

            System.out.print("第" + counter + "轮排序结果：");
            display();
            counter++;
        }
    }

    public static void main (String args[]){
            int [] a = {13,28,88,76,46,5,23,94,91,82,64,4,11,2,20,18};
            BubbleSort sort = new BubbleSort(a);
            System.out.print("未排序时的结果：");
            sort.display();
            sort.bubbleSort4();
    }
}
