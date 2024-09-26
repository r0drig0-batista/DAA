
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


public class paraondeir {

    public static void shortestPath(Graph g, int s, int m_max){
        int nvert= g.num_vertices();
        int[] pai = new int[nvert +1];
        int[] dist = new int[nvert+ 1];
        for(int v = 1; v <= nvert;v++){
            dist[v] = m_max + 1;
        }
        dist[s] = 0;
        //Criacao da heapmin
        Heapmin q = new Heapmin(dist, nvert);
        while(!q.isEmpty()){
            int v = q.extractMin(); 
            for(Edge adj: g.adjs_no(v)){
                int w = adj.endnode(); 
                if(dist[v] + adj.value() < dist[w]){
                    pai[w] = v; 
                    dist[w] = dist[v] + adj.value(); 
                    
                    q.decreaseKey(w, dist[w]);
                    
                    
                }
            }
        }
        int encontrou = 0; 

        for(int i = 1; i<= nvert; i++){
            if(i == s){
                continue; 
            }
            else{
                if(dist[i] <= m_max && dist[i] >= 0){
                    System.out.println("Destino " + i + ": " + dist[i]);
                    encontrou = 1; 
                }
            }
        }
        if(encontrou == 0){
            System.out.println("Impossivel");
        }
    }
    public static void main(String[] args){
        Scanner stdin = new Scanner(System.in); 
        int k = stdin.nextInt(); //Número de elementos do grupo
        int s = stdin.nextInt(); //Origem do grupo
        int montante_maximo = stdin.nextInt(); 
        

        if(k< 1 || k> 50){
            return; 
        }
        if(montante_maximo < 1 || montante_maximo>1000){
            return; 
        }

        //Número de nos
        int n = stdin.nextInt(); 
        if(n>20000 || n<2){
            return; 
        }
        

        //Número de ligacoes
        int r = stdin.nextInt(); 
        if(r>100000 || r<2){
            return; 
        }
        
        //Criacao do grafo
        Graph g = new Graph(n); 
        for(int i = 0; i < r; i++){
            int o = stdin.nextInt(); 
            int f = stdin.nextInt(); 
            int d = stdin.nextInt(); 
            int preco = stdin.nextInt();

            if( d<1 || d>100){
                continue; 
            }
            if(d<k){
                continue; 
            }
        
            g.insert_new_edge(o, f, preco);
        }

        shortestPath(g, s, montante_maximo);
    }
}
