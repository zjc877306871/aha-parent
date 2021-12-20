package com.aha.tom.sort;

/**
 * 简单插入
 */
public class InsertSort {


    public static int[] sort(int[] arrays){
        if(arrays.length == 0){
            return arrays;
        }

        for(int i=0; i<arrays.length-1; i++){
            //获取当前要比较的位置
            int prevent = i+1;

            while(prevent > 0){

                //先获取要比较的数值
                int value = arrays[prevent];
                //循环对比两数之间大小，并移位
                if(arrays[prevent-1] > arrays[prevent]){
                    arrays[prevent] = arrays[prevent-1];
                    arrays[prevent-1] = value;
                }
                prevent--;
            }

        }
        return arrays;
    }


    public static void main(String[] args) {

        PrintArray.print(PrintArray.SRC);
        PrintArray.print(InsertSort.sort(PrintArray.SRC));


    }
}