import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Model {

	static Connector conn = Connector.getConnection();
	
	public static Vector<Operator> getOperatorList(){
		Vector<Operator> operators = new Vector<>();
		String query = "SELECT * FROM operator";
		ResultSet rs = conn.executeQuery(query);
		String operatorID, operatorUsername, operatorPassword, operatorName, operatorPhoneNo, operatorEmail="", role, supervisorPassword;
		
		try {
			while(rs.next()){
				operatorID = rs.getString("operatorID");
				operatorUsername = rs.getString("operatorUsername");
				operatorPassword = rs.getString("operatorPassword");
				operatorName = rs.getString("operatorName");
				operatorPhoneNo = rs.getString("operatorPhoneNo");
				role = rs.getString("role");
				supervisorPassword = rs.getString("password");
				operators.add(new Operator(operatorID, operatorUsername, operatorPassword, operatorName, operatorPhoneNo, operatorEmail, role, supervisorPassword));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return operators;
	}
	
	public static Vector<Transaction> getTransaction(){
		Vector<Transaction> transactions = new Vector<>();
		String query = "SELECT * FROM transactiondetail LEFT OUTER JOIN transaction ON transactiondetail.transactionID = transaction.transactionID";
		ResultSet rs = conn.executeQuery(query);
		
		String transactionID, itemID, operatorID, customerID, memberID, date;
		Integer quantityPurchased, subTotalPrice, totalPrice, paidAmount, changeAmount;
		
		try {
			while(rs.next()){
				transactionID = rs.getString("transactionID");
				itemID = rs.getString("itemID");
				operatorID = rs.getString("operatorID");
				customerID = rs.getString("customerID");
				memberID = rs.getString("memberID");
				date = rs.getString("date");
				quantityPurchased = rs.getInt("quantityPurchased");
				subTotalPrice = rs.getInt("subTotalPrice");
				totalPrice = rs.getInt("totalPrice");
				paidAmount = rs.getInt("paidAmount");
				changeAmount = rs.getInt("changeAmount");
				transactions.add(new Transaction(transactionID, itemID, operatorID, customerID, memberID, date, quantityPurchased, subTotalPrice, totalPrice, paidAmount, changeAmount));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return transactions;
	}
	
	public static boolean insertTransaction(String transactionID, String operatorID, String customerID, String memberID, String date, Integer totalPrice, Integer paidAmount, Integer changeAmount) {
		String query = "INSERT INTO transaction VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ps = conn.prepareStatement(query);
		try {
			ps.setString(1, transactionID);
			ps.setString(2, operatorID);
			ps.setString(3, customerID);
			ps.setString(4, memberID);
			ps.setString(5, date);
			ps.setInt(6, totalPrice);
			ps.setInt(7, paidAmount);
			ps.setInt(8, changeAmount);	
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
		return true;
	}
	
	public static boolean insertTransactionDetail(String transactionID, String itemID, Integer quantityPurchased, Integer subTotalPrice) {
		String query = "INSERT INTO transactiondetail VALUES (?, ?, ?, ?)";
		
		PreparedStatement ps = conn.prepareStatement(query);
		try {
			ps.setString(1, transactionID);
			ps.setString(2, itemID);
			ps.setInt(3, quantityPurchased);	
			ps.setInt(4, subTotalPrice);
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
		return true;
	}
	
	public static boolean deleteTransaction(){
		String query = "DELETE FROM transaction WHERE transactionID = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		try {
			ps.setString(1, OperatorMenu.selectedCode);
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
		return true;
	}
	
	public static boolean deleteTransactionDetail(){
		String query = "DELETE FROM transactiondetail WHERE transactionID = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		try {
			ps.setString(1, OperatorMenu.selectedCode);
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
		return true;
	}
	
	public static Vector<Item> getItem(){
		Vector<Item> items = new Vector<>();
		String query = "SELECT * FROM item";
		ResultSet rs = conn.executeQuery(query);
		
		String itemID, itemName;
		Integer itemPrice, quantityAvailable;
		
		try {
			while(rs.next()){
				itemID = rs.getString("itemID");
				itemName = rs.getString("itemName");
				itemPrice = rs.getInt("itemPrice");
				quantityAvailable = rs.getInt("quantityAvailable");
				items.add(new Item(itemID, itemName, itemPrice, quantityAvailable));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return items;
	}
	
	public static boolean insertOperator(String operatorID, String operatorUsername, String operatorPassword,String operatorName, String operatorPhoneNo, String operatorEmail){
		String query = "INSERT INTO operator(operatorID, operatorUsername, operatorPassword, operatorName, operatorPhoneNo, operatorEmail) VALUES (?, ?, ?, ?, ?, ?)";
	
		PreparedStatement ps = conn.prepareStatement(query);
		try {
			ps.setString(1, operatorID);
			ps.setString(2, operatorUsername);
			ps.setString(3, operatorPassword);
			ps.setString(4, operatorName);
			ps.setString(5, operatorPhoneNo);	
			ps.setString(6, operatorEmail);
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
		return true;
	}
	
	public static boolean updateOperator(String operatorID, String operatorUsername, String operatorPassword, String operatorName, String operatorPhoneNo, String operatorEmail){
		String query = "UPDATE operator SET operatorUsername = ?, operatorPassword = ?, operatorName = ?, operatorPhoneNo = ?, operatorEmail = ? WHERE operatorID = ?";
		
		PreparedStatement ps = conn.prepareStatement(query);
		try {
			ps.setString(1, operatorUsername);
			ps.setString(2, operatorPassword);
			ps.setString(3, operatorName);
			ps.setString(4, operatorPhoneNo);
			ps.setString(5, operatorEmail);
			ps.setString(6, operatorID);
			ps.execute();
			
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

}
