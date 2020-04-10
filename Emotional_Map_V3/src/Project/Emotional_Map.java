package Project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.CardLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Emotional_Map {

	static Gestione_File gf = new Gestione_File();
	static Create_DB db = new Create_DB();
	static Query qy = new Query();
	static Create_Table crt_table = new Create_Table();
	static Mappa map = new Mappa();
	
	public static final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	public static final String jbdc_url = "jdbc:derby:DB_EM;create=true";
	static Connection conn ;
	
	private JFrame frame;
	private JTextField data_1;
	private JTextField data_2;

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
		
	    //creo la tabella eventi
	    crt_table.new_table();
	    //
	    long count_line = gf.getLineCount();
	    //carico nella tabella eventi il file eventi riga per riga
	    for(int i = 0; i<count_line;i++) {
	    crt_table.insert_value(i);
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//
		JLabel lbl_Stat = new JLabel("Visualizza Statistiche POI");
		lbl_Stat.setBounds(10, 155, 414, 17);
		lbl_Stat.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_Stat.setHorizontalAlignment(SwingConstants.CENTER);
		
		//
		JLabel lbl_ = new JLabel("Visualizza Statistiche POI");
		lbl_.setBounds(10, 11, 414, 17);
		lbl_.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		//
		JButton btnConferma = new JButton("CONFERMA");
		btnConferma.setBounds(176, 114, 89, 23);
		
		data_1 = new JTextField();
		data_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

			}
		});
		data_1.setBounds(206, 191, 86, 20);
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
		btn_Par_Stat.setBounds(10, 190, 157, 23);
		
		
		//
		JButton btn_Full_Stat = new JButton("Statistiche totali");
		btn_Full_Stat.setBounds(10, 227, 157, 23);
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
		
		
		
		data_2 = new JTextField();
		data_2.setBounds(325, 191, 86, 20);
		frame.getContentPane().add(data_2);
		data_2.setColumns(10);
		
		JLabel lblDa = new JLabel("Da : ");
		lblDa.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDa.setBounds(173, 190, 33, 25);
		frame.getContentPane().add(lblDa);
		
		JLabel lblA = new JLabel("a : ");
		lblA.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblA.setBounds(302, 194, 23, 14);
		frame.getContentPane().add(lblA);
	}
}
