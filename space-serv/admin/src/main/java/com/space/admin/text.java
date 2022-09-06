package com.space.admin;

public class text {

    public static class hunman{
        static int  num = 0;
        static {
            num = 1;
            System.out.println("hunman static");
        }
        public void printNum(){
            System.out.println(num);
        }
    }
    public static class Man extends hunman{
        static int  num = 2;
        static {
            num = 3;
            System.out.println("Man static");
        }
        public void printNum(){
            System.out.println(num);
        }
    }
    public static void main(String[] args) {
        hunman abc = new Man();
        abc.printNum();
    }
}
