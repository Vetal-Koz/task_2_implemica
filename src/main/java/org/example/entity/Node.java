package org.example.entity;

import java.util.Arrays;

/**
 * Represents a node in a graph with properties such as name, number, and distances to other nodes.
 */
public class Node {
    private final int SUP = 900000;

    private String name;
    private Integer number;
    private int[] distanceToOtherNodes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public int[] getDistanceToOtherNodes() {
        return distanceToOtherNodes;
    }


    /**
     * Initializes the distance array to accommodate a given number of nodes
     * and fills it with a default "infinity" value (SUP).
     *
     * @param number the total number of nodes in the graph
     */
    public void setNumberOfNodes(int number) {
        distanceToOtherNodes = new int[number];
        Arrays.fill(distanceToOtherNodes, SUP);
    }
}
