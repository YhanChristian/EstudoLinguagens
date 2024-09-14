package entities;
public class Agencia  {
    private String numero;
    private String nomeGerente;
    private Banco banco;

    public Agencia(Banco banco, String numero, String nomeGerente) {
        this.banco = banco;
        this.numero = numero;
        this.nomeGerente = nomeGerente;
    }

    public Banco getBanco() {
        return banco;
    }

    public String getNumero() {
        return numero;
    }

    public String getNomeGerente() {
        return nomeGerente;
    }

    @Override
    public String toString() {
        return "Agencia [nomeGerente=" + nomeGerente + ", numero=" + numero + "]";
    }

}
