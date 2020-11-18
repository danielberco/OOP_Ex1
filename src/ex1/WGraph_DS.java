package ex1;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class WGraph_DS implements  weighted_graph {

    private final HashMap<Integer,node_info> nodes;
    private final HashMap<Integer,HashMap<Integer,Double>> edges;

    private int edge;
    private int mc;


    public WGraph_DS() {
        nodes = new HashMap<>();
        edge=0;
        mc=0;
        edges = new HashMap<>();
    }



    /**
     * return the node_data by the node_id,
     *
     * @param key - the node_id
     * @return the node_data by the node_id, null if none.
     */
    @Override
    public node_info getNode(int key) {
        if (!nodes.containsKey(key))
            return null;
        return nodes.get(key);
    }//v

    /**
     * return true iff (if and only if) there is an edge between node1 and node2
     * Note: this method should run in O(1) time.
     *
     * @param node1
     * @param node2
     * @return
     */
    @Override
    public boolean hasEdge(int node1, int node2) {
        if (edges.containsKey(node1) && edges.get(node2).containsKey(getNode(node1))) {
          return edges.containsKey(node2)&&edges.get(node2).containsKey(getNode(node1));
        }
        return false;
    }

    /**
     * return the weight if the edge (node1, node1). In case
     * there is no such edge - should return -1
     * Note: this method should run in O(1) time.
     *
     * @param node1
     * @param node2
     * @return
     */
    @Override
    public double getEdge(int node1, int node2) {
        if (!nodes.containsKey(node1)||!nodes.containsKey(node2))
            return -1;
        if (!hasEdge(node1,node2))
            return -1;
        else
            return edges.get(node1).get(node2);
       }//v


    /**
     * add a new node to the graph with the given key.
     * Note: this method should run in O(1) time.
     * Note2: if there is already a node with such a key -> no action should be performed.
     *
     * @param key
     */
    @Override
    public void addNode(int key) {
        if (!nodes.containsKey(key)) {
            node_info ans = new NodeData(key);
          nodes.put(key, ans);
            edges.put(key,new HashMap<>());
          mc++;
            }
        }//v



    /**
     * Connect an edge between node1 and node2, with an edge with weight >=0.
     * Note: this method should run in O(1) time.
     * Note2: if the edge node1-node2 already exists - the method simply updates the weight of the edge.
     *
     * @param node1
     * @param node2
     * @param w
     */
    @Override
    public void connect(int node1, int node2, double w) {




    }

    /**
     * This method return a pointer (shallow copy) for a
     * Collection representing all the nodes in the graph.
     * Note: this method should run in O(1) tim
     *
     * @return Collection<node_data>
     */
    @Override
    public Collection<node_info> getV() {
        return new LinkedList<>(nodes.values());
    }//v

    /**
     * This method returns a Collection containing all the
     * nodes connected to node_id
     * Note: this method can run in O(k) time, k - being the degree of node_id.
     *
     * @param node_id
     * @return Collection<node_data>
     */
    @Override
    public Collection<node_info> getV(int node_id) {
        LinkedList<node_info> nodeList = new LinkedList<>();

        if(nodes.containsKey(node_id)) {
           for (node_info neighbors : getV(node_id))
               nodeList.add(nodes.get(neighbors));
        }
        return nodeList;
    }

    /**
     * Delete the node (with the given ID) from the graph -
     * and removes all edges which starts or ends at this node.
     * This method should run in O(n), |V|=n, as all the edges should be removed.
     *
     * @param key
     * @return the data of the removed node (null if none).
     */
    @Override
    public node_info removeNode(int key) {
        Iterator<Integer> iter = edges.get(key).keySet().iterator();
        while (iter.hasNext()) {
            edges.get(iter.next()).remove(key);
            edge--;
        }
        edges.get(key).clear();
        nodes.remove(key);
        return nodes.get(key);
    }//v

    /**
     * Delete the edge from the graph,
     * Note: this method should run in O(1) time.
     *
     * @param node1
     * @param node2
     */
    @Override
    public void removeEdge(int node1, int node2) {
        if(hasEdge(node1,node2)) {
            edges.get(node1).remove(node2);
            edges.get(node2).remove(node1);
            edge--;
            mc++;
        }
    }//v

    /**
     * return the number of vertices (nodes) in the graph.
     * Note: this method should run in O(1) time.
     *
     * @return
     */
    @Override
    public int nodeSize() {
        return nodes.size();
    }//v

    /**
     * return the number of edges (undirectional graph).
     * Note: this method should run in O(1) time.
     *
     * @return
     */
    @Override
    public int edgeSize() {
        return edge;
    }//v

    /**
     * return the Mode Count - for testing changes in the graph.
     * Any change in the inner state of the graph should cause an increment in the ModeCount
     *
     * @return
     */
    @Override
    public int getMC() {
        return mc;
    }//v


    //node_info implements********************************************
    private class NodeData implements node_info {
        private final int key;
        private String info;
        private double tag;

        public NodeData(int k) {
            key=k;
            tag=-1;
            info="";
        }


        /**
         * Return the key (id) associated with this node.
         * Note: each node_data should have a unique key.
         *
         * @return
         */
        @Override
        public int getKey() {
            return key;
        }

        /**
         * return the remark (meta data) associated with this node.
         *
         * @return
         */
        @Override
        public String getInfo() {
            return info;
        }

        /**
         * Allows changing the remark (meta data) associated with this node.
         *
         * @param s
         */
        @Override
        public void setInfo(String s) {
            info=s;
        }

        /**
         * Temporal data (aka distance, color, or state)
         * which can be used be algorithms
         *
         * @return
         */
        @Override
        public double getTag() {
            return tag;
        }

        /**
         * Allow setting the "tag" value for temporal marking an node - common
         * practice for marking by algorithms.
         *
         * @param t - the new value of the tag
         */
        @Override
        public void setTag(double t) {
            tag=t;
        }

        public String toString () {
            return "NodeData {"+"key=" +key+"}";
        }
    }

}

///////////////////////////////////////////////////

