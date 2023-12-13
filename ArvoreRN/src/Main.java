import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        File file = new File("C:/Users/bruno/OneDrive/Documentos/dados/dados100_mil.txt");
        List<String> lines = new ArrayList<>();
        long comeco, fim;

        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                lines.add(scan.nextLine());
            }

            List<Integer> nIntegerList = new ArrayList<>();
            for (String line : lines) {
                String[] numbers = line.split(", ");
                for (String num : numbers) {
                    try {
                        int parsedNum = Integer.parseInt(num);
                        nIntegerList.add(parsedNum);
                    } catch (NumberFormatException e) {
                        System.err.println("Erro ao converter numero: " + num);
                    }
                }
            }

            int[] nInteger = nIntegerList.stream().mapToInt(Integer::intValue).toArray();

            // Criar árvore Rubro-Negra
            comeco = System.currentTimeMillis();
            ArvoreRubroNegra arvoreRubroNegra = new ArvoreRubroNegra();
            for (int num : nInteger) {
                arvoreRubroNegra.inserir(num);
            }

            // Impressão em ordem da árvore Rubro-Negra após a inserção inicial
            System.out.println("Arvore Rubro-Negra após insercao inicial:");
            arvoreRubroNegra.imprimirEmOrdem();

            // Sorteio de 50.000 números entre -9999 e 9999
            Random random = new Random();
            for (int i = 0; i < 50000; i++) {
                int sorteado = random.nextInt(19999) - 9999; // Números entre -9999 e 9999

                if (sorteado % 3 == 0) {
                    arvoreRubroNegra.inserir(sorteado);
                } else if (sorteado % 5 == 0) {
                    arvoreRubroNegra.remover(sorteado);
                } else {
                    // Contar quantas vezes esse número aparece na árvore
                    int ocorrencias = contarOcorrencias(arvoreRubroNegra.raiz, sorteado);
                    System.out.println("Numero " + sorteado + " aparece " + ocorrencias + " vezes na arvore.");
                }
            }

            // Impressão em ordem da árvore Rubro-Negra após as operações
            System.out.println("\nArvore Rubro-Negra apos operacoes:");
            arvoreRubroNegra.imprimirEmOrdem();

            fim = System.currentTimeMillis();
            long duracao = fim - comeco;
            long milisegundos = (duracao % 1000) / 100;
            long segundos = (duracao / 1000) % 60;
            long minutos = (duracao / (1000 * 60)) % 60;
            long horas = (duracao / (1000 * 60 * 60)) % 24;

            String tempoRubroNegra = String.format("%02d:%02d:%02d:%02d", horas, minutos, segundos, milisegundos);

            System.out.println("\nTempo para Arvore Rubro-Negra");
            System.out.println(tempoRubroNegra);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Método para contar ocorrências de um número na árvore
    private static int contarOcorrencias(NodeRB no, int numero) {
        if (no == null) {
            return 0;
        }

        if (no.getValor() == numero) {
            return 1 + contarOcorrencias(no.getEsquerda(), numero) + contarOcorrencias(no.getDireita(), numero);
        } else if (numero < no.getValor()) {
            return contarOcorrencias(no.getEsquerda(), numero);
        } else {
            return contarOcorrencias(no.getDireita(), numero);
        }
    }
}
