package ludopatia.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baraja {
    private List<Carta> cartas;

    public Baraja() {
        cartas = new ArrayList<>();
        String[] palos = {"♠", "♥", "♦", "♣"};
        String[] valores = {"As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jota", "Reina", "Rey"};

        for (String palo : palos) {
            for (String valor : valores) {
                cartas.add(new Carta(palo, valor));
            }
        }

        // Barajar las cartas
        Collections.shuffle(cartas);
    }
    public Carta repartirCarta() {
        return cartas.remove(0);
    }
}
