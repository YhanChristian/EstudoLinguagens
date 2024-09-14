package entities;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private String nome;
    private String cnpj;

    public Banco(String nome, String cnpj) {
        this.nome = nome;
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    @Override
    public String toString() {
        return "Banco [cnpj=" + cnpj + ", nome=" + nome + "]";
    }

}
