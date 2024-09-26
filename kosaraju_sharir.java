
// fazer dfs todo (grafo todo)
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

    public static void kosaraju(Graph0 g, Graph0 gt) {

        Stack<Integer> s = dfs(g);

        boolean[] visited = new boolean[g.num_vertices() + 1];
        for (int i = 0; i <= g.num_vertices(); i++) {visited[i] = false;}

        while (!s.isEmpty()) {
            int v = s.pop();
            if (!visited[v]) {
                System.out.println("pop = " + v);
                Stack<Integer> temp = new Stack<>();    // guarda os vértices da componente conexa
                dfs_visit(v, gt, temp, visited);
                System.out.println("Componente conexa");
                while(!temp.isEmpty()) {
                    System.out.println("v = " + temp.pop());
                }
            }
        }
    }