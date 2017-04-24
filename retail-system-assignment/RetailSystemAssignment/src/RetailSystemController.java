
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;

public class RetailSystemController implements Initializable {

	private RetailSystemDatabase rsdb = new RetailSystemDatabase();

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
	private GridPane outerGrid;

	@FXML
	private GridPane newCustomerGrid;

	@FXML
	private GridPane newEmployeeGrid;

	@FXML
	private GridPane newMerchandiseGrid;

	@FXML
	private Button cancelNewCustomer;

	@FXML
	private Button cancelNewEmployee;

	@FXML
	private Button cancelNewMerchandise;

	@FXML
	private ToggleGroup genderToggleGroup;

	@FXML
	private TextField lastNameInput;

	@FXML
	private TextField streetAddressInput;

	@FXML
	private TextField cityInput;

	@FXML
	private TextField zipCodeInput;

	@FXML
	private TableView<ObservableList<StringProperty>> table;

	@FXML
	private MenuItem closeWindow;

	@FXML
	private TextField firstNameInput;

	@FXML
	private Label addCustomerLabel;

	@FXML
	private TextField cityEmInput;

	@FXML
	private ChoiceBox<String> stateChoiceBoxEm;

	@FXML
	private TextField lastNameEmInput;

	@FXML
	private TextField zipCodeEmInput;

	@FXML
	private TextField firstNameEmInput;

	@FXML
	private TextField streetAddressEmInput;

	@FXML
	private ToggleGroup genderToggleGroupEm;

	@FXML
	private Button addNewEmployeeOk;

	@FXML
	private TextField priceInput;

	@FXML
	private TextArea descriptionInput;

	@FXML
	private TextField nameInput;

	@FXML
	private Button addNewMerchandiseOk;

	@FXML
	private Label addMerchandiseLabel;

	@FXML
	private Label addEmployeeLabel;

	@FXML
	private Label tableLabel;

	@Override // Called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		System.out.println(fxmlFileLocation);
		initializeDB();

		if (fxmlFileLocation.toString().contains(("AddNewCustomer.fxml"))) {
			initializeStateChoiceBoxOptions(stateChoiceBox);
		}
		if (fxmlFileLocation.toString().contains(("AddNewEmployee.fxml"))) {
			initializeStateChoiceBoxOptions(stateChoiceBoxEm);
		}
	}

	private void initializeDB() {
		rsdb.createRetailSystemDB();
		rsdb.createDBTables();
	}

	@FXML
	private void handleAddNewCustomer(ActionEvent event) throws IOException {
		System.out.println("Add new customer");

		String firstName = firstNameInput.getText();
		String lastName = lastNameInput.getText();
		String streetAddress = streetAddressInput.getText();
		String city = cityInput.getText();
		String state = stateChoiceBox.getValue();
		if (state == null) {
			state = "";
		}
		String zipcode = zipCodeInput.getText();

		String gender;
		RadioButton selectedRadioButton = (RadioButton) genderToggleGroup.getSelectedToggle();
		if (selectedRadioButton == null) {
			gender = "";
		} else {
			gender = selectedRadioButton.getText();
		}

		System.out.println("Adding customer to db: ");
		System.out.println("\t" + firstName + " " + lastName + " " + streetAddress + " " + city + " " + state + " "
				+ zipcode + " " + gender + " ");
		rsdb.insertIntoCustomerTable(firstName, lastName, streetAddress, city, state, zipcode, gender);

		addCustomerLabel.setText("Customer added with values: " + firstName + ", " + lastName + ", " + streetAddress
				+ ", " + city + ", " + state + ", " + zipcode + ", " + gender + " ");
	}

	@FXML
	private void handleAddNewMerchandise(ActionEvent event) throws IOException {
		System.out.println("Add new merchandise");

		String name = nameInput.getText();
		String price = priceInput.getText();
		String description = descriptionInput.getText();

		System.out.println("Adding merchandise to db: ");
		System.out.println("\t" + name + " " + price + " " + description);
		rsdb.insertIntoMerchandiseTable(name, price, description);

		addMerchandiseLabel.setText("Merchandise added with values: " + name + ", " + price + ", " + description);
	}

	@FXML
	private void handleAddNewEmployee(ActionEvent event) throws IOException {
		System.out.println("Add new employee");

		String firstName = firstNameEmInput.getText();
		String lastName = lastNameEmInput.getText();
		String streetAddress = streetAddressEmInput.getText();
		String city = cityEmInput.getText();
		String state = stateChoiceBoxEm.getValue();
		if (state == null) {
			state = "";
		}
		String zipcode = zipCodeEmInput.getText();

		String gender;
		RadioButton selectedRadioButton = (RadioButton) genderToggleGroupEm.getSelectedToggle();
		if (selectedRadioButton == null) {
			gender = "";
		} else {
			gender = selectedRadioButton.getText();
		}

		System.out.println("Adding employee to db: ");
		System.out.println("\t" + firstName + " " + lastName + " " + streetAddress + " " + city + " " + state + " "
				+ zipcode + " " + gender + " ");
		rsdb.insertIntoEmployeeTable(firstName, lastName, streetAddress, city, state, zipcode, gender);

		addEmployeeLabel.setText("Employee added with values: " + firstName + ", " + lastName + ", " + streetAddress
				+ ", " + city + ", " + state + ", " + zipcode + ", " + gender + " ");
	}

	@FXML
	private void close(ActionEvent event) throws IOException {
		System.out.println("Close");

		if (event.getSource() == closeWindow) {
			Window stage = outerGrid.getScene().getWindow();
			stage.hide();
		}

		if (event.getSource() == cancelNewCustomer) {
			Window stage = newCustomerGrid.getScene().getWindow();
			stage.hide();
		}
		if (event.getSource() == cancelNewEmployee) {
			Window stage = newEmployeeGrid.getScene().getWindow();
			stage.hide();
		}
		if (event.getSource() == cancelNewMerchandise) {
			Window stage = newMerchandiseGrid.getScene().getWindow();
			stage.hide();
		}
	}

	@FXML
	private void popUpAddNew(ActionEvent event) throws IOException {
		System.out.println("Pop up add new");

		Stage stage = new Stage();
		Parent root;
		try {
			if (event.getSource() == addNewEmployee) {
				root = FXMLLoader.load(getClass().getResource("AddNewEmployee.fxml"));
				stage.setScene(new Scene(root));
				stage.setTitle("Add New Employee");
			} else if (event.getSource() == addNewCustomer) {
				root = FXMLLoader.load(getClass().getResource("AddNewCustomer.fxml"));
				stage.setScene(new Scene(root));
				stage.setTitle("Add New Customer");
			} else if (event.getSource() == addNewMerchandise) {
				root = FXMLLoader.load(getClass().getResource("AddNewMerchandise.fxml"));
				stage.setScene(new Scene(root));
				stage.setTitle("Add New Merchandise");
			}
			stage.initModality(Modality.APPLICATION_MODAL);
			// stage.initOwner(addNewCustomerOk.getScene().getWindow());
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	private void handleListAllCustomers(ActionEvent event) throws IOException {
		System.out.println("List all customers");
		tableLabel.setText("CUSTOMER DATA");

		String[] headerValues = { "First Name", "Last Name", "StreetAddress", "City", "State", "Zipcode", "Gender" };
		populateTableColumns(headerValues);

		try {

			ResultSet customerData = rsdb.getTableData("Customer");

			while (customerData.next()) {
				String firstName = customerData.getString("FirstName");
				String lastName = customerData.getString("LastName");
				String streetAddress = customerData.getString("StreetAddress");
				String city = customerData.getString("City");
				String state = customerData.getString("State");
				String zipcode = customerData.getString("Zipcode");
				String gender = customerData.getString("Gender");
				String[] dataValues = { firstName, lastName, streetAddress, city, state, zipcode, gender };

				populateTableDataRow(dataValues);
			}
		} catch (Exception e) {

		}
	}

	@FXML
	private void handleListAllEmployees(ActionEvent event) throws IOException {
		System.out.println("List all employees");
		tableLabel.setText("EMPLOYEE DATA");

		String[] headerValues = { "First Name", "Last Name", "Street Address", "City", "State", "Zipcode", "Gender" };
		populateTableColumns(headerValues);

		try {

			ResultSet employeeData = rsdb.getTableData("Employee");

			while (employeeData.next()) {
				String firstName = employeeData.getString("FirstName");
				String lastName = employeeData.getString("LastName");
				String streetAddress = employeeData.getString("StreetAddress");
				String city = employeeData.getString("City");
				String state = employeeData.getString("State");
				String zipcode = employeeData.getString("Zipcode");
				String gender = employeeData.getString("Gender");
				String[] dataValues = { firstName, lastName, streetAddress, city, state, zipcode, gender };

				populateTableDataRow(dataValues);
			}
		} catch (Exception e) {

		}
	}

	@FXML
	private void handleListAllMerchandise(ActionEvent event) throws IOException {
		System.out.println("List all merchandise");
		tableLabel.setText("MERCHANDISE DATA");

		String[] headerValues = { "Name", "Price", "Description" };
		populateTableColumns(headerValues);

		try {

			ResultSet merchandiseData = rsdb.getTableData("Merchandise");

			while (merchandiseData.next()) {
				String name = merchandiseData.getString("Name");
				String price = merchandiseData.getString("Price");
				String description = merchandiseData.getString("Description");
				String[] dataValues = { name, price, description };

				populateTableDataRow(dataValues);
			}
		} catch (Exception e) {

		}

	}

	private void populateTableColumns(String[] headerValues) {
		table.getItems().clear();
		table.getColumns().clear();
		table.setPlaceholder(new Label("Loading..."));

		for (int column = 0; column < headerValues.length; column++) {
			table.getColumns().add(createColumn(column, headerValues[column]));
		}
	}

	private void populateTableDataRow(String[] dataValues) {
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

}