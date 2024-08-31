import candidatura.ProcessoSeletivo;

public class App {
    public static void main(String[] args) throws Exception {
        ProcessoSeletivo processo = new ProcessoSeletivo();
        processo.analisarCandidato(1500.0);
        processo.analisarCandidato(2000.0);
        processo.selecionaCandidatos();
        processo.imprimirCandidatosSelecionados();
        processo.ligarParaCandidatosSelecionados();

    }
}
