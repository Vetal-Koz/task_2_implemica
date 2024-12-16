package org.example;


import org.example.controller.NodeController;

public class Main {
    public static void main(String[] args) {
        NodeController nodeController = new NodeController();
        nodeController.start();
    }
}