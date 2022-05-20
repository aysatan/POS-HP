import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class Login extends JFrame implements ActionListener{

	JPanel pnlLogin, pnlTop, pnlBottom, pnlUsername, pnlPassword, pnlShowPassword, pnlButton;
	JButton btnLogin, btnCancel;
	JLabel lblUsername, lblPassword;
	JTextField txtUsername;
	JPasswordField txtPassword;
	JCheckBox showPassword = new JCheckBox("Show Password");
	
	Vector<Operator> operators = new Vector<>();
	
	public Login() {
		//inisialisasi
		pnlLogin = new JPanel();
		pnlTop = new JPanel();
		pnlBottom = new JPanel();
		pnlUsername = new JPanel();
		pnlPassword = new JPanel();
		pnlShowPassword = new JPanel();
		pnlButton = new JPanel();
		  
		lblUsername = new JLabel("Username : ");
		lblPassword = new JLabel("Password : ");
		  
		txtUsername = new JTextField();
		txtPassword = new JPasswordField();
		  
		btnLogin = new JButton("Login");
		btnCancel = new JButton("Cancel");
		  
		btnLogin.addActionListener(this);
		btnCancel.addActionListener(this);
		showPassword.addActionListener(this);
		
		//setLayout
		setTitle("Login Operator");
		setLayout(new BorderLayout());
		pnlLogin.setLayout(new BorderLayout());
		pnlTop.setLayout(new GridLayout(2,1,10,10));
		pnlTop.setBorder(new EmptyBorder(10, 10, 10, 10));
		pnlBottom.setLayout(new BorderLayout());
		pnlUsername.setLayout(new BorderLayout());
		pnlPassword.setLayout(new BorderLayout());
		
		//add to panel & frame
		TitledBorder title;
		title = BorderFactory.createTitledBorder("Welcome Operator");
		pnlLogin.setBorder(title);
		
		pnlUsername.add(lblUsername, BorderLayout.WEST);
		pnlUsername.add(txtUsername, BorderLayout.CENTER);
		pnlPassword.add(lblPassword, BorderLayout.WEST);
		pnlPassword.add(txtPassword, BorderLayout.CENTER);
		pnlButton.add(btnLogin);
		pnlButton.add(btnCancel);
		pnlShowPassword.add(showPassword, BorderLayout.CENTER);
		  
		pnlTop.add(pnlUsername, BorderLayout.CENTER);
		pnlTop.add(pnlPassword, BorderLayout.SOUTH);
		  
		pnlBottom.add(pnlShowPassword, BorderLayout.NORTH);
		pnlBottom.add(pnlButton, BorderLayout.CENTER);
		  
		pnlLogin.add(pnlTop, BorderLayout.CENTER);
		pnlLogin.add(pnlBottom, BorderLayout.SOUTH);
		  
		add(pnlLogin, BorderLayout.CENTER);
		
		setSize(350, 220);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void loginUser(){
		String username = txtUsername.getText();
		String password = txtPassword.getText();
		boolean valid = false;
		
		operators = Model.getOperatorList();
		
		for(int i=0; i<operators.size(); i++){
			if(username.equals(operators.get(i).getOperatorUsername())){
				if(password.equals(operators.get(i).getOperatorPassword())){
					valid = true;
					JOptionPane.showMessageDialog(this, "Login Success!", "Success", JOptionPane.INFORMATION_MESSAGE);

					new OperatorMenu();
					setVisible(false);
					
				}
			}
		}
		
		if(valid == false){
			JOptionPane.showMessageDialog(this, "Wrong Credentials!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnLogin){
				if(txtUsername.getText().toString().isEmpty()){
					JOptionPane.showMessageDialog(this, "Username cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(txtPassword.getText().toString().isEmpty()){
					JOptionPane.showMessageDialog(this, "Password cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				loginUser();
			   
		  } else {
		    txtUsername.requestFocus();
		  }
		  
		  if(showPassword.isSelected()){
			  txtPassword.setEchoChar((char)0);
		  } else {
			  txtPassword. setEchoChar('•');
		  }
		  
		  if(e.getSource() == btnCancel){
			  new MainMenu();
			  setVisible(false);
		  }
		
	}
	
//	public static void main(String[] args) {
//		new Login();
//	}

}
