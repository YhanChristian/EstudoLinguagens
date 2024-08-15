public class App {
    public static void main(String[] args) throws Exception {
        ContaTerminal conta = new ContaTerminal();
        System.out.println("Por favor, digite o número da Agência!");

        try {
            conta.setNumero(Integer.parseInt(System.console().readLine()));
        } catch (NumberFormatException e) {
            System.out.println("Por favor, informe um número válido!");
            conta.setNumero(Integer.parseInt(System.console().readLine()));
        }
        System.out.println("Por favor, informe a Agência!");

        conta.setAgencia(System.console().readLine());
        if(conta.getAgencia().isEmpty()){
            System.out.println("Por favor, informe a Agência!");
            conta.setAgencia(System.console().readLine());
        }

        System.out.println("Por favor, informe o nome do cliente!");
        
        conta.setNomeCliente(System.console().readLine());
        if(conta.getNomeCliente().isEmpty()){
            System.out.println("Por favor, informe o nome do cliente!");
            conta.setNomeCliente(System.console().readLine());
        }

        System.out.println("Por favor, informe o saldo da conta!");
        try {
            conta.setSaldo(Double.parseDouble(System.console().readLine()));
        } catch (NumberFormatException e) {
            System.out.println("Por favor, informe um número válido!");
            conta.setSaldo(Double.parseDouble(System.console().readLine()));
        }

        conta.imprimeInfoConta();

    }
}