public class Fila {
    private Node frente;
    private Node tras;

    public Fila() {
        frente = null;
        tras = null;
    }

   
    public boolean estaVazia() {
        return frente == null;
    }

   
    public void enqueue(Elemento e) {
        Node novo = new Node(e);
        if (estaVazia()) {
            frente = novo;
            tras = novo;
        } else {
            tras.next = novo;
            tras = novo;
        }
    }

    
    public Elemento dequeue() {
        if (estaVazia()) {
            return null;
        }
        Elemento ret = frente.data;
        frente = frente.next;
        if (frente == null) tras = null;
        return ret;
    }

    
    public Elemento peek() {
        if (estaVazia()) return null;
        return frente.data;
    }

    
    public void imprimir() {
        if (estaVazia()) {
            System.out.println("[Fila vazia]");
            return;
        }
        System.out.println("=== Fila de Atendimento (Frente -> Tras) ===");
        Node atual = frente;
        while (atual != null) {
            System.out.println(atual.data.toString());
            atual = atual.next;
        }
    }
}
