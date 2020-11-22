package ex1.src;

import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

/**
 * This class represents an interface of undirectional weighted graph by HashMap
 * It contains methods of getting node, adding or removing one
 * Getting the edge weight between two nodes and removing it
 */
public class WGraph_DS implements weighted_graph, java.io.Serializable {

    private final HashMap<node_info,HashMap<node_info,Double>> vertexEdge = new HashMap<>();
    private int edge;
    private int mc;

    /**
     * return the node_data by the node_id,
     */
    @Override
    public node_info getNode(int key) {
        if (!vertexEdge.keySet().isEmpty()) {
            for (node_info getN: vertexEdge.keySet()) {
                if (getN.getKey() == key)
                    return getN;
            }
        }
        return null;
    }

    /**
     * return true iff (if and only if) there is an edge between node1 and node2
     * Time complexity: O(1)
     */
    @Override
    public boolean hasEdge(int node1, int node2) {
        if ((getNode(node1)!=null && getNode(node2)!=null) && (node1!=node2)) {
            if (!vertexEdge.get(getNode(node2)).isEmpty() && getV(node1).contains(getNode(node2)) && getV(node2).contains(getNode(node1)))
                return true;
        }
        return false;
    }

    /**
     * return the weight if the edge (node1, node1). In case
     * there is no such edge - should return -1
     * Time complexity : O(1).
     */
    @Override
    public double getEdge(int node1, int node2) {
        if (hasEdge(node1,node2))
            return vertexEdge.get(getNode(node1)).get(getNode(node2));
        return -1;
    }

    /**
     * This method add a new node to the graph with the given key.
     * if there is already a node with such a key -> no action should be performed.
     * Time complexity : O(1)
     */
    @Override
    public void addNode(int key) {
        if (vertexEdge != null && !vertexEdge.containsKey(getNode(key))) {
            node_info add = new NodeData (key,"",0);
            vertexEdge.put(add,new HashMap<node_info,Double>());
        }
    }


    /**
     * This method connect an edge between node1 and node2, with an edge with weight >=0.
     * if the edge node1-node2 already exists - the method simply updates the weight of the edge.
     * Time complexity : O(1)
     */
    @Override
    public void connect(int node1, int node2, double w) {
        if (getNode(node1) == null || getNode(node2) == null)
            return;
        if (!getV().contains(getNode(node1)) || !getV().contains(getNode(node2)))
            return;
        if (node1 == node2)
            return;
        if (!hasEdge(node1,node2) && w>=0) {
            node_info n1 = getNode(node1);
            node_info n2 = getNode(node2);
            vertexEdge.get(getNode(node1)).put(n2,w);
            vertexEdge.get(getNode(node2)).put(n1,w);
            edge++;
            mc++;
        }
        node_info n1 = getNode(node1);
        node_info n2 = getNode(node2);
        vertexEdge.get(getNode(node1)).put(n2,w);
        vertexEdge.get(getNode(node2)).put(n1,w);
    }

    /**
     * This method return a pointer (shallow copy) for a
     * Collection representing all the nodes in the graph.
     * Time complexity : O(1)
     */
    @Override
    public Collection<node_info> getV() {
        return vertexEdge.keySet();
    }

    /**
     * This method returns a Collection containing all the
     * nodes connected to node_id
     * Time complexity -O(K)
     */
    @Override
    public Collection<node_info> getV(int node_id) {
        if (vertexEdge.keySet().contains(getNode(node_id)))
            return vertexEdge.get(getNode(node_id)).keySet();
        return null;
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
        if (getNode(key) == null)
            return null;
        node_info remove = new NodeData (key,getNode(key).getInfo(),getNode(key).getTag());
        if (vertexEdge.keySet().contains(getNode(key))) {
            if (vertexEdge.get(getNode(key)) != null && !vertexEdge.get(getNode(key)).isEmpty()) {
                for (node_info i : getV(key)) {
                    getV(i.getKey()).remove(getNode(key)); //removing the node
                    edge--;
                }
                vertexEdge.keySet().remove(getNode(key)); //removing the node
                mc++;
            }
        }
        vertexEdge.keySet().remove(getNode(key));
        mc++;
        return remove;
    }

    /**
     * Delete the edge from the graph
     * Time complexity - O(1)
     */
    @Override
    public void removeEdge(int node1, int node2) {
        if (!vertexEdge.containsKey(getNode(node1)) || !vertexEdge.containsKey(getNode(node2)))
            return;
        node_info n1 = getNode(node1);
        node_info n2 = getNode(node2);
        if (!vertexEdge.get(n1).isEmpty() && !vertexEdge.get(n2).isEmpty()) {
            vertexEdge.get(n1).remove(n2);
            vertexEdge.get(n2).remove(n1);
            mc++;
            edge--;
        }
    }

    /**
     * return the number of vertices (nodes) in the graph.
     * Time complexity - O(1) time.
     */
    @Override
    public int nodeSize() {
        return vertexEdge.keySet().size();
    }

    /**
     * return the number of edges (undirectional graph).
     * Time complexity - O(1) time.
     */
    @Override
    public int edgeSize() {
        return edge;
    }

    /**
     * return the Mode Count - for testing changes in the graph.
     * Any change in the inner state of the graph should cause an increment in the ModeCount
     */
    @Override
    public int getMC() {
        return mc;
    }

    @Override
    public String toString () {
        Set<node_info> nodeSet = vertexEdge.keySet();
        String str ="";
        int count =0;
        for (node_info keySet:nodeSet) {
            if (count ==0)
                str = str + keySet + "~~~~~~" + vertexEdge.get(keySet).toString();
            else
                str = str + keySet + "~~~~~~" + vertexEdge.get(keySet).toString();
            count++;
        }
        return str;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o)
            return true;
        if ( o == null || getClass() != o.getClass())
            return false;
        WGraph_DS wGraph_ds = (WGraph_DS) o;
        return this.toString().equals(wGraph_ds.toString());
    }

    @Override
    public int hashCode () {
        return Objects.hash(vertexEdge,edge,mc);
    }

    /**
     * This private class represents the interface of node (vertex) in weighted graph
     * It contains the node key,tag and info
     */
     static  class  NodeData implements node_info, java.io.Serializable {
        private final int key;
        private String info;
        private double tag;

        public NodeData () {
            key =hashCode();
            info="";
            tag=0.0;
        }

        public  NodeData (int k, String s, double n) {
            key=k;
            info=s;
            tag=n;
        }

        /**
         * Return the key (id) associated with this node.
         * Note: each node_data should have a unique key.
         */
        @Override
        public int getKey() {
            return key;
        }

        /**
         * return the remark (meta data) associated with this node.
         */
        @Override
        public String getInfo() {
            return info;
        }

        /**
         * Allows changing the remark (meta data) associated with this node.
         */
        @Override
        public void setInfo(String s) {
            info=s;
        }

        /**
         * Temporal data (aka distance, color, or state)n
         */
        @Override
        public double getTag() {
            return tag;
        }

        /**
         * Allow setting the "tag" value for temporal marking an node
         */
        @Override
        public void setTag(double t) {
            tag=t;
        }

        /**
         * The following methods are for the class to compile without exceptions.
         */

        @Override
        public  String toString () {
            return "NodeData {"+"key=" + key +"}";
        }

        @Override
        public boolean equals (Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            NodeData node = (NodeData) o;
            return this.toString().equals(node.toString());
        }

        @Override
        public int hashCode () {
            return Objects.hash(key,info);
        }
    }

}
