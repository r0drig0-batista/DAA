import java.lang.*;
import java.util.*;

class Qnode {
    int vert;
    int vertkey;
    
    Qnode(int v, int key) {
	vert = v;
	vertkey = key;
    }
}

class Heapmin {
    public static int posinvalida = 0;
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
        // vertex labels are integers from 1 to n (position 0 is not used)
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


public class B4{


    public static int bfs(Graph g, int s, int destino){

        int[] pai = new int[g.num_vertices() + 1];

        int[] dist = new int[g.num_vertices() + 1];

        for (int i=0; i<g.num_vertices() + 1; i++){pai[i]=-1; dist[i]=Integer.MAX_VALUE;}

        pai[s]=0;
        dist[s]=0;

       Heapmin h = new Heapmin(dist,g.num_vertices());

        while(!h.isEmpty()){

            int cur = h.extractMin();

            if (cur == destino){
                return dist[destino];
            }

            for (Edge adj:g.adjs_no(cur)){

                int neighbor=adj.endnode();

                //System.out.println("Atual " + cur);
                //System.out.println("Vizinho " + neighbor);
                //System.out.println("Valor da aresta: " + adj.value()[1]);

                if(dist[cur]+adj.value() < dist[neighbor]){
                    
                    pai[neighbor]=cur;

                    dist[neighbor]= dist[cur] + adj.value();

                    if (h.pos_a[neighbor] != h.posinvalida){
                        h.decreaseKey(neighbor, dist[neighbor]);
                    }
                }

            }
        }

        return -1;
    }

    public static void main(String[] args){

        Scanner stdin = new Scanner(System.in);

        int temp_min=stdin.nextInt();

        int temp_max=stdin.nextInt();

        int origem=stdin.nextInt();

        int destino=stdin.nextInt();

        int n_nos=stdin.nextInt();

        int n_ramos=stdin.nextInt();

        Graph g = new Graph(n_nos);

        for (int i=0;i<n_ramos;i++){

            int n1=stdin.nextInt();

            int n2=stdin.nextInt();

            int temp=stdin.nextInt();

            int lixo=stdin.nextInt();

            if(temp > temp_max || temp < temp_min){
                continue;
            }

            g.insert_new_edge(n1, n2, lixo);
            g.insert_new_edge(n2, n1, lixo);

        }

        int n_cenarios= stdin.nextInt();

        int resultado=bfs(g, origem, destino);

        for (int j=0; j<n_cenarios; j++){
        
            int montante=stdin.nextInt();


            if(resultado<=montante){
                System.out.println("Sim");
            }
            else{
                System.out.println("Nao");
            }
        }

    }

}