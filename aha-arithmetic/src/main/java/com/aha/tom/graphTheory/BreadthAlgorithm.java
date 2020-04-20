package com.aha.tom.graphTheory;

import java.util.ArrayList;
import java.util.List;

/**
 * 广度算法
 */
public class BreadthAlgorithm extends Graph{

    private int[] visit = new int[this.size];

    //低端的写法
    public void breadthAny(List start){

        for(int j = 0; j < start.size(); j++){
            List subNodes = new ArrayList();

            System.out.println("节点" + nodes[j] + "被访问");
            for(int i = 0; i < size; i++){
                if(edges[j][i] == 1 && visit[i] == 0){
                    visit[i] = 1;
                    subNodes.add(nodes[i]);

                    if(subNodes.size() > 0 ){

                        breadthAny(subNodes);
                    }

                }
            }
        }


    }

    private int[] queue = new int[this.size];

    public void breadthBetter(int front, int last){

        //
        int tail = last;

        for(int i = front; i <= last; i++){
            int node = queue[i];
            System.out.println("齐天大圣到—>" + this.nodes[node]+"一游"); //输出节点数据
            for(int j = 0; j < this.size; j++){
                if(edges[node][j] == 1 && visit[j] == 0){
                    visit[i] = 1;
                    queue[++last] = j;

                }
            }

        }
        //遍历下一批节点
        if(last > tail){
            breadthBetter(tail+1, last);
        }

    }


    public void breadthBetter(int start){
        queue[0] = start;
        visit[start] = 1;
        breadthBetter(0,0);
    }


}