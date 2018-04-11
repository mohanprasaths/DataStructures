package SpanningTreeConnected;

import Graphs.Graph;
import com.sun.xml.internal.ws.wsdl.parser.InaccessibleWSDLException;

import java.util.*;

public class SpanningTreeConnected {
    public static void main(String args[]){
        PrimsSpanningTree myGraphWeight = new PrimsSpanningTree(8);
        myGraphWeight.addEdge(0,1,1);
        myGraphWeight.addEdge(0,7,1);
        myGraphWeight.addEdge(1,0,1);
        myGraphWeight.addEdge(1,2,2);
        myGraphWeight.addEdge(1,3,2);
        myGraphWeight.addEdge(1,4,3);
        myGraphWeight.addEdge(2,1,2);
        myGraphWeight.addEdge(2,3,2);
        myGraphWeight.addEdge(3,2,2);
        myGraphWeight.addEdge(3,1,2);
        myGraphWeight.addEdge(3,5,1);
        myGraphWeight.addEdge(3,6,1);
        myGraphWeight.addEdge(4,1,3);
        myGraphWeight.addEdge(4,5,3);
        myGraphWeight.addEdge(5,4,3);
        myGraphWeight.addEdge(5,3,1);
        myGraphWeight.addEdge(6,3,1);
        myGraphWeight.addEdge(6,7,1);
        myGraphWeight.addEdge(7,0,1 );
        myGraphWeight.addEdge(7,6,1);

        int arr[][] = (myGraphWeight.findMinimumSpanningTree());
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                System.out.print("  "+arr[i][j]);
            }
            System.out.println();
        }
    }
}

class PrimsSpanningTree extends Graph implements MinimumSpanningTree{
    int length;
    Comparator priorityQueueComparator = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            InnerObjectForQueue one = (InnerObjectForQueue)o1;
            InnerObjectForQueue two = (InnerObjectForQueue)o2;
            if(one.weight == two.weight){
                if(one.vertexp < two.vertexp){
                    return -1;
                }else{
                    return 1;
                }
            }else if(one.weight > two.weight){
                return 1;
            }else if(one.weight < two.weight){
                return -1;
            }
            return 0;
        }
    };
    PrimsSpanningTree(int n){
        super(n);
        this.length = n;
    }

    public void addEdge(int x,int y,int weight){
        this.a[x][y] = weight;
    }

    @Override
    public int[][] findMinimumSpanningTree(){
        int[][] minimumTree = new int[this.length][this.length];
        PriorityQueue processingQueue =new PriorityQueue<InnerObjectForQueue>(priorityQueueComparator);
        processingQueue.add(new InnerObjectForQueue(1,1,0));
        Queue visitedNodes = new LinkedList();
        while( visitedNodes.size() < this.length){
            InnerObjectForQueue currentN = (InnerObjectForQueue)processingQueue.poll();
            int currentNode = currentN.vertex;
            if(!visitedNodes.contains(currentNode)){
                visitedNodes.add(currentNode);
            }
            Iterator iter2 = visitedNodes.iterator();
            int currentNodes = 0;
            PriorityQueue wieghtChecker =new PriorityQueue<InnerObjectForQueue>(priorityQueueComparator);

            while(iter2.hasNext()) {
                 currentNodes = (int)iter2.next();
                List adjNodes = this.getAdjacentVertices(currentNodes);
                Iterator iter = adjNodes.iterator();
                while (iter.hasNext()) {
                    int currentNodeToProcess = (int) iter.next();
                    if (visitedNodes.contains(currentNodeToProcess)) {
                        continue;

                    }
                    wieghtChecker.add(new InnerObjectForQueue(currentNodes,currentNodeToProcess, this.getWeightFromTo(currentNodes, currentNodeToProcess)));
                    processingQueue.add(new InnerObjectForQueue(currentNodes,currentNodeToProcess, this.getWeightFromTo(currentNodes, currentNodeToProcess)));

                }

            }
            //Edge to process
            if (wieghtChecker.size() > 0) {
                InnerObjectForQueue currentNProcess = (InnerObjectForQueue) wieghtChecker.poll();
                int currentNodeProcess = currentNProcess.vertex;
                if (currentNProcess.vertexp == currentNodeProcess || minimumTree[currentNProcess.vertexp][currentNodeProcess] > 0 ||
                        minimumTree[currentNodeProcess][currentNProcess.vertexp] > 0 ) {
                    continue;
                }
                minimumTree[currentNProcess.vertexp][currentNodeProcess] = currentNProcess.weight;
//                minimumTree[currentNodeProcess][currentNProcess.vertexp] = currentNProcess.weight;

//                processingQueue.add(currentNProcess);
            }

        }

        return minimumTree;
    }
    class InnerObjectForQueue{
        InnerObjectForQueue(int vertex2,int vertex,int weight){
            this.vertexp = vertex2;
            this.vertex = vertex;
            this.weight = weight;
        }
        int vertexp;
        int vertex;
        int weight;
    }
}

interface MinimumSpanningTree{
     int[][] findMinimumSpanningTree();
}