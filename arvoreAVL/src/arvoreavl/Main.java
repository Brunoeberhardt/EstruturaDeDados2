package arvoreavl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
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

            comeco = System.currentTimeMillis();
            // Arvore avl
            ArvoreAvl arvoreAvl = new ArvoreAvl();
            for (int num : nInteger) {
                arvoreAvl.inserir(num);
            }
            // Print
            System.out.println();
            System.out.println("Em ordem");
            arvoreAvl.imprmirEmOrdem(arvoreAvl.raiz);

            fim = System.currentTimeMillis();
            long duracao = fim - comeco;
            long milisegundos = (duracao % 1000) / 100;
            long segundos = (duracao / 1000) % 60;
            long minutos = (duracao / (1000 * 60)) % 60;
            long horas = (duracao / (1000 * 60 * 60)) % 24;

            String tempoAvl = String.format("%02d:%02d:%02d:%02d", horas, minutos, segundos, milisegundos);

            System.out.println("\nTempo para Arvore AVL");
            System.out.println(tempoAvl);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
