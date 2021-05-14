package investmentService;
import java.sql.*;

public class Investment {
    
	private Connection connect()
	 {
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");

			//connecting with the database by giving correct username nane and pwd //
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget", "root", "");
		}
		
		catch (Exception e)
		{e.printStackTrace();}
		return con;
	 }
	
	public String insertInvestment(String iname, String date, String amount, String description)
	 {
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			
			
			String query = "INSERT INTO `investment`(`iID`, `iname`, `date`, `amount`, `description`) values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, iname);
			preparedStmt.setString(3, date);
			preparedStmt.setDouble(4, Double.parseDouble(amount));
			preparedStmt.setString(5, description);
			// execute the statement
	 
			preparedStmt.execute();
			con.close();
			String newItems = readInvestment();
			 output = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
			 }
			 catch (Exception e)
			 {
			 output = "{\"status\":\"error\", \"data\":\"Error while inserting the Investment.\"}";
			 System.err.println(e.getMessage());
			 } 
		return output;
	}
	
	
	public String readInvestment()
	 {
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for reading."; }
				
				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th>Investment title</th>" +
						"<th>Investing date</th>" +
						"<th>Investing amount</th>"+
						"<th>description</th>" +
						"<th>Update</th><th>Remove</th></tr>";

				String query = "SELECT `iID`, `iname`, `date`, `amount`, `description` FROM `investment`";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
	
				// iterate through the rows in the result set
				
				while (rs.next())
				{
						String iID = Integer.toString(rs.getInt("iID"));
						String iname = rs.getString("iname");
						String date = rs.getString("date");
						String amount = Double.toString(rs.getDouble("amount"));
						String description = rs.getString("description");
	 
						// Add into the html table
						
						output += "<tr><td>"+ iname + "</td>";
						output += "<td>" + date + "</td>";
						output += "<td>" + amount + "</td>";
						output += "<td>" + description + "</td>";
	
						// buttons
						output += "<td><input name='btnUpdate' type='button' value='Update' "
								+ "class='btnUpdate btn btn-secondary' data-itemid='" + iID + "'></td>"
								+ "<td><input name='btnRemove' type='button' value='Remove' "
								+ "class='btnRemove btn btn-danger' data-itemid='" + iID + "'></td></tr>";
				}
				con.close();
				// Complete the html table
				output += "</table>";
			}
			
			catch (Exception e)
			{
				output = "Error while reading the investment.";
				System.err.println(e.getMessage());
			}
	 
			return output;
	 }
	
	public String updateInvestment(String iID, String iname, String date, String amount, String description)
	{
		 String output = "";
		 try
		 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
		
			 // create a prepared statement
			 String query = "UPDATE `investment` SET `iname`=?,`date`=?,`amount`=?,`description`=? WHERE  `iID`= ? ";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
		
			 // binding values
			 preparedStmt.setString(1, iname);
			 preparedStmt.setDouble(3, Double.parseDouble(amount));
			 preparedStmt.setString(2, date);
			 preparedStmt.setString(4, description);
			 preparedStmt.setInt(5, Integer.parseInt(iID));
		
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 String newItems = readInvestment();
			 output = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
			 }
			 catch (Exception e)
			 {
			 output = "{\"status\":\"error\", \"data\":\"Error while updating the Investment.\"}";
			 System.err.println(e.getMessage());
			 }
		
		 return output;
	 }
	
	public String deleteInvestment(String iID)
	 {
		 String output = "";
		 try
		 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for deleting."; }
		
			 // create a prepared statement
			 String query = "DELETE FROM `investment` WHERE `iID`=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
		
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(iID));
		
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
		
			 String newItems = readInvestment();
			 output = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
			 }
			 catch (Exception e)
			 {
			 output = "{\"status\":\"error\", \"data\":\"Error while deleting the Investment.\"}";
			 System.err.println(e.getMessage());
			 }
		 return output;
		 }
} 
	
