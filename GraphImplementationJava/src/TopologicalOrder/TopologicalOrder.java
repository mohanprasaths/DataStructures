package TopologicalOrder;

import Graphs.Graph;

import java.util.*;

public class TopologicalOrder {
    public static void main(String args[]){
        int n = 5;
        Graph dag = new Graph(5);
        dag.addEdge(0,1);
        dag.addEdge(0,2);
        dag.addEdge(2,4);
        dag.addEdge(1,3);
        dag.addEdge(4,3);
        dag.addEdge(4,1);
        dag.printGraph();
        int indegreeArray[] = new int[n];
        Queue<Integer> toVisitQ = new PriorityQueue<>();
        int visitedCounter = 0;
        for(int i=0;i<n;i++){
            List<Integer> topoOrder = new ArrayList<>();
            indegreeArray[i] = dag.getIndegree(i);
            if(indegreeArray[i] == 0){
                toVisitQ.add(i);
                visitedCounter++;
            }
            while(!toVisitQ.isEmpty()){
                int currentNode = toVisitQ.poll();
                topoOrder.add(currentNode);
                List adjacentVerticesOfVisitedNode = dag.getAdjacentVertices(currentNode);
                Iterator iter = adjacentVerticesOfVisitedNode.iterator();
                while(iter.hasNext()){
                    int adjNode = (int)iter.next();
                    dag.a[currentNode][adjNode] = dag.a[currentNode][adjNode] -1;
                    if(dag.getIndegree(adjNode) == 0){
                        toVisitQ.add(adjNode);
                    }
                }
            }
            if(topoOrder.size() == n)
                System.out.println(topoOrder);
        }
    }
}
