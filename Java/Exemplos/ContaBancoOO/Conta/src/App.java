import entities.Agencia;
import entities.Banco;
import entities.Cliente;
import entities.ContaCorrente;
import entities.ContaPoupanca;

public class App {
    public static void main(String[] args) throws Exception {

        /*Primeiro Cliente */
        Cliente cliente = new Cliente("Fulano", "123.456.789-00", "Rua 1", "1234-5678");
        Banco banco = new Banco("Banco do Brasil", "00.000.000/0001-91");
        Agencia agencia = new Agencia(banco, "1234", "Ciclano");
        ContaPoupanca cp = new ContaPoupanca(agencia, cliente);
        cp.imprimirExtrato();

        /* Segundo Cliente */
        Cliente cliente2 = new Cliente("Ciclano", "987.654.321-00", "Rua 2", "8765-4321");
        ContaCorrente cc = new ContaCorrente(agencia, cliente2);
        
        try {
            cp.depositar(100);
            cp.imprimirExtrato();
        } catch (Exception e) {
            printException(e);
        }   

        /*Aqui estoura Exceção - Pois conta Poupança não transfere */
        try {
            cp.transferir(50, cc);
            cp.imprimirExtrato();
        } catch (Exception e) {
            printException(e);
        }

        try {
            cc.depositar(50);
            cc.imprimirExtrato();
        } catch (Exception e) {
            printException(e);
        }

        try {
            cc.transferir(20, cp);
            cc.imprimirExtrato();
        } catch (Exception e) {
            printException(e);
        }
    }

    private static void printException(Exception e) {
        System.out.println(e.getMessage());
    }
}
