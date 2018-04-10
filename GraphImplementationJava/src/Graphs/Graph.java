package Graphs;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    public int a[][];
    public Graph(int n){
        this.a = new int[n][n];
    }

    public void addEdge(int x, int y){
        this.a[x][y] = 1;
    }

    public int getIndegree(int vertice){
        int total= 0 ;
        for(int i=0;i<this.a.length;i++){
            total = total+this.a[i][vertice];
        }
        return total;
    }

   public  List<Integer> getAdjacentVertices(int vertice){
        List adjacentVertices = new ArrayList();
        for(int i=0;i<this.a.length;i++){
            if(this.a[vertice][i] > 0){
                adjacentVertices.add(i);
            }
        }
        return adjacentVertices;
    }

    public int getWeightFromTo(int from,int to){
        return this.a[from][to];
    }

    //Function to print the adjacency matrix
    public void printGraph(){
        for(int i=0;i<this.a.length;i++){
            for(int j=0;j<this.a.length;j++){
                System.out.print(" "+this.a[i][j]+" ");
            }
            System.out.println("");
        }
    }
}
