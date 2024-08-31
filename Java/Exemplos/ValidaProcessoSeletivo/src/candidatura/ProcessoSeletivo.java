package candidatura;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ProcessoSeletivo {
    private static final double SALARIO_BASE = 2000.0;
    private static final int NUMERO_VAGAS = 5;
    private static final int TENTATIVAS_LIGACAO = 3;
    private String[] selecionados = new String[NUMERO_VAGAS];

    public ProcessoSeletivo() {
        System.out.println("Processo Seletivo Iniciado");
    }

    public void analisarCandidato(Double salarioPretendido) {
        if (SALARIO_BASE > salarioPretendido) {
            System.out.println("LIGAR PARA O CANDITADO");
            return;
        }
        if (SALARIO_BASE == salarioPretendido) {
            System.out.println("LIGAR PARA O CANDITADO COM CONTRAPROPOSTA");
            return;
        }
        System.out.println("AGUARDANDO O RESULTADO DOS DEMAIS CANDIDATOS");
    }

    public void selecionaCandidatos() {
        String[] candidatos = { "JOAO", "MARIA", "JOSÉ", "ANA", "PEDRO", "LUCAS", "LUCIA", "CARLOS", "FERNANDA",
                "MARCOS" };
        int candidatosSelecionados = 0;
        int candidatoAtual = 0;
        while (candidatosSelecionados < NUMERO_VAGAS && candidatoAtual < candidatos.length) {
            String candidato = candidatos[candidatoAtual];
            double salarioPretendido = geraSalarioPretendido();
            System.out
                    .println("Analisando candidato: " + candidato + " com salário pretendido de " + salarioPretendido);
            if (SALARIO_BASE >= salarioPretendido) {
                System.out.println("O Candidato " + candidato + " selecionado");
                selecionados[candidatosSelecionados] = candidato;
                candidatosSelecionados++;
            } else {
                System.out.println("O Candidato " + candidato + " não foi selecionado");
            }
            candidatoAtual++;
        }
    }

    public void imprimirCandidatosSelecionados() {
        System.out.println("Candidatos Selecionados");
        for (String selecionado : selecionados) {
            if (selecionado == null)
                break;
            System.out.println("Candidato Selecionado: " + selecionado);
        }
    }

    public void ligarParaCandidatosSelecionados() {
        for (String selecionado : selecionados) {
            if (selecionado == null)
                break;
            entrouEmContato(selecionado);
        }
    }

    private static void entrouEmContato(String candidato) {
        int tentativasRealizadas = 1;
        boolean continuarTentando = true;
        boolean atendeu = false;
        do {
            atendeu = canditadoAtendeuLigacao();
            continuarTentando = !atendeu;
            if (!continuarTentando) {
                System.out.println("CONTADO REALIZADO COM SUCESSO");
                break;
            }
            tentativasRealizadas++;

        } while (continuarTentando && tentativasRealizadas < TENTATIVAS_LIGACAO);

        if (atendeu) {
            System.out.println("Candidato " + candidato + " atendeu a ligação");
            return;
        }
        System.out.println("Candidato " + candidato + " não atendeu a ligação após " +
                TENTATIVAS_LIGACAO + " tentativas");
    }

    private static double geraSalarioPretendido() {
        return ThreadLocalRandom.current().nextDouble(1800.0, 2200.0);
    }

    private static boolean canditadoAtendeuLigacao() {
        return new Random().nextInt(3) == 1;
    }
}
