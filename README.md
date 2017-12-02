# DataStructures and Algorithms

Algrithms should be unambiguous (not more than one meaning/step)

Analysis:
A Priori Analysis : Theorectical Analysis - Without implementing it - Assuming all factors
A posterior Analysis : Emprical Analysis - Implement and anaysed

Complexity:
Time : counted by number of key operations
Space : counted by max memory space used by program (number of variables static/dynamic used)


Three types :
 Best case : Upper bound of algorithms running time -  Ω notation
 Worst case : Lower bound of algorithms running time - O notation
 Average case : average of Best and worst case - θ notation

Aysmptotic notations :
O(1) - constant time - one operations

O(n) - linear time - number operations is same as the input numbers

O(log n) -  basically means time goes up linearly while the n goes up exponentially. So if it takes 1 second to compute 10 elements, it will take 2 seconds to compute 100 elements, 3 seconds to compute 1000 elements, and so on (Divide and conquer may be) - https://i.stack.imgur.com/spHFh.png

O(n log n) -  when we do divide and conquer type of algorithms e.g binary search. Another example is quick sort where each time we divide the array into two parts and each time it takes
O(N) time to find a pivot element. Hence it  N O(log N)

O(n2) - number of operations increases n*n times with n number of inputs


Popular Alogorithm types :

Greedy algorithms : Being greedy to provide optimal solution
examples :
Counting coins
Travelling Salesman Problem
Prim's Minimal Spanning Tree Algorithm
Kruskal's Minimal Spanning Tree Algorithm
Dijkstra's Minimal Spanning Tree Algorithm
Graph - Map Coloring
Graph - Vertex Cover
Knapsack Problem
Job Scheduling Problem

Divide and conquer : Break a problem in to subproblems recursively untill it cannot be further divided and solve the subproblems and merge the solutions together for the solution of original Problem
examples :
Merge Sort
Quick Sort
Binary Search
Strassen's Matrix Multiplication
Closest pair (points)

Dynamic programming - Problem is divided in to subproblems and the result of the subproblems will be remembered for having the optimal solution while solving the original problem .
examples :
Fibonacci number series
Knapsack problem
Tower of Hanoi
All pair shortest path by Floyd-Warshall
Shortest path by Dijkstra
Project scheduling


Skipping stack and queue to Graph DS

Graph DS
Vertex is the point
Edge is the line connecting the points  
Connected vertices are considered adjacent
Path is connected edges from one vertex to another
Degree is the number of vertex connected to the current Vertex
Types:
1.)Undirected graph - two way relationship eg: Jim and jack play basketball , where jim and jack are vertex and play-basketball is edge
2.) Directed graph - One way relationship eg: Jim drives car ,  car cannot drive jim , where jim and car are vertex and drive is edge

Undirected cyclic  graph : from a  vertex we can traverse and reach the same vertex again -
Undirected acyclic  graph : from a  vertex we can traverse and cannot reach the same vertex again - by removing few of the edges . It is a tree actually , since all the vertex can reach other .
If a edge is removed from Undirected acyclic graph , it will result in two seperate graph or disjoint trees .
Joining trees form a forest .


Ways to represent graph :
Adjacency matrices
Adjacency lists
Adjacency sets


Adjacency  matrix for undirected graph will be symmetric
Adjacency  matrix for directed graph will be asymmetric


Common graph problems -
Topological sort - Computation graphs in neural networks - DAG (directed acyclic graph)   eg: scheduling tasks
Shortest path - fast delivery of anything , packets or networks
Minimum spanning - Planning transport roads , Planning railway lines

Topological sort = Directed acyclic graph - used for scheduling and neural networks - precedence order - one task after another - A graph can have more than one Topological sort/order
Calculate in-degree (number of incoming edges on vertex )in DAG - if no vertex has in-degree of 0 , then the graph is cyclic and Topological sort is not possible
Steps :  
1.) Start with the 0 in-degree vertex , process it and then mark it as processed , add it to precedencelist and remove from the graph
2.) Calculate in-degree again - process the vertex with in-degree equals 0 ,and mark it as processed , add it to the precedencelist and  and remove it from graph , repeat


Shortest Path :
Finding the shortest path between two nodes - tranportation  -
Edge weights determine the cost of the path
Two algorithms :
If all edges of same weight - unweighted shortest path algorithms
if the edges are of different weight - Dijkstra algorithm
