# Implementation-of-Graph-Algorithms
Project-2
Algorithms and data Structures

Name: SreeLakshmi Setturu


Description: 

Consider a data communication network that must route data packets (email, MP3 files, or video
files, for example). Such a network consists of routers connected by physical cables or links. A
router can act as a source, a destination, or a forwarder of data packets. We can model a network
as a graph with each router corresponding to a vertex and the link or physical connection between
two routers corresponding to a pair of directed edges between the vertices.
A network that follows the OSPF (Open Shortest Path First) protocol routes packets using
Dijkstra’s shortest path algorithm. The criteria used to compute the weight corresponding to a
link can include the time taken for data transmission, reliability of the link, transmission cost, and
available bandwidth. Typically each router has a complete representation of the network graph
and associated information available to it.
For the purposes of this project, each link has associated with it the transmission time taken
for data to get from the vertex at one end to the vertex at the other end. Computed the
best path using the criterion of minimizing the total time taken for data to reach the destination.
The shortest time path minimizes the sum of the transmission times of the links along the path.
The network topology can change dynamically based on the state of the links and the routers.
For example, a link may go down when the corresponding cable is cut, and a vertex may go down
when the corresponding router crashes. In addition to these transient changes, changes to a network
occur when a link is added or removed.

Since the states of the vertices and edges may change with time, it is useful to know the set of
vertices reachable from each vertex by valid paths.Implemented breadth first search algorithm
that identifies for each vertex, the set of all its reachable vertices.


Implementation:

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
14)vertexUp: Mark the vertex as “up” again
15)vertexDown: Marks the vertex as “down”. None of its edges are used. Marking
a vertex as “down” won't mark  any of its incoming or outgoing edges to be marked
as “down”. So the graph can have “up” edges going to and leaving from a “down” vertex.
However a path through such a “down” vertex is not  used as a valid path.

16)edgeUp: Marks the directed edge as “UP” and therefore makes it available.
for use
17)edgeDown: Marks the directed edge as “down” and therefore unavailable
for use
18)print: prints the contents of the graph. Vertices are printed in alphabetical order and the
outward edge for each vertex are also printed in alphabetical order.

Vertex class:vertex class has information about 
 * vertex name,distance,previous vertex,
 * and availability of a vertex,
 * position(position is vertex in priority queue, used in implementation of decrease key  priority)
 * color of the vertex (used in BFS to discover reachable vertices)
 
 Edge Class:Edge class has information about Source vertex,
 * Destination Vertex, Cost of each edge(transmission time)
 * availability of each edge
