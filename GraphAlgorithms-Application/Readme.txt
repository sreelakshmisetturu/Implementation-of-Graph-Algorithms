Project-2
Algorithms and data structures
Name: SreeLakshmi Setturu
sreelakshmisetturu@gmail.com
ssetturu@uncc.edu
..........................................
Programming language used:JAVA
Compiler Version : 51
........................................

Graph class:
------------
This class has the main method. Following methods are implemented in graph class
1)buildGraph: This method constructs a graph in which each link represinting two directional edges
2)getVertex:This method adds the vertex to vertexMap, if vertex is not present already.
Itreturns the vertex object for the given vertex name.
3)addEdge:This method adds the single directed edges. 
If the vertices are not present in the graph they are added to the graph.
4)deleteEdge:Deletes the specified edge. It does nothing if edge is not present.
5)edgeDown:Marks the directed edges DOWN and makes unavailable for use.
 Makes only the specified edge down not its companion edge.
6)clearAll: For every Vertex reset() method is called to set dist to INFINITY and prev to null, these are used in shortest path algorithm 
and color to "White", which is required in BFS.
7)main: File is read and appropriate methods are called accroding to the input query.
8)reachable:eachable method computes reachable vertices of every "up" vertex.
BFS is called for every "UP" vertex.Breadth First Search builds breadth first tree of a vertex with all its reachable vertices.
Running time of BFS is (|V|+|E|).Since I have to find reachable vertices of every "Up" vertex,
Running time of the algorithm of "reachable" is O( V(|V|+|E|)).

other methods are,

9)shortestpath:shortestpath method Computes shortest path between Specified source and destination vertices.
 * Dijkstras algorithm is implemented to calculate shortest path.
 * Priority queue using Binary heaps is implemented.
 * Running time of the shortest path algorithm is O((|V|+|E|)lnV)
10)printPath
11)buildMinHeap
12)minHeapify
13)extractMin
14)vertexUp
15)vertexDown
16)edgeUp
17)edgeDown
18)print

Vertex class:vertex class has information about 
 * vertex name,distance,previous vertex,
 * and availability of a vertex,
 * position(position is vertex in priority queue, used in implementation of decrease key  priority)
 * color of the vertex (used in BFS to discover reachable vertices)
 
 Edge Class:Edge class has information about Source vertex,
 * Destination Vertex, Cost of each edge(transmission time)
 * availability of each edge
 
What I think fails in the program?
 
When I compile my program, I am getting,  
Note: Graph.java uses unchecked or unsafe operations.
Note: Recompile with -Xlint:unchecked for details.
But the program is running fine.

The program is tested with two test cases (sample test case given and another test case build from a quiz problem)
Program is working fine and giving the expected output.