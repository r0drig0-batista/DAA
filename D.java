import java.util.*;

public class D {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int n_pessoas = stdin.nextInt();
        int n_tarefas = stdin.nextInt();

        Map<Integer, Set<Integer>> dicionario = new HashMap<>();
        Map<Integer, Integer> capacidades = new HashMap<>(); // Armazena a capacidade máxima de tarefas aceitas por pessoa

        // Ler informações sobre as pessoas e suas capacidades
        for (int i = 0; i < n_pessoas; i++) {
            int cod_pessoa = stdin.nextInt();
            int n_tarefas_pessoa = stdin.nextInt();
            int max_tarefas = stdin.nextInt();

            Set<Integer> tarefas = new HashSet<>();
            for (int j = 0; j < n_tarefas_pessoa; j++) {
                tarefas.add(stdin.nextInt());
            }

            dicionario.put(cod_pessoa, tarefas);
            capacidades.put(cod_pessoa, max_tarefas);
        }

        // Ler propostas e calcular as tarefas atribuídas para cada pessoa
        Map<Integer, Integer> tarefasAtribuidas = new HashMap<>();
        for (int i = 0; i < n_tarefas; i++) {
            int cod_tarefa = stdin.nextInt();
            int cod_pessoa = stdin.nextInt();

            if (cod_tarefa == 0 && cod_pessoa == 0) {
                break;
            }

            if (dicionario.containsKey(cod_pessoa) && dicionario.get(cod_pessoa).contains(cod_tarefa)) {
                int capacidadeAtual = tarefasAtribuidas.getOrDefault(cod_pessoa, 0);
                int maxCapacidade = capacidades.get(cod_pessoa);

                if (capacidadeAtual < maxCapacidade) {
                    tarefasAtribuidas.put(cod_pessoa, capacidadeAtual + 1);
                }
            }
        }

        // Calcular a taxa de esforço para cada pessoa
        Map<Integer, Double> taxaEsforco = new HashMap<>();
        for (int cod_pessoa : tarefasAtribuidas.keySet()) {
            int tarefasAtrib = tarefasAtribuidas.get(cod_pessoa);
            int maxCapacidade = capacidades.get(cod_pessoa);
            double taxa = (double) tarefasAtrib / maxCapacidade;
            taxaEsforco.put(cod_pessoa, taxa);
        }

        // Encontrar as propostas que satisfazem as condições
        int propostasSatisfatorias = 0;
        int melhorProposta = -1;
        double melhorTaxa = Double.NEGATIVE_INFINITY;

        for (int i = 0; i < n_tarefas; i++) {
            int cod_tarefa = stdin.nextInt();
            int cod_pessoa = stdin.nextInt();

            if (cod_tarefa == 0 && cod_pessoa == 0) {
                break;
            }

            if (dicionario.containsKey(cod_pessoa) && dicionario.get(cod_pessoa).contains(cod_tarefa)) {
                propostasSatisfatorias++;
                double taxa = taxaEsforco.getOrDefault(cod_pessoa, 0.0);
                if (taxa > melhorTaxa) {
                    melhorTaxa = taxa;
                    melhorProposta = i + 1;
                }
            }
        }

        // Imprimir resultado
        System.out.println(propostasSatisfatorias + "/" + n_tarefas);
        if (melhorProposta != -1) {
            System.out.println(melhorProposta);
        }
    }
}
