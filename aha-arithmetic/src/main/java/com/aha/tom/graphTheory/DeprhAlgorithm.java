package com.aha.tom.graphTheory;

/**
 * 图论的深度算法
 */
public class DeprhAlgorithm extends Graph{

    //定义图论已访问的节点
    private int[] visit = new int[size];

    public void searchAny(int node){

            visit[node] = 1;
            System.out.println("节点"+ nodes[node]+"被访问了");

            for(int j = 0; j < size; j++){
                //邻接点可达，且邻接点未被访问过
                if(1 == edges[node][j] && visit[j] == 0){
                    searchAny(j);
                };
            }

        }




}