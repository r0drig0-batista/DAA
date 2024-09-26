import java.util.*;


public class DFSBFS {

    bool find (int v, int t){

        bool visited[] = new int[n];
        Stack<Integer> s = new Stack<>();
        s.push(s);

        while(!s.isempty()){

            int cur=s.pop();

            if (cur == t) return True;

            for (adj : cur.adjs()){
                if (!visited[adj]){
                    visited[adj]=True;
                    s.push(adj);
                }
            }
        }
        return False;

    }
    
}
