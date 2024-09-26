import java.util.*;

class Edge {
    private int endnode;
    private int[] value;
    
    Edge(int endv, int[] v) {
        endnode = endv;
        value = v;
    }

    public int endnode() {
        return endnode;
    }

    public int[] value() {
        return value;
    }

    public void newvalue(int[] v) {
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
    
    public void insert_new_edge(int i, int j, int[] value_ij) {
        verts[i].adjs().addFirst(new Edge(j, value_ij));
        nedges++;
    }

    public Edge find_edge(int i, int j) {
        for (Edge adj : adjs_no(i))
            if (adj.endnode() == j) return adj;
        return null;
    }
}



public class G2 {
    

    public static int[] BFS(int s, Graph g){

        boolean[] visitados = new boolean[g.num_vertices() + 1];

        int[] pai = new int[g.num_vertices() + 1];

        int[] dist = new int[500];

        visitados[s]=true;

        dist[s]=0;

        Queue<Integer> fila= new LinkedList<>();

        fila.add(s);

        do{

            int v=fila.poll();

            for (Edge e : g.adjs_no(v)){

                int w = e.endnode();

                if (!visitados[w]){

                    fila.add(w);
                    dist[w]=dist[v]+1;
                    visitados[w]=true;
                    pai[w]=v;
                }
            }
        } while (!fila.isEmpty());
    
        return dist;

    }


    public static void main(String[] args){

        Scanner stdin= new Scanner(System.in);

        int n_nos=stdin.nextInt();

        Graph g = new Graph(n_nos);

        int lar_min=stdin.nextInt();
        int lar_max=stdin.nextInt();
        int comp_min=stdin.nextInt();
        int comp_max=stdin.nextInt();
        int altura_min=stdin.nextInt();

        int origem=stdin.nextInt();
        int destino=stdin.nextInt();

        int no1=stdin.nextInt();
        int no2=stdin.nextInt();
        int lar_max_cam=stdin.nextInt();
        int comp_max_cam=stdin.nextInt();
        int alt_max_cam=stdin.nextInt();

        int[] vec = new int[3];

        while (no1!=-1){

            vec[0]=lar_max_cam;
            vec[1]=comp_max_cam;
            vec[2]=alt_max_cam;

            if ((lar_min<=lar_max_cam) && (comp_min<=comp_max_cam) && (altura_min<=alt_max_cam)){

                g.insert_new_edge(no1, no2, vec);

                g.insert_new_edge(no2, no1, vec);

                //System.out.println("Teste: " + no1);

            }

            no1=stdin.nextInt();
            if (no1!=-1){
                no2=stdin.nextInt();
                lar_max_cam=stdin.nextInt();
                comp_max_cam=stdin.nextInt();
                alt_max_cam=stdin.nextInt();
            }

        }

        int dist[] = BFS(origem,g);

        int min=dist[0];

        boolean ok=false;

        if (dist[destino]!=0) {
            System.out.println(dist[destino]);
        }

        else{
        System.out.println("Impossivel");
        }
    }
}
