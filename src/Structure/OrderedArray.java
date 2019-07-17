package Structure;

/*
* 数据结构：有序数组
* function : 增删改查
* advance : 比无序数组查询速度快
* */
public class OrderedArray {

    private int[] intArray;
    private int length;
    //构造函数
    public OrderedArray (int length){
        intArray =new int[length];
    }

    //查找，此处查找用二分法查找,存在返回下标，否则返回-1
    public int find (int target) {
        int lowerBound = 0;  //最小下标
        int upperBound = length - 1; //最大下标
        int curIn; //正在搜索下标

        //先做超范围判断
        if(upperBound < 0){
            return -1;
        }

        while (true) {
            curIn = (lowerBound + upperBound) / 2;   //小数向下取整
            if(target == intArray[curIn]){  //恰好等于中间值的情况
                return curIn;
            }else if(curIn == lowerBound){ //如果中间下标==最小下标 说明数组只有1或2个值
                if(target == intArray[upperBound]) {    //如果只有1或者2值 判断是否等于最大值或者最小值即可
                                                        // 而上一个if已经判断过不等于中间的情况，int向下取整，即不等于最小值
                    return upperBound;
                }else{  //高位元素也不等于target
                    return -1;
                }
            }else{
                //搜索段中至少有三个元素 且当前元素不等于target
                if(intArray[curIn] < target){
                    lowerBound = curIn;
                }else{
                    upperBound = curIn;
                }
            }
        }
    }

    //增加
    public void insert (int target){
        int location = 0;
        for(;location < length;location ++){
            if(intArray[location] > target) break;
        }
        for(int i = length; i>location;i --){
            intArray[i] = intArray[i-1];
        }
        intArray[location] = target;
        length++;
    }

    //删除
    public boolean delete (int target) {
        int location = this.find(target);
        if(location != -1){
            //找到了相应的数值
            for (int i =location ;i<length -1;i ++){
                intArray[i] = intArray[i + 1];
            }
            length--;
            return true;
        }else{
            return false;
        }
    }

    //修改,修改k处的值为val后重新排序
    public boolean update (int k,int val) {
        int location = this.find(intArray[k]);
        if(location != -1){
            this.delete(intArray[k]);
            this.insert(val);
        }
        return false;
    }
    //列出所有
    public void display () {
        for(int i = 0; i<length; i++){
            System.out.println(intArray[i]+ "    ");
        }
    }

    public static void main (String[] args){
        OrderedArray orderedArray = new OrderedArray(5);
        orderedArray.insert(3);
        orderedArray.insert(4);
        orderedArray.insert(89);
        orderedArray.insert(11);
        orderedArray.insert(7);
        System.out.println(orderedArray.delete(71));
        System.out.println(orderedArray.delete(11));
        orderedArray.display();
    }
}
