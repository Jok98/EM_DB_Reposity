package Project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class Create_Table {
	static Gestione_File gf = new Gestione_File();
	static  Emotional_Map em = new Emotional_Map();
	public static void main(String[] args) {

	}
	
	public void new_table() throws SQLException{
		String table = "CREATE TABLE Eventi(Iscrizione VARCHAR(3), Stato VARCHAR(6), Data INT, ID VARCHAR(20) , Coor_X INT, Coor_Y INT, Emotion VARCHAR(6))";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(em.jbdc_url);
			conn.createStatement().execute(table);
			System.out.println("Tabella creata");
			conn.close();
		} catch (SQLException e) {
			
			System.out.println("La tabella Eventi è già esistente");
			conn.close();
		}
		conn.close();

	}
	

	
	
	public void insert_value(int i) throws SQLException, ParseException, IOException {
		Connection conn = DriverManager.getConnection(em.jbdc_url);
		List<String> file_element = gf.read_file_line(i);
		System.out.println("stringa inserita: "+file_element);
		long count_lines = gf.getLineCount();
		
		String IN_OUT = file_element.get(0);
		String LOG = file_element.get(1);
		int Data =  Integer.parseInt(file_element.get(2));
		String ID = file_element.get(3);
		int Coor_X = Integer.parseInt(file_element.get(4));
		int Coor_Y = Integer.parseInt(file_element.get(5));
		String Emotion = file_element.get(6);
		
		String value = "INSERT INTO Eventi(Iscrizione, Stato, Data, ID, Coor_X, Coor_Y, Emotion )"
				+ "VALUES" +"(?,?,?,?,?,?,?)";
		PreparedStatement p_stmt = conn.prepareStatement(value);
		
			p_stmt.setString(1, IN_OUT);
			p_stmt.setString(2, LOG);
			p_stmt.setLong(3, Data);
			p_stmt.setString(4, ID);
			p_stmt.setLong(5, Coor_X);
			p_stmt.setLong(6, Coor_Y);
			p_stmt.setString(7, Emotion);
			p_stmt.executeUpdate();
		
		
		System.out.println("Valori inseriti");
		conn.close();
	}
}
