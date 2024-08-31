import contador.Contador;
import contador.ParametrosInvalidosException;

public class App {
    public static void main(String[] args) throws Exception {
        getParametros();
    }

    static void getParametros() {
        boolean valid = false; 
        do {
            System.out.println("Insira o valor de param1: ");
            int param1 = Integer.parseInt(System.console().readLine());
            System.out.println("Insira o valor de param2: ");
            int param2 = Integer.parseInt(System.console().readLine());
            valid = atualizaContador(param1, param2);
        } while (!valid);
    }

    static boolean atualizaContador(int param1, int param2) {
        try {
            Contador contador = new Contador(param1, param2);
            contador.contador();
            return true;
        } catch (ParametrosInvalidosException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
