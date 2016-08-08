package com.my.jlms;

import com.my.classes.*;

import javax.swing.ImageIcon;
import com.my.classes.Database;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.awt.event.KeyAdapter;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import com.my.classes.Transaction;
import com.my.classes.Validation;
import java.awt.SystemColor;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.ButtonGroup;

public class Borrow extends JFrame {

	private JPanel contentPane;
	private JTextField txtISBN;
	private JTextField txtIsbn;
	private JTextField txtBorrowDate;
	private JTextField txtReturnDate;
	private JTextField txtNameB;
	private JTextField txtStatus;
	private JTextField txtDuration;
	private JTextField txtDate;
	private JTextField txtName;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JTextField txtPassport;
	private JTextField txtAge;
	private JTextField txtGender;
	private JTextField txtDateofIssue;
	private ButtonGroup btnGroup;
	
	Guest g = new Guest();
	Transaction t= new Transaction();
	Validation v = new Validation();
	Database database = new Database();
	Books book = new Books();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Borrow frame = new Borrow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Borrow() {
		setResizable(false);
		setTitle("Borrow / Return Books");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 427, 313);
		JTabbedPane tab = new JTabbedPane();
		Font font = new Font(null,Font.BOLD,11);
		tab.setFont(font);
		JPanel panel2 = new JPanel();
		tab.addTab("Register",null,panel2);
		panel2.setLayout(null);
		
		JLabel lblNameR = new JLabel("Name:");
		lblNameR.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNameR.setBounds(22, 11, 40, 14);
		panel2.add(lblNameR);
		
		txtName = new JTextField();
		txtName.setBounds(72, 8, 136, 20);
		panel2.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmail.setBounds(22, 42, 40, 14);
		panel2.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(72, 39, 136, 20);
		panel2.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtPhone = new JTextField();
		txtPhone.setBounds(72, 70, 136, 20);
		panel2.add(txtPhone);
		txtPhone.setColumns(10);
		
		JLabel lblPhoneno = new JLabel("PhoneNo:");
		lblPhoneno.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPhoneno.setBounds(10, 73, 52, 14);
		panel2.add(lblPhoneno);
		
		txtPassport = new JTextField();
		txtPassport.setBounds(72, 101, 136, 20);
		panel2.add(txtPassport);
		txtPassport.setColumns(10);
		
		JLabel lblPassportno = new JLabel("PassportNo:");
		lblPassportno.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPassportno.setBounds(0, 104, 67, 14);
		panel2.add(lblPassportno);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAddress.setBounds(11, 137, 51, 14);
		panel2.add(lblAddress);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(SystemColor.scrollbar);
		textArea.setBounds(72, 132, 136, 53);
		panel2.add(textArea);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRegister.setBounds(266, 191, 100, 52);
		panel2.add(btnRegister);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAge.setBounds(32, 199, 30, 14);
		panel2.add(lblAge);
		
		txtAge = new JTextField();
		txtAge.setBounds(72, 196, 136, 20);
		panel2.add(txtAge);
		txtAge.setColumns(10);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGender.setBounds(22, 229, 46, 14);
		panel2.add(lblGender);
		
		txtGender = new JTextField();
		txtGender.setBounds(72, 226, 136, 20);
		panel2.add(txtGender);
		txtGender.setColumns(10);
		//setContentPane(contentPane);
		getContentPane().add(tab);
		contentPane = new JPanel();
		tab.addTab("Borrow Books", null, contentPane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		JLabel lblISBN = new JLabel("ISBN:");
		lblISBN.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblISBN.setBounds(49, 11, 46, 14);
		contentPane.add(lblISBN);
		
		txtISBN = new JTextField();
		txtISBN.setBounds(93, 8, 142, 20);
		contentPane.add(txtISBN);
		txtISBN.setColumns(10);
		
		JLabel lblDuration = new JLabel("Duration:");
		lblDuration.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDuration.setBounds(30, 39, 65, 14);
		contentPane.add(lblDuration);
		
		JButton btnBorrow = new JButton("Borrow");
		btnBorrow.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBorrow.setBounds(115, 181, 123, 35);
		contentPane.add(btnBorrow);
		
		JLabel lblImage = new JLabel("Image");
		lblImage.setBounds(255, 11, 143, 144);
		contentPane.add(lblImage);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStatus.setBounds(40, 73, 46, 14);
		contentPane.add(lblStatus);
		
		txtStatus = new JTextField();
		txtStatus.setEditable(false);
		txtStatus.setBounds(93, 70, 142, 20);
		contentPane.add(txtStatus);
		txtStatus.setColumns(10);
		
		txtDuration = new JTextField();
		txtDuration.setBounds(93, 39, 142, 20);
		contentPane.add(txtDuration);
		txtDuration.setColumns(10);
		txtDuration.setToolTipText("mm/dd/yy");
		
		JLabel lblIssuedTo = new JLabel("Issued to:");
		lblIssuedTo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIssuedTo.setBounds(30, 102, 65, 14);
		contentPane.add(lblIssuedTo);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(93, 101, 142, 20);
		contentPane.add(comboBox);
		
		JLabel lblDateOfIssue = new JLabel("Issuing Date:");
		lblDateOfIssue.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDateOfIssue.setBounds(10, 135, 74, 14);
		contentPane.add(lblDateOfIssue);
		
		txtDateofIssue = new JTextField(getDate());
		txtDateofIssue.setEditable(false);
		txtDateofIssue.setBounds(93, 132, 142, 20);
		contentPane.add(txtDateofIssue);
		txtDateofIssue.setColumns(10);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExit.setBounds(282, 181, 89, 35);
		contentPane.add(btnExit);


		JPanel panel = new JPanel();
		tab.addTab("Return Books", null,panel);
		panel.setLayout(null);
		
		JLabel lblIsbn = new JLabel("ISBN:");
		lblIsbn.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIsbn.setBounds(73, 11, 34, 14);
		panel.add(lblIsbn);
		
		JLabel lblBorrowDate = new JLabel("Borrowing Date:");
		lblBorrowDate.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBorrowDate.setBounds(10, 74, 90, 14);
		panel.add(lblBorrowDate);
		
		JLabel lblReturnTime = new JLabel("Duration:");
		lblReturnTime.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblReturnTime.setBounds(48, 105, 52, 14);
		panel.add(lblReturnTime);
		
		txtIsbn = new JTextField();
		txtIsbn.setBounds(110, 8, 103, 20);
		panel.add(txtIsbn);
		txtIsbn.setColumns(10);
		
		txtBorrowDate = new JTextField();
		txtBorrowDate.setEditable(false);
		txtBorrowDate.setBounds(110, 71, 103, 20);
		panel.add(txtBorrowDate);
		txtBorrowDate.setColumns(10);
		
		txtReturnDate = new JTextField();
		txtReturnDate.setEditable(false);
		txtReturnDate.setBounds(110, 102, 103, 20);
		panel.add(txtReturnDate);
		txtReturnDate.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblName.setBounds(64, 42, 43, 14);
		panel.add(lblName);
		
		txtNameB = new JTextField();
		txtNameB.setEditable(false);
		txtNameB.setBounds(110, 39, 103, 20);
		panel.add(txtNameB);
		txtNameB.setColumns(10);
		
		JLabel lblImageB = new JLabel("Image");
		lblImageB.setBounds(242, 15, 150, 133);
		panel.add(lblImageB);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.setBounds(283, 181, 89, 61);
		panel.add(btnReturn);
		
		JLabel lblCurrentDate = new JLabel("Current Date:");
		lblCurrentDate.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCurrentDate.setBounds(24, 136, 76, 14);
		panel.add(lblCurrentDate);
		
		txtDate = new JTextField();
		txtDate.setEditable(false);
		txtDate.setBounds(110, 133, 103, 20);
		panel.add(txtDate);
		txtDate.setColumns(10);
		txtDate.setText(getDate());
		
		JLabel lblFine = new JLabel("Fine:");
		lblFine.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFine.setBounds(73, 178, 34, 14);
		panel.add(lblFine);
		
		JRadioButton rbtnNo = new JRadioButton("No");
		rbtnNo.setFont(new Font("Tahoma", Font.BOLD, 11));
		rbtnNo.setBounds(110, 174, 109, 23);
		panel.add(rbtnNo);
		
		JRadioButton rbtn7 = new JRadioButton("RM 7");
		rbtn7.setFont(new Font("Tahoma", Font.BOLD, 11));
		rbtn7.setBounds(110, 200, 109, 23);
		panel.add(rbtn7);
		
		JRadioButton rbtn20 = new JRadioButton("RM 20");
		rbtn20.setFont(new Font("Tahoma", Font.BOLD, 11));
		rbtn20.setBounds(110, 226, 109, 23);
		panel.add(rbtn20);
		
		btnGroup = new ButtonGroup();
		btnGroup.add(rbtnNo);
		btnGroup.add(rbtn7);
		btnGroup.add(rbtn20);
		
		txtISBN.addKeyListener(
				new KeyAdapter() {
					public void keyPressed(KeyEvent ev) {
						if(ev.getKeyCode() == KeyEvent.VK_ENTER) {					
							try {
								Books buk = (Books) database.readBook(Integer.parseInt(txtISBN.getText()));
								if(buk != null) {
									lblImage.setIcon(new ImageIcon(buk.getImage()));
							}
							}catch (SQLException ex) {
								JOptionPane.showMessageDialog(null,  ex.getMessage());
							}
							int val = Integer.parseInt(txtISBN.getText());
							boolean isTrue = book.checkStatus(val);
							if(isTrue) {
								txtStatus.setDisabledTextColor(Color.RED);
								txtStatus.setText("Available");
								btnBorrow.setEnabled(true);
							}else {
								txtStatus.setText("Not Available");
								btnBorrow.setEnabled(false);
							}
						}
					}
				});
		btnReturn.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int price = 0;
						if(rbtnNo.isSelected()) {
							price = 0;
							t.returnBook(Integer.parseInt(txtIsbn.getText()), price);
						} else if(rbtn7.isSelected()) {
							price = 7;
							t.returnBook(Integer.parseInt(txtIsbn.getText()), price);
						}else if(rbtn20.isSelected()) {
							price = 20;
							t.returnBook(Integer.parseInt(txtIsbn.getText()), price);
						} else {
							JOptionPane.showMessageDialog(null, "Please select one radio button.");
							//System.exit(1);
						}
						JOptionPane.showMessageDialog(null, "Book returned Successfully.");
						//database.close(3);
					}
				});
		
		btnBorrow.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							//JOptionPane.showMessageDialog(null, comboBox.getSelectedItem().toString() + "| " + Integer.parseInt(txtISBN.getText()) + "| " +  Authorization.s + "| " + t);
						
						if(v.validateIsbn(txtISBN.getText()) && v.validateAddress(txtDuration.getText())) {
							try {
								t.setStatus(txtStatus.getText());
								t.setDuration(txtDuration.getText());
								t.setIsbn(Integer.parseInt(txtISBN.getText()));

								SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
								Date date = null;
								try {
									date = formatter.parse(txtDateofIssue.getText());
									if(date != null) {
										t.setBorrowDate(date);
									}
								}catch(ParseException ee) {
									JOptionPane.showMessageDialog(null, ee.getMessage());
								}
								t.setGuest(comboBox.getSelectedItem().toString());
								if(t != null) {
									t.createOrder(comboBox.getSelectedItem().toString(), Integer.parseInt(txtISBN.getText()), Authorization.s,t);
									txtStatus.setText(null);
									txtDuration.setText(null); 
									txtISBN.setText(null); 
									//txtDateofIssue.setText(null); 
									JOptionPane.showMessageDialog(null, "Order was successfuly created.");
								}
								
							} catch (NumberFormatException e1) {
								JOptionPane.showMessageDialog(null,e1.getMessage());
							}
						}else {
							JOptionPane.showMessageDialog(null, "Error! Please check your input");
						}
					}
				});
		
		btnRegister.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(v.validateAddress(textArea.getText()) && v.validateAge(txtAge.getText())
								&& v.validateEmail(txtEmail.getText()) && v.validatePassport(txtPassport.getText())
								&& v.validatePhone(txtPhone.getText()) && v.validateName(txtName.getText()) && v.validateName(txtGender.getText())) {
							

							g.setAddress(textArea.getText());
							g.setAge(Integer.parseInt(txtAge.getText()));
							g.setEmail(txtEmail.getText());
							g.setGender(txtGender.getText());
							g.setName(txtName.getText());
							g.setPassport(txtPassport.getText());	
							g.setPhone(Integer.parseInt(txtPhone.getText()));
							
							//create the guest account
							if(g != null) {
								try {
									g.create(g);
								}catch(Exception x) {
									JOptionPane.showMessageDialog(null, "Error! Please try again..");
								}
								JOptionPane.showMessageDialog(null, "Account created Successfully.");
								textArea.setText(null);
								txtAge.setText(null);
								txtEmail.setText(null);
								txtGender.setText(null);
								txtName.setText(null);
								txtPassport.setText(null);
								txtPhone.setText(null);
							}else {
								JOptionPane.showMessageDialog(null, "Error! Please try again.");
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "Error, Wrong Input or try again!");
						}
					}
				});
		
		comboBox.addMouseListener(
				new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent ev) {
						List<Guest> list = null;
						list = database.readGuest();
						int n = list.size();
						List<String> pass = new ArrayList<String>();
						if(n <= 0) {
							JOptionPane.showMessageDialog(null, "No data found.");
						}else {
							for(int count = 0; count < n; count++) {
								g = list.get(count);
								pass.add(g.getPassportNp());
							}
							
							int size = pass.size();
							if(comboBox.getItemCount() != 0) {
								
								comboBox.removeAllItems();
								for(int c = 0; c < size; ++c) {
								comboBox.addItem(pass.get(c));
							}
							}else {
								for(int c = 0; c < size; ++c) {
									comboBox.addItem(pass.get(c));
								}
							}
						}
					}
				});
		
		txtIsbn.addKeyListener(
				new KeyAdapter() {
					public void keyPressed(KeyEvent e) {
						if(e.getKeyCode() == KeyEvent.VK_ENTER) {		
							try {
								boolean isTrue = book.checkStatus(Integer.parseInt(txtIsbn.getText()));
								if(isTrue){
									JOptionPane.showMessageDialog(null, "Ths book has not been borrowed yet!");
									btnReturn.setEnabled(false);
								}else {
									Books buk = (Books) database.readBook(Integer.parseInt(txtIsbn.getText()));
									Transaction tr = (Transaction) database.readOrder(Integer.parseInt(txtIsbn.getText()), "select", 0);
									if(buk != null) {
										lblImageB.setIcon(new ImageIcon(buk.getImage()));
										txtNameB.setText(buk.getBookName());
									}
									
									if(tr != null) {
										txtBorrowDate.setText(tr.getBorrowDate().toString().substring(0, 10));
										txtReturnDate.setText(tr.getDuration());
									}
									btnReturn.setEnabled(true);
								}
								
							}catch (SQLException ex) {
								ex.printStackTrace();
							}
							//database.close(2);
						}
					}
				});
		
		btnExit.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ac) {
						setVisible(false);
					}
				});
	}
	
	//method to get the date
	String getDate() {
		
		Calendar clock = new GregorianCalendar();
		int day = clock.get(Calendar.DAY_OF_MONTH);
		int month = clock.get(Calendar.MONTH);
		int year = clock.get(Calendar.YEAR);
		return day+"/"+month+"/"+year;
	}
}
