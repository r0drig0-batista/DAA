// max
public static List<int[]> prim(Graph g, int s) {
    int[] pai = new int[g.num_vertices() + 1];
    int[] dist = new int[g.num_vertices() + 1];
    boolean[] ok = new boolean[g.num_vertices() + 1];
    for (int i = 1; i <= g.num_vertices(); i++) {pai[i] = -1; dist[i] = 0; ok[i] = false;}
    dist[s] = Integer.MAX_VALUE;
    pai[s] = 0;
    Heapmin q = new Heapmax(dist, g.num_vertices());

    while(!q.isEmpty()) {
        int cur = q.extractMax();
        ok[cur] = true;
        for (Edge adj : g.adjs_no(cur)) {
            int neighbor = adj.endnode();
            if (!ok[neighbor] && adj.value() > dist[neighbor]) {
                dist[neighbor] = adj.value();
                pai[neighbor] = cur;
                q.decreaseKey(neighbor, dist[neighbor]);
            }
        }
    }
    List<int[]> ans = new ArrayList<>();
    ans.add(pai);
    ans.add(dist);
    return ans;
}