import java.util.*;

class Solution {
    public int reachableNodes(int[][] edges, int maxMoves, int n) {

       
        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int cnt = edge[2];

            graph[u].add(new int[]{v, cnt + 1});
            graph[v].add(new int[]{u, cnt + 1});
        }

        // Dijkstra
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);

        PriorityQueue<long[]> pq =
            new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));

        dist[0] = 0;
        pq.offer(new long[]{0, 0});

        while (!pq.isEmpty()) {

            long[] current = pq.poll();

            int node = (int) current[0];
            long distance = current[1];

            if (distance != dist[node]) {
                continue;
            }

            for (int[] next : graph[node]) {

                int neighbor = next[0];
                int cost = next[1];

                long newDistance = distance + cost;

                if (newDistance < dist[neighbor]) {
                    dist[neighbor] = newDistance;
                    pq.offer(new long[]{neighbor, newDistance});
                }
            }
        }

        
        int answer = 0;

        for (int i = 0; i < n; i++) {
            if (dist[i] <= maxMoves) {
                answer++;
            }
        }

        
        for (int[] edge : edges) {

            int u = edge[0];
            int v = edge[1];
            int cnt = edge[2];

            long fromU = 0;
            long fromV = 0;

            if (dist[u] <= maxMoves) {
                fromU = maxMoves - dist[u];
            }

            if (dist[v] <= maxMoves) {
                fromV = maxMoves - dist[v];
            }

            answer += (int) Math.min(cnt, fromU + fromV);
        }

        return answer;
    }
}