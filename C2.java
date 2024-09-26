import java.util.Scanner;
import java.util.LinkedList;

//import java.util.*;


//============================== Graph.java 

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

class C2{

    public static void main(String[] args){

        Scanner stdin = new Scanner(System.in);

        int n_trajetos=stdin.nextInt();

        int n_nos=stdin.nextInt();

        Graph g = new Graph(n_nos);

        for (int i=0;i<n_trajetos;i++) {

            int cont=0;

            int k=stdin.nextInt();

            int no1=stdin.nextInt();

            int comp=stdin.nextInt();

            int no2=stdin.nextInt();
            
            if (g.find_edge(no1, no2) == null){
                g.insert_new_edge(no1, no2, comp);
                g.insert_new_edge(no2, no1, comp);
            }

            cont+=comp;

            k=k-2;

            while (k>0) {

                no1=no2;

                comp=stdin.nextInt();

                no2=stdin.nextInt();

                if (g.find_edge(no1, no2) == null){
                    g.insert_new_edge(no1, no2, comp);
                    g.insert_new_edge(no2, no1, comp);
                }

                cont+=comp;

                k--;
            }

            System.out.println("Trajeto " + (i+1) + ": " + cont);

        }

        for (int i = 1; i <= n_nos; i++) {
            LinkedList<Edge> adjacentesNoi = g.adjs_no(i);
            System.out.println("No " + i + ": " + adjacentesNoi.size());
        }


    }

}