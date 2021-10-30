package GraphAlgorithms;

import java.util.ArrayList;

public class TopologicalOrder {

    static class Graph {
        private ArrayList<GraphNode> vertices;
        //To modify the graph play with the constructor
        Graph() {
            vertices = new ArrayList<>();
            //Node creation
            GraphNode shoes = new GraphNode("Shoes");
            GraphNode socks = new GraphNode("Socks");
            GraphNode pants = new GraphNode("Pants");
            GraphNode watch = new GraphNode("Watch");
            GraphNode shirt = new GraphNode("Shirt");
            GraphNode jacket = new GraphNode("Jacket");
            GraphNode tie = new GraphNode("Tie");
            GraphNode underwear = new GraphNode("Underwear");
            GraphNode belt = new GraphNode("Belt");
            //Node linking
            underwear.addArc(pants);
            underwear.addArc(shoes);
            pants.addArc(belt);
            pants.addArc(shoes);
            belt.addArc(jacket);
            shirt.addArc(belt);
            shirt.addArc(tie);
            tie.addArc(jacket);
            socks.addArc(shoes);
            // shoes.addArc(underwear); This arc makes the graph NOT acyclic!
            //Store the vertices
            vertices.add(shoes);
            vertices.add(socks);
            vertices.add(pants);
            vertices.add(watch);
            vertices.add(shirt);
            vertices.add(jacket);
            vertices.add(tie);
            vertices.add(underwear);
            vertices.add(belt);
        }

        ArrayList<GraphNode> getVertices() { return this.vertices; }

        static class GraphNode {
            private String clothing;
            private ArrayList<GraphNode> nodes;
            private String color;

            GraphNode(String clothing) {
                this.clothing = clothing;
                this.nodes = new ArrayList<>();
                this.color = "White";
            }

            void addArc(GraphNode node) { this.nodes.add(node); }

            ArrayList<GraphNode> getArcs() { return this.nodes; }

            String getColor() { return color; }

            void setColor(String color) { this.color = color; }

            @Override
            public String toString() { return clothing; }
        }

        @Override
        public String toString() { return vertices.toString(); }
    }

    static class LinkedElement {
        private Graph.GraphNode current;
        private LinkedElement next;

        LinkedElement(Graph.GraphNode current, LinkedElement next) {
            this.current = current;
            this.next = next;
        }

        LinkedElement getNext() { return next; }

        @Override
        public String toString() { return current.toString(); }
    }

    private static LinkedElement DepthFirstSearch(Graph.GraphNode currentNode, LinkedElement linkedHeader) {
        currentNode.setColor("Gray");
        for (Graph.GraphNode childNode : currentNode.getArcs()) {
            if (childNode.getColor().equals("Gray"))
                return null;
            if (childNode.getColor().equals("White")) {
                linkedHeader = DepthFirstSearch(childNode, linkedHeader);
                if (linkedHeader == null)
                    return null;
            }
        }
        linkedHeader = new LinkedElement(currentNode, linkedHeader);
        currentNode.setColor("Black");
        return linkedHeader;
    }

    private static LinkedElement TopologicalOrderAlgorithm(Graph graph) {
        LinkedElement linkedHeader = null;
        for (Graph.GraphNode graphNode : graph.getVertices()) {
            if (graphNode.getColor().equals("White")) {
                linkedHeader = DepthFirstSearch(graphNode, linkedHeader);
                if (linkedHeader == null)
                    return null;
            }
        }
        return linkedHeader;
    }

    public static void main(String[] args) {
        Graph clothes = new Graph();
        LinkedElement linkedElement = TopologicalOrderAlgorithm(clothes);
        if (linkedElement == null)
            System.out.println("The graph in NOT acyclic!");
        else {
            while (linkedElement != null) {
                System.out.println(linkedElement);
                linkedElement = linkedElement.getNext();
            }
        }
    }
}
