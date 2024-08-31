package contador;

public class Contador {
    private int param1;
    private int param2;

    public Contador(int param1, int param2) {
        this.param1 = param1;
        this.param2 = param2;
        validateParams();
    }

    private void validateParams() {
        if (param1 < 0 || param2 < 0) {
            throw new ParametrosInvalidosException("Parâmetros inválidos - valores negativos");
        }
        if (param1 > param2) {
            throw new ParametrosInvalidosException("Parâmetros inválidos: param1 deve ser menor que param2");
        }
    }

    public void contador() {
        int contador = param2 - param1;
        System.out.println("O contador será de " + contador + " passos");
        for (int i = 0; i < contador; i++) {
            System.out.println("Imprimindo a contagem: " + i);
        }
    }
}
