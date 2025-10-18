
public class Pilha {
    private Node topo;

    public Pilha() {
        topo = null;
    }

   
    public boolean estaVazia() {
        return topo == null;
    }

   
    public void push(Elemento e) {
        Node novo = new Node(e);
        novo.next = topo;
        topo = novo;
    }

   
    public Elemento pop() {
        if (estaVazia()) {
            return null;
        }
        Elemento ret = topo.data;
        topo = topo.next;
        return ret;
    }

    
    public void limpar() {
        topo = null;
    }

  
    public Elemento topo() {
        if (estaVazia()) return null;
        return topo.data;
    }

    
    public void imprimir() {
        if (estaVazia()) {
            System.out.println("[Histórico vazio]");
            return;
        }
        System.out.println("=== Histórico (Top -> Bottom) ===");
        Node atual = topo;
        while (atual != null) {
            System.out.println(atual.data.toString());
            atual = atual.next;
        }
    }
}
