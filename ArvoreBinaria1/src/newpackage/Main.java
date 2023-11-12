package newpackage;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        NoArvore arvore = new NoArvore();
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            int numero = random.nextInt(101);
            arvore = arvore.Insere(arvore, numero);
        }

        System.out.println("Impressao em Pre-Ordem:");
        arvore.Imp_CresPreOrdem();

        System.out.println("\nImpressao em In-Ordem:");
        arvore.Imp_CresInOrdem();

        System.out.println("\nImpressao em Pos-Ordem:");
        arvore.Imp_CresPosOrdem();

        System.out.println("\nImpressao em Nivel:");
        printEmNivel(arvore);

       
        for (int i = 0; i < 5; i++) {
            int numero = random.nextInt(101);
            arvore = arvore.Retira(arvore, numero);
        }

        System.out.println("\nApos a retirada de 5 elementos:");

        NoArvore copiaArvore = arvore; 

        System.out.println("Impressao em Pre-Ordem:");
        copiaArvore.Imp_CresPreOrdem();

        System.out.println("\nImpressao em In-Ordem:");
        copiaArvore.Imp_CresInOrdem();

        System.out.println("\nImpressao em Pos-Ordem:");
        copiaArvore.Imp_CresPosOrdem();

        System.out.println("\nImpressao em Nivel:");
        printEmNivel(copiaArvore);
    }

    
    static void printEmNivel(NoArvore raiz) {
        int altura = alturaArvore(raiz);
        for (int i = 1; i <= altura; i++) {
            printNivel(raiz, i);
            System.out.println();
        }
    }

    
    static void printNivel(NoArvore no, int nivel) {
        if (no == null) {
            return;
        }
        if (nivel == 1) {
            System.out.print(no.info + " ");
        } else if (nivel > 1) {
            printNivel(no.esquerda, nivel - 1);
            printNivel(no.direita, nivel - 1);
        }
    }

    
    static int alturaArvore(NoArvore no) {
        if (no == null) {
            return 0;
        } else {
            int alturaEsquerda = alturaArvore(no.esquerda);
            int alturaDireita = alturaArvore(no.direita);
            return Math.max(alturaEsquerda, alturaDireita) + 1;
        }
    }
}
