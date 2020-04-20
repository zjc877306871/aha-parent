package com.aha.tom.graphTheory.shortest;

import java.util.HashMap;
import java.util.Map;

public class ShortestAlgorithm {

    //节点数
    int size;
    //节点集合
    String[] nodes;
    //边集合
    int[][] edages;

    //到邻接点的最端距离集合
    int[] distants;
    //是否被标记为主节点
    int[] isRemark;
    //最短路径路的集合
    String[] paths;

    //各个节点间最短距离的统计
    Map<String, Integer> map = new HashMap<>();

    public ShortestAlgorithm(){
        init();
        distants = new int[this.size];
        isRemark = new int[size];
        paths = new String[size];

        for(int i=0; i<size; i++){
            distants[i] = Integer.MAX_VALUE;
            isRemark[i] = 0;
            paths[i] = "";
        }

    }

    public static void main(String[] args) {
        ShortestAlgorithm shortest = new ShortestAlgorithm();
//        shortest.searchShortest(1);
        int distance = shortest.distance(0, 6);
        System.out.println(distance);


    }


    public void searchShortest(int node){
        int start = node ;
        paths[node] = nodes[node];
        distants[node] = 0;

        do {
            neighbor(node,start);
            node = shortestNode();
        } while (node != -1);


    }



    //找出主点的邻接点
    public void neighbor(int node, int start){

        //先标记
        isRemark[node] = 1;
        System.out.println(paths[node]);
        for(int i=0; i<this.size; i++){
            //与节点边距大于0，说明是相邻节点
            if(edages[node][i] > 0){
                int distance = distants[node] + edages[node][i];
                //取出相邻的节点的最短距离和最新的距离比较，去除节点两次被当作相邻节点
                if(distance < distants[i]){
                    distants[i] = distance;
                    paths[i] = paths[node] + "---->" + nodes[i];
                    //储存所有可达性的最短距离
                    map.put(nodes[start]+nodes[i],distance);
                }
            }
        }


    }
//输入开始，结束点，获取最短距离值
    public int distance(int start, int end){
        searchShortest(start);
        return map.get(nodes[start] + nodes[end]);

    }


    //找出初始节点到邻接点的最短距离

    public int shortestNode(){

        int shortest = -1;


        int min = Integer.MAX_VALUE;
        for(int i=0; i<distants.length; i++){

            //如果节点为主节点，直接排除
            if(isRemark[i] == 1){
                continue;
            }


            if(distants[i] < min){
                min = distants[i];
                shortest = i;
            }

        }

        return shortest;



    }

    public void init(){
//初始化顶点
        nodes = new String[]{"AA","A","B","C","D","E","F","G","H","M","K","N"};
        //节点编号-常量
        final int AA=0,A=1,B=2,C=3,D=4,E=5,F=6,G=7,H=8,M=9,K=10,N=11;
        size=nodes.length;

        edages = new int[size][size];
        edages[AA][A] = 3;
        edages[AA][B] = 2;
        edages[AA][C] = 5;
        edages[A][AA] = 3;
        edages[A][D] = 4;
        edages[B][AA] = 2;
        edages[B][C] = 2;
        edages[B][G] = 2;
        edages[B][E] = 3;
        edages[C][AA] = 5;
        edages[C][E] = 2;
        edages[C][B] = 2;
        edages[C][F] = 3;
        edages[D][A] = 4;
        edages[D][G] = 1;
        edages[E][B] = 3;
        edages[E][C] = 2;
        edages[E][F] = 2;
        edages[E][K] = 1;
        edages[E][H] = 3;
        edages[E][M] = 1;
        edages[F][C] = 3;
        edages[F][E] = 2;
        edages[F][K] = 4;
        edages[G][B] = 2;
        edages[G][D] = 1;
        edages[G][H] = 2;
        edages[H][G] = 2;
        edages[H][E] = 3;
        edages[K][E] = 1;
        edages[K][F] = 4;
        edages[K][N] = 2;
        edages[M][E] = 1;
        edages[M][N] = 3;
        edages[N][K] = 2;
        edages[N][M] = 3;
    }

}