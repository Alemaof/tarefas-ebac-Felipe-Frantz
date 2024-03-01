import dinamica.FatorialDinamica;
import pura.FatorialPura;

public class Main {
    public static void main(String[] args) {

        int n = 23;
        FatorialPura fatorialPura = new FatorialPura();
        System.out.println(n + "! = " + fatorialPura.fatorial(n));

        FatorialDinamica fatorialDinamica = new FatorialDinamica();
        System.out.println(n + "! = " + fatorialDinamica.calcFatorial(n));

    }
}