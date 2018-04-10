package UnweightedShortestPath;

import Graphs.Graph;

import java.util.*;

public class UnWeightedShortestPath {
    public static  void main(String args[]){

    ShortedPathGraph myGraph = new ShortedPathGraph(8);
        myGraph.addEdge(0,3);
        myGraph.addEdge(0,7);
        myGraph.addEdge(1,6);
        myGraph.addEdge(1,5);
        myGraph.addEdge(2,3);
        myGraph.addEdge(2,4);
        myGraph.addEdge(3,0);
        myGraph.addEdge(3,2);
        myGraph.addEdge(3,4);
        myGraph.addEdge(4,3);
        myGraph.addEdge(4,2);
        myGraph.addEdge(4,6);
        myGraph.addEdge(4,5);
        myGraph.addEdge(5,7);
        myGraph.addEdge(5,4);
        myGraph.addEdge(5,6);
        myGraph.addEdge(5,1);
        myGraph.addEdge(6,5);
        myGraph.addEdge(6,4);
        myGraph.addEdge(6,1);
        myGraph.addEdge(7,5);
        myGraph.addEdge(7,6);



        myGraph.simpleFindShortestPath(0,1);
        myGraph.printGraph();

    }
}

interface UnweightedShortestPathUtility{
    void FindShortesPath(int source,int dest);
    void simpleFindShortestPath(int source,int dest);
}

 class ShortedPathGraph extends Graph implements UnweightedShortestPathUtility{
    int length = 0;
     Hashtable<Integer,distanceTableRow> distanceTb = new Hashtable<Integer,distanceTableRow>();
     public ShortedPathGraph(int n) {
         super(n);
         this.length = n;
     }

    @Override
    public void simpleFindShortestPath(int source , int dest){
         int distanceTable[][]=new int[this.length][3];
         this.buildDistanceTable(distanceTable,source,dest);
        for(int i=0;i<this.length;i++){
            System.out.printf("%d   %d    %d \n",i,distanceTable[i][1],distanceTable[i][2]);
        }
        Queue path = new LinkedList();
        path = backTrackFromDt(distanceTable,source,dest,0,path );
        Iterator iter = path.iterator();
        System.out.println("-----------------------------");
        System.out.println(dest);
        while(iter.hasNext()){
            System.out.println((int)iter.next());
        }
        System.out.println("-----------------------------");

    }

    Queue backTrackFromDt(int[][] dt,int source,int dest,int addValue,Queue path){
         if(dest == source){
             return path;
         }
        path.add(dt[dest][2]);
        return backTrackFromDt(dt,source,dt[dest][2],addValue+1,path);
    }
    int[][] buildDistanceTable(int arr[][],int source,int dest){
         Queue processingQueue = new LinkedList();
         Queue visited = new LinkedList();
         for(int i=0;i<this.length;i++){
             arr[i][1] = -1;
             arr[i][2] = -1;
         }
         arr[source][1] = 0;
         arr[source][2] = 0;
         processingQueue.add(source);
         int currentAddValue= 0 ;
         while(!processingQueue.isEmpty()){
             int currentNode = (int)processingQueue.poll();
             currentAddValue = arr[currentNode][1];
             visited.add(currentNode);
             List adjNodes = this.getAdjacentVertices(currentNode);
             Iterator iter = adjNodes.iterator();
             while(iter.hasNext()){
                 int currentNodeToProcess = (int)iter.next();
                 if(visited.contains(currentNodeToProcess)){
                     continue;
                 }
                 if(arr[currentNodeToProcess][1] == -1){
                     arr[currentNodeToProcess][1] = currentAddValue+1;
                     arr[currentNodeToProcess][2] = currentNode;
                 }else {
//                     arr[currentNodeToProcess][1] = currentAddValue + 1;
                 }
                 if(this.getAdjacentVertices(currentNodeToProcess).size() > 0){
                     processingQueue.add(currentNodeToProcess);
                 }
             }
         }
         return arr;
    }

     @Override
     public void FindShortesPath(int source,int dest) {
         Queue<Integer> processingQueue = new LinkedList<>();

         for(int i=0;i<length;i++){
             distanceTableRow newRow;
             if(i == source){
                 newRow = new distanceTableRow(0,source);
             }
             else{
                  newRow = new distanceTableRow(-1,-1);
             }
             distanceTb.put(i,newRow);
         }
         List<Integer> adjVertices1 = this.getAdjacentVertices(source);
         if(adjVertices1.size() > 0){
             processingQueue.add(source);
         }
         int previousNode = source;
         Queue visitedNodes = new LinkedList();
         int distanceGoing = 0;
         while(!processingQueue.isEmpty()){
             distanceGoing = distanceGoing+1;
             int currentProcessingNode = processingQueue.poll();
             visitedNodes.add(currentProcessingNode);
             List<Integer> adjVertices = this.getAdjacentVertices(currentProcessingNode);
             Iterator iter = adjVertices.iterator();
             while(iter.hasNext()){
                 int processingNode = (int)iter.next();
                 if(distanceTb.get(processingNode).distance == -1){
                     distanceTb.put(processingNode,new distanceTableRow(distanceGoing+1+distanceTb.get(processingNode).distance,currentProcessingNode));
                 }else if(!visitedNodes.contains(processingNode)){
                     distanceTb.put(processingNode,new distanceTableRow(distanceGoing+distanceTb.get(processingNode).distance,currentProcessingNode));
                 }
                 if(this.getAdjacentVertices(processingNode).size() > 0){
                     processingQueue.add(processingNode);
                 }
             }
         }
         for(int i=0;i<distanceTb.size();i++){
             System.out.print(i);
             System.out.print(distanceTb.get(0).distance);
             System.out.println(distanceTb.get(0).preceedingNode);
         }
     }
     class distanceTableRow{
         int distance;
         int preceedingNode;
         distanceTableRow(int distance, int preceedingNode){
             this.distance = distance;
             this.preceedingNode = preceedingNode;
         }
     }
 }