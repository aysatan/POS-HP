import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class EditProfile extends JFrame{

	JPanel pnlTop, pnlCenter, pnlBottom;
	JLabel lblTitle, lblOperatorID, lblUsername, lblPassword, lblName, lblPhoneNo, lblEmail;
	JTextField txtOperatorID, txtUsername, txtPassword, txtName, txtPhoneNo, txtEmail;
	JButton btnUpdate, btnCancel;
	
	private JOptionPane optionPane;
	
	public EditProfile() {
		setTitle("Update Operator");
		//inisialisasi
		JPanel pnlTop = new JPanel();
		JPanel pnlCenter = new JPanel();
		JPanel pnlBottom = new JPanel();
		
		JLabel lblTitle = new JLabel("Update Operator Account");
		JLabel lblOperatorID = new JLabel("Operator ID");
		JLabel lblUsername = new JLabel("Username");
		JLabel lblPassword = new JLabel("Password");
		JLabel lblName = new JLabel("Name");
		JLabel lblPhoneNo = new JLabel("Phone Number");
		JLabel lblEmail = new JLabel("Email");
		
		JTextField txtOperatorID = new JTextField();
		JTextField txtUsername = new JTextField();
		JTextField txtPassword = new JTextField();
		JTextField txtName = new JTextField();
		JTextField txtPhoneNo = new JTextField();
		JTextField txtEmail = new JTextField();
		
		JButton btnUpdate = new JButton("Update");
		JButton btnCancel = new JButton("Cancel");
		
		//set layout
		pnlCenter.setLayout(new GridLayout(6,2,10,10));
		pnlCenter.setBorder(new EmptyBorder(20, 50, 20, 50));
		pnlBottom.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		//add to panel & frame
		pnlTop.add(lblTitle);
		pnlCenter.add(lblOperatorID);
		pnlCenter.add(txtOperatorID);
		pnlCenter.add(lblUsername);
		pnlCenter.add(txtUsername);
		pnlCenter.add(lblPassword);
		pnlCenter.add(txtPassword);
		pnlCenter.add(lblName);
		pnlCenter.add(txtName);
		pnlCenter.add(lblPhoneNo);
		pnlCenter.add(txtPhoneNo);
		pnlCenter.add(lblEmail);
		pnlCenter.add(txtEmail);
		pnlBottom.add(btnUpdate);
		pnlBottom.add(btnCancel);
		
		add(pnlTop, BorderLayout.NORTH);
		add(pnlCenter, BorderLayout.CENTER);
		add(pnlBottom, BorderLayout.SOUTH);
		
		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty() || txtName.getText().isEmpty() || txtPhoneNo.getText().isEmpty() || txtEmail.getText().isEmpty()){
					optionPane.showMessageDialog(null, "Please fill all the fields!");
					return;
				}
				
				String operatorID = txtOperatorID.getText();
				String operatorUsername = txtUsername.getText();
				String operatorPassword = txtPassword.getText();
				String operatorName = txtName.getText();
				String operatorPhoneNo = txtPhoneNo.getText();
				String operatorEmail = txtEmail.getText();
				
				Model.updateOperator(operatorID, operatorUsername, operatorPassword, operatorName, operatorPhoneNo, operatorEmail);
				
				optionPane.showMessageDialog(null, "Update Successful");
				new OperatorMenu();
				setVisible(false);
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new OperatorMenu();
				setVisible(false);
				
			}
		});
		
		setSize(500, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}
