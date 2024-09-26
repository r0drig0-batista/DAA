import java.util.*;

public class E {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Stack<Integer> locais = new Stack<>();
        Set<Integer> locaisVisitados = new HashSet<>();

        int local;
        while ((local = scanner.nextInt()) != 0) {
            if (!locaisVisitados.contains(local)){
                locais.push(local);
                locaisVisitados.add(local);
            }

            if (locaisVisitados.contains(local)){
                while (locais.peek()!=local){
                    locaisVisitados.remove(locais.pop());
                }
            }
        }

        List<Integer> resp = new ArrayList<>();

        while (!locais.isEmpty()){
            int a=locais.pop();
            resp.add(a);
        }

        for (int i=resp.size() - 1; i>=0; i--){
            System.out.println(resp.get(i));
        }

        scanner.close();
    }
}

