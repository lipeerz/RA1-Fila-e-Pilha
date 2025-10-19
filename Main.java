import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    private static Fila fila = new Fila();
    private static Pilha historico = new Pilha();

    public static void main(String[] args) {
      
        popularFilaInicial();

        
        popularHistoricoInicial();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String opc = "";

        System.out.println("=== Sistema de Gerenciamento de Atendimento ===");
        try {
            while (true) {
                mostrarMenu();
                opc = br.readLine();
                if (opc == null) opc = "";
                opc = opc.trim();

                if (opc.equals("0")) {
                    System.out.println("Encerrando...");
                    break;
                } else if (opc.equals("1")) {
                    fila.imprimir();
                } else if (opc.equals("2")) {
                    atenderProximoCliente();
                } else if (opc.equals("3")) {
                    historico.imprimir();
                } else if (opc.equals("4")) {
                    adicionarSolicitacaoAoHistorico(br);
                } else if (opc.equals("5")) {
                    removerUltimaSolicitacaoDoHistorico();
                } else if (opc.equals("6")) {
                    adicionarClienteNaFila(br);
                } else {
                    System.out.println("Opção inválida. Tente novamente.");
                }
                System.out.println(); 
            }
        } catch (IOException ex) {
            System.out.println("Erro de entrada: " + ex.getMessage());
        }
    }

    private static void mostrarMenu() {
        System.out.println("Escolha uma opção:");
        System.out.println("1 - Ver fila de atendimento");
        System.out.println("2 - Atender próximo cliente (dequeue) -> e adicionar ao histórico se desejar");
        System.out.println("3 - Ver histórico de solicitações (pilha)");
        System.out.println("4 - Adicionar solicitação ao histórico (push)");
        System.out.println("5 - Remover última solicitação do histórico (pop)");
        System.out.println("6 - Adicionar cliente à fila (enqueue)");
        System.out.println("0 - Sair");
        System.out.print("Opção: ");
    }

    private static void atenderProximoCliente() throws IOException {
        if (fila.estaVazia()) {
            System.out.println("Fila vazia. Nenhum cliente para atender.");
            return;
        }
        Elemento atendido = fila.dequeue();
        System.out.println("Atendendo: " + atendido.toString());

        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Deseja salvar atendimento no histórico? (s/n): ");
        String resp = br.readLine();
        if (resp != null && (resp.equalsIgnoreCase("s") || resp.equalsIgnoreCase("sim"))) {
            
            String dataHora = obterDataHoraSimples();
            Elemento req = new Elemento("REQ_AUTO_" + geradorID(), atendido.descricao + " - atendido por operador", dataHora, true);
            historico.push(req);
            System.out.println("Atendimento salvo no histórico.");
        }
    }

    private static void adicionarSolicitacaoAoHistorico(BufferedReader br) throws IOException {
        System.out.print("Digite ID da solicitação (ex: REQ011): ");
        String id = br.readLine();
        System.out.print("Descrição: ");
        String desc = br.readLine();
        System.out.print("Data e Hora (YYYY-MM-DD HH:mm) ou deixe vazio: ");
        String dh = br.readLine();
        if (dh == null || dh.trim().length() == 0) {
            dh = obterDataHoraSimples();
        }
        Elemento e = new Elemento(id, desc, dh, true);
        historico.push(e);
        System.out.println("Solicitação adicionada ao histórico.");
    }

    private static void removerUltimaSolicitacaoDoHistorico() {
        if (historico.estaVazia()) {
            System.out.println("Histórico vazio.");
            return;
        }
        Elemento rem = historico.pop();
        System.out.println("Removido do histórico: " + rem.toString());
    }

    private static void adicionarClienteNaFila(BufferedReader br) throws IOException {
        System.out.print("ID cliente (ex: CLI011): ");
        String id = br.readLine();
        System.out.print("Nome: ");
        String nome = br.readLine();
        System.out.print("Motivo do atendimento: ");
        String motivo = br.readLine();
        Elemento cliente = new Elemento(id, nome, motivo);
        fila.enqueue(cliente);
        System.out.println("Cliente adicionado na fila.");
    }

    
    private static long geradorID() {
        return System.currentTimeMillis() % 100000;
    }

    
    private static String obterDataHoraSimples() {
        java.util.Date d = new java.util.Date();
        
        @SuppressWarnings("deprecation")
        int year = d.getYear() + 1900;
        @SuppressWarnings("deprecation")
        int month = d.getMonth() + 1;
        @SuppressWarnings("deprecation")
        int day = d.getDate();
        @SuppressWarnings("deprecation")
        int hours = d.getHours();
        @SuppressWarnings("deprecation")
        int minutes = d.getMinutes();

        String mm = (month < 10) ? "0" + month : String.valueOf(month);
        String dd = (day < 10) ? "0" + day : String.valueOf(day);
        String hh = (hours < 10) ? "0" + hours : String.valueOf(hours);
        String min = (minutes < 10) ? "0" + minutes : String.valueOf(minutes);

        return year + "-" + mm + "-" + dd + " " + hh + ":" + min;
    }

    
    private static void popularFilaInicial() {
        fila.enqueue(new Elemento("CLI001", "Maria Silva", "Dúvida sobre produto"));
        fila.enqueue(new Elemento("CLI002", "João Souza", "Reclamação de serviço"));
        fila.enqueue(new Elemento("CLI003", "Ana Costa", "Solicitação de reembolso"));
        fila.enqueue(new Elemento("CLI004", "Pedro Alves", "Informações de entrega"));
        fila.enqueue(new Elemento("CLI005", "Carla Dias", "Agendamento de visita"));
        fila.enqueue(new Elemento("CLI006", "Lucas Martins", "Alteração de pedido"));
        fila.enqueue(new Elemento("CLI007", "Patrícia Rocha", "Cancelamento de contrato"));
        fila.enqueue(new Elemento("CLI008", "Rafael Lima", "Renovação de assinatura"));
        fila.enqueue(new Elemento("CLI009", "Fernanda Gomes", "Suporte para instalação"));
        fila.enqueue(new Elemento("CLI010", "Carlos Eduardo", "Pedido de orçamento"));
    }

    
    private static void popularHistoricoInicial() {
        
        
        historico.push(new Elemento("REQ001", "Instalação de software", "2024-08-20 10:30", true));
        historico.push(new Elemento("REQ002", "Manutenção preventiva", "2024-08-20 11:00", true));
        historico.push(new Elemento("REQ003", "Atualização de sistema", "2024-08-20 11:30", true));
        historico.push(new Elemento("REQ004", "Suporte técnico", "2024-08-20 12:00", true));
        historico.push(new Elemento("REQ005", "Troca de equipamento", "2024-08-20 12:30", true));
        historico.push(new Elemento("REQ006", "Consulta de garantia", "2024-08-20 13:00", true));
        historico.push(new Elemento("REQ007", "Reparo de impressora", "2024-08-20 13:30", true));
        historico.push(new Elemento("REQ008", "Configuração de rede", "2024-08-20 14:00", true));
        historico.push(new Elemento("REQ009", "Restauração de dados", "2024-08-20 14:30", true));
        historico.push(new Elemento("REQ010", "Consulta técnica", "2024-08-20 15:00", true));
    }
}
