
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent load = FXMLLoader
				.load(getClass().getResource("/blokkzaro_2/view/project.fxml"));
		Scene value = new Scene(load);
		primaryStage.setScene(value);

		primaryStage.setResizable(true);
		primaryStage.setTitle("Project Planner");
		primaryStage.show();
	}

}
