package ex1;

import java.util.Collection;
import java.util.HashMap;

public class WGraph_DS implements  weighted_graph {

    private final HashMap<node_info,HashMap<node_info,Double>> vertexEdge =new HashMap<node_info,HashMap<node_info, Double>>();
    private int edge;
    private int mc;

    /**
     * return the node_data by the node_id,
     *
     * @param key - the node_id
     * @return the node_data by the node_id, null if none.
     */
    @Override
    public node_info getNode(int key) {
        if (!vertexEdge.keySet().isEmpty()) {
            for (node_info node : vertexEdge.keySet()) {
                if (node.getKey() == key)
                    return node;
            }
        }
        return null;
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
        if ((getNode(node1)!= null && getNode(node2)!=null) && (node1!=node2)) {
            if (!vertexEdge.get(getNode(node2)).isEmpty() && getV(node1).contains(getNode(node2)) && getV(node2).contains(getNode(node1)))
                return true;
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
        if (hasEdge(node1,node2))
            return vertexEdge.get(getNode(node1)).get(getNode(node2));
        return -1;
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
        if ( vertexEdge !=null &&!vertexEdge.containsKey(getNode(key))) {
            node_info add= new NodeData(key,"",0);
            vertexEdge.put(add,new HashMap<node_info,Double>());
            mc++;
        }
    }



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
        if (getNode(node1)==null || getNode(node2)==null)
            return;
        if (!getV().contains(getNode(node1)) || !getV().contains(getNode(node2)))
            return;
        if (node1==node2)
            return;

        if (!hasEdge(node1,node2)) {
            node_info n1 = getNode(node1);
            node_info n2 =getNode(node2);
            vertexEdge.get(getNode(node1)).put(n2,w);
            vertexEdge.get(getNode(node2)).put(n1,w);
            edge++;
            mc++;
        }
        node_info n1 = getNode(node1);
        node_info n2 =getNode(node2);
        vertexEdge.get(getNode(node1)).put(n2,w);
        vertexEdge.get(getNode(node2)).put(n1,w);

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
        return vertexEdge.keySet();
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
        if (getNode(key)==null)
            return null;

        node_info remove = new NodeData(key, getNode(key).getInfo(), getNode(key).getTag());

        if (vertexEdge.keySet().contains(getNode(key))) {
            if (vertexEdge.get(getNode(key))!=null && !vertexEdge.get(getNode(key)).isEmpty()) {
                for (node_info n : getV(key)) {
                    getV(n.getKey()).remove(getNode(key));
                    edge--;
                }
                vertexEdge.keySet().remove(getNode(key));
                mc++;
            }
        }
        vertexEdge.keySet().remove(getNode(key));
        mc++;
        return remove;
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
        if (!this.vertexEdge.containsKey(getNode(node1)) || !this.vertexEdge.containsKey(getNode(node2)))
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
     * Note: this method should run in O(1) time.
     *
     * @return
     */
    @Override
    public int nodeSize() {
        return vertexEdge.keySet().size();
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
    static class NodeData implements node_info {
        private final int key;
        private String info;
        private double tag;
        private int v;

        public NodeData() {
            v=0;
            key=v++;
            info="";
            tag=0.0;
        }



        public NodeData(int k,String s,double n) {
            key=k;
            info=s;
            tag=n;
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

