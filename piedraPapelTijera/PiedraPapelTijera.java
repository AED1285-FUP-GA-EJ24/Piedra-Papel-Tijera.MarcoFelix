package piedraPapelTijera;

import java.util.Scanner;

public class PiedraPapelTijera {
    private static final String PIEDRA = "P";
    private static final String PAPEL = "L";
    private static final String TIJERAS = "T";
    private static final String SALIR = "S";
    private static final String[] JUEGO = {PIEDRA, PAPEL, TIJERAS};
    private static final int EMPATE = 0;
    private static final int GANAS = 1;
    private static final int PIERDES = 2;
    private static final int ERROR_NO_ENCONTRADA = -1;

    // Mensajes al usuario
    private static final String BIENVENIDA = "¡Bienvenido al juego Piedra-papel-tijeras!";
    private static final String OPCIONES = PIEDRA + " (piedra), " + PAPEL + " (papel), " + TIJERAS + " (tijeras) o " + SALIR + " (salir)";
    private static final String PEDIR_JUGADA = "¿Cuál es tu jugada? " + OPCIONES;
    private static final String PEDIR_NUEVA_JUGADA = "¿Cuál es tu nueva jugada? " + OPCIONES;
    private static final String FIN = "Fin de la partida";
    private static final String MSJ_ERROR_NO_ENCONTRADA = "No entiendo tu jugada";

    public static void main(String[] args) {
        // Abrimos un scanner para leer la entrada del usuario
        Scanner s = new Scanner(System.in);

        // Instrucciones
        System.out.println(BIENVENIDA);
        System.out.println(PEDIR_JUGADA);

        while (true) { // Iteramos para siempre
            // Jugada del ordenador
            int eleccionPC = (int) (Math.random() * JUEGO.length);

            // Jugada del usuario
            String sEleccionUsuario = s.next();
            if (sEleccionUsuario.equalsIgnoreCase(SALIR)) {
                break; // Si nos da una S, cortamos el bucle para terminar
            }

            // Interpretación de la jugada del usuario
            int eleccionUsuario = convertir(sEleccionUsuario);
            if (eleccionUsuario == ERROR_NO_ENCONTRADA) {
                System.err.println(MSJ_ERROR_NO_ENCONTRADA);
                continue; // Seguimos en el bucle, siguiente iteración
            }

            // Calcular el ganador de la jugada
            int resultado = usuarioGana(eleccionPC, eleccionUsuario);

            // Mostrar el resultado de la jugada
            switch (resultado) {
                case GANAS:
                    System.out.println("¡Enhorabuena! Tu " + JUEGO[eleccionUsuario] + " gana a " + JUEGO[eleccionPC]);
                    break;
                case PIERDES:
                    System.out.println("¡Lo siento! Tu " + JUEGO[eleccionUsuario] + " pierde ante " + JUEGO[eleccionPC]);
                    break;
                case EMPATE:
                    System.out.println("¡Empate a " + JUEGO[eleccionPC] + "!");
                    break;
            }
            System.out.println(PEDIR_NUEVA_JUGADA);
        }

        System.out.println(FIN);
    }

    private static int convertir(String jugada) {
        switch (jugada.toUpperCase()) {
            case PIEDRA:
                return 0;
            case PAPEL:
                return 1;
            case TIJERAS:
                return 2;
            default:
                return ERROR_NO_ENCONTRADA;
        }
    }

    private static int usuarioGana(int eleccionPC, int eleccionUsuario) {
        if (eleccionPC == eleccionUsuario) {
            return EMPATE;
        }

        switch (eleccionUsuario) {
            case 0: // Piedra
                return (eleccionPC == 2) ? GANAS : PIERDES;
            case 1: // Papel
                return (eleccionPC == 0) ? GANAS : PIERDES;
            case 2: // Tijeras
                return (eleccionPC == 1) ? GANAS : PIERDES;
            default:
                return ERROR_NO_ENCONTRADA;
        }
    }
}