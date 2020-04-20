package com.aha.tom.graphTheory;

public class Graph {

    protected String[] nodes;

    protected int size;

    protected int[][] edges;


    public Graph(){

        nodes = new String[]{"A","B","C","D","E","F","G"};
        size = nodes.length;

        final int A = 0; int B = 1; int C = 2; int D = 3; int E = 4; int F = 5; int G = 6;
        edges = new int[size][size];
        edges[A][C] = 1;
        edges[A][D] = 1;
        edges[A][F] = 1;
        edges[B][C] = 1;
        edges[C][A] = 1;
        edges[C][D] = 1;
        edges[C][B] = 1;
        edges[D][A] = 1;
        edges[D][C] = 1;
        edges[E][G] = 1;
        edges[F][A] = 1;
        edges[F][G] = 1;
        edges[G][F] = 1;
        edges[G][E] = 1;

    }

}