
import java.util.ArrayList;
import java.util.List;

class AnimeSorter {

    // Método de Merge Sort para ordenar a lista de animes por avaliação
    public static List<Anime> mergeSort(List<Anime> animes) {
        if (animes.size() <= 1) {
            return animes;
        }

        int mid = animes.size() / 2;
        List<Anime> leftHalf = mergeSort(animes.subList(0, mid));
        List<Anime> rightHalf = mergeSort(animes.subList(mid, animes.size()));

        return merge(leftHalf, rightHalf);
    }

    private static List<Anime> merge(List<Anime> left, List<Anime> right) {
        List<Anime> result = new ArrayList<>();
        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex).avaliacao > right.get(rightIndex).avaliacao) {
                result.add(left.get(leftIndex));
                leftIndex++;
            } else {
                result.add(right.get(rightIndex));
                rightIndex++;
            }
        }

        result.addAll(left.subList(leftIndex, left.size()));
        result.addAll(right.subList(rightIndex, right.size()));

        return result;
    }

    // Método de Selection Search para pesquisa em uma lista ordenada
    public static Anime pesquisaPorInsertion(List<Anime> animes, String titulo) {
        for (int i = 1; i < animes.size(); i++) {
            Anime chave = animes.get(i);
            int j = i - 1;

            // Move os elementos da lista que são maiores que a chave para uma posição à frente
            while (j >= 0 && animes.get(j).avaliacao > chave.avaliacao) {
                animes.set(j + 1, animes.get(j));
                j = j - 1;
            }
            animes.set(j + 1, chave);
        }

        // Procura pelo anime com o título desejado
        for (Anime anime : animes) {
            if (anime.titulo.equals(titulo)) {
                return anime;
            }
        }

        return null;
    }
}
