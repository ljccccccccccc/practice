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
            curIn = (lowerBound + upperBound) / 2;
            if(target == intArray[curIn]){
                return curIn;
            }else if(target == intArray[upperBound]){
                return upperBound;
            }else if(target == intArray[lowerBound]){
                return lowerBound;
            }else if(target < intArray[curIn]){
                curIn = upperBound;
            }else if(target > intArray[curIn]){
                curIn = lowerBound;
            }else{
                return -1;
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
        System.out.println("进入delete了！");
        int location = this.find(target);
        System.out.println("进入delete了！");

        if(location != -1){
            System.out.println("进入if判断了！");
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
        orderedArray.delete(71);
        orderedArray.display();
    }
}
