public class ContaTerminal {
    private Integer numero;
    private String agencia;
    private String nomeCliente;
    private double saldo;

    public ContaTerminal() {
        this.numero = 0;
        this.agencia = "";
        this.nomeCliente = "";
        this.saldo = 0.0;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void imprimeInfoConta() {
        System.out.println("Olá, " + nomeCliente + ", sua conta de número " + numero + " na agência " + agencia
                + " e seu saldo de R$ " + saldo +
                " já esta disponível para saque");
    }
}
