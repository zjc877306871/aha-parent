package com.aha.tom.sort;

/**
 * 冒泡排序
 */
public class BubbleSort {


    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();

        PrintArray.print(PrintArray.SRC);
        int[] resultt = bubbleSort.sortAsc(PrintArray.SRC);
        PrintArray.print(resultt);
    }



    /**
     * 升序
     * @param arrays
     */
    public int[] sortAsc(int[] arrays){
        if(arrays.length == 0){
            return arrays;
        }

        //循环n次
        for(int i=0; i<arrays.length; i++){
            //每次循环，从头对比彼此相邻的两个数的大小；并交换位置。循环次数越多，对比的次数越少
            for(int j=0; j<arrays.length-1-i; j++){

                if(arrays[j] > arrays[j+1]){
                    int temp = arrays[j+1];
                    arrays[j+1] = arrays[j];
                    arrays[j] = temp;
                }

            }
        }

        return arrays;
    }

}