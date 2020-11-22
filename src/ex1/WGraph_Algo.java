package ex1.src;

import java.io.*;
import java.util.*;

public class WGraph_Algo implements  weighted_graph_algorithms, java.io.Serializable{

    private weighted_graph graphW = new WGraph_DS();

    /**
     * Init the graph on which this set of algorithms operates on.
     */
    @Override
    public void init(weighted_graph g) {
        graphW = g;
    }

    /**
     * Return the underlying graph of which this class works.
     */
    @Override
    public weighted_graph getGraph() {
        return graphW;
    }

    /**
     * Compute a deep copy of this weighted graph.
     * Time Complexibity - O(K^2)
     */
    @Override
    public weighted_graph copy() {
        weighted_graph clone = new WGraph_DS();
        Collection<node_info> vertics = graphW.getV();

        for (node_info copy : vertics) {
            node_info cloneNode = new WGraph_DS.NodeData(copy.getKey(),copy.getInfo(),copy.getTag());
            clone.addNode(cloneNode.getKey());
        }
        for (node_info combine : vertics) {
            if (!graphW.getV(combine.getKey()).isEmpty() && graphW.getV(combine.getKey()) != null) {
                for (node_info i : graphW.getV(combine.getKey()))
                    clone.connect(combine.getKey(),i.getKey(),graphW.getEdge(combine.getKey(),i.getKey())); //connecting the nodes values to the new graph nodes
            }
        }
        return clone;
    }

    /**
     * Returns true if and only if (iff) there is a valid path from EVREY node to each
     * other node. NOTE: assume ubdirectional graph.
     * by using implements from bfs algorithm, storing the graph nodes in a HashSet and then sorting between each
     * connected node (an edge) and storing them in a Queue as a LinkedList.
     *
     */
    @Override
    public boolean isConnected() {
        if (graphW.nodeSize() == 0 || graphW.nodeSize() == 1)
            return true;
        node_info next = graphW.getV().iterator().next(); //getting every node from the graph
        Queue<node_info> qNode = new LinkedList<>();
        HashSet<node_info> sNode = new HashSet<>();
        qNode.add(next);
        sNode.add(next);
        while (!qNode.isEmpty()) {
            next = qNode.poll();
            for (node_info n: graphW.getV(next.getKey())) {
                if (!sNode.contains(n)) { //checking if there is a node that connected
                    sNode.add(n);
                    qNode.add(n);
                }
                if (sNode.size() == graphW.nodeSize()) //if the set of the connected nodes are at the same size as the graph, it connected
                    return true;
            }
            if (sNode.size() == graphW.nodeSize())
                return true;
        }
        return sNode.size() == graphW.nodeSize();
    }

    /**
     * returns the length of the shortest path between src to dest
     * Note: if no such path --> returns -1
     * @param src  - start node
     * @param dest - end (target) node
     * Using dijkAlgo
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        if (src == dest)
            return 0;
        node_info srcNode = graphW.getNode(src);
        node_info destNode = graphW.getNode(dest);
        if (srcNode == null || destNode == null)
            return -1;
        dijkAlgo(graphW.getNode(src)); //start looking for path with dijka
        if (graphW.getNode(dest).getTag() == Integer.MAX_VALUE) //if there is not a short path
            return -1;
        return graphW.getNode(dest).getTag();
    }

    /**
     * returns the the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     * see: https://en.wikipedia.org/wiki/Shortest_path_problem
     * Note if no such path --> returns null;
     * This method used by dijkAlgo
     */
    @Override
    public List<node_info> shortestPath(int src, int dest) {
        if (graphW.getNode(src) == null || graphW.getNode(dest) == null)
            return null;
        List <node_info> shortList = new LinkedList<>();
        int _dest =dest; // avoiding Exception
        shortList.add(graphW.getNode(dest));
        dijkAlgo(graphW.getNode(src)); // looking for the shortest path
        if (src == dest)
            return shortList;
        if (graphW.getNode(dest).getTag() == Integer.MAX_VALUE)
            return null;
        Iterator<node_info> iter = graphW.getV(_dest).iterator();
        while (iter.hasNext()) {
            node_info nextNode = iter.next();
            double nodeW = graphW.getEdge(nextNode.getKey(),_dest)+nextNode.getTag(); // getting the node weight
            if (nodeW == graphW.getNode(_dest).getTag()) { //if the the nodes path have the same weight
                _dest = nextNode.getKey();
                iter = graphW.getV(_dest).iterator();
                shortList.add(nextNode); //adding the nodes that making the shortest path to the list
            }
            if (nextNode.getKey() == src) // if we got to the first node
                break;
        }
        Collections.reverse(shortList);
        return shortList;
    }

    /**
     * Saves this weighted (undirected) graph to the given
     * file name
     *
     * @param file - the file name (may include a relative path).
     * @return true - iff the file was successfully saved
     */
    @Override
    public boolean save(String file) {
        try {
            FileOutputStream save = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(save);
            oos.writeObject(this.graphW);
            oos.close();
            save.close();
            return true;
        }
        catch (IOException exception) {
            exception.printStackTrace();
            System.out.println("Something went wrong : IOException is caught");
            return false;
        }
    }

    /**
     * This method load a graph to this graph algorithm.
     * if the file was successfully loaded - the underlying graph
     * of this class will be changed (to the loaded one), in case the
     * graph was not loaded the original graph should remain "as is".
     *
     * @param file - file name
     * @return true - iff the graph was successfully loaded.
     */
    @Override
    public boolean load(String file) {
        try {
            FileInputStream load = new FileInputStream(file);
            ObjectInputStream oop = new ObjectInputStream(load);
            weighted_graph loadG = (weighted_graph) oop.readObject();
            load.close();
            oop.close();
            init(this.graphW);
        }

        catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
            System.out.println("Something went wrong : IOException");
            return false;
        }
        return true;
    }

    /**
     * This algorithm is inspired from Dijkstra algorithm of finding the shortest path in weighted graph.
     * This method is used by Queue and List both as a LinkedList of the nodes.
     * This algorithm works by sorting the nodes by their tags so we could notice each path every node is doing by it's tag.
     * After each node tag is set beside the given node , we can start calculating each path that start from this node tag.
     */
    public void dijkAlgo (node_info n) {
        Queue <node_info> qNode = new LinkedList<>();
        List <node_info> lNode = new LinkedList<>();
        for (node_info set : graphW.getV())
            set.setTag(Integer.MAX_VALUE); //setting the tag in each node in the graph to max value
        n.setTag(0.0);
        qNode.add(n);
        lNode.add(n);
        while (!qNode.isEmpty()) {
            node_info firstNode = qNode.poll();
            Iterator <node_info> iter = graphW.getV(firstNode.getKey()).iterator(); //setting Iterator
            while (iter.hasNext()) {
                node_info nextNode = iter.next(); // getting the next node each time
                double nodeTag = firstNode.getTag();
                double nodeEdge = graphW.getEdge(firstNode.getKey(),nextNode.getKey()); // edges between each nodes that connected
                if (nodeTag + nodeEdge < nextNode.getTag()) {
                    nextNode.setTag(nodeTag+nodeEdge);
                    if(!lNode.contains(nextNode)) {
                        qNode.add(nextNode);
                        lNode.add(nextNode);
                    }
                }
            }
        }
    }//dijkAlgo
}
