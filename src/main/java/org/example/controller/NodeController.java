package org.example.controller;

import org.example.entity.Node;
import org.example.service.NodeService;
import org.example.util.DijkstraMethodUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Controller class to manage the interaction between the application logic and input/output.
 * Handles reading input, executing graph-related operations (e.g., finding shortest paths), and writing output.
 */
public class NodeController {
    private final File inputFile = new File("input.txt");
    private final File outputFile = new File("output.txt");
    private NodeService nodeService = new NodeService();
    private final DijkstraMethodUtil dijkstraMethodUtil = new DijkstraMethodUtil();

    /**
     * Entry point for the controller, processes multiple tests from the input file.
     */
    public void start() {
        try (
                FileReader fileReader = new FileReader(inputFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
        ) {
            int numberOfTest = Integer.valueOf(bufferedReader.readLine());
            for (int i = 0; i < numberOfTest; i++) {
                runTest(bufferedReader);
            }
        } catch (IOException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    /**
     * Runs a single test case by parsing input, processing nodes, and calculating shortest paths.
     *
     * @param bufferedReader reader for the input file
     * @throws IOException in case of input/output errors
     */
    public void runTest(BufferedReader bufferedReader) throws IOException {
        nodeService = new NodeService();
        String nodeSize = "";
        nodeSize = bufferedReader.readLine();
        nodeService.setNodeSize(Integer.parseInt(nodeSize));
        fillNodes(Integer.parseInt(nodeSize), bufferedReader);
        int[][] ways = nodeService.getWays();
        List<Node> allNodes = nodeService.allNodes();
        List<List<String>> nodesForSearchWay = listsNodeForSearchWay(bufferedReader);
        for (List<String> fromTo : nodesForSearchWay) {
            String from = fromTo.get(0);
            String to = fromTo.get(1);
            // Find the start and end nodes by their names.
            Node toNode = allNodes.stream().filter(node -> node.getName().equals(to)).findFirst().get();
            Node fromNode = allNodes.stream().filter(node -> node.getName().equals(from)).findFirst().get();

            // Use Dijkstra's algorithm to calculate the shortest distance.
            Integer[] minimalWays = dijkstraMethodUtil.minimalWays(fromNode.getNumber() - 1, ways);
            writeInFile(String.valueOf(minimalWays[toNode.getNumber() - 1]), outputFile);
        }
        bufferedReader.readLine();
    }

    /**
     * Fills the graph with nodes and their distances to other nodes.
     *
     * @param nodeSize the number of nodes in the graph
     * @param reader   reader for the input file
     * @throws IOException in case of input errors
     */
    public void fillNodes(int nodeSize, BufferedReader reader) throws IOException {
        for (int i = 0; i < nodeSize; i++) {
            Node node = new Node();
            String nodeName = "";
            nodeName = reader.readLine();
            node.setName(nodeName);
            int numberOfNeighbours = Integer.valueOf(reader.readLine());
            node.setNumberOfNodes(nodeSize);
            fillDistanceBetweenNodes(numberOfNeighbours, node, reader);
            nodeService.create(node);


        }

    }


    /**
     * Writes a given text to the output file, appending to existing content.
     *
     * @param text the text to write
     * @param file the output file
     */
    public void writeInFile(String text, File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try (FileWriter fileWriter = new FileWriter(file, true)) {
                fileWriter.write(text + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    /**
     * Fills the distances from a node to its neighbors.
     *
     * @param numberOfNeighbours the number of neighbors
     * @param node               the node being processed
     * @param reader             reader for the input file
     * @throws IOException in case of input errors
     */
    public void fillDistanceBetweenNodes(int numberOfNeighbours, Node node, BufferedReader reader) throws IOException {
        for (int i = 0; i < numberOfNeighbours; i++) {
            String distances = reader.readLine();
            String[] text = distances.split(" ");
            String nodeNumberString = text[0];
            String distanceString = text[1];
            int number = Integer.parseInt(nodeNumberString);
            int distance = Integer.parseInt(distanceString);
            node.getDistanceToOtherNodes()[number - 1] = distance;
        }
    }


    /**
     * Parses the list of node pairs for which the shortest paths should be calculated.
     *
     * @param reader reader for the input file
     * @return a list of node pairs (from and to) for pathfinding
     * @throws IOException in case of input errors
     */
    public List<List<String>> listsNodeForSearchWay(BufferedReader reader) throws IOException {
        String numberOfWaysString = reader.readLine();
        List<List<String>> lists = new ArrayList<>();
        int numberOfWays = Integer.parseInt(numberOfWaysString);
        for (int i = 0; i < numberOfWays; i++) {
            String[] fromToWays = reader.readLine().split(" ");
            lists.add(List.of(fromToWays));
        }
        return lists;
    }
}
