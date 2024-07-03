import java.util.*;

class Challenge_3 {
    static class Edge {
        int node;
        int weight;

        Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    static class Graph {
        private final Map<Integer, List<Edge>> edges = new HashMap<>();

        public void addEdge(int u, int v, int weight) {
            edges.putIfAbsent(u, new ArrayList<>());
            edges.putIfAbsent(v, new ArrayList<>());
            edges.get(u).add(new Edge(v, weight));
            edges.get(v).add(new Edge(u, weight));
        }

        public int dijkstra(int start, int end) {
            PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));
            Map<Integer, Integer> distances = new HashMap<>();
            Set<Integer> visited = new HashSet<>();

            queue.add(new Edge(start, 0));
            distances.put(start, 0);

            while (!queue.isEmpty()) {
                Edge current = queue.poll();
                if (!visited.add(current.node)) {
                    continue;
                }

                if (current.node == end) {
                    return current.weight;
                }

                for (Edge edge : edges.getOrDefault(current.node, Collections.emptyList())) {
                    int newDist = current.weight + edge.weight;
                    if (newDist < distances.getOrDefault(edge.node, Integer.MAX_VALUE)) {
                        distances.put(edge.node, newDist);
                        queue.add(new Edge(edge.node, newDist));
                    }
                }
            }

            return -1;
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Graph graph = new Graph();
            
            System.out.println("Enter the edges in the format 'u v weight', type 'x' to finish:");
            
            while (true) {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("x")) {
                    break;
                }
                String[] parts = input.split(" ");
                int u = Integer.parseInt(parts[0]);
                int v = Integer.parseInt(parts[1]);
                int weight = Integer.parseInt(parts[2]);
                graph.addEdge(u, v, weight);
            }
            
            System.out.println("Enter the start node:");
            int start = scanner.nextInt();
            
            System.out.println("Enter the end node:");
            int end = scanner.nextInt();
            
            int shortestPathLength = graph.dijkstra(start, end);
            
            if (shortestPathLength != -1) {
                System.out.println("Shortest path length: " + shortestPathLength);
            } else {
                System.out.println("No path exists between the nodes.");
            }
        }
    }
}
