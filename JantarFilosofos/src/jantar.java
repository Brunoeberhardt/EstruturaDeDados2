
public class jantar {

    public static void main(String[] args) {
        mesa mesa = new mesa();
        for (int filosofo = 0; filosofo < 5; filosofo++) {
            new filosofo("Filosofo_" + filosofo, mesa, filosofo).start();
        }
    }
}
