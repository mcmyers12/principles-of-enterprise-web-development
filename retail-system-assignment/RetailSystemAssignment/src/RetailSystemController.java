
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

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

	@FXML
	private TableView<ObservableList<StringProperty>> table;

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

	@FXML
	private void handleListAllCustomers(ActionEvent event) throws IOException {
		// if (event.getSource() == addNewCustomerOk) {
		// }
		populateCustomerTable();
		System.out.println("List all customers");
	}

	@FXML
	private void handleListAllMerchandise(ActionEvent event) throws IOException {
		// if (event.getSource() == addNewCustomerOk) {
		// }
		populateMerchandiseTable();
		System.out.println("List all merchandise");
	}

	private void populateCustomerTable() {
		table.getItems().clear();
		table.getColumns().clear();
		table.setPlaceholder(new Label("Loading..."));

		final String[] headerValues = { "First Name", "Last Name", "Street Address", "City", "State", "Zipcode",
				"Gender" };
		for (int column = 0; column < headerValues.length; column++) {
			table.getColumns().add(createColumn(column, headerValues[column]));
		}

		final String[] dataValues = { "1", "2", "3", "4", "5", "6", "7" };
		// Add additional columns if necessary:
		for (int columnIndex = table.getColumns().size(); columnIndex < dataValues.length; columnIndex++) {
			table.getColumns().add(createColumn(columnIndex, ""));
		}
		// Add data to table:
		ObservableList<StringProperty> data = FXCollections.observableArrayList();
		for (String value : dataValues) {
			data.add(new SimpleStringProperty(value));
		}
		table.getItems().add(data);

	}

	private void populateMerchandiseTable() {
		table.getItems().clear();
		table.getColumns().clear();
		table.setPlaceholder(new Label("Loading..."));

		final String[] headerValues = { "Name", "Price", "Description" };
		for (int column = 0; column < headerValues.length; column++) {
			table.getColumns().add(createColumn(column, headerValues[column]));
		}

		final String[] dataValues = { "1", "2", "3" };
		// Add additional columns if necessary:
		for (int columnIndex = table.getColumns().size(); columnIndex < dataValues.length; columnIndex++) {
			table.getColumns().add(createColumn(columnIndex, ""));
		}
		// Add data to table:
		ObservableList<StringProperty> data = FXCollections.observableArrayList();
		for (String value : dataValues) {
			data.add(new SimpleStringProperty(value));
		}
		table.getItems().add(data);

	}

	private TableColumn<ObservableList<StringProperty>, String> createColumn(final int columnIndex,
			String columnTitle) {
		TableColumn<ObservableList<StringProperty>, String> column = new TableColumn<>();
		String title;
		if (columnTitle == null || columnTitle.trim().length() == 0) {
			title = "Column " + (columnIndex + 1);
		} else {
			title = columnTitle;
		}
		column.setText(title);
		column.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<ObservableList<StringProperty>, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							CellDataFeatures<ObservableList<StringProperty>, String> cellDataFeatures) {
						ObservableList<StringProperty> values = cellDataFeatures.getValue();
						if (columnIndex >= values.size()) {
							return new SimpleStringProperty("");
						} else {
							return cellDataFeatures.getValue().get(columnIndex);
						}
					}
				});
		return column;
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

	public class PersonData {
		private String firstName;
		private String lastName;
		private String streetAddress;
		private String city;
		private String state;
		private String zipCode;
		private String gender;

		public PersonData(String firstName, String lastName, String streetAddress, String city, String state,
				String zipCode, String gender) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.streetAddress = streetAddress;
			this.city = city;
			this.state = state;
			this.zipCode = zipCode;
			this.gender = gender;
		}

		public String getFirstName() {
			return firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public String getStreetAddress() {
			return streetAddress;
		}

		public String getCity() {
			return city;
		}

		public String getState() {
			return state;
		}

		public String getZipCode() {
			return zipCode;
		}

		public String getGender() {
			return gender;
		}
	}

}