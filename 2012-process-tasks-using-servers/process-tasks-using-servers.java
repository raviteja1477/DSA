class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        PriorityQueue<int[]> free = new PriorityQueue<>(
            (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]
        );
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> a[0] != b[0] ? Long.compare(a[0], b[0]) : Long.compare(a[1], b[1])
        );

        int n = servers.length, m = tasks.length;
        int[] ans = new int[m];

        for (int i = 0; i < n; i++) {
            free.offer(new int[]{servers[i], i});
        }

        long time = 0;

        for (int j = 0; j < m; j++) {
            time = Math.max(time, j);   // never go backwards

            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                int idx = (int) busy.poll()[1];
                free.offer(new int[]{servers[idx], idx});
            }

            if (free.isEmpty()) {
                time = busy.peek()[0];  // wait for the earliest server
                while (!busy.isEmpty() && busy.peek()[0] <= time) {
                    int idx = (int) busy.poll()[1];
                    free.offer(new int[]{servers[idx], idx});
                }
            }

            int[] s = free.poll();
            ans[j] = s[1];
            busy.offer(new long[]{time + tasks[j], s[1]});
        }

        return ans;
    }
}