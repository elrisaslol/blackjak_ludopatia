package ludopatia.model;

import java.util.ArrayList;
import java.util.List;

public class Crupier {
    private List<Carta> mano;

    public Crupier() {
        mano = new ArrayList<>();
    }

    public void recibirCarta(Carta carta) {
        mano.add(carta);
    }

    public List<Carta> getMano() {
        return mano;
    }

    public int getPuntos() {
        int puntos = 0;
        int ases = 0;

        for (Carta carta : mano) {
            puntos += carta.obtenerValor();
            if (carta.obtenerValor() == 11) {
                ases++;
            }
        }

        // Ajustar los valores de los ases si es necesario
        while (puntos > 21 && ases > 0) {
            puntos -= 10;
            ases--;
        }

        return puntos;
    }

    public void imprimirCartaVisible() {
        System.out.println("Carta visible del crupier:");
        mano.get(0).imprimirCarta();
        System.out.println("Total de puntos del crupier: " + mano.get(0).obtenerValor());
    }
}
