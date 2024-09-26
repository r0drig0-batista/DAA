import java.util.*;


public class Teste{

    public static int[] bfs_visit(int s,Graph g){

        boolean[] visitados = new boolean[g.num_vertices() + 1];

        int[] pai = new int[g.num_vertices() + 1];

        int[] dist = new int[g.num_vertices() + 1];

        Queue<Integer> fila = new LinkedList<>();

        fila.add(s);

        visitados[s]=true;

        dist[s]=0;

        do{

            int v=fila.poll();

            for (Edge e: g.adjs_no(v)){

                int w = e.endnode();

                if (!visitados[w]){

                    fila.add(w);
                    visitados[w]=true;
                    pai[w]=v;
                    dist[w]= dist[v] + 1;

                }

            }


        } while(!fila.isEmpty());

        return dist;


    }

    public static void main(String[] args){

    }



}
