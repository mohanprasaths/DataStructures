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
