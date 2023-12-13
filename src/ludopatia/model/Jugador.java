package ludopatia.model;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nombre;
    private List<Carta> mano;
    private int fichas;
    public int apuesta;
    public Jugador(String nombre, int fichas) {
        this.nombre = nombre;
        this.fichas = fichas;
        this.apuesta= apuesta;
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

    public String getNombre() {
        return nombre;
    }

    public int getFichas() {
        return fichas;
    }
    public int getapuesta() {
        return apuesta;
    }

    public void restarFichas(int cantidad) {
        fichas -= cantidad;
    }

    public void agregarFichas(int cantidad) {
        fichas += cantidad;
    }
}
