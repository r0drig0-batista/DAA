
import java.util.*;


class Edge {
    private int enode;
    private int value;
    
    Edge(int endv, int v){
	enode = endv;
	value = v;
    }

    public int endnode() {
	return enode;
    }

    public int value() {
	return value;
    }

    public void newvalue(int v) {
	value = v;
    }
}


class Node {
    //private int label;
    private LinkedList<Edge> neighbours;

    Node() {
	neighbours  = new LinkedList<Edge>();
    }

    public LinkedList<Edge> adjs() {
	return neighbours;
    }
   
}


/**
 * Defines a Weighted Directed Graph
 * Represented by Adjacency list (i.e., an array of lists of edges).
 * Assumes that vertex labels are integers from 1 to n (position 0 not used)
 */

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
    
    public int num_vertices(){
	return nverts;
    }

    public int num_edges(){
	return nedges;
    }

    public LinkedList<Edge> adjs_no(int i) {
	return verts[i].adjs();
    }
    
    public void insert_new_edge(int i, int j, int value_ij){
	verts[i].adjs().addFirst(new Edge(j,value_ij));
        nedges++;
    }

    public Edge find_edge(int i, int j){
	for (Edge adj: adjs_no(i))
	    if (adj.endnode() == j) return adj;
	return null;
    }
}


public class sobrecargatrabalhos {

    public static int TopSort_DFS(Graph grafo, int[] GrauEntrada, int[] ES, int[] Prec){
        //Definir Stack 
        Stack<Integer> pilha = new Stack<>(); 
        int DurMin = -1; 
        
        for(int i = 1; i < grafo.num_vertices() +1; i++){
            if(GrauEntrada[i] == 0){
                pilha.push(i);
            }
        }
        int vf = 0;
        while(!pilha.isEmpty()){
            int v = pilha.pop(); 
            if(DurMin < ES[v]){
                DurMin = ES[v]; 
                vf = v; 
            }
            for(Edge adj : grafo.adjs_no(v)){
                int w = adj.endnode(); 
                if(ES[w] < ES[v] + adj.value()){
                    ES[w] = ES[v] + adj.value(); 
                    Prec[w] = v; 
                }
                GrauEntrada[w] = GrauEntrada[w]  - 1;
                if(GrauEntrada[w] == 0){
                    pilha.push(w);
                }
            }
        } 

        //USAR ES Para determinar o pico, os earliest start tem que ser iguais 
        //USAR LISTA DE TAREFAS ATIVAS
        //QUANDO VERIFICO EARLIEST START 

        return DurMin; 

    }
    public static void main(String[] args){
        Scanner stdin = new Scanner(System.in);
        //Número de nós do grafo
        int n = stdin.nextInt(); 
        //Número de tarefas
        int t = stdin.nextInt(); 
        //Por os nos no grafo
        Graph grafo = new Graph(n);
        int[] GrauEntrada = new int[n+1];
        for(int i = 0; i < t;i++){
            int c = stdin.nextInt(); 
            int j = stdin.nextInt(); 
            int d = stdin.nextInt();    
            grafo.insert_new_edge(c,j,d); 
            GrauEntrada[j] = GrauEntrada[j] + 1; 
        }
        int[] ES = new int[n+1];
        int[] Prec = new int[n+1];
        int durmin = TopSort_DFS(grafo, GrauEntrada, ES, Prec);
        System.out.println("Duracao Minima:" + durmin);
    }

}

