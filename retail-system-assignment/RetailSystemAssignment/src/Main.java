import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	public static Stage primaryStage;

	public static void main(String[] args) {
		Application.launch(Main.class, (java.lang.String[]) null);
	}

	@Override
	public void start(Stage primaryStage) {
		Main.primaryStage = primaryStage;
		try {
			Scene scene = new Scene(FXMLLoader.load(Main.class.getResource("RetailSystem.fxml")));
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception ex) {
			System.out.println("Error loading application");
			ex.printStackTrace();
		}
	}
}
