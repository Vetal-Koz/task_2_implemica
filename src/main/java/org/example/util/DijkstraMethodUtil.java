package org.example.util;

import java.util.Arrays;


/**
 * A utility class for implementing Dijkstra's algorithm to find the shortest paths
 * from a starting point to all other nodes in a graph represented by an adjacency matrix.
 */
public final class DijkstraMethodUtil {
    private final int SUP = 30000000;

    /**
     * Finds the shortest distances from a starting node to all other nodes in a graph.
     *
     * @param startIndex the index of the starting node
     * @param ways       the adjacency matrix representing the graph, where ways[i][j]
     *                   is the weight of the edge from node i to node j. A value of 0
     *                   indicates no direct connection between nodes i and j.
     * @return an array of shortest distances from the starting node to each node
     */
    public Integer[] minimalWays(int startIndex, int[][] ways) {
        Integer[] distances = new Integer[ways.length];
        boolean[] visited = new boolean[ways.length];
        Arrays.fill(distances, SUP);
        distances[startIndex] = 0;
        for (int count = 0; count < distances.length; count++) {
            int u = findMinimalWayIndex(distances, visited);
            visited[u] = true;
            for (int v = 0; v < distances.length; v++) {
                if (!visited[v] && ways[u][v] != 0
                        && distances[u] + ways[u][v] < distances[v]) {
                    distances[v] = distances[u] + ways[u][v];
                }
            }
        }
        return distances;
    }

    /**
     * Finds the index of the node with the smallest distance that has not been visited.
     *
     * @param distances the array of known shortest distances
     * @param visited   the array tracking whether a node has been visited
     * @return the index of the node with the smallest distance
     */
    private int findMinimalWayIndex(Integer[] distances, boolean[] visited) {
        int min = 9000000;
        int minWayIndex = 100;
        for (int i = 0; i < distances.length; i++) {
            if (!visited[i] && min > distances[i]) {
                min = distances[i];
                minWayIndex = i;
            }
        }
        return minWayIndex;
    }
}
