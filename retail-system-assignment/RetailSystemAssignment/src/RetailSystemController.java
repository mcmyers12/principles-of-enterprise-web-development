
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RetailSystemController implements Initializable {

	@FXML
	private MenuBar menuBar;

	@FXML
	private MenuItem listAllEmployees;

	@FXML
	private MenuItem addNewMerchandise;

	@FXML
	private MenuItem listAllCustomers;

	@FXML
	private MenuItem addNewEmployee;

	@FXML
	private MenuItem addNewCustomer;

	@FXML
	private MenuItem listAllMerchandise;

	@FXML
	private Button addNewCustomerOk;

	@FXML
	private ChoiceBox<String> stateChoiceBox;

	@Override // Called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		System.out.println(fxmlFileLocation);
		if (fxmlFileLocation.toString().contains(("RetailSystem.fxml"))) {
			addNewCustomer.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					System.out.println("CLICK");

					Stage stage = new Stage();
					Parent root;
					try {
						root = FXMLLoader.load(getClass().getResource("AddNewCustomer.fxml"));
						stage.setScene(new Scene(root));
						stage.setTitle("My modal window");
						stage.initModality(Modality.APPLICATION_MODAL);
						// stage.initOwner(addNewCustomerOk.getScene().getWindow());
						stage.showAndWait();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			});
		}
		if (fxmlFileLocation.toString().contains(("AddNewCustomer.fxml"))) {
			initializeStateChoiceBoxOptions(stateChoiceBox);
		}

	}

	@FXML
	private void handleAddNewCustomer(ActionEvent event) throws IOException {
		// if (event.getSource() == addNewCustomerOk) {
		// }
		System.out.println("Add new customer");
	}

	public void initializeStateChoiceBoxOptions(ChoiceBox<String> choiceBox) {
		ObservableList<String> interestRateOptions = FXCollections.observableArrayList("Alabama", "Alaska", "Arizona",
				"Arkansas", "California", "Colorado", "Connecticut", "Delaware", "District of Columbia", "Florida",
				"Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine",
				"Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska",
				"Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota",
				"Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota",
				"Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin",
				"Wyoming");
		choiceBox.setItems(interestRateOptions);
	}

}