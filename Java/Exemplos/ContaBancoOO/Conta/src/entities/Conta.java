package entities;

public class Conta implements IConta {
    protected Agencia agencia;
    protected int numeroConta;
    protected double saldo;
    protected Cliente cliente;

    private static int NUMERO_CONTA_SEQUENCIAL = 1;

    public Conta(Agencia agencia, Cliente cliente) {
        this.agencia = agencia;
        this.cliente = cliente;
        this.numeroConta = NUMERO_CONTA_SEQUENCIAL++;
    }

    public Agencia getAgencia() {
        return agencia;
    }
    
    public int getNumeroConta() {
        return numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    protected void imprimirInfoComum() {
        System.out.println("Cliente: " + this.cliente.getNome());
        System.out.println("Banco: " + this.agencia.getBanco().getNome());
        System.out.println("Agência: " + this.agencia.getNumero());
        System.out.println("Conta: " + this.numeroConta);
        System.out.println(String.format("Saldo: %.2f", this.saldo));
    }

    @Override
    public void sacar(double valor) {
        if(valor <= 0) {
            throw new IllegalArgumentException("Valor inválido");
        }
        if(this.saldo < valor) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
        this.saldo -= valor;
    }

    @Override
    public void depositar(double valor) {
        if(valor <= 0) {
            throw new IllegalArgumentException("Valor inválido");
        }
        this.saldo += valor;
    }

    @Override
    public void transferir(double valor, IConta contaDestino) {
        throw new UnsupportedOperationException("Operação não suportada");
    }

    @Override
    public void imprimirExtrato() {
        throw new UnsupportedOperationException("Operação não suportada");
    }
}
