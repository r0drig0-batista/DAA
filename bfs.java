
// bfs que vê todos ligados a partir de v

import java.util.LinkedList;

public static void bfs_visit(Graph g, int v) {
    boolean[] visited = new boolean[g.num_vertices + 1];
    for (int i = 0; i <= g.num_vertices; i++) {visited[i] = false;}
    
    visited[v] = true;
    
    Queue<Integer> s = new LinkedList<>();
    s.add(v);
    
    while (!s.isEmpty()) {
        int cur = s.poll();
        
        for (Edge adj : g.adjs_no()) {
            int neighbor = adj.endnode();
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                s.add(neighbor);
            }
        }

    }
}




public static void bfs_visit(Graph g, int v){
    boolean[] visited = new boolean[g.num_vertices() + 1];

    for (int i=0; i<g.num_vertices()+1; i++) {visited[i] = false;}

    visited[v]=true;

    Queue<Integer> q = new LinkedList<>();

    q.add(v);

    while (!s.isEmpty()){

        int w = q.poll();

        for (Edge adj : g.adjs_no()){
            int neighbour = adj.endnode();
            if (!visited[neighbour]){
                visited[neighbour] = true;
                q.add(neighbour);
            }
        }


    }

}