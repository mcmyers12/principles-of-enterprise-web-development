
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
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
	private TableView<ObservableList<StringProperty>> table;

	@FXML
	private MenuItem closeWindow;

	@Override // Called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		System.out.println(fxmlFileLocation);
		if (fxmlFileLocation.toString().contains(("RetailSystem.fxml"))) {

		}
		if (fxmlFileLocation.toString().contains(("AddNewCustomer.fxml"))) {
			initializeStateChoiceBoxOptions(stateChoiceBox);
		}

	}

	@FXML
	private void handleAddNewCustomer(ActionEvent event) throws IOException {
		System.out.println("Add new customer");
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
		String[] headerValues = { "First Name", "Last Name", "Street Address", "City", "State", "Zipcode", "Gender" };
		String[] dataValues = { "1", "2", "3", "4", "5", "6", "7" };
		populateTable(headerValues, dataValues);
		System.out.println("List all customers");
	}

	@FXML
	private void handleListAllEmployees(ActionEvent event) throws IOException {
		String[] headerValues = { "First Name", "Last Name", "Street Address", "City", "State", "Zipcode", "Gender" };
		String[] dataValues = { "11", "22", "33", "4", "5", "6", "7" };
		populateTable(headerValues, dataValues);
		System.out.println("List all customers");
	}

	@FXML
	private void handleListAllMerchandise(ActionEvent event) throws IOException {
		String[] headerValues = { "Name", "Price", "Description" };
		String[] dataValues = { "1", "2", "3" };
		populateTable(headerValues, dataValues);
		System.out.println("List all merchandise");
	}

	private void populateTable(String[] headerValues, String[] dataValues) {
		table.getItems().clear();
		table.getColumns().clear();
		table.setPlaceholder(new Label("Loading..."));

		for (int column = 0; column < headerValues.length; column++) {
			table.getColumns().add(createColumn(column, headerValues[column]));
		}

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

}