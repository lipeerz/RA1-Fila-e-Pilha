ELEMENTO.JAVA:
public class Elemento {
    private String nome;
    private int id;

    public Elemento(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Elemento{" +
                "nome='" + nome + '\'' +
                ", id=" + id +
                '}';
    }
}
