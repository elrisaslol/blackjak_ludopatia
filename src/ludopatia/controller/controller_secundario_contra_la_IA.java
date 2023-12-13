package ludopatia.controller;

import ludopatia.model.*;

import java.util.List;
import java.util.Scanner;

public class controller_secundario_contra_la_IA {
    private static final int APUESTA_MINIMA = 10;

    public static void menu_secundario() {
        Scanner teclado = new Scanner(System.in);

        System.out.print("Ingresa tu nombre: ");
        String nombre = leerNombreJugador(teclado);

        System.out.print("¿Cuántas fichas quieres tener inicialmente? ");
        int fichas = 10;
        fichas= ui.readint("recuerda la apuesta minima es de diez fichas y el maximo de fichas es de 3000000", fichas, 3000000, 9);

        Jugador jugador = new Jugador(nombre, fichas);
        Crupier crupier = new Crupier();
        Baraja baraja = new Baraja();

        while (true) {
            System.out.println("\n=== Nueva Ronda ===");
            System.out.println("Fichas disponibles: " + jugador.getFichas());

            // Realizar apuesta
            System.out.print("Realiza tu apuesta (Mínimo " + APUESTA_MINIMA + "): ");
            jugador.apuesta=ui.readint("",jugador.apuesta,300000,0);

            while (jugador.apuesta < APUESTA_MINIMA || jugador.apuesta > jugador.getFichas()) {
                System.out.println("La apuesta no es válida. Debe ser al menos " + APUESTA_MINIMA + " y no puede exceder ni tus fichas ni la apuesta maxima(300000 fichas).");
                System.out.print("¿Cuántas fichas quieres apostar? ");
                jugador.apuesta = ui.readint("", jugador.apuesta, 300000, 0);
            }

            // Repartir cartas iniciales
            jugador.recibirCarta(baraja.repartirCarta());
            jugador.recibirCarta(baraja.repartirCarta());
            crupier.recibirCarta(baraja.repartirCarta());
            crupier.recibirCarta(baraja.repartirCarta());

            // Mostrar cartas iniciales
            System.out.println("Tus cartas iniciales:");
            for (Carta carta : jugador.getMano()) {
                carta.imprimirCarta();
            }
            System.out.println("Total de puntos: " + jugador.getPuntos());

            crupier.imprimirCartaVisible();

            // Turno del jugador
            while (true) {
                System.out.print("¿Quieres tomar otra carta? (s/n): ");
                String respuesta = teclado.next().toLowerCase();

                if (respuesta.equals("s")) {
                    Carta nuevaCarta = baraja.repartirCarta();
                    jugador.recibirCarta(nuevaCarta);
                    System.out.println("Nueva carta:");
                    nuevaCarta.imprimirCarta();
                    System.out.println("Total de puntos: " + jugador.getPuntos());

                    if (jugador.getPuntos() > 21) {
                        System.out.println("¡Te has pasado de 21! Pierdes la ronda.");
                        break;
                    }
                } else {
                    break;
                }
            }

            // Turno del crupier
            /*while (crupier.getPuntos() < 17) {
                Carta nuevaCarta = baraja.repartirCarta(); //<----mi primera ide de funcionamiento del crupier
                crupier.recibirCarta(nuevaCarta);               //En esta ni siquera se imprimian las cartas
            }*/
            while (crupier.getPuntos() < 17 &&jugador.getPuntos() < 21 && crupier.getPuntos()<jugador.getPuntos()) {
                Carta nuevaCarta = baraja.repartirCarta();                      //<---resultado final
                crupier.recibirCarta(nuevaCarta);                              //no hay quien le gane
                System.out.println("El crupier toma otra carta:");
                nuevaCarta.imprimirCarta();
                System.out.println("Total de puntos del crupier: " + crupier.getPuntos());
            }

            System.out.println("\n=== Fin de la Ronda ===");
            System.out.println("Tus cartas finales:");
            for (Carta carta : jugador.getMano()) {
                carta.imprimirCarta();
            }
            System.out.println("Total de puntos: " + jugador.getPuntos());

            System.out.println("Cartas del crupier:");
            for (Carta carta : crupier.getMano()) {
                carta.imprimirCarta();
            }
            System.out.println("Total de puntos del crupier: " + crupier.getPuntos());

            // Determinar ganador
            if (jugador.getPuntos() > 21) {
                System.out.println("¡Te has pasado de 21! Pierdes la ronda.");
                jugador.restarFichas(jugador.apuesta);
            } else if (crupier.getPuntos() > 21 || jugador.getPuntos() > crupier.getPuntos()) {
                System.out.println("¡Ganaste la ronda!");
                jugador.agregarFichas(jugador.apuesta);
            } else if (jugador.getPuntos() == crupier.getPuntos()) {
                System.out.println("Es un empate. Se devuelve la apuesta.");
            } else {
                System.out.println("El crupier gana. Pierdes la ronda.");
                jugador.restarFichas(jugador.apuesta);
            }

            // Fin de la ronda
            jugador.getMano().clear();
            crupier.getMano().clear();
            System.out.println("\n=== Fin de la Ronda ===");
            System.out.println("¿Quieres jugar otra ronda? (s/n): ");
            String respuesta = teclado.next().toLowerCase();


            if (!respuesta.equals("s")) {

                break;
            }
        }

        System.out.println("\n=== Fin del Juego ===");
        System.out.println("Fichas totales: " + jugador.getFichas());

    }private static String leerNombreJugador(Scanner scanner) {
        String nombre;
        do {
            System.out.print("Ingrese el nombre del jugador: ");
            nombre = scanner.nextLine().trim();

            if (nombre.equalsIgnoreCase("crupier")) {
                System.out.println("Nombre no válido. No se permite 'crupier'.");
                continue;
            }

            if (nombre.isEmpty()) {
                System.out.println("Nombre no válido. No puede estar vacío.");
                continue;
            }
            break;
        } while (true);

        return nombre;
    }

}
