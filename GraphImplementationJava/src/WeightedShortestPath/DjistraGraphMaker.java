package WeightedShortestPath;

import Graphs.Graph;

import java.util.*;

public class DjistraGraphMaker {
    public static  void main(String args[]){
        DjistraGraph myGraphWeight = new DjistraGraph(8);
        myGraphWeight.addEdge(0,1,1);
        myGraphWeight.addEdge(0,7,8);
        myGraphWeight.addEdge(1,0,1);
        myGraphWeight.addEdge(1,2,2);
        myGraphWeight.addEdge(1,3,6);
        myGraphWeight.addEdge(1,4,3);
        myGraphWeight.addEdge(2,1,2);
        myGraphWeight.addEdge(2,3,2);
        myGraphWeight.addEdge(3,2,2);
        myGraphWeight.addEdge(3,1,6);
        myGraphWeight.addEdge(3,5,1);
        myGraphWeight.addEdge(3,6,1);
        myGraphWeight.addEdge(4,1,3);
        myGraphWeight.addEdge(4,5,3);
        myGraphWeight.addEdge(5,4,3);
        myGraphWeight.addEdge(5,3,1);
        myGraphWeight.addEdge(6,3,1);
        myGraphWeight.addEdge(6,7,1);
        myGraphWeight.addEdge(7,0,8);
        myGraphWeight.addEdge(7,6,1);
        DjistraGraph.OutputFormat outputFormatShortestPath = myGraphWeight.findShortestPath(0,5);

        for(int i= outputFormatShortestPath.shortestPath.size()-1;i>=0;i--){
            System.out.printf("%d --->  ", outputFormatShortestPath.shortestPath.get(i));
        }
        System.out.println("\n Total distance is ..."+outputFormatShortestPath.distance);
    }

}

class DjistraGraph extends Graph implements shortestPathFunc{
    int length;
    DjistraGraph(int n){
        super(n);
        this.length = n;
    }

    class OutputFormat{
        List shortestPath;
        int distance;
        OutputFormat(List shortestPath,int distance){
            this.shortestPath = shortestPath;
            this.distance = distance;
        }
    }

    public void addEdge(int x,int y,int weight){
        this.a[x][y] = weight;
    }

    @Override
    public OutputFormat findShortestPath(int source, int dest) {
        List shortestPath = new LinkedList();
        int distanceTableUnweight[][] = new int[this.length][3];
        distanceTableUnweight = buildDistanceTable(source, dest, distanceTableUnweight);
        shortestPath = backTrackFromDistanceTable(shortestPath,distanceTableUnweight,source,dest);
        int distance = distanceTableUnweight[dest][1];
        return new OutputFormat(shortestPath,distance);
    }

    List backTrackFromDistanceTable(List shortestPath,int[][] distanceTable,int source,int dest){
        int currentNode = dest;
        int distance = 0;
        while(currentNode != source){
            shortestPath.add(currentNode);
            currentNode = distanceTable[currentNode][2];
        }
        shortestPath.add(source);
        return shortestPath;
    }

    int[][] buildDistanceTable(int source,int dest,int distanceTable[][]){
        Comparator priorityQueueComparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                InnerObjectForQueue one = (InnerObjectForQueue)o1;
                InnerObjectForQueue two = (InnerObjectForQueue)o2;
                if(one.weight > two.weight){
                    return 1;
                }else{
                    return -1;
                }
            }
        };
        Queue processingQueue = new PriorityQueue<InnerObjectForQueue>(priorityQueueComparator);
        for(int i=0;i<this.length;i++){
            distanceTable[i][0] = i;
            distanceTable[i][1] = -1;
            distanceTable[i][2] = -1;
        }
        distanceTable[source][1] = 0;
        distanceTable[source][2] = source;
        processingQueue.add(new InnerObjectForQueue(source,0));

        while(!processingQueue.isEmpty()){
            InnerObjectForQueue currentNodeInnerObject = (InnerObjectForQueue) processingQueue.poll();
            int currentNode = currentNodeInnerObject.vertex;
            if(this.getAdjacentVertices(currentNode).size() > 0){
                List adjNodes = this.getAdjacentVertices(currentNode);
                Iterator iter = adjNodes.iterator();
                while(iter.hasNext()){
                    int currentNodeToProcess = (int)iter.next();
                    int currentNodeToProcessNodeWeight = this.getDistance(source,currentNode,currentNodeToProcess,distanceTable);
                    if(distanceTable[currentNodeToProcess][1] == -1 || currentNodeToProcessNodeWeight < distanceTable[currentNodeToProcess][1]){
                        distanceTable[currentNodeToProcess][1] = currentNodeToProcessNodeWeight;
                        distanceTable[currentNodeToProcess][2] = currentNode;
                        processingQueue.add(new InnerObjectForQueue(currentNodeToProcess,currentNodeToProcessNodeWeight));

                    }
                    if(this.getAdjacentVertices(currentNodeToProcess).size() > 0){
//                        processingQueue.add(new InnerObjectForQueue(currentNodeToProcess,currentNodeToProcessNodeWeight));
                    }
                }
            }
        }

        return distanceTable;
    }
    int getDistance(int source,int currentNode,int currentNodeToProcess,int distanceTable[][]){
        if(currentNode == source){
            return this.getWeightFromTo(currentNode,currentNodeToProcess);
        }else{
            return getRecursiveDistance(source,currentNode,currentNodeToProcess,distanceTable);
        }
    }

    int getRecursiveDistance(int source,int currentNode,int currentNodeToProcess,int distanceTable[][]){
        if(currentNode == source){
            return this.getWeightFromTo(source,currentNodeToProcess);
        }else{
            return this.getWeightFromTo(currentNode,currentNodeToProcess) + this.getRecursiveDistance(source,distanceTable[currentNode][2],currentNode,distanceTable);
        }
    }
    class InnerObjectForQueue{
        InnerObjectForQueue(int vertex,int weight){
            this.vertex = vertex;
            this.weight = weight;
        }
        int vertex;
        int weight;
    }
}

interface shortestPathFunc{
    DjistraGraph.OutputFormat findShortestPath(int source, int dest);
}