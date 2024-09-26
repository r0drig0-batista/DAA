import java.util.Scanner;
import java.util.LinkedList;

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

public class D2 {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int n_nos = stdin.nextInt();
        int n_ligacoes = stdin.nextInt();
        Graph g = new Graph(n_nos);

        for (int i = 0; i < n_ligacoes; i++) {
            int origem = stdin.nextInt();
            int destino = stdin.nextInt();
            int lugares = stdin.nextInt();
            int preco = stdin.nextInt();
            int[] carac = new int[] {lugares, preco};
            g.insert_new_edge(origem, destino, carac);
        }

        int t = stdin.nextInt();

        for (int j = 0; j < t; j++) {
            int lugares = stdin.nextInt();
            int nos = stdin.nextInt();
            int no1 = stdin.nextInt();
            int no2 = stdin.nextInt();
            int total = 0;
            boolean verificado = true;
            LinkedList<int[]> changes = new LinkedList<>();

            while (nos > 1) {
                if (g.find_edge(no1, no2) != null) {
                    int[] vec = g.find_edge(no1, no2).value();
                    if ((vec[0] - lugares) >= 0) {
                        total += vec[1] * lugares;

                        changes.add(new int[] {no1, no2, vec[0]});
                    } else {
                        System.out.println("Sem lugares suficientes em (" + no1 + "," + no2 + ")");
                        verificado = false; 
                        break;
                    }
                } else {
                    System.out.println("(" + no1 + "," + no2 +") inexistente");
                    verificado = false; 
                    break;
                }
                no1 = no2;
                no2 = stdin.nextInt();
                nos--;
            }
            if (verificado) {
                System.out.println("Total a pagar: " + total);
                // Atualizar os lugares apenas se todos os trechos forem viáveis
                for (int[] change : changes) {
                    g.find_edge(change[0], change[1]).newvalue();
                }
            }
        }
    }
}
