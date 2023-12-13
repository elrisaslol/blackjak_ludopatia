package ludopatia.model;



import java.util.InputMismatchException;
import java.util.Scanner;

public class ui {


    public static int readint(String msg,int x,int valormaximo,int valorminimo){

        Scanner teclado= new Scanner(System.in);
        do {
            System.out.println(msg);
            try{
                x=teclado.nextInt();
            }catch (InputMismatchException e){
                x=-1;
                teclado.next();
                System.out.println("numeros bobo numeros...");
            }
            if (x<=valorminimo||x>valormaximo) {
                System.out.println("recuerda el numero tiene que ser valido ");
            }



        }while (x<=valorminimo||x>valormaximo);

        return x;



    }




}
