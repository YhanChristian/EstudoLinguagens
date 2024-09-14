package entities;
public class ContaPoupanca extends Conta{

    public ContaPoupanca(Agencia agencia, Cliente cliente) {
        super(agencia, cliente);
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("Tipo de conta: Poupança");
        imprimirInfoComum();
    }

    @Override
    public String toString() {
        return "ContaPoupanca [agencia=" + agencia + ", cliente=" + cliente +
         ", numeroConta=" + numeroConta + ", saldo=" + saldo + "]";
    }
}
