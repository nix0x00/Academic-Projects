package com.my.jlms;
import com.my.classes.Books;
import com.my.classes.Database;
import com.my.classes.Validation;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.*;
import java.sql.SQLException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.InputStream;
import java.io. FileInputStream;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JButton;

public class ManageBooks extends JFrame {

	private JPanel contentPane;
	private static ManageBooks frame;
	private JTextField txtBook;
	private JTextField txtISBN;
	private JTextField txtAuthor;
	private JTextField txtEdition;
	private JTextField txtRow;
	private JTextField txtShelf;
	private JTextField txtCol;
	private JTextField txtImageA;
	private JTextField txtIsbnM;
	private JTextField txtBookM;
	private JTextField txtAuthorM;
	private JTextField txtEdiM;
	private JTextField txtRowM;
	private JTextField txtShelfM;
	private JTextField txtColM;
	private JTextField txtImage;
	private JTextField txtIsbnD;

	Books book = new Books();
	Database db = new Database();
	Validation valid = new Validation();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ManageBooks();
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
	public ManageBooks() {
		setTitle("Manage Books");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 283);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		JTabbedPane tab = new JTabbedPane();
		tab.addTab("Add Books", null,contentPane);
		getContentPane().add(tab);
		//setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBookTitle = new JLabel("Book Title:");
		lblBookTitle.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBookTitle.setBounds(10, 11, 71, 14);
		contentPane.add(lblBookTitle);
		
		JLabel lblIsbnNo = new JLabel("ISBN no:");
		lblIsbnNo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIsbnNo.setBounds(25, 36, 56, 14);
		contentPane.add(lblIsbnNo);
		
		JLabel lblAuthor = new JLabel("Author:");
		lblAuthor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAuthor.setBounds(25, 64, 46, 14);
		contentPane.add(lblAuthor);
		
		JLabel lblEdition = new JLabel("Edition:");
		lblEdition.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEdition.setBounds(25, 96, 46, 14);
		contentPane.add(lblEdition);
		
		JLabel lblShelf = new JLabel("Shelf No:");
		lblShelf.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblShelf.setBounds(25, 157, 56, 14);
		contentPane.add(lblShelf);
		
		JLabel lblRowNo = new JLabel("Row No:");
		lblRowNo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRowNo.setBounds(25, 127, 46, 14);
		contentPane.add(lblRowNo);
		
		JLabel lblColumnNo = new JLabel("Column No:");
		lblColumnNo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblColumnNo.setBounds(10, 185, 71, 14);
		contentPane.add(lblColumnNo);
		
		txtBook = new JTextField();
		txtBook.setBounds(78, 8, 113, 20);
		contentPane.add(txtBook);
		txtBook.setColumns(10);
		txtBook.setToolTipText("Only alphabets/words are valid.");
		
		txtISBN = new JTextField();
		txtISBN.setBounds(78, 36, 113, 20);
		contentPane.add(txtISBN);
		txtISBN.setColumns(10);
		txtISBN.setToolTipText("Only integers are valid.");
		
		txtAuthor = new JTextField();
		txtAuthor.setBounds(78, 64, 113, 20);
		contentPane.add(txtAuthor);
		txtAuthor.setColumns(10);
		txtAuthor.setToolTipText("Only words/Alphabets are valid.");
		
		txtEdition = new JTextField();
		txtEdition.setBounds(78, 93, 113, 20);
		contentPane.add(txtEdition);
		txtEdition.setColumns(10);
		txtEdition.setToolTipText("Integers and alphabets are valid.");
		
		txtRow = new JTextField();
		txtRow.setBounds(78, 124, 113, 20);
		contentPane.add(txtRow);
		txtRow.setColumns(10);
		txtRow.setToolTipText("Only integers are valid.");
		
		txtShelf = new JTextField();
		txtShelf.setBounds(78, 154, 113, 20);
		contentPane.add(txtShelf);
		txtShelf.setColumns(10);
		txtShelf.setToolTipText("Only integers are alphabet are valid.");
		
		txtCol = new JTextField();
		txtCol.setBounds(78, 182, 113, 20);
		contentPane.add(txtCol);
		txtCol.setColumns(10);
		txtCol.setToolTipText("Only integers are valid.");
		
		txtImageA = new JTextField();
		txtImageA.setEditable(false);
		txtImageA.setBounds(297, 8, 122, 20);
		contentPane.add(txtImageA);
		txtImageA.setColumns(10);
		
		JButton btnBrowse = new JButton("Browse Image");
		btnBrowse.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBrowse.setBounds(297, 36, 122, 37);
		contentPane.add(btnBrowse);
		
		JLabel lblBrowseImage = new JLabel("Image:");
		lblBrowseImage.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBrowseImage.setBounds(241, 11, 46, 14);
		contentPane.add(lblBrowseImage);
		
		JButton btnAdd = new JButton("Add new Book");
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAdd.setBounds(291, 157, 128, 50);
		contentPane.add(btnAdd);
		JPanel panel = new JPanel();
		tab.addTab("Modify Books", null,panel);
		panel.setLayout(null);
		
		JLabel lblISBNM = new JLabel("ISBN No :");
		lblISBNM.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblISBNM.setBounds(23, 11, 60, 14);
		panel.add(lblISBNM);
		
		JLabel lblBookM = new JLabel("Book Title :");
		lblBookM.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBookM.setBounds(10, 36, 71, 14);
		panel.add(lblBookM);
		
		JLabel lblAuthorM = new JLabel("Author :");
		lblAuthorM.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAuthorM.setBounds(23, 61, 51, 14);
		panel.add(lblAuthorM);
		
		txtIsbnM = new JTextField();
		txtIsbnM.setBounds(91, 8, 106, 20);
		panel.add(txtIsbnM);
		txtIsbnM.setColumns(10);
		txtIsbnM.setToolTipText("Only integers are valid. Press Enter to retrieve Data!");
		
		txtBookM = new JTextField();
		txtBookM.setBounds(91, 33, 106, 20);
		panel.add(txtBookM);
		txtBookM.setColumns(10);
		txtBook.setToolTipText("Only words/alphabets are valid.");
		
		txtAuthorM = new JTextField();
		txtAuthorM.setBounds(91, 58, 106, 20);
		panel.add(txtAuthorM);
		txtAuthorM.setColumns(10);
		txtAuthorM.setToolTipText("Only words/alphabets are valid.");
		
		JLabel lblEdiM = new JLabel("Edition :");
		lblEdiM.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEdiM.setBounds(23, 87, 46, 14);
		panel.add(lblEdiM);
		
		txtEdiM = new JTextField();
		txtEdiM.setBounds(91, 84, 106, 20);
		panel.add(txtEdiM);
		txtEdiM.setColumns(10);
		txtEdiM.setToolTipText("Only alphabets/integers are valid.");
		
		txtRowM = new JTextField();
		txtRowM.setBounds(91, 110, 106, 20);
		panel.add(txtRowM);
		txtRowM.setColumns(10);
		txtRowM.setToolTipText("Only integers are valid.");
		
		JLabel lblRowM = new JLabel("Row No :");
		lblRowM.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRowM.setBounds(23, 113, 51, 14);
		panel.add(lblRowM);
		
		txtShelfM = new JTextField();
		txtShelfM.setBounds(91, 136, 106, 20);
		panel.add(txtShelfM);
		txtShelfM.setColumns(10);
		txtShelfM.setToolTipText("Only integers/alphabets are valid.");
		
		JLabel lblShelfNo = new JLabel("Shelf No :");
		lblShelfNo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblShelfNo.setBounds(23, 138, 59, 14);
		panel.add(lblShelfNo);
		
		txtColM = new JTextField();
		txtColM.setBounds(91, 161, 106, 20);
		panel.add(txtColM);
		txtColM.setColumns(10);
		txtColM.setToolTipText("Only Integers are valid.");
		
		JLabel lblColumnM = new JLabel("Column No :");
		lblColumnM.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblColumnM.setBounds(10, 163, 73, 14);
		panel.add(lblColumnM);
		
		JLabel lblImage = new JLabel("Image:");
		lblImage.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblImage.setBounds(225, 11, 46, 14);
		panel.add(lblImage);
		
		txtImage = new JTextField();
		txtImage.setEditable(false);
		txtImage.setBounds(284, 8, 125, 20);
		panel.add(txtImage);
		txtImage.setColumns(10);
		
		JButton btnBrowseM = new JButton("Browse Image");
		btnBrowseM.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBrowseM.setBounds(284, 36, 125, 33);
		panel.add(btnBrowseM);
		
		JButton btnM = new JButton("Modify / Edit");
		btnM.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnM.setBounds(284, 135, 125, 46);
		panel.add(btnM);
		JPanel panel1 = new JPanel();
		tab.addTab("Delete Books", null,panel1);
		panel1.setLayout(null);
		
		JLabel lblIsbnNoD = new JLabel("ISBN No:");
		lblIsbnNoD.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIsbnNoD.setBounds(85, 28, 46, 14);
		panel1.add(lblIsbnNoD);
		
		txtIsbnD = new JTextField();
		txtIsbnD.setBounds(152, 25, 125, 20);
		panel1.add(txtIsbnD);
		txtIsbnD.setColumns(10);
		txtIsbnD.setToolTipText("Only integers are valid.");
		
		JButton btnDeleteBook = new JButton("Delete Book");
		btnDeleteBook.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDeleteBook.setBounds(152, 80, 127, 29);
		panel1.add(btnDeleteBook);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExit.setBounds(152, 140, 125, 29);
		panel1.add(btnExit);
		
		
		//Below are the event handlers/listener
		txtIsbnM.addKeyListener(
				new KeyAdapter() {
					public void keyPressed(KeyEvent event) {
						if(event.getKeyCode() == KeyEvent.VK_ENTER) {
							try {
								Books buk = (Books) db.readBook(Integer.parseInt(txtIsbnM.getText()));
								txtAuthorM.setText(buk.getAuthorName());
								txtBookM.setText(buk.getBookName());
								txtColM.setText(Integer.toString(buk.getColNo()));
								txtEdiM.setText(buk.getEdition());
								txtShelfM.setText(buk.getShelfNo());
								txtRowM.setText(Integer.toString(buk.getRowNo()));
								txtImage.setText(buk.getImage());
								
							}catch(SQLException ee) {
								ee.printStackTrace();
							}
							catch(Exception er) {
								JOptionPane.showMessageDialog(null, er.getMessage());
							}
						}
					}
				});
		btnAdd.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if(book.validate(txtBook.getText(), txtISBN.getText(), txtAuthor.getText(), txtEdition.getText(), txtRow.getText(), txtCol.getText(), txtShelf.getText())) {
							book.setAuthorName(txtAuthor.getText());
							book.setColNo(Integer.parseInt(txtCol.getText()));
							book.setIsbn(Integer.parseInt(txtISBN.getText()));
							book.setEdition(txtEdition.getText());
							book.setRowNo(Integer.parseInt(txtRow.getText()));
							book.setShelfNo(txtShelf.getText());
							book.setbookName(txtBook.getText());
							
							if(book != null) {
								book.insertBookRecord(book, "add");
								JOptionPane.showMessageDialog(null, "Book record was added Successfully.");
							}
							txtBook.setText(null); txtShelf.setText(null); txtRow.setText(null);
							txtEdition.setText(null); txtISBN.setText(null); txtCol.setText(null);
							txtAuthor.setText(null);
						}
						else {
							JOptionPane.showMessageDialog(null, "Error! in input, please check.");
						}
					}
				});
		
		btnM.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(book.validate(txtBookM.getText(), txtIsbnM.getText(), txtAuthorM.getText(), txtEdiM.getText(), txtRowM.getText(), txtColM.getText(), txtShelfM.getText())) {
							
							book.setAuthorName(txtAuthorM.getText());
							book.setColNo(Integer.parseInt(txtColM.getText()));
							book.setIsbn(Integer.parseInt(txtIsbnM.getText()));
							book.setEdition(txtEdiM.getText());
							book.setRowNo(Integer.parseInt(txtRowM.getText()));
							book.setShelfNo(txtShelfM.getText());
							book.setbookName(txtBookM.getText());
							
							book.insertBookRecord(book, "mod");
							JOptionPane.showMessageDialog(null, "Book record was modified Successfully.");

						}else {
							JOptionPane.showMessageDialog(null, "Error! in input, please check.");
						}
					}
				});
		
		btnDeleteBook.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(valid.validateIsbn(txtIsbnD.getText())) {
							int id = Integer.parseInt(txtIsbnD.getText());
							boolean isTrue = db.removeBook(id);
							if(isTrue) {
								JOptionPane.showMessageDialog(null, "Book Record was sucessfully deleted!");
							}else {
								JOptionPane.showMessageDialog(null, "Unable to delete. Please try again later.");
							}
						}else {
							JOptionPane.showMessageDialog(null, "Error! in input, please check.");
						}
						txtIsbnD.setText(null);
					}
				});
		
		btnBrowse.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						browse();
				}
			});
		
		btnBrowseM.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ev) {
						browse();
					}
				});
		
		btnExit.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ev) {
						//tab.removeAll();
						setVisible(false);
						
					}
				});
	   }
	
	public void browse() {
		JFileChooser fch = new JFileChooser();

		fch.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".jpeg", ".jpg", ".png");
		fch.addChoosableFileFilter(filter);
		fch.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fch.showSaveDialog(null);
		
		if(result == JFileChooser.CANCEL_OPTION) {
			JOptionPane.showMessageDialog(null, "Please choose an image.");
		}
		else if(result == JFileChooser.APPROVE_OPTION) {
			File fileName = fch.getSelectedFile();
			String path = fileName.getAbsolutePath();
			txtImageA.setText(path);
			book.setImage(path);
			
	}
	}
}
