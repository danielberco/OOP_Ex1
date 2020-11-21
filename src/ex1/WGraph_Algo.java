package ex1;

import java.util.Collection;
import java.util.List;

public class WGraph_Algo implements weighted_graph_algorithms {

    private weighted_graph graphW = new WGraph_DS();

    /**
     * Init the graph on which this set of algorithms operates on.
     *
     * @param g
     */
    @Override
    public void init(weighted_graph g) {
        graphW=g;
    }

    /**
     * Return the underlying graph of which this class works.
     *
     * @return
     */
    @Override
    public weighted_graph getGraph() {
        return graphW;
    }

    /**
     * Compute a deep copy of this weighted graph.
     *
     * @return
     */
    @Override
    public weighted_graph copy() {
        weighted_graph clone = new WGraph_DS();
        Collection<node_info> vertics = graphW.getV();

        for (node_info copy : vertics) {
            node_info cloneNode = new WGraph_DS.NodeData(copy.getKey(), copy.getInfo(), copy.getTag());
            clone.addNode(cloneNode.getKey());
        }

        for (node_info combine : vertics) {
            if (!graphW.getV(combine.getKey()).isEmpty() && graphW.getV(combine.getKey()) != null) {
                for (node_info i : graphW.getV(combine.getKey()))
                    clone.connect(combine.getKey(), i.getKey(), graphW.getEdge(combine.getKey(), i.getKey()));
            }
        }
        return clone;
    }

    /**
     * Returns true if and only if (iff) there is a valid path from EVREY node to each
     * other node. NOTE: assume ubdirectional graph.
     *
     * @return
     */
    @Override
    public boolean isConnected() {
        return false;
    }

    /**
     * returns the length of the shortest path between src to dest
     * Note: if no such path --> returns -1
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        return 0;
    }

    /**
     * returns the the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     * see: https://en.wikipedia.org/wiki/Shortest_path_problem
     * Note if no such path --> returns null;
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public List<node_info> shortestPath(int src, int dest) {
        return null;
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
        return false;
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
        return false;
    }


}
