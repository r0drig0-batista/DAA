
// fazer dfs todo (grafo todo)

import java.util.Stack;

public static Stack<Integer> dfs(Graph g) {
    Stack<Integer> s = new Stack<>();
    boolean[] visited = new boolean[g.num_vertices() + 1];
    for (int i = 0; i <= g.num_vertices(); i++) {visited[i] = false;}

    for (int v = 1; v <= g.num_vertices(); v++) {
        if(!visited[v]) {dfs_visit(v, g, s, visited);}
    }
    return s;
}

// faz o dfs só a partir de um vértice
public static void dfs_visit(int v, Graph g, Stack<Integer> s, boolean[] visited) {
    visited[v] = true;
    for (Edge adj : g.adjs_no(v)) {
        int neighbor = adj.endnode();
        if (!visited[neighbor]) {dfs_visit(neighbor, g, s, visited);}
    }
    s.push(v);
}


public static Stack<Integer> dfs(Graph g){

    Stack<Integer> s = new Stack<>();
    boolean[] visited = new boolean[g.num_vertices() + 1];

    for (int i=0; i< g.num_vertices()+1; i++){ visited[i] = false;}

    for (int v=1; v< g.num_vertices() + 1; v++) {
        if (!visited[v]) { dfs_visit(v, g, s, visited);}
    }
    return s;
}


public static void dfs_visit(int v, Graph g, Stack s, boolean[] visited){

    visited[v]=true;

    for (Edge adj:g.adjs_no(v)){

        int neighbour = adj.endnode();

        if (!visited[neighbour]) {dfs_visit(neighbour, g, s, visited);}
    }
    s.push(v);

}