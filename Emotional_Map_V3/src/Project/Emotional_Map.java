package Project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Emotional_Map  {
	static Emotional_Map emotional_map;
	static Gestione_File gf = new Gestione_File();
	static Query qy = new Query();
	static Create_Table crt_table = new Create_Table();
	static Mappa map = new Mappa();
	
	public static final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	public static final String jbdc_url = "jdbc:derby:DB_EM;create=true";
	static Connection conn ;
	
	private JFrame frame;
	private JTextField data_1;
	private JTextField data_2;
	private JTextField txtfld_ID;
	
	static int path;
	static File file_path;
	
	/**
	 * Launch the application.
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException, ParseException, IOException {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Emotional_Map window = new Emotional_Map();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		//inizializo connessione con il driver
		if ((conn==null)) {
	    	conn = DriverManager.getConnection(jbdc_url);
			System.out.println("Connessione al database aperta !");
		} else {
			System.out.println("Connessione al database già aperta !");
		}
		gf.get_file();
		//file_path = emotional_map.get_file();
	    //creo la tabella eventi
	    crt_table.new_table();
	    //
	    long count_line = gf.getLineCount();
	    List<String> file_element = new ArrayList<String>();
	    Boolean new_ins = false;
	    //carico nella tabella eventi il file eventi riga per riga
	    for(int i = 0; i<count_line;i++) {
	    crt_table.insert_value(i,new_ins,file_element);
	    }
		
	}

	
	/**
	 * Create the application.
	 */
	public Emotional_Map() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 495, 407);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//
		JLabel lbl_Stat = new JLabel("Visualizza Statistiche POI");
		lbl_Stat.setBounds(10, 261, 414, 17);
		lbl_Stat.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_Stat.setHorizontalAlignment(SwingConstants.CENTER);
		
		//
		JComboBox cb_Emotion = new JComboBox();
		cb_Emotion.setModel(new DefaultComboBoxModel(new String[] {"F", "A", "S", "T", "N"}));
		cb_Emotion.setSelectedIndex(0);
		cb_Emotion.setFont(new Font("Tahoma", Font.BOLD, 12));
		cb_Emotion.setBounds(384, 56, 40, 19);
		frame.getContentPane().add(cb_Emotion);
		
		//
		JComboBox cb_X = new JComboBox();
		cb_X.setFont(new Font("Tahoma", Font.BOLD, 12));
		cb_X.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6"}));
		cb_X.setSelectedIndex(0);
		cb_X.setBounds(166, 55, 40, 20);
		frame.getContentPane().add(cb_X);
		
		//
		JComboBox cb_Y = new JComboBox();
		cb_Y.setFont(new Font("Tahoma", Font.BOLD, 12));
		cb_Y.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6"}));
		cb_Y.setSelectedIndex(0);
		cb_Y.setBounds(244, 54, 40, 22);
		frame.getContentPane().add(cb_Y);
		
		//
		JLabel lbl_ = new JLabel("Inserire dati spostamento");
		lbl_.setBounds(10, 11, 414, 17);
		lbl_.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		
		//
		JButton btnConferma = new JButton("CONFERMA");
		btnConferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String ID = txtfld_ID.getText();
				String x = Integer.toString(cb_X.getSelectedIndex());
				String y = Integer.toString(cb_Y.getSelectedIndex());
				//inserire data atuuale
				String data = "20200412";
				String emotion = (String) cb_Emotion.getSelectedItem();
				List<String> file_element = new ArrayList<String>();
				file_element.add(0,"IN");
				file_element.add(1,"LOGIN");
				file_element.add(2, data);
				file_element.add(3,ID);
				file_element.add(4,x);
				file_element.add(5,y);
				file_element.add(6,emotion);
				boolean new_ins = true;
				try {
					crt_table.insert_value(0, new_ins, file_element);
				} catch (SQLException | ParseException | IOException e) {
					
					e.printStackTrace();
				}
				
			}
		});
		btnConferma.setBounds(10, 90, 89, 23);
		
		//
		data_1 = new JTextField();
		data_1.setHorizontalAlignment(SwingConstants.CENTER);
		data_1.setText("AAAAMMGG");
		data_1.setToolTipText("");
		data_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

			}
		});
		data_1.setBounds(206, 297, 78, 20);
		frame.getContentPane().add(data_1);
		data_1.setColumns(10);
		
		
		
		//
		JButton btn_Par_Stat = new JButton("Statistiche parziali");
		btn_Par_Stat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int d_1 =Integer.parseInt(data_1.getText());
				int d_2 = Integer.parseInt(data_2.getText());
				String query ="SELECT Coor_X,Coor_Y,Emotion FROM Eventi WHERE Data BETWEEN "+d_1+" AND "+d_2;
				  
			    try {
					qy.run_query(query);
				} catch (SQLException | IOException e) {
					
					e.printStackTrace();
				}
			    map.show_stat(qy.poi_1, 1);
				map.show_stat(qy.poi_2, 2);
				map.show_stat(qy.poi_3, 3);
		
			}
		});
		btn_Par_Stat.setBounds(10, 296, 157, 23);
		
		
		//
		JButton btn_Full_Stat = new JButton("Statistiche totali");
		btn_Full_Stat.setBounds(147, 334, 157, 23);
		btn_Full_Stat.addActionListener(new ActionListener() {
			//gestione evento click
			public void actionPerformed(ActionEvent arg0) {
				  String query ="SELECT Coor_X,Coor_Y,Emotion FROM Eventi" ;
				  
				    try {
						qy.run_query(query);
					} catch (SQLException | IOException e) {
					
						e.printStackTrace();
					}
				    map.show_stat(qy.poi_1, 1);
					map.show_stat(qy.poi_2, 2);
					map.show_stat(qy.poi_3, 3);
			}
		});
		
		
		
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btn_Full_Stat);
		frame.getContentPane().add(lbl_Stat);
		frame.getContentPane().add(lbl_);
		frame.getContentPane().add(btnConferma);
		frame.getContentPane().add(btn_Par_Stat);
		
		//
		JButton btn_Disiscrizione = new JButton("DISISCRIVI");
		btn_Disiscrizione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String ID = "JOK" ;
				String delete_query = "DELETE * FROM eventi WHERE ID = JOK" ;
				try {
					qy.delete_ID();
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
				
				
			}
		});
		btn_Disiscrizione.setBounds(10, 177, 89, 23);
		frame.getContentPane().add(btn_Disiscrizione);
		
		//
		data_2 = new JTextField();
		data_2.setHorizontalAlignment(SwingConstants.CENTER);
		data_2.setText("AAAAMMGG");
		data_2.setBounds(325, 297, 86, 20);
		frame.getContentPane().add(data_2);
		data_2.setColumns(10);
		
		JLabel lblDa = new JLabel("Da : ");
		lblDa.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDa.setBounds(173, 296, 33, 25);
		frame.getContentPane().add(lblDa);
		
		JLabel lblA = new JLabel("a : ");
		lblA.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblA.setBounds(302, 300, 23, 14);
		frame.getContentPane().add(lblA);
		
		JLabel lblID = new JLabel("ID : ");
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblID.setBounds(10, 58, 33, 14);
		frame.getContentPane().add(lblID);
		
		txtfld_ID = new JTextField();
		txtfld_ID.setBounds(51, 54, 86, 20);
		frame.getContentPane().add(txtfld_ID);
		txtfld_ID.setColumns(10);
		
		JLabel lblX = new JLabel("X : ");
		lblX.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblX.setBounds(147, 58, 30, 14);
		frame.getContentPane().add(lblX);
		
		JLabel lblY = new JLabel("Y : ");
		lblY.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblY.setBounds(223, 57, 30, 14);
		frame.getContentPane().add(lblY);
		
		JLabel lblEmozione = new JLabel("Emozione :");
		lblEmozione.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmozione.setBounds(302, 57, 77, 14);
		frame.getContentPane().add(lblEmozione);
		
		JButton btn_help = new JButton("?");
		btn_help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String show_all_qy = "SELECT * FROM Eventi";
				try {
					qy.run_query(show_all_qy);
				} catch (SQLException | IOException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btn_help.setBounds(390, 10, 89, 23);
		frame.getContentPane().add(btn_help);
		
		
		

	}
}
