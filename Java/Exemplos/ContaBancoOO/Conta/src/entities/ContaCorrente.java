package entities;

public class ContaCorrente extends Conta {

    public ContaCorrente(Agencia agencia, Cliente cliente) {
        super(agencia, cliente);
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("Tipo de conta: Corrente");
        imprimirInfoComum();
    }

    @Override
    public void transferir(double valor, IConta contaDestino) {
        try {
            this.sacar(valor);
            contaDestino.depositar(valor);
        } catch (Exception e) {
            throw new IllegalArgumentException("Erro ao transferir valor");
        }
    }

    @Override
    public String toString() {
        return "ContaCorrente [agencia=" + agencia + ", cliente=" + cliente +
         ", numeroConta=" + numeroConta + ", saldo=" + saldo + "]";
    }

}
