import java.util.*;

class Edge {
    private int endnode;
    private int value;
    
    Edge(int endv, int v) {
        endnode = endv;
        value = v;
    }

    public int endnode() {
        return endnode;
    }

    public int value() {
        return value;
    }

    public void newvalue(int v) {
        value = v;
    }
}

class Node {
    private LinkedList<Edge> neighbours;

    Node() {
        neighbours  = new LinkedList<Edge>();
    }

    public LinkedList<Edge> adjs() {
        return neighbours;
    }
}

class Graph {
    private Node verts[];
    private int nverts, nedges;
			
    public Graph(int n) {
        nverts = n;
        nedges = 0;
        verts  = new Node[n+1];
        for (int i = 0 ; i <= n ; i++)
            verts[i] = new Node();
    }
    
    public int num_vertices() {
        return nverts;
    }

    public int num_edges() {
        return nedges;
    }

    public LinkedList<Edge> adjs_no(int i) {
        return verts[i].adjs();
    }
    
    public void insert_new_edge(int i, int j, int value_ij) {
        verts[i].adjs().addFirst(new Edge(j, value_ij));
        nedges++;
    }

    public Edge find_edge(int i, int j) {
        for (Edge adj : adjs_no(i))
            if (adj.endnode() == j) return adj;
        return null;
    }
}

public class F2 {
    
    public static int[] bfs_visit(int s, Graph g, int k, int[] vec){

        boolean[] visitados = new boolean[g.num_vertices()+1];

        int[] pai = new int[g.num_vertices() + 1];

        //int[] dist = new int[g.num_vertices() + 1];

        visitados[s]=true;

        //dist[s]=0;

        int cont=0;

        Queue<Integer> fila = new LinkedList<>();

        boolean ok= false;

        int[] resp = new int[g.num_vertices()+1];

        fila.add(s);

        do{

            int v = fila.poll();

            for (Edge e: g.adjs_no(v)){

                int w=e.endnode();

                if(!visitados[w]){

                    //dist[w]=dist[v] + 1;

                    if (vec[w] == 0) {
                        ok=true;
                        System.out.println("Wally: " + w);
                    }

                    else if ((vec[w]<=k) && (vec[w]!=-1) && (vec[w]!=0)){

                        resp[cont]=vec[w];
                        cont+=1;
                    
                    }

                    fila.add(w);
                    visitados[w]=true;
                    pai[w]=v;
                }
            }

        } while (!fila.isEmpty());

        if (!ok){
            System.out.println("Wally not found");
        }

        return resp;

    }
    public static void main(String[] args){

        Scanner stdin = new Scanner(System.in);

        int n_nos=stdin.nextInt();

        int n_ramos=stdin.nextInt();

        int k=stdin.nextInt();

        int origem=stdin.nextInt();

        int[] vec = new int[n_nos + 1];

        Graph g = new Graph(n_nos);

        for (int i=1; i<=n_nos; i++){

            int identificador = stdin.nextInt();

            vec[i] = identificador;

        }

        for (int j=0; j<n_ramos; j++){

            int no1=stdin.nextInt();

            int no2=stdin.nextInt();

            //System.out.println("No 1: " + no1);
            //System.out.println("No 2: " + no2);

            g.insert_new_edge(no1, no2, 0);
            g.insert_new_edge(no2, no1, 0);

        }

        int[] resp= bfs_visit(origem, g, k, vec);
        int count=0;

        for (int i=0; i<=n_nos; i++){
            if (resp[i]!=0){
                count+=1;
            }
        }
            
        System.out.print(count);

        for (int i=0; i<count; i++){
            System.out.print(" " + resp[i]);
        }

    }

}
