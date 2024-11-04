package ca.uwaterloo.cs846;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CallGraph {

    public static class Node {
        /** The full descriptor (bytecode signature) of the method, e.g., ca/uwaterloo/cs846/Subject#foo(II)I */
        private String descriptor;
        /** The list of nodes that this node calls */
        private Set<Node> edgesTo = new HashSet<>();

        public Node(String descriptor) {
            this.descriptor = descriptor;
        }

        public String getDescriptor() {
            return descriptor;
        }

        public Set<Node> getEdgesTo() {
            return Collections.unmodifiableSet(edgesTo);
        }
    }

    private List<Node> nodes = new ArrayList<>();
    private Map<String, Integer> nodeIndex = new HashMap<>();

    public void addCall(String callerDescriptor, String calleeDescriptor) {
        Node caller = getOrCreateNode(callerDescriptor);
        Node callee = getOrCreateNode(calleeDescriptor);
        caller.edgesTo.add(callee);
    }

    private Node getOrCreateNode(String descriptor) {
        if (nodeIndex.containsKey(descriptor)) {
            return nodes.get(nodeIndex.get(descriptor));
        } else {
            Node node = new Node(descriptor);
            nodes.add(node);
            nodeIndex.put(descriptor, nodes.size() - 1);
            return node;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node node : nodes) {
            // skip nodes that have no outgoing edges
            if (node.getEdgesTo().isEmpty()) {
                continue;
            }

            sb.append(node.getDescriptor()).append(" ->\n");
            for (Node edge : node.getEdgesTo()) {
                sb.append("  ").append(edge.getDescriptor()).append("\n");
            }
        }
        return sb.toString();
    }
}
