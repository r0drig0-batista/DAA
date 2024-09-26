
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

class Heapmin {
    private static int posinvalida = 0;
    int sizeMax,size;
    
    Qnode[] a;
    int[] pos_a;

    Heapmin(int vec[], int n) {
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

    int extractMin() {
	int vertv = a[1].vert;
	swap(1,size);
	pos_a[vertv] = posinvalida;  // assinala vertv como removido
	size--;
	heapify(1);
	return vertv;
    }

    void decreaseKey(int vertv, int newkey) {

	int i = pos_a[vertv];
	a[i].vertkey = newkey;

	while (i > 1 && compare(i, parent(i)) < 0) { 
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
	decreaseKey(vertv,key);   // diminui a chave e corrige posicao se necessario
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
	int l, r, smallest;

	l = left(i);
	if (l > size) l = i;

	r = right(i);
	if (r > size) r = i;

	smallest = i;
	if (compare(l,smallest) < 0)
	    smallest = l;
	if (compare(r,smallest) < 0)
	    smallest = r;
	
	if (i != smallest) {
	    swap(i, smallest);
	    heapify(smallest);
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



public class transporteluxo {

    public static int bfs(Graph grafo, int s, int t){
        boolean[] visited = new boolean[grafo.num_vertices()+1];
        Queue<Integer> queue = new LinkedList<>(); 
        queue.add(s);
        int[] distances = new int[grafo.num_vertices() + 1];
        distances[s] = 0; 
        visited[s] = true;

        while(!queue.isEmpty()){
            int curr = queue.poll();
            for(Edge e: grafo.adjs_no(curr)){

                int next = e.endnode();
                if (!visited[next]) { // Checa se o próximo vértice já foi visitado
                    visited[next] = true; // Marca como visitado
                    queue.add(next); // Adiciona na fila
                    distances[next] = distances[curr] + 1; // Atualiza a distância
                    if (next == t) {
                        return distances[t]; // Retorna a distância se alcançar o destino
                    }
                }
            }
        }
        return -1;
    }
    
            
            
    public static int shortestPath(int s,int t,Graph g){
        int nverts = g.num_vertices();
        int [] pai = new int[nverts+1];    // all zero
        int [] dist = new int[nverts+1];   
		for (int v = 1; v <= nverts; v++)
			dist[v] = Integer.MAX_VALUE;
		dist[s] = 0;
		Heapmin q = new Heapmin(dist,nverts);
		while (!q.isEmpty()) {
			int v = q.extractMin();
			if (v == t){
                int count = 0; 
                while(pai[t]!= 0){
                    t = pai[t];
                    count++;
                    if(t == s){
                        return count; 
                    }
                }
                break;  //return dist[t];

            }
			for (Edge adjs: g.adjs_no(v)) {
				int w = adjs.endnode();
				if (dist[v]+adjs.value() < dist[w]) {
							pai[w] = v;
							dist[w] = dist[v]+adjs.value();
							q.decreaseKey(w,dist[w]);
				}
			}

		}
        return -1; 
    
    }
    
    
    
    public static void main(String[] args){
        Scanner stdin = new Scanner(System.in);
        int temp_min = stdin.nextInt(); 
        int temp_max = stdin.nextInt(); 
        int s = stdin.nextInt(); 
        int t= stdin.nextInt(); 
        int n = stdin.nextInt(); //Número de nos 
        int l = stdin.nextInt(); //Numero de trocos
        //Grafo
        Graph grafo = new Graph(n);
        for(int i = 0; i < l; i++){
            int inicio_no = stdin.nextInt(); 
            int fim_no = stdin.nextInt(); 
            int temp = stdin.nextInt(); 
            int custo = stdin.nextInt(); 
            if(temp > temp_max || temp < temp_min ){
                continue; 
            }
            grafo.insert_new_edge(inicio_no,fim_no,custo);
            grafo.insert_new_edge(fim_no,inicio_no,custo);
        }
        int resultado = bfs(grafo,s, t);
        if(resultado == -1){
            System.out.println("Nao");
        }
        else{
            System.out.println("Sim " + resultado);
        }
     
    }
}
