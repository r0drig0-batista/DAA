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


public class E2 {
    
    public static int bfs_visit(int s, Graph g, Map<Integer,Integer> dicionario){
        boolean[] visitados = new boolean[g.num_vertices() + 1];
        
        int[] pai = new int[g.num_vertices()+1];

        visitados[s]=true;

        Queue<Integer> q= new LinkedList<>();

        q.add(s);

        int maximo=0;
        int no_final=s;

        do {

            int v = q.poll();

            for (Edge e: g.adjs_no(v)){
                
                int w = e.endnode();

                if (!visitados[w]){

                    //System.out.println("Valor de w (" + w + ") é " + dicionario.get(w));

                    if ((dicionario.get(w) > maximo) || (dicionario.get(w)==maximo && no_final>w)){
                        maximo= dicionario.get(w);
                        no_final=w;
                    }

                    q.add(w);
                    visitados[w]=true;
                    pai[w]=v;
                }
            }

        } while(!q.isEmpty());


        return no_final;
    }

    public static void main(String[] args){

        Scanner stdin = new Scanner(System.in);

        Map<Integer,Integer> dicionario = new HashMap<>();

        int n = stdin.nextInt();

        Graph g = new Graph(n);

        for (int i=1;i<=n;i++){
            
            int aboboras=stdin.nextInt();

            dicionario.put(i,aboboras);
            
        }

        int ligacoes=stdin.nextInt();

        for (int j=0;j<ligacoes;j++){

            int no1=stdin.nextInt();

            int no2=stdin.nextInt();

            g.insert_new_edge(no1, no2, null);

            g.insert_new_edge(no2, no1, null);

        }

        int n_familias=stdin.nextInt();

        for (int f=0; f<n_familias; f++){

            int supermercado=stdin.nextInt();

            //System.out.println("Aboboras no supermercado: " + dicionario.get(supermercado));

            if (dicionario.get(supermercado) > 0) System.out.println(supermercado);

            else{

                int maximo =bfs_visit(supermercado, g, dicionario);

                //System.out.println("Maximo: " + maximo);

                if (dicionario.get(maximo)==0) System.out.println("Impossivel");

                else{
                    System.out.println(maximo);
                }

            }

        }


    }
}
