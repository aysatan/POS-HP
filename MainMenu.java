import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainMenu extends JFrame{

	//NATASYA KINATA - 2440097373
	//UAS BAD LC11
	
	Register register;
	Login login;
	JMenuBar menuBar = new JMenuBar();
	JMenu menuOperator = new JMenu("Operator");
	JMenuItem menuLogin, menuRegister;
	JPanel pnlTitle;
	JLabel lblTitle;
	
	Font heading1 = new Font("Arial", Font.BOLD, 40);
	
	public MainMenu() {
		setTitle("POS HandPhone");
		
		JPanel pnlTitle = new JPanel();
		JLabel lblTitle = new JLabel("Welcome to Point of Sales Handphone!");
		JMenuItem menuLogin = new JMenuItem("Login");
		JMenuItem menuRegister = new JMenuItem("Register");
		
		menuOperator.add(menuLogin);
		menuOperator.add(menuRegister);
		menuBar.add(menuOperator);
		menuBar.setBackground(Color.LIGHT_GRAY);
		setJMenuBar(menuBar);
		
		menuLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Login();
				setVisible(false);
				
			}
		});
		
		menuRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Register();
				setVisible(false);
				
			}
		});
		
		pnlTitle.add(lblTitle);
		pnlTitle.setBorder(new EmptyBorder(300, 0, 0, 0));
		lblTitle.setFont(heading1);
		add(pnlTitle, BorderLayout.CENTER);
		
		setSize(800, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainMenu();
	}

}
