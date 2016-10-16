//Name: Sree Lakshmi Setturu
// 800934503
//ssetturu
package com;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;

class GraphException extends RuntimeException {
	public GraphException(String name) {
		super(name);
	}
}
/**vertex class has information about 
 * vertex name,distance,previous vertex,
 * and availability of a vertex,
 * position(position is vertex in priority queue, used in implementation of decrease key  priority)
 * color of the vertex (used in BFS to discover reachable vertices)
 *Adjacency list of edges stores the adjacent edges information of a vertex.
 */
class Vertex {
	public String name;
	public List<Edge> adj;
	public double dist; 
	public Vertex prev;
	public boolean availability;
	int pos;
	public String color;

	public Vertex(String nm, boolean availability) {
		name = nm;
		this.availability = availability;
		adj = new LinkedList();
		reset();
	}

	public Vertex() {

	}

	public boolean getAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public double getDist() {
		return dist;
	}

	public void setDist(double dist) {
		this.dist = dist;
	}

	public Vertex getPrev() {
		return prev;
	}

	public void setPrev(Vertex prev) {
		this.prev = prev;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public void reset() {
		dist = Graph.INFINITY;
		prev = null;
		color = "White";
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}

/**Edge class has information about Source vertex,
 * Destination Vertex, Cost of each edge(transmission time)
 * availability of each edge
 *
 */
class Edge {
	public Vertex source, dest; 
	public double cost; 
	boolean availability;

	public Edge(Vertex v, Vertex d, double c, boolean availability) {
		source = v;
		dest = d;
		cost = c;
		this.availability = availability;
	}

	public Edge() {
		// TODO Auto-generated constructor stub
	}

	public String getSource() {
		return source.name;
	}

	public String getDest() {
		return dest.name;
	}

	public double getCost() {
		return cost;
	}

	public boolean getAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public void setCost(double cost) {
		// TODO Auto-generated method stub
		this.cost = cost;
	}
}
/**
 * Graph class has the main method,
 * input file is read and appropriate methods are called.
 * vertex map stores vertices in sorted order
 *
 */

public class Graph {

	public static final double INFINITY = Double.MAX_VALUE;
	private Map<String, Vertex> vertexMap = new TreeMap<String, Vertex>();

//This method constructs graph
	public void buildGraph(String sourceName, String destName, double cost, boolean availability) {
		Vertex v = getVertex(sourceName, availability);
		Vertex w = getVertex(destName, availability);
		v.adj.add(new Edge(v, w, cost, availability));
		w.adj.add(new Edge(w, v, cost, availability));
	}

	//This method adds the vertex to vertexMap, if vertex is not present already
	//returns the vertex object for the given vertex name.
	public Vertex getVertex(String name, boolean status) {
		Vertex v = vertexMap.get(name);

		if (v == null) {
			v = new Vertex(name, status);
			vertexMap.put(name, v);
		}
		return v;
	}

	//This method adds the single directed edges. 
	//If the vertices are not present in the graph they are added to the graph
	public void addEdge(String sourceName, String destName, double cost, boolean availability) {
		Vertex ver = getVertex(sourceName, availability);
		Vertex des = getVertex(destName, availability);
		Iterator it = ver.adj.listIterator();
		while (it.hasNext()) {
			Edge edge = (Edge) it.next();
			if (destName.equals(edge.getDest())) {
				it.remove();
			}
		}
		ver.adj.add(new Edge(ver, des, cost, availability));
	}

//Deletes the specified edge. It does nothing if edge is not present.
	public void deleteEdge(String sourceName, String destName) {
		Vertex src = vertexMap.get(sourceName);
		Iterator it = src.adj.listIterator();
		while (it.hasNext()) {
			Edge edge = (Edge) it.next();
			if (destName.equals(edge.getDest())) {
				it.remove();
			}
		}
	}
/**Marks the directed edges DOWN and makes unavailable for use.
 * makes only the specified edge down not its companion edge
 */
	public void edgeDown(String sourceName, String destName) {
		Vertex src = vertexMap.get(sourceName);
		Iterator it = src.adj.listIterator();
		while (it.hasNext()) {
			Edge edge = new Edge();
			edge = (Edge) it.next();
			if (destName.equals(edge.getDest())) {
				edge.setAvailability(false);
			}
		}
	}

	private void clearAll() {
		for (Vertex v : vertexMap.values())
			v.reset();
	}

	public static void main(String[] args) {
		Graph g = new Graph();
		boolean availability = true;
		try {
			FileReader fin = new FileReader(args[0]);
			BufferedReader graphFile = new BufferedReader(fin);
			String line;
			while ((line = graphFile.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line);

				try {
					if (st.countTokens() != 3) {
						System.err.println("Skipping ill-formatted line " + line);
						continue;
					}
					String source = st.nextToken();
					String dest = st.nextToken();
					double cost = Double.parseDouble(st.nextToken());
					g.buildGraph(source, dest, cost, availability);
				} catch (NumberFormatException e) {
					System.err.println("Skipping ill-formatted line " + line);
				}
			}
		} catch (IOException e) {
			System.err.println(e);
		}

		System.out.println("File read...");
		System.out.println(g.vertexMap.size() + " vertices");
//Takes input of queries and relevant methods are called.
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter query");
			String query = sc.nextLine();
			StringTokenizer st = new StringTokenizer(query);
			if (st.countTokens() == 4) {
				String method = st.nextToken();
				String source = st.nextToken();
				String dest = st.nextToken();
				Double cost = Double.parseDouble(st.nextToken());
				if (method.equals("addedge")) {
					g.addEdge(source, dest, cost, availability);
				}
			} else if (st.countTokens() == 3) {
				String method = st.nextToken();
				String source = st.nextToken();
				String dest = st.nextToken();
				if (method.equals("deleteedge")) {
					g.deleteEdge(source, dest);
				} else if (method.equals("edgedown")) {
					g.edgeDown(source, dest);
				} else if (method.equals("edgeup")) {
					g.edgeUp(source, dest);
				} else if (method.equals("path")) {
					double dist = g.shortestPath(source, dest);
					g.printPath(dest);
					BigDecimal a = new BigDecimal(dist);
					BigDecimal roundOff = a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
					System.out.println(" " + roundOff);
				}
			} else if (st.countTokens() == 2) {
				String method = st.nextToken();
				String vertex = st.nextToken();
				if (method.equals("vertexdown")) {
					g.vertexDown(vertex);
				} else if (method.equals("vertexup")) {
					g.vertexUp(vertex);
				}
			} else if (st.countTokens() == 1) {
				String method = st.nextToken();
				if (method.equals("print")) {
					g.print();
				} else if (method.equals("reachable")) {
					g.reachable();
				} else if (method.equals("quit")) {
					System.exit(0);
				}
			}
		}
	}

	/**reachable method computes reachable vertices of every "up" vertex.
	 * BFS is called for every "UP" vertex.
	 * Breadth First Search builds breadth first tree 
	 * of a vertex with all its reachable vertices.
	 * Running time of BFS is (|V|+|E|)
	 * since I have to find reachable vertices of every "Up" vertex,
	 * running time of the algorithm of "reachable" is O( V(|V|+|E|))
	 */
	public void reachable() {
		for (Vertex v : vertexMap.values()) {

			if (v.getAvailability()) {
				System.out.println(v.name);
				BFS(v);
			}
		}
	}

	public void BFS(Vertex v) {
		Map<String, Vertex> tmap = new TreeMap<String, Vertex>();
		clearAll();
		v.setColor("Gray");
		v.setDist(0);
		v.setPrev(null);
		PriorityQueue<String> q = new PriorityQueue<String>();
		q.add(v.name);
		while (!q.isEmpty()) {
			String u = q.remove();
			Vertex U = vertexMap.get(u);
			Iterator it = U.adj.listIterator();
			while (it.hasNext()) {
				Edge edge = (Edge) it.next();
				Vertex ver = vertexMap.get(edge.getDest());
				if (!ver.name.equals(U.name) && ver.availability) {
					if (ver.getColor().equals("White")) {
						ver.setColor("Gray");
						ver.dist = U.getDist() + 1.0;
						ver.setPrev(U);
						q.add(ver.name);
						tmap.put(ver.name, ver);
					}
				}
			}
			U.setColor("Black");
		}
		for (Vertex w : tmap.values()) {
			System.out.println("  " + w.name);
		}
	}
	
/**shortestpath method Computes shortest path between Specified source and destination vertices.
 * Dijkstras algorithm is implemented to calculate shortest path.
 * Priority queue using Binary heaps is implemented.
 * Running time of the shortest path algorithm is O((|V|+|E|)lnV)
 * 
 */
	private double shortestPath(String source, String dest) {
		// TODO Auto-generated method stub
		clearAll();
		int count = 0;
		for (Vertex v : vertexMap.values()) {
			if (v.availability == true) {
				count++;
			}
		}
		Vertex s = vertexMap.get(source);
		s.dist = 0;
		s.prev = null;

		Vertex[] Q = new Vertex[count];
		int i = 0;
		for (Vertex v : vertexMap.values()) {
			if (v.getAvailability() == true) {
				Q[i] = v;
				v.setPos(i);
				i++;
			}
		}
		buildMinHeap(Q, count);
		int qsize = count;
		while (qsize != 0) {
			Vertex U = extractMin(Q, qsize);
			qsize--;
			Iterator it = U.adj.listIterator();
			while (it.hasNext()) {
				Edge edge = (Edge) it.next();
				if (edge.getAvailability() == true) {
					String des = edge.getDest();
					Vertex desti = vertexMap.get(des);
					if (desti.getAvailability()) {
						if (desti.dist > (U.dist + edge.getCost())) {
							double w = U.dist + edge.getCost();
							desti.dist = w;
							desti.setPrev(U);
							int position = desti.getPos();
							HeapdecreaseKey(Q, position, desti);
						}
					}
				}
			}
		}
		return vertexMap.get(dest).dist;
	}
//prints shortest path from source to destination vertex.
	private void printPath(String desti) {
		Vertex dest = vertexMap.get(desti);
		if (dest.prev != null) {
			printPath(dest.prev.name);
		}
		System.out.print(dest.name+" ");
	}
//HeapdecreaseKey method is called in Dijkstras algorithm after the distance is updated
//for each adjacent vertex. This is to maintain to heap order. 
//Running time of this algorithm is O(lnV)
	public void HeapdecreaseKey(Vertex[] vertex, int pos, Vertex key) {
		vertex[pos] = key;
		while (pos > 0 && vertex[(pos - 1) / 2].dist > vertex[pos].dist) {
			Vertex temp = vertex[(pos - 1) / 2];
			Vertex antemp = vertex[pos];
			temp.setPos(pos);
			vertex[pos] = temp;
			antemp.setPos((pos - 1) / 2);
			vertex[(pos - 1) / 2] = antemp;
			pos = (pos - 1) / 2;
		}
	}
//Builds minimum binary heap
	public void buildMinHeap(Vertex[] vertex, int index) {
		int ind = index / 2;
		for (int j = ind - 1; j >= 0; j--) {
			// System.out.println(j);
			minHeapify(vertex, j, index);
		}
	}
	public void minHeapify(Vertex[] vertex, int j, int index) {
		int l, r, smallest;
		l = 2 * j + 1;
		r = 2 * j + 2;
		if (l < index && vertex[l].dist < vertex[j].dist) {
			smallest = l;
		} else
			smallest = j;
		if (r < index && vertex[r].dist < vertex[smallest].dist) {
			smallest = r;
		}
		if (smallest != j) {
			Vertex temp = vertex[smallest];
			Vertex an = vertex[j];
			an.setPos(smallest);
			vertex[smallest] = an;
			temp.setPos(j);
			vertex[j] = temp;
			minHeapify(vertex, smallest, index);
		}
	}
//Extracts min element of priority queue
	public Vertex extractMin(Vertex[] vertex, int size) {
		int lastind = size - 1;
		if (size < 1) {
			System.out.println("Heap Underflow");
			System.exit(0);
		}
		Vertex min = vertex[0];
		Vertex ano = vertex[lastind];
		ano.setPos(0);
		vertex[0] = vertex[lastind];
		min.setPos(lastind);
		vertex[lastind] = min;
		minHeapify(vertex, 0, lastind);
		return min;
	}
//Marks the vertex "up" again by setting availability to true
	private void vertexUp(String vertex) {
		// TODO Auto-generated method stub
		Vertex v = vertexMap.get(vertex);
		v.setAvailability(true);
	}
//Marks the vertex "down"
	private void vertexDown(String source) {
		// TODO Auto-generated method stub
		Vertex v = vertexMap.get(source);
		v.setAvailability(false);
	}
//Marks an edge "up"
	private void edgeUp(String source, String dest) {
		// TODO Auto-generated method stub
		Vertex src = vertexMap.get(source);
		Iterator it = src.adj.listIterator();
		while (it.hasNext()) {
			Edge edge = new Edge();
			edge = (Edge) it.next();
			if (dest.equals(edge.getDest())) {
				edge.setAvailability(true);
			}
		}
	}
//prints contents of the graph. Vertices and their outward edges are printed in alphabetical order
	public void print() {
		for (Vertex v : vertexMap.values()) {
			Comparator<Edge> cmp=new Comparator<Edge>(){
				public int compare(Edge e1, Edge e2) {
					int c = e1.getDest().compareTo(e2.getDest());
					return c;
				}
			};
			Collections.sort(v.adj, cmp);
		}
		for (Vertex ver : vertexMap.values()) {
			if (ver.availability == false) {
				System.out.println(ver.name + " " + "DOWN");
			} else {
				System.out.println(ver.name);
			}
			Iterator it = ver.adj.listIterator();
			while (it.hasNext()) {
				Edge edge = new Edge();
				edge = (Edge) it.next();
				String dest = edge.getDest();
				double cos = edge.getCost();
				if (edge.availability == false) {
					System.out.println(" " + dest + " " + cos + " " + "DOWN");
				} else {
					System.out.println(" " + dest + " " + cos);
				}
			}
		}
	}
}
