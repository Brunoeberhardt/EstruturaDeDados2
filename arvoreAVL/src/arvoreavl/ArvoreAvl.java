package arvoreavl;

import java.util.ArrayList;

public class ArvoreAvl {

    NoAvl raiz;

    // Método para inserir um valor na árvore AVL
    public void inserir(int k) {
        NoAvl n = new NoAvl(k);
        inserirAVL(this.raiz, n);
    }

    // Método para a inserção AVL
    public void inserirAVL(NoAvl aComparar, NoAvl aInserir) {
        // Se o nó de comparação for nulo, o nó a ser inserido torna-se a raiz
        if (aComparar == null) {
            this.raiz = aInserir;

        } else {
            // Compara as chaves para decidir se o novo nó vai para a esquerda ou direita
            if (aInserir.chave < aComparar.chave) {

                if (aComparar.esquerda == null) {
                    aComparar.esquerda = aInserir;
                    aInserir.pai = aComparar;
                    verificarBalanceamento(aComparar);

                } else {
                    inserirAVL(aComparar.esquerda, aInserir);
                }

            } else if (aInserir.chave > aComparar.chave) {

                if (aComparar.direita == null) {
                    aComparar.direita = aInserir;
                    aInserir.pai = aComparar;
                    verificarBalanceamento(aComparar);

                } else {
                    inserirAVL(aComparar.direita, aInserir);
                }

            } else {
                // O nó já existe
            }
        }
    }

    // Método para verificar e realizar balanceamento
    public void verificarBalanceamento(NoAvl atual) {
        setBalanceamento(atual);
        int balanceamento = atual.balanceamento;

        if (balanceamento == -2) {
            // Rotações para balanceamento quando o fator de equilíbrio é -2
            if (altura(atual.esquerda.esquerda) >= altura(atual.esquerda.direita)) {
                atual = rotacaoDireita(atual);

            } else {
                atual = duplaRotacaoEsquerdaDireita(atual);
            }

        } else if (balanceamento == 2) {
            // Rotações para balanceamento quando o fator de equilíbrio é 2
            if (altura(atual.direita.direita) >= altura(atual.direita.esquerda)) {
                atual = rotacaoEsquerda(atual);

            } else {
                atual = duplaRotacaoDireitaEsquerda(atual);
            }
        }
        // Verifica o balanceamento do pai recursivamente
        if (atual.pai != null) {
            verificarBalanceamento(atual.pai);
        } else {
            this.raiz = atual;// Se o nó atual não tem pai, então ele é a nova raiz
        }
    }

    // Método para remover um valor da árvore AVL
    public void remover(int k) {
        removerAVL(this.raiz, k);
    }

    // Método para a remoção AVL
    public void removerAVL(NoAvl atual, int k) {
        if (atual == null) {
            return;

        } else {

            if (atual.chave > k) {
                removerAVL(atual.esquerda, k);

            } else if (atual.chave < k) {
                removerAVL(atual.direita, k);

            } else if (atual.chave == k) {
                removerNoAvlEncontrado(atual);
            }
        }
    }

    // Método para remover um nó AVL encontrado
    public void removerNoAvlEncontrado(NoAvl aRemover) {
        NoAvl r;
        // Caso em que o nó a ser removido tem apenas um filho ou nenhum filho
        if (aRemover.esquerda == null || aRemover.direita == null) {
            // Se o nó a ser removido não tem pai, então ele é a raiz
            if (aRemover.pai == null) {
                this.raiz = null;
                aRemover = null;
                return;
            }
            r = aRemover;

        } else {
            // Caso em que o nó a ser removido tem dois filhos
            r = sucessor(aRemover);
            aRemover.chave = r.chave;
        }
        // Substitui o nó a ser removido pelo seu filho (se houver)
        NoAvl p;
        if (r.esquerda != null) {
            p = r.esquerda;
        } else {
            p = r.direita;
        }
        // Atualiza o pai do filho, se existir
        if (p != null) {
            p.pai = r.pai;
        }
        // Se o nó a ser removido não tem pai, então o filho (se houver) torna-se a nova raiz
        if (r.pai == null) {
            this.raiz = p;
        } else {
            // Caso contrário, atualiza a referência do pai para o novo filho e verifica o balanceamento
            if (r == r.pai.esquerda) {
                r.pai.esquerda = p;
            } else {
                r.pai.direita = p;
            }
            verificarBalanceamento(r.pai);
        }
        r = null;// Limpa a referência ao nó removido
    }

    public NoAvl rotacaoEsquerda(NoAvl inicial) {

        NoAvl direita = inicial.direita;
        direita.pai = inicial.pai;

        inicial.direita = direita.esquerda;

        if (inicial.direita != null) {
            inicial.direita.pai = inicial;
        }

        direita.esquerda = inicial;
        inicial.pai = direita;

        if (direita.pai != null) {

            if (direita.pai.direita == inicial) {
                direita.pai.direita = direita;

            } else if (direita.pai.esquerda == inicial) {
                direita.pai.esquerda = direita;
            }
        }

        setBalanceamento(inicial);
        setBalanceamento(direita);

        return direita;
    }

    public NoAvl rotacaoDireita(NoAvl inicial) {

        NoAvl esquerda = inicial.esquerda;
        esquerda.pai = inicial.pai;

        inicial.esquerda = esquerda.direita;

        if (inicial.esquerda != null) {
            inicial.esquerda.pai = inicial;
        }

        esquerda.direita = inicial;
        inicial.pai = esquerda;

        if (esquerda.pai != null) {

            if (esquerda.pai.direita == inicial) {
                esquerda.pai.direita = esquerda;

            } else if (esquerda.pai.esquerda == inicial) {
                esquerda.pai.esquerda = esquerda;
            }
        }

        setBalanceamento(inicial);
        setBalanceamento(esquerda);

        return esquerda;
    }

    public NoAvl duplaRotacaoEsquerdaDireita(NoAvl inicial) {
        inicial.esquerda = rotacaoEsquerda(inicial.esquerda);
        return rotacaoDireita(inicial);
    }

    public NoAvl duplaRotacaoDireitaEsquerda(NoAvl inicial) {
        inicial.direita = rotacaoDireita(inicial.direita);
        return rotacaoEsquerda(inicial);
    }

    // Método para encontrar o sucessor
    public NoAvl sucessor(NoAvl q) {
        // Se o nó q possuir filho a direita
        if (q.direita != null) {
            NoAvl r = q.direita;
            while (r.esquerda != null) {
                r = r.esquerda;
            }
            return r;
            // Se o nó q não possui um filho à direita
        } else {
            NoAvl p = q.pai;
            while (p != null && q == p.direita) {
                q = p;
                p = q.pai;
            }
            return p;
        }
    }

    // Método para calcular a altura de um nó
    private int altura(NoAvl atual) {
        if (atual == null) {
            return -1;
        }

        if (atual.esquerda == null && atual.direita == null) {
            return 0;

        } else if (atual.esquerda == null) {
            return 1 + altura(atual.direita);

        } else if (atual.direita == null) {
            return 1 + altura(atual.esquerda);

        } else {
            return 1 + Math.max(altura(atual.esquerda), altura(atual.direita));
        }
    }

    private void setBalanceamento(NoAvl no) {
        no.balanceamento = altura(no.direita) - altura(no.esquerda);
    }

    final protected ArrayList<NoAvl> inorder() {
        ArrayList<NoAvl> ret = new ArrayList<NoAvl>();
        inorder(raiz, ret);
        return ret;
    }

    final protected void inorder(NoAvl no, ArrayList<NoAvl> lista) {
        if (no == null) {
            return;
        }
        inorder(no.esquerda, lista);
        lista.add(no);
        inorder(no.direita, lista);
    }

    public void imprmirEmOrdem(NoAvl node) {
        if (node != null) {
            imprmirEmOrdem(node.esquerda);
            System.out.print(" \n" + node.chave);
            imprmirEmOrdem(node.direita);
        }
    }

    public void imprimirPreOrdem(NoAvl node) {
        if (node != null) {
            System.out.print(" " + node.chave);
            imprimirPreOrdem(node.esquerda);
            imprimirPreOrdem(node.direita);
        }
    }

    public void imprimirPosOrdem(NoAvl node) {
        if (node != null) {
            imprimirPosOrdem(node.esquerda);
            imprimirPosOrdem(node.direita);
            System.out.print(" " + node.chave);
        }
    }
}
