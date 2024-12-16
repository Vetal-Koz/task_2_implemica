package org.example.service;

import org.example.db.NodeDb;
import org.example.entity.Node;

import java.util.List;

/**
 * Service class for managing nodes and their relationships in the graph.
 * Provides a higher-level API to interact with the underlying NodeDb class.
 */
public class NodeService {
    private final NodeDb nodeDb = new NodeDb();


    /**
     * Adds a new node to the graph by delegating the operation to NodeDb.
     *
     * @param node the node to create and add to the database
     */
    public void create(Node node) {
        nodeDb.create(node);
    }

    public void setNodeSize(int size) {
        nodeDb.setNodeSize(size);
    }


    /**
     * Retrieves all nodes currently stored in the graph.
     *
     * @return a list of all nodes
     */
    public List<Node> allNodes() {
        return nodeDb.allNodes();
    }


    /**
     * Retrieves the adjacency matrix representing the distances (weights) between nodes.
     *
     * @return the adjacency matrix
     */
    public int[][] getWays() {
        return nodeDb.getWays();
    }
}
