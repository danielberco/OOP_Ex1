# OOP- Ex1 : Undirectional weighted graph

This project representing an undirectional weighted graph using the interfaces `weighted_graph` and it's nodes (vertex) `node_info`.
Each edge, as the connection between two nodes contain a weight. 
In this project we wiil use some methods with the edges of the graph.
Let's start by breaking down each class  :


# WGraph_DS 
`WGraph_DS` is a class that represnets an interface of `weighted_graph` of undirectioanl weighted graph.
The nodes of the graph are implemented by inner class - `NodeData`, that implemented from the interface `node_info`.
A graph is bulit by `HashMap` data sturctue that stores the vertices and the edges between each connected nodes.
In addition, there is two `Integers` that counts the number of edges and internal count changes of the graph `MC`.

**WGraph_DS Functions :**

Function   | Description
------------ | -------------
`node_info getNode` | Returns a node by a given key number
`boolean hasEdge` | returns true only if two given node keys are connected between them.
`double getEdge` | returns the weight if the edge between two given node's id.
`void addNode` | adding a new node to the graph by a given key 
`void connect` | connect an edge between two given nodes key , with an edge that his weight >=0
`Collection<node_info> getV` | returns a collection of all the nodes in the graph
`Collection<node_info> getV(int node_id)` | returns a collection of nodes by given node_id
`node_info removeNode` | delete the node with the given id from the graph, and returns the data of the removed node
`void removeEdge` | delete the edge from the graph between two given nodes id 
`int nodeSize` | return the number of verices in the graph
`int edgeSize` | return the number of the edges in the graph
`int getMC` | return the number of changes in the inner state of the graph
`String toString` | create a string of the graph  
`boolean equalds` | returns true if two given graphs are equals 
`int hashCode` | returns value of the graph key value of each node int the graph 

## NodeData :
This class is private and inside WGraph_DS, and implemented by `node_info`.

**NodeData Functions :**

Function   | Description
------------ | -------------
`int getKey` | Returns the key id that belong to the node
`String getInfo` | returns the meta data of the specific node
`void setInfo` | changing the remark of the node
`double getTag` | return the tag of the node
`void setTag` | changing the tag of the node
`String toString` | create a string of the node key id 
`boolean equalds` | returns true if two given nodes are equals 
`int hashCode` | returns value of the node key 

## WGraph_Algo 
This class is implemented from the interface `weighted_graph_algorithms` and represents 
the following methods :

Function   | Description
------------ | -------------
`void init` | initializing the graph 
`weighted_graph getGraph` | return the specific graph 
`weighted_graph copy` | return a dupllicated graph 
`boolean isConnected` | checking if there is a path between every node in the graph
`double shortestPathDist` | returns the lenght of the shortest path between two given numbers
`List <node_info> shortestPath` | returns a list that represents the shortest path between two given nodes id
`boolean save ` | saving the weighted graph to the given file name
`boolean load` | loading the graph
`void ___` | an algorithem that finding the shortest path


**Algorithms that used in the project :**
* The method `isConnected` is made by bfs algorithm , that tags every node that is connected on the graph, with a use of `Queue <node_info>` and a `HashSet<node_info>` .

* The method `shortestPath` is made by using with variations of Dijkstra’s algorithm that with a given `node_info` , it's generates a `List` that contains the shortest path in a graph according
to the node. In each step at the algorithm , we find a node that is not included yet in the set and has a minimum distance from the given source in the graph. It used by `Queue<node_info>` that stores
the shortest path and a `List<node_info>` of the visited noeds.

**Tests**
In addition, there is tests that created to test all the method that used in `WGraph_DS` and `WGraph_Algo` , it's located in the test folders and 
they are `WGraph_DSTest` and `WGraph_AlgoTest`.


![Alt Text](https://static.packt-cdn.com/products/9781789611151/graphics/314a3e62-7180-4ff3-bb90-f5c4a4599540.png)



**Reference and resources of information to the project :**
* Weighted graph - https://en.wikipedia.org/wiki/Graph_(discrete_mathematics)#Weighted_graph

* BFS algorithm - https://www.youtube.com/watch?v=oDqjPvD54Ss&t=358s&ab_channel=WilliamFiset

* Dijkstra algorithm - https://www.youtube.com/watch?v=pVfj6mxhdMw&ab_channel=ComputerScience

* Shortest path distance - https://en.wikipedia.org/wiki/Shortest_path_problem














