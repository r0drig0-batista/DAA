
import java.lang.*;
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


class Qnode {
    int vert;
    int vertkey;
    
    Qnode(int v, int key) {
	vert = v;
	vertkey = key;
    }
}

class Heapmax {
    private static int posinvalida = 0;
    int sizeMax,size;
    
    Qnode[] a;
    int[] pos_a;

    Heapmax(int vec[], int n) {
	a = new Qnode[n + 1];
	pos_a = new int[n + 1];
	sizeMax = n;
	size = n;
	for (int i = 1; i <= n; i++) {
	    a[i] = new Qnode(i,vec[i]);
	    pos_a[i] = i;
	}

	for (int i = n/2; i >= 1; i--)
	    heapify(i);
    }

    boolean isEmpty() {
	if (size == 0) return true;
	return false;
    }

    int extractMax() {
	int vertv = a[1].vert;
	swap(1,size);
	pos_a[vertv] = posinvalida;  // assinala vertv como removido
	size--;
	heapify(1);
	return vertv;
    }

    void increaseKey(int vertv, int newkey) {

	int i = pos_a[vertv];
	a[i].vertkey = newkey;

	while (i > 1 && compare(i, parent(i)) > 0) { 
	    swap(i, parent(i));
	    i = parent(i);
	}
    }


    void insert(int vertv, int key)
    { 
	if (sizeMax == size)
	    new Error("Heap is full\n");
	
	size++;
	a[size].vert = vertv;
	pos_a[vertv] = size;   // supondo 1 <= vertv <= n
	increaseKey(vertv,key);   // aumenta a chave e corrige posicao se necessario
    }

    void write_heap(){
	System.out.printf("Max size: %d\n",sizeMax);
	System.out.printf("Current size: %d\n",size);
	System.out.printf("(Vert,Key)\n---------\n");
	for(int i=1; i <= size; i++)
	    System.out.printf("(%d,%d)\n",a[i].vert,a[i].vertkey);
	
	System.out.printf("-------\n(Vert,PosVert)\n---------\n");

	for(int i=1; i <= sizeMax; i++)
	    if (pos_valida(pos_a[i]))
		System.out.printf("(%d,%d)\n",i,pos_a[i]);
    }
    
    private int parent(int i){
	return i/2;
    }
    private int left(int i){
	return 2*i;
    }
    private int right(int i){
	return 2*i+1;
    }

    private int compare(int i, int j) {
	if (a[i].vertkey < a[j].vertkey)
	    return -1;
	if (a[i].vertkey == a[j].vertkey)
	    return 0;
	return 1;
    }

  
    private void heapify(int i) {
	int l, r, largest;

	l = left(i);
	if (l > size) l = i;

	r = right(i);
	if (r > size) r = i;

	largest = i;
	if (compare(l,largest) > 0)
	    largest = l;
	if (compare(r,largest) > 0)
	    largest = r;
	
	if (i != largest) {
	    swap(i, largest);
	    heapify(largest);
	}
	
    }

    private void swap(int i, int j) {
	Qnode aux;
	pos_a[a[i].vert] = j;
	pos_a[a[j].vert] = i;
	aux = a[i];
	a[i] = a[j];
	a[j] = aux;
    }
    
    private boolean pos_valida(int i) {
	return (i >= 1 && i <= size);
    }
}




public class opticaminimalista {


    public static int muitosprim(Graph grafo){
        int max = -2; 

        for(int i = 1; i<= grafo.num_vertices();i++){
            int resultado = prim(grafo, i);
            if(resultado > max){
                max = resultado; 
            }


        }
        return max;
    }

    public static int prim(Graph grafo, int s){
        int[] pai = new int[grafo.num_vertices()+1];
        int[] dist = new int[grafo.num_vertices()+1];
        boolean[] ok = new boolean[grafo.num_vertices()+1];
        for(int i = 0; i <= grafo.num_vertices();i++){
            pai[i] = 0; 
            dist[i] = 0; 
            ok[i] = false; 
        }
        dist[s] = 10000000; 
        Heapmax q = new Heapmax(dist, grafo.num_vertices());
        while(!q.isEmpty()){
            int v = q.extractMax();
            ok[v] = true; 
            for(Edge adj: grafo.adjs_no(v)){
                int w = adj.endnode(); 
                if(!ok[w] && adj.value() > dist[w]){
                    dist[w] = adj.value(); 
                    pai[w] = v; 
                    q.increaseKey(w,dist[w]);
                }
            }
        }
        int rendimento = 0; 
        for(int j = 1; j<grafo.num_vertices()+1;j++){
            if(dist[j]== 0){
                return -1;
            }
            if(dist[s] == dist[j]){
                continue;
            }
            //System.out.print(" " +dist[j]);
            rendimento += dist[j];
        }
        return rendimento;
    }
    public static void main(String[] args){
        Scanner stdin = new Scanner(System.in); 
        int n = stdin.nextInt(); //Numero de nos da rede
        if(n > 1000){
            return; 
        }
        int l = stdin.nextInt(); 
        if( l> 160000){
            return; 
        }

        int c = stdin.nextInt(); 
        //Construir o grafo 
        Graph grafo = new Graph(n);
        for(int i = 0; i < l; i++){
            int s = stdin.nextInt(); 
            int e = stdin.nextInt(); 
            int rendimento_bruto = stdin.nextInt(); 
            int rendimento_liquido = rendimento_bruto - c; 
            grafo.insert_new_edge(s,e,rendimento_liquido); 
            grafo.insert_new_edge(e,s,rendimento_liquido); 
        }

        int resultado = prim(grafo,1);
        if(resultado == -1){
            System.out.println("impossivel");
        }
        else{
            System.out.println("rendimento optimo: " + resultado);
        }


        
    }
}
