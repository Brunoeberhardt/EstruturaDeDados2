
public class filosofo extends Thread {

    final static int TEMPO_MAXIMO = 100;
    mesa mesa;
    int filosofo;

    public filosofo(String nome, mesa mesadejantar, int fil) {
        super(nome);
        mesa = mesadejantar;
        filosofo = fil;
    }

    public void run() {
        int tempo = 0;
        while (true) {
            tempo = (int) (Math.random() * TEMPO_MAXIMO);
            pensar(tempo);
            getGarfos();
            tempo = (int) (Math.random() * TEMPO_MAXIMO);
            comer(tempo);
            returnGarfos();
        }
    }

    public void pensar(int tempo) {
        try {
            sleep(tempo);
        } catch (InterruptedException e) {
            System.out.println("O Filofoso pensou demais");
        }
    }

    public void comer(int tempo) {
        try {
            sleep(tempo);
        } catch (InterruptedException e) {
            System.out.println("O Filosofo comeu comeus");
        }
    }

    public void getGarfos() {
        mesa.pegarGarfos(filosofo);
    }

    public void returnGarfos() {
        mesa.returnGarfos(filosofo);
    }
}
