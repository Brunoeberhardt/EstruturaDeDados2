package newpackage;

public class NoArvore {

    int info;
    NoArvore direita = null;
    NoArvore esquerda = null;

    void Imp_Cres(NoArvore tree) {
        if (tree != null) {
            Imp_Cres(tree.esquerda);
            System.out.println(tree.info);
            Imp_Cres(tree.direita);
        }
    }

    NoArvore Search(NoArvore tree, int valor) {
        if (tree == null) {
            return null;
        } else if (tree.info > valor) {
            return (Search(tree.esquerda, valor));
        } else if (tree.info < valor) {
            return (Search(tree.direita, valor));
        } else {
            return (tree);
        }
    }

    NoArvore Insere(NoArvore tree, int valor) {
        if (tree == null) {
            tree = new NoArvore();
            tree.info = valor;
            tree.direita = null;
            tree.esquerda = null;
        } else if (valor < tree.info) {
            tree.esquerda = Insere(tree.esquerda, valor);
        } else {
            tree.direita = Insere(tree.direita, valor);
        }
        return (tree);
    }

    NoArvore Retira(NoArvore tree, int valor) {
        if (tree == null) {
            System.out.println("Elemento nao encontrado..: " + valor);
            return (null);
        } else if (tree.info > valor) {
            tree.esquerda = Retira(tree.esquerda, valor);
        } else if (tree.info < valor) {
            tree.direita = Retira(tree.direita, valor);
        } else {
            if ((tree.esquerda == null) && (tree.direita == null)) {
                tree = null;
            } else if (tree.esquerda == null) {
//nó só tem filho a direita NoArvore novo = tree; tree = tree.direita;
            } else if (tree.direita == null) {
//nó só tem filho a esquerda NoArvore novo = tree; tree=tree.esquerda;
            } else {
                NoArvore novo = tree.esquerda;
                while (novo.direita != null) {
                    novo = novo.direita;
                }
                tree.info = novo.info; //troca de informações
                novo.info = valor;
                tree.esquerda = Retira(tree.esquerda, valor);
            }
        }
        return (tree);
    }

    void Imp_CresPreOrdem() {
        if (this != null) {
            System.out.println(this.info);
            if (this.esquerda != null) {
                this.esquerda.Imp_CresPreOrdem();
            }
            if (this.direita != null) {
                this.direita.Imp_CresPreOrdem();
            }
        }
    }

    void Imp_CresInOrdem() {
        if (this != null) {
            if (this.esquerda != null) {
                this.esquerda.Imp_CresInOrdem();
            }
            System.out.println(this.info);
            if (this.direita != null) {
                this.direita.Imp_CresInOrdem();
            }
        }
    }

    void Imp_CresPosOrdem() {
        if (this != null) {
            if (this.esquerda != null) {
                this.esquerda.Imp_CresPosOrdem();
            }
            if (this.direita != null) {
                this.direita.Imp_CresPosOrdem();
            }
            System.out.println(this.info);
        }
    }

}
