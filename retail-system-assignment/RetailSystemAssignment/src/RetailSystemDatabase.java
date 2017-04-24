import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RetailSystemDatabase {
	static Connection conn;

	// public static void main(String[] args) {
	// RetailSystemDatabase rsdb = new RetailSystemDatabase();
	//
	// rsdb.createRetailSystemDB();
	// rsdb.createDBTables();
	// rsdb.insertIntoEmployeeTable("miranda", "myers", "9902 Deer Run",
	// "Laurel", "Maryland", "99292", "Female");
	// rsdb.insertIntoCustomerTable("danny", "oliden", "9902 Deer Run",
	// "Laurel", "Maryland", "99292", "Male");
	// rsdb.insertIntoMerchandiseTable("Computer", "$200", "Cool computer");
	// }

	public void createRetailSystemDB() {
		try {
			String driver = "org.apache.derby.jdbc.EmbeddedDriver";
			String dbName = "RetailSystemDBMirandaMyers";
			String connectionURL = "jdbc:derby:" + dbName + ";create=true";
			Class.forName(driver);
			conn = DriverManager.getConnection(connectionURL);
		} catch (Exception e) {
			System.out.println("Error creating database");
			e.printStackTrace();
		}

	}

	public ResultSet getTableData(String tableName) {
		try {
			String selectString = "SELECT * FROM " + tableName;
			Statement stmt = conn.createStatement();
			ResultSet tableData = stmt.executeQuery(selectString);

			return tableData;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void createDBTables() {
		try {
			String createString = "CREATE TABLE Employee (FirstName VARCHAR(32) NOT NULL, LastName VARCHAR(50) NOT NULL, StreetAddress VARCHAR(50) NOT NULL, City VARCHAR(50) NOT NULL, State VARCHAR(50) NOT NULL, Zipcode VARCHAR(50) NOT NULL, Gender VARCHAR(50) NOT NULL)";
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(createString);
		} catch (Exception e) {
			System.out.println("Employee table exists");
		}

		try {
			String createString = "CREATE TABLE Customer (FirstName VARCHAR(32) NOT NULL, LastName VARCHAR(50) NOT NULL, StreetAddress VARCHAR(50) NOT NULL, City VARCHAR(50) NOT NULL, State VARCHAR(50) NOT NULL, Zipcode VARCHAR(50) NOT NULL, Gender VARCHAR(50) NOT NULL)";
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(createString);
		} catch (Exception e) {
			System.out.println("Customer table exists");
		}

		try {
			String createString = "CREATE TABLE Merchandise (Name VARCHAR(32) NOT NULL, Price VARCHAR(50) NOT NULL, Description VARCHAR(300) NOT NULL)";
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(createString);
		} catch (Exception e) {
			System.out.println("Merchandise table exists");
		}

	}

	public void insertIntoEmployeeTable(String firstName, String lastName, String streetAddress, String city,
			String state, String zipcode, String gender) {
		try {
			PreparedStatement psInsert = conn.prepareStatement("insert into Employee values (?,?,?,?,?,?,?)");

			psInsert.setString(1, firstName);
			psInsert.setString(2, lastName);
			psInsert.setString(3, streetAddress);
			psInsert.setString(4, city);
			psInsert.setString(5, state);
			psInsert.setString(6, zipcode);
			psInsert.setString(7, gender);

			psInsert.executeUpdate();

			Statement stmt2 = conn.createStatement();
			ResultSet rs = stmt2.executeQuery("select * from Employee");
			System.out.println("Employee Data:\n");
			int num = 0;

			while (rs.next()) {
				System.out.println(++num + "\n " + rs.getString(1) + "\n " + rs.getString(2) + "\n " + rs.getString(3)
						+ "\n " + rs.getString(4) + "\n " + rs.getString(5) + "\n " + rs.getString(6) + "\n "
						+ rs.getString(7) + "\n ");
			}
			rs.close();
		} catch (Exception e) {

		}
	}

	public void insertIntoCustomerTable(String firstName, String lastName, String streetAddress, String city,
			String state, String zipcode, String gender) {
		try {
			PreparedStatement psInsert = conn.prepareStatement("insert into Customer values (?,?,?,?,?,?,?)");

			psInsert.setString(1, firstName);
			psInsert.setString(2, lastName);
			psInsert.setString(3, streetAddress);
			psInsert.setString(4, city);
			psInsert.setString(5, state);
			psInsert.setString(6, zipcode);
			psInsert.setString(7, gender);

			psInsert.executeUpdate();

			Statement stmt2 = conn.createStatement();
			ResultSet rs = stmt2.executeQuery("select * from Customer");
			System.out.println("Customer Data:\n");
			int num = 0;

			while (rs.next()) {
				System.out.println(++num + "\n " + rs.getString(1) + "\n " + rs.getString(2) + "\n " + rs.getString(3)
						+ "\n " + rs.getString(4) + "\n " + rs.getString(5) + "\n " + rs.getString(6) + "\n "
						+ rs.getString(7) + "\n ");
			}
			rs.close();
		} catch (Exception e) {

		}
	}

	public void insertIntoMerchandiseTable(String name, String price, String description) {
		try {
			PreparedStatement psInsert = conn.prepareStatement("insert into Merchandise values (?,?,?)");

			psInsert.setString(1, name);
			psInsert.setString(2, price);
			psInsert.setString(3, description);

			psInsert.executeUpdate();

			Statement stmt2 = conn.createStatement();
			ResultSet rs = stmt2.executeQuery("select * from Merchandise");
			System.out.println("Merchandise Data:\n");
			int num = 0;

			while (rs.next()) {
				System.out.println(
						++num + "\n " + rs.getString(1) + "\n " + rs.getString(2) + "\n " + rs.getString(3) + "\n ");
			}
			rs.close();
		} catch (Exception e) {

		}
	}
}
