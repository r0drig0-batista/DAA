
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


public class cronogramaslda {

    public static int caminhoCritico(Graph grafo, int instante, int a){
        //Definir Stack 
        Stack<Integer> pilha = new Stack<>(); 
        int[] ES = new int[grafo.num_vertices() +1 ];
        int[] GrauEntrada = new int[grafo.num_vertices()+1];
        int[] conclusoes= new int[grafo.num_vertices() + 1];
        int DurMin = -1; 

        //Inicializar grau de entrada
        for(int i = 1; i<= grafo.num_vertices();i++){
            for(Edge adj: grafo.adjs_no(i)){
                GrauEntrada[adj.endnode()] ++; 
            }
        }
        
        for(int i = 1; i < grafo.num_vertices() +1; i++){
            if(GrauEntrada[i] == 0){
                pilha.push(i);
            }
        }
        int vf = 0;
        while(!pilha.isEmpty()){

            int v = pilha.pop(); 

        
            for(Edge adj : grafo.adjs_no(v)){
                int w = adj.endnode(); 
                if(ES[w] < ES[v] + adj.value()){
                    ES[w] = ES[v] + adj.value(); 
                }
                
                GrauEntrada[w] = GrauEntrada[w]  - 1;
                if(GrauEntrada[w] == 0){
                    pilha.push(w);
                }
            }

        } 

        //Calcular conclusões 
        int contagem = 0; 
        for(int v = 1; v<= grafo.num_vertices(); v++){
            for( Edge adj: grafo.adjs_no(v)){
                int w = adj.endnode(); 
                int conclusao = ES[v] + adj.value();
                if(conclusao <=  instante){
                    contagem++;
                }
            }
        }
        
        return contagem;
    }

    public static void main(String[] args){
        Scanner stdin  = new Scanner(System.in); 
        //Número de nos do grafo
        int n = stdin.nextInt(); 
        //Número de atividades(ligacoes)
        int a = stdin.nextInt(); 
        //Criacao do grafo
        Graph g = new Graph(n);
        for(int i = 0; i < a; i++){
            int inicio = stdin.nextInt(); 
            int fim = stdin.nextInt(); 
            int dur = stdin.nextInt(); 
            g.insert_new_edge(inicio,fim,dur);
        }
        int perguntas = stdin.nextInt(); 
        for(int j = 0; j < perguntas; j++){
            int instante = stdin.nextInt(); 
            //Usar método o caminho crítico - earliest start
            int resultado  = caminhoCritico(g,instante,a);
            System.out.println(instante + " " + resultado);
        }

    }
}
