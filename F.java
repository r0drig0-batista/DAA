import java.util.Scanner;
import java.util.PriorityQueue;

class Cliente implements Comparable<Cliente> {
    int hora;
    int minuto;
    int duracao;

    public Cliente(int hora, int minuto, int duracao) {
        this.hora = hora;
        this.minuto = minuto;
        this.duracao = duracao;
    }

    public int compareTo(Cliente other) {
        if (this.hora != other.hora) {
            return this.hora - other.hora;
        } else {
            return this.minuto - other.minuto;
        }
    }
}

public class F {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int K = scanner.nextInt();
        PriorityQueue<Cliente> fila = new PriorityQueue<>();
        int perdidos = 0;
        int horaAtual = 0;
        int minutoAtual = 0;

        for (int i = 0; i < K; i++) {
            int hora = scanner.nextInt();
            int minuto = scanner.nextInt();
            int duracao = scanner.nextInt();

            // Atualiza a hora atual para o horário do cliente
            if (horaAtual < hora || (horaAtual == hora && minutoAtual <= minuto)) {
                horaAtual = hora;
                minutoAtual = minuto;
            }

            // Verifica se o barbeiro está livre no momento da chegada do cliente
            if (fila.isEmpty() && (horaAtual < 12 || (horaAtual >= 15 && horaAtual < 19))) {
                horaAtual = hora;
                minutoAtual = minuto;
            }

            // Verifica se o cliente pode ser atendido
            if (horaAtual < 12 || (horaAtual >= 15 && horaAtual < 19)) {
                // Adiciona o cliente à fila
                fila.offer(new Cliente(hora, minuto, duracao));

                // Atende o cliente se o barbeiro estiver livre
                if (horaAtual < 12 || (horaAtual >= 15 && horaAtual < 19)) {
                    Cliente clienteAtendido = fila.poll();
                    minutoAtual += clienteAtendido.duracao;
                    if (minutoAtual >= 60) {
                        horaAtual++;
                        minutoAtual -= 60;
                    }
                }
            } else {
                // Cliente não pode entrar
                perdidos++;
            }
        }

        System.out.println("Perdeu = " + perdidos);
    }
}
