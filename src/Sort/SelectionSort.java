package Sort;


public class SelectionSort {
    private int [] array;

    public SelectionSort (int [] array) {
        this.array = array;
    }

    public void display(){
        for(int i=1;i<array.length;i++){
            System.out.print(array[i]+"\t");
        }
        System.out.println();
    }

    public void selectionSort () {
        int minPoint;//最小元素
        int len = array.length;
        int tmp; //交换变量
        int counter = 1;

        for(int i = 0;i<len-1;i++){
            minPoint = i;
            for (int j = i+1;j<len-1;j++){
                if (array[j] < array[minPoint]){
                    minPoint = j;
                }
            }

            if (minPoint != i){ //发现了更小的元素
                tmp = array[i];
                array[i] = array[minPoint];
                array[minPoint] = tmp;
            }

            System.out.print("第"+counter+"轮排序结果：");
            display();
            counter++;
        }
    }

    public void selectionSort2 () {
        int minPoint;
        int maxPoint;
        int len = array.length;
        int tmp;
        int counter = 1;

        for(int i = 0;i<len/2;i++){
            minPoint = i;
            maxPoint = i;

            for (int j =i+1;j<=len-1-i;j++){
                if(array[minPoint] > array[j]){
                    minPoint = j;
                    continue;
                }else if(array[maxPoint] < array[j]){
                    maxPoint = j;    //保证maxPoint始终是最大值
                }
            }
            if(minPoint != i) {
                tmp = array[i];
                array[i] = array[minPoint];
                array[minPoint] = tmp;

                if(maxPoint == i){
                    maxPoint = minPoint;
                }
            }
            if(maxPoint!=len-1-i){  //如果发现了更大的元素，与最后一个元素交换位置
                tmp= array[len-1-i];
                array[len-1-i]= array[maxPoint];
                array[maxPoint]= tmp;
            }
            System.out.print("第"+counter+"轮排序结果：");
            display();
            counter++;
        }
    }

    public static void main (String args[]){
        int [] a = {13,28,88,76,46,5,23,94,91,82,64,11,4,2,20,18};
        SelectionSort sort = new SelectionSort(a);
        System.out.print("未排序时的结果：");
        sort.display();
        sort.selectionSort2();
    }
}
