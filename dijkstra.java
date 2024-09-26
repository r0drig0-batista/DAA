
public static int[] dijkstra(Graph g, s) {
    int[] pai = new int[g.num_vertices() + 1];
    int[] dist = new int[g.num_vertices() + 1];
    for (int i = 1; i <= g.num_vertices(); i++) {pai[i] = -1; dist[i] = Integer.MAX_VALUE;}

    dist[s] = 0;
    pai[s] = 0;

    Heapmin h = new Heapmin(dist, g.num_vertices());

    while (!h.isEmpty()) {
        int cur = h.extractMin();

        for (Edge adj : g.adj_no(cur)) {
            int neighbor = adj.endnode();
            if (dist[cur] + adj.value() < dist[neighbor]) {
                dist[neighbor] = dist[cur] + adj.value();
                pai[neighbor] = cur;
                h.decreaseKey(neighbor, dist[neighbor]);
            }
        }
    }
    return dist;
}


public static int[] dijkstra(Graph s, int s){

    int[] pai = new int[g.num_vertices() + 1];

    int[] dist = new int[g.num_vertices() + 1];

    for (int i=1; i<g.num_vertices() + 1; i++){ pai[i] =-1; dist[i]=Integer.MAX_VALUE;}

    dist[s]=0;
    pai[s]=0;

    Heapmin h = new Heapmin(dist,g.num_vertices());

    while (!h.isEmpty()){

        int cur=h.extractMin();

        for (Edge adj:g.adj_no(cur)){

            int neighbor = adj.endnode();

            if (dist[cur] + adj.value() < dist[neighbor]){
                dist[neighbor]=dist[cur] + adj.value();
                pai[neighbor]=cur;
                h.decreaseKey(neighbor,dist[neighbor]);
            }
        }
    }
    return dist;


}