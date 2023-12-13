package ludopatia.model;

public class Carta {
    private String palo;
    private String valor;

    public Carta(String palo, String valor) {
        this.palo = palo;
        this.valor = valor;
    }

    public String toString() {
        return valor + " de " + palo;
    }

    public void imprimirCarta() {
        String[] lines = {
                "┌───────────┐",
                String.format("│%-5s      │", valor),
                "│           │",
                String.format("│     " + (palo.equals("♦") || palo.equals("♥") ? "\033[31m" : "") + "%-1s   \033[0m  │", palo),
                "│           │",
                String.format("│      %-5s│", valor),
                "└───────────┘"
        };

        for (String line : lines) {
            System.out.println(line);
        }
    }

    public int obtenerValor() {
        switch (valor) {
            case "As" -> {
                return 11;
            }
            case "2" -> {
                return 2;
            }
            case  "3" -> {
                return 3;
            }
            case  "4" -> {
                return 4;
            }
            case  "5" -> {
                return 5;
            }
            case  "6" -> {
                return 6;
            }
            case  "7" -> {
                return 7;
            }
            case  "8"  -> {
                return 8;
            }
            case  "9"  -> {
                return 9;
            }
            case "10","Jota", "Reina", "Rey" -> {
                return 10;
            }
            default -> {
                return 0;
            }
        }
    }
}
/* public int obtenerValor() {  //valores predeterminados de las cartas.
        int valor = 0;
        if(this.valor.equalsIgnoreCase("as")){
            valor=11;
        }else if (this.valor.equalsIgnoreCase("jota")
                || this.valor.equalsIgnoreCase("reina") ||
                this.valor.equalsIgnoreCase("rey") ){
            valor = 10;
        }else{
            valor=Integer.valueOf(this.valor);
        }
        return valor;*/
