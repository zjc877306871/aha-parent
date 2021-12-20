package com.aha.tom.sort;

public class SelectSort {

    public static void main(String[] args) {

        PrintArray.print(PrintArray.SRC);
        PrintArray.print(SelectSort.sort(PrintArray.SRC));


    }


    public static int[] sort(int[] arrays){
        if(arrays.length == 0){
            return arrays;
        }

        for(int i=0; i<arrays.length; i++){

            //最小值的下标
            int min = i;
            for(int j=i; j<arrays.length-i; j++){
                if(arrays[j] < arrays[min]){
                    min = j;
                }
            }

            //找到最小的坐标之后进行交换
            int temp = arrays[min];
            arrays[min] = arrays[i];
            arrays[i] = temp;

        }
        return arrays;

    }
}