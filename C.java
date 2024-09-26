import java.util.*;

public class C {
    public static void main(String[] args) {
        
        Scanner stdin = new Scanner(System.in);

        Map<Integer,Integer> tipos = new HashMap<>();

        int n_tipos = stdin.nextInt();

        int count=0;

        for(int i=0; i<n_tipos; i++){
            int t = stdin.nextInt();
            int v = stdin.nextInt();
            tipos.put(t,v);
        }

        int n_pessoas = stdin.nextInt();

        for(int j=0; j<n_pessoas; j++){
            int n_opcoes = stdin.nextInt();
            boolean sentado = false;

            for(int g=0; g<n_opcoes; g++){
                int cadeira = stdin.nextInt();
                if(tipos.containsKey(cadeira) && tipos.get(cadeira)>0 && !sentado){
                    sentado=true;
                    tipos.put(cadeira,tipos.get(cadeira)-1);
                }
            }

            if (sentado==false) count++;
        }

        System.out.println(count);

        int soma=0;

        for (int v : tipos.values()) {
            soma += v;
        }

        System.out.println(soma);

    }
}
