import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class App extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Exemplo JavaFX");
        Button button = new Button("Clique aqui");

        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                System.out.println("Clicou no button");
            }
            
        });

        StackPane root = new StackPane();
        root.getChildren().addAll(button);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}
