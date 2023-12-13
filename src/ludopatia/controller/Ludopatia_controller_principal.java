package ludopatia.controller;


import ludopatia.model.ui;
import ludopatia.view.vista;

public class Ludopatia_controller_principal {


    public static void mainmenu() {
        vista.inicio();
        int i = 0;
        i = ui.readint("****************\n"+"Elija una opción:  \n"+"****************",i,3,0);
        switch (i) {
            case 1:
                opcion1();
                break;
            case 2:
                opcion2();
                break;
            case 3:
                opcion3();
                break;
            default:
                System.out.println("mi pana ya la cagaste");
        }
    }
    public static void opcion1() {
        vista.primera_opcion();
        int i = 0;
        i = ui.readint("****************\n"+"Elija un opción: \n"+"****************",i,3,0);
        switch (i) {
            case 1:
                subopcion1();
                break;
            case 2:
                subopcion1_2();
                break;
            case 3:
                mainmenu();
                break;
            default:
                System.out.println("mi pana ya la cagaste");
        }
    }
    public static void subopcion1(){
        vista.primera_opcion_subopcio1_1();
        controller_secundario_contra_la_IA.menu_secundario();
        vista.primera_opcion_subopcio1_2();
        int i = 0;
        i = ui.readint("",i,1,0);
        if (i==1){
            mainmenu();
        }
    }
    public static void subopcion1_2(){
        vista.primera_opcion_subopcio2_1();
        controller_secundario_contra_gente.menu_secundario();
        vista.primera_opcion_subopcio2_2();
        int i = 0;
        i = ui.readint("",i,1,0);
        if (i==1){
            mainmenu();
        }
    }
    public static void opcion2() {
        vista.segunda_opcion();
        int i = 0;
        i = ui.readint("****************\n"+"Elija un opción: \n"+"****************",i,2,0);
        switch (i) {
            case 1:
                subopcion2();
                break;
            case 2:
                mainmenu();
                break;
            default:
                System.out.println("mi pana ya la cagaste");
        }
    }
    public static void subopcion2(){
        vista.segunda_opcion_subopcion1();
        int i = 0;
        i = ui.readint("",i,1,0);
        if (i == 1) {
            opcion2();
        }
    }
    public static void opcion3() {
        vista.tercera_opcion();
    }
}
