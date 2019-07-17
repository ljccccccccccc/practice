package Structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class CharStack {
    private int top;
    private int size;
    private char[] stackArray;

    //构造方法
    public CharStack(int size){
        stackArray = new char[size];
        top = -1;
        this.size = size;
    }

    //入栈
    private void push (char ch){
        stackArray[++top] = ch;
    }

    //出栈
    private char pop (){
        return stackArray[top--];
    }

    //查看栈顶元素
    private char peek () {
        return stackArray[top];
    }

    //是否为空
    private boolean isEmpty() {
        return (top == -1);
    }

    //是否已满
    private boolean isFull () {
        return (top == size-1);
    }

    public  static class BrecketChecker {
        private String input;

        public  BrecketChecker (String in){
            this.input = in;
        }

        public void check() {
            int strLength = this.input.length();
            CharStack stack = new CharStack(strLength);

            for(int i = 0; i<strLength;i++){
                char ch = input.charAt(i);
                switch (ch){
                    case '{' :
                    case '[':
                    case '(':
                        stack.push(ch);
                        break;
                    case ')':
                    case ']':
                    case '}':
                        if(!stack.isEmpty()){
                            char chx = stack.pop();
                            if(ch == '{' && chx != '}' ||
                                ch == '[' && chx != ']' ||
                                ch == '(' && chx != ')'
                            )System.out.println("括号多了！字符 "+ ch +" ,下标: "+i);

                        }else{
                            System.out.println("括号多了！字符 "+ ch +" ,下标: "+i);
                        }
                        default: break;
                }
            }
            if(!stack.isEmpty()){
                System.out.println((stack.top+1) +" 个括号未匹配到结束符号！");
            }
        }
    }

    public static void main (String[] args){
        System.out.println("请输入需要检测的字符串：");
        String str = getString();
        BrecketChecker checker = new BrecketChecker(str);
        checker.check();
    }

    private static String getString() {
        String str ="";
        try {
            InputStreamReader reader = new InputStreamReader(System.in);
            BufferedReader bReader = new BufferedReader(reader);
            str = bReader.readLine();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
