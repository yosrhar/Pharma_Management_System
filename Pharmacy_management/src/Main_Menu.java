import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main_Menu {

	private JFrame frame;
	private JTextField txtname;
	private JTextField txtprice;
	private JTextField txttotal;
	private JTextField txtpaid;
	private JTextField txtchange;
	private JTable txttable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Menu window = new Main_Menu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	Connection con;
	PreparedStatement pst;
	ResultSet rs;

	public Main_Menu() {
		initialize();
		Table();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/pharma", "root", "");
			System.out.println("Connection established");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	private void Table() {
		try {
			Connect();
			String[] header = { "Code", "Name", "Price", "Amt", "Total", "Paid", "Change" };
			String[] line = new String[8];

			DefaultTableModel model = new DefaultTableModel(null, header);
			String sql = "select * from tb_pharma";
			Statement st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next())
			{
				line[0] = rs.getString("code");
				line[1] = rs.getString("name");
				line[2] = rs.getString("price");
				line[3] = rs.getString("amt");
				line[4] = rs.getString("total");
				line[5] = rs.getString("paid");
				line[6] = rs.getString("change");
				model.addRow(line);
			}
txttable.setModel(model);
con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1035, 716);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 21, 1001, 114);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("PHARMACY MANAGEMENT");
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 6, 981, 97);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(10, 146, 1001, 287);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1_1 = new JLabel("Name:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_1_1.setBounds(28, 11, 110, 49);
		panel_1.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Price:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_1_2.setBounds(28, 71, 110, 49);
		panel_1.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Amount:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_1_3.setBounds(28, 131, 110, 49);
		panel_1.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("Total:");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_1_4.setBounds(574, 11, 110, 49);
		panel_1.add(lblNewLabel_1_4);

		JLabel lblNewLabel_1_5 = new JLabel("Paid:");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_1_5.setBounds(574, 71, 110, 49);
		panel_1.add(lblNewLabel_1_5);

		JLabel lblNewLabel_1_6 = new JLabel("Change:");
		lblNewLabel_1_6.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_1_6.setBounds(574, 131, 110, 49);
		panel_1.add(lblNewLabel_1_6);

		txtname = new JTextField();
		txtname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtname.setColumns(10);
		txtname.setBounds(148, 11, 258, 49);
		panel_1.add(txtname);

		txtprice = new JTextField();
		txtprice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtprice.setColumns(10);
		txtprice.setBounds(148, 71, 258, 49);
		panel_1.add(txtprice);

		txttotal = new JTextField();
		txttotal.setHorizontalAlignment(SwingConstants.CENTER);
		txttotal.setForeground(new Color(255, 0, 0));
		txttotal.setBackground(new Color(0, 0, 0));
		txttotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txttotal.setColumns(10);
		txttotal.setBounds(698, 11, 258, 49);
		panel_1.add(txttotal);

		txtpaid = new JTextField();
		txtpaid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				int total = Integer.parseInt(txttotal.getText());
				int paid = Integer.parseInt(txtpaid.getText());
				int change = paid - total;
				txtchange.setText(String.valueOf(change));
			}
		});
		txtpaid.setHorizontalAlignment(SwingConstants.CENTER);
		txtpaid.setForeground(new Color(0, 0, 0));
		txtpaid.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtpaid.setColumns(10);
		txtpaid.setBackground(new Color(0, 255, 0));
		txtpaid.setBounds(698, 71, 258, 49);
		panel_1.add(txtpaid);

		txtchange = new JTextField();
		txtchange.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

			}
		});
		txtchange.setHorizontalAlignment(SwingConstants.CENTER);
		txtchange.setForeground(new Color(0, 0, 255));
		txtchange.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtchange.setColumns(10);
		txtchange.setBackground(new Color(255, 255, 255));
		txtchange.setBounds(698, 131, 258, 49);
		panel_1.add(txtchange);

		JSpinner txtamount = new JSpinner();
		txtamount.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int price = Integer.parseInt(txtprice.getText());
				int amt = Integer.parseInt(txtamount.getValue().toString());
				int total = price * amt;
				txttotal.setText(String.valueOf(total));

			}
		});
		txtamount.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtamount.setBounds(148, 140, 258, 37);
		panel_1.add(txtamount);

		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connect();
					pst = con.prepareStatement(
							"insert into tb_pharma(name, price, amt, total, paid, change) values (?,?,?,?,?,?)");
					pst.setString(1, txtname.getText());
					pst.setString(2, txtprice.getText());
					pst.setString(3, txtamount.getValue().toString());
					pst.setString(4, txttotal.getText());
					pst.setString(5, txtpaid.getText());
					pst.setString(6, txtchange.getText());
					pst.executeUpdate();
					con.close();
					JOptionPane.showMessageDialog(null, txtname.getText() + "Added");
					Table();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(760, 225, 196, 37);
		panel_1.add(btnNewButton);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 444, 1001, 201);
		frame.getContentPane().add(scrollPane_1);

		txttable = new JTable();
		scrollPane_1.setViewportView(txttable);
		txttable.setBackground(new Color(192, 192, 192));
		txttable.setFont(new Font("Tahoma", Font.PLAIN, 16));
	}
}
