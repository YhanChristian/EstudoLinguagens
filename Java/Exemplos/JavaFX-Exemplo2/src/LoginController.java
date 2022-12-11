import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField tfSenha;

    @FXML
    private TextField tfLogin;

    @FXML
    private Button btnSubmit;

    @FXML
    void submitLogin(ActionEvent event) {
        String user = tfLogin.getText();
        String pass = tfSenha.getText();

        if(user.equals("yhan") && pass.equals("1234"))
        {
            System.out.println("Login realizado com sucesso!");
        }
        else
        {
            System.out.println("Falha na autenticação");
        }

    }

}
