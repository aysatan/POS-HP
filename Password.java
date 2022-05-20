import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Password extends JFrame{

	JPanel pnlLogin, pnlTop, pnlBottom, pnlPassword, pnlShowPassword, pnlButton;
	JButton btnSubmit, btnCancel;
	JLabel lblPassword;
	JPasswordField txtPassword;
	JCheckBox showPassword = new JCheckBox("Show Password");
	private JOptionPane optionPane;
	
	public Password() {
		setTitle("Supervisor");
		inisialisasi();
		
		showPassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			 if(showPassword.isSelected()){
					 txtPassword.setEchoChar((char)0);
				 } else {
					 txtPassword. setEchoChar('•');
				 }
			}
		});
		
		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtPassword.getText().isEmpty()){
					optionPane.showMessageDialog(null, "Please the password!");
					return;
				} 
				
				if(txtPassword.getText().equals("supervisor001")){
					optionPane.showMessageDialog(null, "Verification Successful!");
					new OperatorMenu();
					setVisible(false);
				} else {
					optionPane.showMessageDialog(null, "Wrong Password!");
					return;
				}
				
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new OperatorMenu();
				setVisible(false);
				
			}
		});
		
		setSize(300,170);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	void inisialisasi(){
		pnlLogin = new JPanel();
		pnlTop = new JPanel();
		pnlBottom = new JPanel();
		pnlPassword = new JPanel();
		pnlShowPassword = new JPanel();
		pnlButton = new JPanel();
		
		lblPassword = new JLabel("Password : ");
		
		txtPassword = new JPasswordField();
		  
		btnSubmit = new JButton("Submit");
		btnCancel = new JButton("Cancel");
		  
		setLayout(new BorderLayout());
		pnlLogin.setLayout(new BorderLayout());
		pnlTop.setLayout(new GridLayout(1,1,10,10));
		pnlTop.setBorder(new EmptyBorder(10, 10, 10, 10));
		pnlBottom.setLayout(new BorderLayout());
		pnlPassword.setLayout(new BorderLayout());
		pnlPassword.add(lblPassword, BorderLayout.WEST);
		pnlPassword.add(txtPassword, BorderLayout.CENTER);
		pnlButton.add(btnSubmit);
		pnlButton.add(btnCancel);
		pnlShowPassword.add(showPassword, BorderLayout.CENTER);
		 
		pnlTop.add(pnlPassword, BorderLayout.SOUTH);
		  
		pnlBottom.add(pnlShowPassword, BorderLayout.NORTH);
		pnlBottom.add(pnlButton, BorderLayout.CENTER);
		  
		pnlLogin.add(pnlTop, BorderLayout.CENTER);
		pnlLogin.add(pnlBottom, BorderLayout.SOUTH);
		  
		add(pnlLogin, BorderLayout.CENTER);
	}

}
