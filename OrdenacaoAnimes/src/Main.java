
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        // Criar uma lista de 1000 animes com valores aleatórios
        List<Anime> listaDeAnimes = new ArrayList<>();
        Random random = new Random();

        // Antes da ordenação
        System.out.println("Lista de Animes antes da Ordenacao:");
        for (int i = 1; i <= 1000; i++) {
            String titulo = "anime" + i;
            String genero = "genero" + random.nextInt(10);
            int numEpisodios = random.nextInt(50) + 1;
            double avaliacao = random.nextDouble() * 10;

            Anime anime = new Anime(titulo, genero, numEpisodios, avaliacao);
            listaDeAnimes.add(anime);

            System.out.println("Titulo: " + anime.titulo + ", Genero: " + anime.genero + ", Numero de Episodios: " + anime.numEpisodios + ", Avaliacao: " + anime.avaliacao);
        }

        // Ordenar a lista de animes por avaliação usando Merge Sort
        List<Anime> listaOrdenadaMergeSort = AnimeSorter.mergeSort(new ArrayList<>(listaDeAnimes));

        // Depois da ordenação
        System.out.println("\nLista de Animes depois da Ordenacao:");
        for (Anime anime : listaOrdenadaMergeSort) {
            System.out.println("Titulo: " + anime.titulo + ", Genero: " + anime.genero + ", Numero de Episodios: " + anime.numEpisodios + ", Avaliacao: " + anime.avaliacao);
        }

        // Realizar uma pesquisa de um anime específico usando Insertion Sort
        String animePesquisado = "anime500";
        Anime resultadoPesquisa = AnimeSorter.pesquisaPorInsertion(new ArrayList<>(listaOrdenadaMergeSort), animePesquisado);

        // Imprimir o resultado da pesquisa
        if (resultadoPesquisa != null) {
            System.out.println("\nAnime Encontrado:");
            System.out.println("Titulo: " + resultadoPesquisa.titulo);
            System.out.println("Genero: " + resultadoPesquisa.genero);
            System.out.println("Numero de Episodios: " + resultadoPesquisa.numEpisodios);
            System.out.println("Avaliacao: " + resultadoPesquisa.avaliacao);
            System.out.println("Posição no Ranking: " + (listaOrdenadaMergeSort.indexOf(resultadoPesquisa) + 1));
        } else {
            System.out.println("\nAnime nao encontrado.");
        }
    }
}
