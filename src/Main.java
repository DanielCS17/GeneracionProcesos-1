import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("¿Cuantos blocs de notas quieres abrir?: ");
        int winNumber = sc.nextInt();

        while (winNumber < 0){
            System.out.println("ERROR! No se permiten números negativos \n");
            System.out.print("¿Cuantos blocs de notas quieres abrir?: ");
            winNumber = sc.nextInt();
        }
        if (winNumber == 0) {
            System.out.println("AVISO! ha introducido 0, por lo que no se abrirán Notepads \n");
        }

        ArrayList<Process> listOfProcess = new ArrayList<>(winNumber);

        ProcessBuilder process = new ProcessBuilder();
        if (System.getProperty("os.name").startsWith("Windows")) {
            process.command("cmd.exe", "/c", "notepad");
        } else process.command("firefox");

        try {

            int processNumber = winNumber;

            while (processNumber != 0) {
                listOfProcess.add(process.start());
                processNumber -= 1;
            }

            int processCount = 0;
            while (listOfProcess.size() != 0) {
                if (!listOfProcess.get(processCount).isAlive()) {
                    listOfProcess.remove(processCount);
                }
            }

            System.out.println("El proceso de apertura de " + winNumber + " Notepads ha finalizado correctamente");

        } catch (IOException | IllegalArgumentException | InputMismatchException e) {
            System.out.println("ERROR! Parámetros de entrada incorrectos.");
        }
    }
}
