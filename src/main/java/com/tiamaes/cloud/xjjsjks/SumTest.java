package com.tiamaes.cloud.xjjsjks;

public class SumTest {


    public static void main(String[] args) {
        // 求自然数1到100的和
        int sum = 0;
        for (int i = 1;i <= 100;i++){
            sum += i;
        }
        System.out.println("自然数1到100的和为：" + sum);
        // 排序
        int[] numbers = {32,87,3,589,12,1076,2000,8,622,127};
        for (int i = 0;i<numbers.length-1;i++){
            for (int j = 0;j<numbers.length-1-i;j++){
                if (numbers[j]>numbers[j+1]){
                    int temp=numbers[j];
                    numbers[j]=numbers[j+1];
                    numbers[j+1]=temp;
                }
            }
        }
        System.out.println("从小到大排序后的结果是:");
        for(int i=0;i<numbers.length;i++){
            System.out.print(numbers[i]+" ");
        }
        System.out.println();
        // 屏幕输出结果
        System.out.println("java\n语\b言");
        System.out.println("java\r语言");
        System.out.println("java\t语言");
        System.out.println("\\java语言\\");
        System.out.println("\'java语言\'");
        System.out.println("\"java语言\"");




    }

}

