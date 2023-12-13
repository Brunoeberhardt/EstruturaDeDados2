
public class NodeRB {

    public enum Cor {
        VERMELHO,
        PRETO
    }

    private int valor;
    private NodeRB esquerda;
    private NodeRB direita;
    private Cor cor;

    public NodeRB(int valor, Cor cor) {
        this.valor = valor;
        this.cor = cor;
        this.esquerda = null;
        this.direita = null;
    }

    // Métodos getters e setters
    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public NodeRB getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(NodeRB esquerda) {
        this.esquerda = esquerda;
    }

    public NodeRB getDireita() {
        return direita;
    }

    public void setDireita(NodeRB direita) {
        this.direita = direita;
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    // Método para inverter a cor do nó
    public void inverterCor() {
        this.cor = (this.cor == Cor.VERMELHO) ? Cor.PRETO : Cor.VERMELHO;
    }
}
