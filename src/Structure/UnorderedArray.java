package Structure;

/*
* 数据结构：数组
* function : 增删改查 打印
*
* */
public class UnorderedArray {

    private String [] strArray;
    private int length = 0;

    //构造方法
    private UnorderedArray (int max) {
        strArray = new String [max];
    }


    //查找
    private int find (String target){
        int index = -1;
        for(int i = 0; i < length ; i++){
            if(strArray[i].equals(target)){
                index = i;
                return index;
            }
        }
        return index;
    }
    //增
    private String[] insert (String el){
        strArray[length] = el;
        length++;
        return strArray;
    }

    //删除  删除指定元素值
    private boolean delete (String target){
        int index = this.find(target);
        if( index != -1){
            for(int i = index ; i< length - 1 ;i++){
                strArray[i] = strArray[i+1];
            }
            length --;
            return true;
        }else{
            return false;
        }
    }

    //修改
    private boolean update (int k, String val){
        int index = this.find(strArray[k]);
        if(index != -1){
            //有此值，开始修改
            strArray[k] = val;
            return true;
        }else{
            return false;
        }
    }

    //列出所有元素
    private void display () {
        for(int i = 0; i < length; i++){
            System.out.println(strArray[i] + "\n");
        }
    }

    public static void main (String[] args){
        UnorderedArray unorderedArray = new UnorderedArray(3);
        unorderedArray.insert("a");
        unorderedArray.insert("b");
        unorderedArray.insert("c");
        unorderedArray.update(2,"bbb");
        System.out.println(unorderedArray.delete("c"));
        unorderedArray.display();
    }


}
