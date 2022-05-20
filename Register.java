import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Register extends JFrame{
	
	JPanel pnlTop, pnlCenter, pnlBottom;
	JLabel lblTitle, lblUsername, lblPassword, lblName, lblPhoneNo, lblEmail;
	JTextField txtUsername, txtPassword, txtName, txtPhoneNo, txtEmail;
	JButton btnRegister, btnCancel;
	
	private JOptionPane optionPane;
	
	Random r = new Random();
	
	int randomInt(int min, int max){
		//min = 0
		//max = 9
		return r.nextInt(max-min+1)+min;
	}
	
	public Register() {
		setTitle("Register Operator");
		//inisialisasi
		JPanel pnlTop = new JPanel();
		JPanel pnlCenter = new JPanel();
		JPanel pnlBottom = new JPanel();
		
		JLabel lblTitle = new JLabel("Register Operator Account");
		JLabel lblUsername = new JLabel("Username");
		JLabel lblPassword = new JLabel("Password");
		JLabel lblName = new JLabel("Name");
		JLabel lblPhoneNo = new JLabel("Phone Number");
		JLabel lblEmail = new JLabel("Email");
		
		JTextField txtUsername = new JTextField();
		JTextField txtPassword = new JTextField();
		JTextField txtName = new JTextField();
		JTextField txtPhoneNo = new JTextField();
		JTextField txtEmail = new JTextField();
		
		JButton btnRegister = new JButton("Register");
		JButton btnCancel = new JButton("Cancel");
		
		//set layout
		pnlCenter.setLayout(new GridLayout(5,2,10,10));
		pnlCenter.setBorder(new EmptyBorder(20, 50, 20, 50));
		pnlBottom.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		//add to panel & frame
		pnlTop.add(lblTitle);
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
		pnlBottom.add(btnRegister);
		pnlBottom.add(btnCancel);
		
		add(pnlTop, BorderLayout.NORTH);
		add(pnlCenter, BorderLayout.CENTER);
		add(pnlBottom, BorderLayout.SOUTH);
	
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty() || txtName.getText().isEmpty() || txtPhoneNo.getText().isEmpty() || txtEmail.getText().isEmpty()){
					optionPane.showMessageDialog(null, "Please fill all the fields!");
					return;
				}
				
				String operatorID = "OP" + (randomInt(0,9)) + (randomInt(0,9)) + (randomInt(0,9));
				String operatorUsername = txtUsername.getText();
				String operatorPassword = txtPassword.getText();
				String operatorName = txtName.getText();
				String operatorPhoneNo = txtPhoneNo.getText();
				String operatorEmail = txtEmail.getText();
				
				Model.insertOperator(operatorID, operatorUsername, operatorPassword, operatorName, operatorPhoneNo, operatorEmail);
				
				optionPane.showMessageDialog(null, "Registration Successful");
				new MainMenu();
				setVisible(false);
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainMenu();
				setVisible(false);
				
			}
		});
		
		setSize(500, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
