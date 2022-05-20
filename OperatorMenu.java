import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class OperatorMenu extends JFrame{

	//Component Left
	JPanel pnlLeft, pnlLTop, pnlLCenter, pnlLBottom;
	//Top
	JPanel pnlMenu, pnlLogout, pnlProfile, pnlLTitle;
	JLabel lblLTitle;
	JButton btnLogout, btnProfile;
	
	DefaultTableModel transactionData, itemData;
	JTable transactionTable, itemTable;
	JScrollPane scroll, scroll2;
	//Bottom
	JPanel pnlInput, pnlITop, pnlICenter, pnlIBottom, pnlAction;
	JButton btnInsert, btnDelete, btnHelp, btnClear;
	JLabel lblOperatorID, lblCustomerID, lblMemberID, lblTransactionID, lblDate, lblItemID, lblQuantityPurchased, lblSubTotalPrice, lblTotalPrice, lblPaidAmount, lblChangeAmount;
	JTextField txtOperatorID, txtCustomerID, txtMemberID, txtTransactionID, txtDate, txtItemID, txtQuantityPurchased, txtSubTotalPrice, txtTotalPrice, txtPaidAmount, txtChangeAmount;
	JCheckBox checkNonMember = new JCheckBox("Non-Member");
	
	//Component Right
	JPanel pnlRight, pnlRTop, pnlRCenter, pnlRBottom, pnlIphone, pnlSamsung, pnlXiaomi, pnlVivo, pnlOppo, pnlHuawei;
	JLabel lblRTitle, lblIphone, lblHuawei, lblOppo, lblSamsung, lblXiaomi, lblVivo, lblIcon1, lblIcon2, lblIcon3, lblIcon4, lblIcon5, lblIcon6;
	
	static int selected;
	static String selectedCode;
	
	Vector<Transaction> transactions = new Vector<>();
	Vector<Item> items = new Vector<>();
	
	Object header[] = new Object[]{
			"Transaction ID",
			"Item ID",
			"Operator ID",
			"Customer ID",
			"Member ID",
			"Date",
			"Quantity Purchased",
			"SubTotal Price",
			"Total Price",
			"Paid Amount",
			"Change Amount"
	  };
	
	Object header2[] = new Object[]{
			"Item ID",
			"Item Name",
			"Item Price",
			"Item Quantity"
	  };
	
	public OperatorMenu() {
		setTitle("POS Handphone");
		inisialisasi();
		
		checkNonMember.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkNonMember.isSelected()){
					txtMemberID.setEnabled(false);
					txtMemberID.setText("");
					
				} else{
					txtMemberID.setEnabled(true);
				}
			}
		});
		
		btnHelp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnHelp){
					new Password();
					setVisible(false);
				}
			}
		});
		
		btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnLogout){
					new MainMenu();
					setVisible(false);
				}
			}
		});
		
		btnInsert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnInsert){
					if(txtCustomerID.getText().isEmpty() || txtTransactionID.getText().isEmpty() || txtDate.getText().isEmpty() || txtItemID.getText().isEmpty() || txtQuantityPurchased.getText().isEmpty() || txtSubTotalPrice.getText().isEmpty()){
						JOptionPane.showMessageDialog(null, "Please fill all the fields!");
						return;
					}
					
					insertTransaction();
				}
			}
		});
		
		transactionTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				selected = transactionTable.getSelectedRow();
				txtTransactionID.setText(transactionTable.getValueAt(selected, 0).toString());
				txtItemID.setText(transactionTable.getValueAt(selected, 1).toString());
				txtOperatorID.setText(transactionTable.getValueAt(selected, 2).toString());
				txtCustomerID.setText(transactionTable.getValueAt(selected, 3).toString());
				
				if(transactionTable.getValueAt(selected, 4).toString().equals("")){
					checkNonMember.setSelected(false);
				} else{
					txtMemberID.setText(transactionTable.getValueAt(selected, 4).toString());
					checkNonMember.setSelected(true);
				}
				
				txtItemID.setText(transactionTable.getValueAt(selected, 5).toString());
				txtQuantityPurchased.setText(transactionTable.getValueAt(selected, 6).toString());
				txtSubTotalPrice.setText(transactionTable.getValueAt(selected, 7).toString());
				txtTotalPrice.setText(transactionTable.getValueAt(selected, 8).toString());
				txtPaidAmount.setText(transactionTable.getValueAt(selected, 9).toString());
				txtChangeAmount.setText(transactionTable.getValueAt(selected, 10).toString());
				selectedCode = transactionTable.getValueAt(selected, 0).toString();
				
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnDelete){
					deleteTransaction();
				}
			}
		});
		
		btnProfile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnProfile){
					new EditProfile();
					setVisible(false);
				}
			}
		});
		
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnClear){
					selected = 0;
					selectedCode = null;
					txtOperatorID.setText("");
					txtCustomerID.setText("");
					txtMemberID.setText("");
					checkNonMember.setSelected(false);
					txtTransactionID.setText("");
					txtDate.setText("");
					txtItemID.setText("");
					txtQuantityPurchased.setText("");
					txtSubTotalPrice.setText("");
					txtTotalPrice.setText("");
					txtPaidAmount.setText("");
					txtChangeAmount.setText("");
				}
			}
		});
		
	}
	
	void insertTransaction(){
		String operatorID = txtOperatorID.getText();
		String customerID = txtCustomerID.getText();
		String memberID = txtMemberID.getText();
		String transactionID = txtTransactionID.getText();
		String date = txtDate.getText();
		String itemID = txtItemID.getText();
		Integer quantityPurchased = Integer.parseInt(txtQuantityPurchased.getText());
		Integer subTotalPrice = Integer.parseInt(txtSubTotalPrice.getText());
		Integer totalPrice = Integer.parseInt(txtTotalPrice.getText());
		Integer paidAmount = Integer.parseInt(txtPaidAmount.getText());
		Integer changeAmount = Integer.parseInt(txtChangeAmount.getText());
		
		Model.insertTransaction(transactionID, operatorID, customerID, memberID, date, totalPrice, paidAmount, changeAmount);
		Model.insertTransactionDetail(transactionID, itemID, quantityPurchased, subTotalPrice);
		
		if(txtCustomerID.getText().isEmpty() || txtTransactionID.getText().isEmpty() || txtDate.getText().isEmpty() || txtItemID.getText().isEmpty() || txtQuantityPurchased.getText().isEmpty() || txtSubTotalPrice.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Please fill all the fields!");
			return;
		}
		
		JOptionPane.showMessageDialog(null, "Insert Successful");
		initTableTransaction();
		
		txtOperatorID.setText("");
		txtCustomerID.setText("");
		txtMemberID.setText("");
		checkNonMember.setSelected(false);
		txtTransactionID.setText("");
		txtDate.setText("");
		txtItemID.setText("");
		txtQuantityPurchased.setText("");
		txtSubTotalPrice.setText("");
		txtTotalPrice.setText("");
		txtPaidAmount.setText("");
		txtChangeAmount.setText("");
	}
	
	void deleteTransaction(){
		Model.deleteTransaction();
		Model.deleteTransactionDetail();
		
		if(txtCustomerID.getText().isEmpty() || txtTransactionID.getText().isEmpty() || txtDate.getText().isEmpty() || txtItemID.getText().isEmpty() || txtQuantityPurchased.getText().isEmpty() || txtSubTotalPrice.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Please fill all the fields!");
			return;
		}
		
		JOptionPane.showMessageDialog(null, "Delete Successful");
		initTableTransaction();
		
		selected = 0;
		selectedCode = null;
		txtOperatorID.setText("");
		txtCustomerID.setText("");
		txtMemberID.setText("");
		checkNonMember.setSelected(false);
		txtTransactionID.setText("");
		txtDate.setText("");
		txtItemID.setText("");
		txtQuantityPurchased.setText("");
		txtSubTotalPrice.setText("");
		txtTotalPrice.setText("");
		txtPaidAmount.setText("");
		txtChangeAmount.setText("");
	}
	
	void createTableTransaction(){
		transactionData = new DefaultTableModel(header, 0);
		transactionTable = new JTable(transactionData);
		
		scroll = new JScrollPane(transactionTable);
		pnlLCenter.add(scroll);
		  
		initTableTransaction();
	}
	
	void initTableTransaction(){
		transactionData.setRowCount(0);
		transactions = Model.getTransaction();
		for (Transaction t : transactions) {
			Object transaction[] = new Object[]{
				t.getTransactionID(),
				t.getItemID(),
				t.getOperatorID(),
				t.getCustomerID(),
				t.getMemberID(),
				t.getDate(),
				t.getQuantityPurchased(),
				t.getSubTotalPrice(),
				t.getTotalPrice(),
				t.getPaidAmount(),
				t.getChangeAmount()
			};
			transactionData.addRow(transaction);
		}
	}
	
	void createTableItem(){
		itemData = new DefaultTableModel(header2, 0);
		itemTable = new JTable(itemData);
		scroll2 = new JScrollPane(itemTable);
		pnlRBottom.add(scroll2);
		pnlRBottom.setPreferredSize(new Dimension(100,125));
		initTableItem();
	}
	
	void initTableItem(){
		itemData.setRowCount(0);
		items = Model.getItem();
		for (Item i : items) {
			Object item[] = new Object[]{
				i.getItemID(),
				i.getItemName(),
				i.getItemPrice(),
				i.getQuantityAvailable(),
			};
			itemData.addRow(item);
		}
	}
	
	void inisialisasi(){
		//Left Component
		pnlLeft = new JPanel();
		pnlLTop = new JPanel();
		pnlLCenter = new JPanel();
		pnlLBottom = new JPanel();
		//Top
		pnlMenu = new JPanel();
		pnlLogout = new JPanel();
		pnlProfile = new JPanel();
		btnLogout = new JButton("Logout");
		btnProfile = new JButton("Edit Profile");
		pnlLTitle = new JPanel();
		lblLTitle = new JLabel("Transaction");
		//Bottom
		pnlInput = new JPanel();
		pnlITop = new JPanel();
		pnlICenter = new JPanel();
		pnlIBottom = new JPanel();
		pnlAction = new JPanel();
		btnInsert = new JButton("Insert");
		btnDelete = new JButton("Delete");
		btnClear = new JButton("Clear all fields");
		btnHelp = new JButton("Ask Supervisor");
		lblOperatorID = new JLabel("Operator ID");
		lblCustomerID = new JLabel("Customer ID");
		lblMemberID = new JLabel("Member ID");
		lblTransactionID = new JLabel("Transaction ID");
		lblDate = new JLabel("Date");
		lblItemID = new JLabel("Item ID");
		lblQuantityPurchased = new JLabel("Quantity Purchased");
		lblSubTotalPrice = new JLabel("SubTotal Price");
		lblTotalPrice = new JLabel("Total Price");
		lblPaidAmount = new JLabel("Paid Amount");
		lblChangeAmount = new JLabel("Change Amount");
		txtOperatorID = new JTextField();
		txtCustomerID = new JTextField();
		txtMemberID = new JTextField();
		txtTransactionID = new JTextField();
		txtDate = new JTextField();
		txtItemID = new JTextField();
		txtQuantityPurchased = new JTextField();
		txtSubTotalPrice = new JTextField();
		txtTotalPrice = new JTextField();
		txtPaidAmount = new JTextField();
		txtChangeAmount = new JTextField();
		
		//Right
		pnlRight = new JPanel();
		pnlRTop = new JPanel();
		pnlRCenter = new JPanel();
		pnlRBottom = new JPanel();
		lblRTitle = new JLabel("Item List");
		lblSamsung = new JLabel("Samsung Galaxy A3");
		lblIphone = new JLabel("Iphone X");
		lblVivo = new JLabel("Vivo S2");
		lblOppo = new JLabel("Oppo F5");
		lblXiaomi = new JLabel("Xiaomi Poco F5");
		lblHuawei = new JLabel("Huawei P50");
		lblIcon1 = new JLabel("");
		lblIcon2 = new JLabel("");
		lblIcon3 = new JLabel("");
		lblIcon4 = new JLabel("");
		lblIcon5 = new JLabel("");
		lblIcon6 = new JLabel("");
		
		//set layout
//		setLayout(new BorderLayout());
		setLayout(new GridLayout(1,2,0,0));
		pnlLeft.setLayout(new BorderLayout());
		//Left Top
		pnlLTop.setLayout(new BorderLayout());
		pnlLCenter.setLayout(new GridLayout(1,1));
		pnlMenu.setLayout(new GridLayout(1,2,300,300));
		pnlProfile.setLayout(new BorderLayout());
		pnlLogout.setLayout(new BorderLayout());
		//Left Bottom
		pnlLBottom.setLayout(new BorderLayout());
		pnlInput.setLayout(new BorderLayout());
		pnlInput.setBorder(new EmptyBorder(10, 200, 20, 200));
		pnlITop.setLayout(new GridLayout(3,2,10,10));
		pnlICenter.setLayout(new BorderLayout());
		pnlIBottom.setLayout(new GridLayout(8,2,10,10));
		//Right
		pnlRight.setLayout(new BorderLayout());
		pnlRCenter.setLayout(new GridLayout(6,2,10,10));
		pnlRCenter.setBorder(new EmptyBorder(0, 250, 0, 250));
		
		//add to frame Left
		//top
		pnlProfile.add(btnProfile, BorderLayout.WEST);
		pnlLogout.add(btnLogout, BorderLayout.EAST);
		pnlMenu.add(pnlProfile);
		pnlMenu.add(pnlLogout);
		pnlLTitle.add(lblLTitle);
		
		pnlLTop.add(pnlMenu, BorderLayout.NORTH);
		pnlLTop.add(pnlLTitle, BorderLayout.CENTER);
		
		//center
		createTableTransaction();
		
		//bottom
		pnlITop.add(lblOperatorID);
		pnlITop.add(txtOperatorID);
		pnlITop.add(lblCustomerID);
		pnlITop.add(txtCustomerID);
		pnlITop.add(lblMemberID);
		pnlITop.add(txtMemberID);
		pnlICenter.add(checkNonMember, BorderLayout.EAST);
		pnlIBottom.add(lblTransactionID);
		pnlIBottom.add(txtTransactionID);
		pnlIBottom.add(lblDate);
		pnlIBottom.add(txtDate);
		pnlIBottom.add(lblItemID);
		pnlIBottom.add(txtItemID);
		pnlIBottom.add(lblQuantityPurchased);
		pnlIBottom.add(txtQuantityPurchased);
		pnlIBottom.add(lblSubTotalPrice);
		pnlIBottom.add(txtSubTotalPrice);
		pnlIBottom.add(lblTotalPrice);
		pnlIBottom.add(txtTotalPrice);
		pnlIBottom.add(lblPaidAmount);
		pnlIBottom.add(txtPaidAmount);
		pnlIBottom.add(lblChangeAmount);
		pnlIBottom.add(txtChangeAmount);
		
		pnlInput.add(pnlITop, BorderLayout.NORTH);
		pnlInput.add(pnlICenter, BorderLayout.CENTER);
		pnlInput.add(pnlIBottom, BorderLayout.SOUTH);
		
		pnlAction.add(btnInsert);
		pnlAction.add(btnDelete);
		pnlAction.add(btnClear);
		pnlAction.add(btnHelp);
		
		pnlLBottom.add(pnlInput, BorderLayout.NORTH);
		pnlLBottom.add(pnlAction, BorderLayout.CENTER);
		
		pnlLeft.add(pnlLTop, BorderLayout.NORTH);
		pnlLeft.add(pnlLCenter, BorderLayout.CENTER);
		pnlLeft.add(pnlLBottom, BorderLayout.SOUTH);
		
		//add to frame Right
		pnlRTop.add(lblRTitle);
		pnlRight.add(pnlRTop, BorderLayout.NORTH);
		
		//item image
		
		ImageIcon iphone = new ImageIcon(this.getClass().getResource("/iphone.png"));
		lblIcon1.setIcon(iphone);
		pnlRCenter.add(lblIcon1);
		pnlRCenter.add(lblIphone);
		
		ImageIcon samsung = new ImageIcon(this.getClass().getResource("/samsung.png"));
		lblIcon2.setIcon(samsung);
		pnlRCenter.add(lblIcon2);
		pnlRCenter.add(lblSamsung);
		
		ImageIcon xiaomi = new ImageIcon(this.getClass().getResource("/xiaomi.png"));
		lblIcon3.setIcon(xiaomi);
		pnlRCenter.add(lblIcon3);
		pnlRCenter.add(lblXiaomi);
		
		ImageIcon vivo = new ImageIcon(this.getClass().getResource("/vivo.png"));
		lblIcon4.setIcon(vivo);
		pnlRCenter.add(lblIcon4);
		pnlRCenter.add(lblVivo);
		
		ImageIcon oppo = new ImageIcon(this.getClass().getResource("/oppo.png"));
		lblIcon5.setIcon(oppo);
		pnlRCenter.add(lblIcon5);
		pnlRCenter.add(lblOppo);
		
		ImageIcon huawei = new ImageIcon(this.getClass().getResource("/huawei.png"));
		lblIcon6.setIcon(huawei);
		pnlRCenter.add(lblIcon6);
		pnlRCenter.add(lblHuawei);
		pnlRight.add(pnlRCenter, BorderLayout.CENTER);
		
		//Right Bottom
		createTableItem();
		pnlRight.add(pnlRBottom, BorderLayout.SOUTH);
		
		add(pnlLeft, BorderLayout.WEST);
		add(pnlRight, BorderLayout.EAST);
		
		setSize(1700,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
