package ludopatia.controller;

import ludopatia.model.Baraja;
import ludopatia.model.Carta;
import ludopatia.model.Jugador;
import ludopatia.model.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;









public class controller_secundario_contra_gente {
    private static final int APUESTA_MINIMA = 10;

    public static void menu_secundario() {
        Scanner Teclado = new Scanner(System.in);

        System.out.println("¿Cuántos jugadores participarán? ");
        System.out.println("recuerda que como minimo necesitas 2 jugadores para poder jugar juntos ");
        int numJugadores = 2;
        numJugadores = ui.readint("",numJugadores,4,1);


        List<Jugador> jugadores = new ArrayList<>();

        for (int i = 1; i <= numJugadores; i++) {
            System.out.print("Nombre del Jugador " + i + ": ");
            String nombre = leerNombreJugador(Teclado,jugadores);
            System.out.println("Fichas iniciales para " + nombre + ": ");
            int fichas = 10;
            fichas = ui.readint("recuerda la apuesta minima es de diez fichas y el maximo de fichas es de 3000000", fichas, 3000000, 9);

            jugadores.add(new Jugador(nombre,fichas));
        }



            Baraja baraja = new Baraja();

        while (true) {
            // Definir apuestas iniciales
           for (Jugador jugador : jugadores) {
                System.out.print(jugador.getNombre() + ", ¿Cuántas fichas quieres apostar? (Mínimo " + APUESTA_MINIMA + "): ");
                jugador.apuesta = 10;
                jugador.apuesta=ui.readint("",jugador.apuesta,300000,0);

                while (jugador.apuesta < APUESTA_MINIMA || jugador.apuesta > jugador.getFichas()) {
                    System.out.println("La apuesta no es válida. Debe ser al menos " + APUESTA_MINIMA + " y no puede exceder ni tus fichas ni la apuesta maxima(300000 fichas).");
                    System.out.print("¿Cuántas fichas quieres apostar? ");
                    jugador.apuesta = ui.readint("", jugador.apuesta, 300000, 0);
                }

                jugador.restarFichas(jugador.apuesta);

            }

            // Repartir cartas iniciales
            for (int a = 0; a < 2; a++) {
                for (Jugador jugador : jugadores) {
                    Carta carta = baraja.repartirCarta();
                    jugador.recibirCarta(carta);
                }
            }

            // Mostrar cartas iniciales de los jugadores
            for (Jugador jugador : jugadores) {
                System.out.println(jugador.getNombre() + " tiene las siguientes cartas:");
                for (Carta carta : jugador.getMano()) {
                    carta.imprimirCarta();
                }
                System.out.println("Total de puntos: " + jugador.getPuntos() + "\n");
            }

            // Turno de los jugadores
            for (Jugador jugador : jugadores) {
                while (true) {
                    System.out.print(jugador.getNombre() + ", ¿Quieres tomar otra carta? (s/n): ");
                    System.out.println("Total de puntos: " + jugador.getPuntos() + "\n");
                    String respuesta = Teclado.nextLine().toLowerCase();

                    if (respuesta.equals("s")) {
                        Carta nuevaCarta = baraja.repartirCarta();
                        jugador.recibirCarta(nuevaCarta);
                        System.out.println("Has recibido una nueva carta:");
                        nuevaCarta.imprimirCarta();
                        System.out.println("Total de puntos: " + jugador.getPuntos());

                        if (jugador.getPuntos() > 21) {
                            System.out.println("¡Te has pasado de 21! Has perdido.");
                            break;
                        }
                    } else if (respuesta.equals("n")) {
                        break;
                    } else {
                        System.out.println("Respuesta no válida. Por favor, ingresa 's' o 'n'.");
                    }
                }
            }

            // Determinar el resultado del juego y distribuir fichas
            Jugador ganador = determinarGanador(jugadores);
            System.out.println("\n¡" + ganador.getNombre() + " es el ganador!");

            // Mostrar fichas finales
            for (Jugador jugador : jugadores) {
                System.out.println(jugador.getNombre() + ", fichas restantes: " + jugador.getFichas());
            }

            // Preguntar si quieren jugar otra vez
            System.out.print("¿Quieren jugar otra vez? (s/n): ");
            String jugarOtraVez = Teclado.nextLine().toLowerCase();
            if (!jugarOtraVez.equals("s")) {
                break;
            }

            // Recoger cartas y barajar de nuevo para la próxima ronda
            for (Jugador jugador : jugadores) {
                jugador.getMano().clear();
            }
            baraja = new Baraja();
        }


    }

    private static Jugador determinarGanador(List<Jugador> jugadores) {
        Jugador ganador = jugadores.get(0);
        Jugador empate = jugadores.get(0);
        for (Jugador jugador : jugadores) {
            if (jugador.getPuntos() <= 21 && jugador.getPuntos() != ganador.getPuntos()&&jugador.getPuntos() > ganador.getPuntos() || ganador.getPuntos() > 21) {
                ganador = jugador;
            }else if(jugador.getPuntos() == ganador.getPuntos()){
                empate=jugador;
            }


        }

        // Devolver las apuestas al ganador
        for (Jugador jugador : jugadores) {
            if (jugador.equals(ganador)) {
                jugador.agregarFichas(jugador.getapuesta()*2); // Doble de la apuesta
            }if (jugador.equals(empate)){
                jugador.agregarFichas(jugador.getapuesta());
            }
        }

        return ganador;
    }
    private static String leerNombreJugador(Scanner scanner, List<Jugador> jugadores) {
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

            String finalNombre = nombre;
            boolean nombreExistente = jugadores.stream()
                    .anyMatch(jugador -> jugador.getNombre().equalsIgnoreCase(finalNombre));

            if (nombreExistente) {
                System.out.println("Nombre no válido. Ya existe un jugador con ese nombre.");
                continue;
            }

            break;
        } while (true);

        return nombre;
    }
}
