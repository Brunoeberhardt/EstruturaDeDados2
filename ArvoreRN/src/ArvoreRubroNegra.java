
public class ArvoreRubroNegra {

    NodeRB raiz;

    // Métodos de inserção e outras operações aqui...
    // Método para inserir um valor na árvore Rubro-Negra
    public void inserir(int valor) {
        raiz = inserirRecursivo(raiz, valor);
        raiz.setCor(NodeRB.Cor.PRETO); // A raiz sempre deve ser preta
    }

    // Método para inserção recursiva
    private NodeRB inserirRecursivo(NodeRB no, int valor) {
        if (no == null) {
            return new NodeRB(valor, NodeRB.Cor.VERMELHO); // Novos nós são sempre vermelhos
        }

        if (valor < no.getValor()) {
            no.setEsquerda(inserirRecursivo(no.getEsquerda(), valor));
        } else if (valor > no.getValor()) {
            no.setDireita(inserirRecursivo(no.getDireita(), valor));
        } else {
            // Valor já existe, não faz nada
            return no;
        }

        // Realiza as operações de balanceamento e ajuste de cores
        if (ehVermelho(no.getDireita()) && !ehVermelho(no.getEsquerda())) {
            no = rotacaoEsquerda(no);
        }
        if (ehVermelho(no.getEsquerda()) && ehVermelho(no.getEsquerda().getEsquerda())) {
            no = rotacaoDireita(no);
        }
        if (ehVermelho(no.getEsquerda()) && ehVermelho(no.getDireita())) {
            inverterCores(no);
        }

        return no;
    }

    // Método para remover um valor da árvore Rubro-Negra
    public void remover(int valor) {
        if (raiz != null) {
            raiz = removerRecursivo(raiz, valor);
            if (raiz != null) {
                raiz.setCor(NodeRB.Cor.PRETO); // A raiz sempre deve ser preta
            }
        }
    }

// Método de remoção recursiva
    private NodeRB removerRecursivo(NodeRB no, int valor) {
        if (valor < no.getValor()) {
            no.setEsquerda(removerRecursivo(no.getEsquerda(), valor));
        } else if (valor > no.getValor()) {
            no.setDireita(removerRecursivo(no.getDireita(), valor));
        } else {
            if (no.getEsquerda() == null) {
                return no.getDireita();
            } else if (no.getDireita() == null) {
                return no.getEsquerda();
            }

            NodeRB sucessor = encontrarMenorValor(no.getDireita());
            no.setValor(sucessor.getValor());
            no.setDireita(removerMenorValor(no.getDireita()));
        }

        // Realiza as operações de balanceamento e ajuste de cores após a remoção
        if (ehVermelho(no.getDireita()) && !ehVermelho(no.getEsquerda())) {
            no = rotacaoEsquerda(no);
        }
        if (ehVermelho(no.getEsquerda()) && ehVermelho(no.getEsquerda().getEsquerda())) {
            no = rotacaoDireita(no);
        }
        if (ehVermelho(no.getEsquerda()) && ehVermelho(no.getDireita())) {
            inverterCores(no);
        }

        return no;
    }

    // Métodos de rotação e inversão de cores
    private NodeRB rotacaoEsquerda(NodeRB no) {
        NodeRB temp = no.getDireita();
        no.setDireita(temp.getEsquerda());
        temp.setEsquerda(no);
        temp.setCor(no.getCor());
        no.setCor(NodeRB.Cor.VERMELHO);
        return temp;
    }

    private NodeRB rotacaoDireita(NodeRB no) {
        NodeRB temp = no.getEsquerda();
        no.setEsquerda(temp.getDireita());
        temp.setDireita(no);
        temp.setCor(no.getCor());
        no.setCor(NodeRB.Cor.VERMELHO);
        return temp;
    }

    private void inverterCores(NodeRB no) {
        no.inverterCor();
        no.getEsquerda().inverterCor();
        no.getDireita().inverterCor();
    }

    private boolean ehVermelho(NodeRB no) {
        if (no == null) {
            return false;
        }
        return no.getCor() == NodeRB.Cor.VERMELHO;
    }

    // Métodos auxiliares de remoção
    private NodeRB encontrarMenorValor(NodeRB no) {
        while (no.getEsquerda() != null) {
            no = no.getEsquerda();
        }
        return no;
    }

    private NodeRB removerMenorValor(NodeRB no) {
        if (no.getEsquerda() == null) {
            return no.getDireita();
        }
        no.setEsquerda(removerMenorValor(no.getEsquerda()));

        return no;
    }
// Método para imprimir em ordem (in-order traversal)

    public void imprimirEmOrdem() {
        imprimirEmOrdemRecursivo(raiz);
    }

// Método de impressão em ordem recursivo
    private void imprimirEmOrdemRecursivo(NodeRB no) {
        if (no != null) {
            imprimirEmOrdemRecursivo(no.getEsquerda());
            System.out.print("\n " + no.getValor());
            imprimirEmOrdemRecursivo(no.getDireita());
        }
    }

}
