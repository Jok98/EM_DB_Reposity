package Project;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class Create_DB {
	
	public static final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	public static final String jbdc_url = "jdbc:derby:DB_EM;create=true";
	static Connection conn ;
	
	static Gestione_File gf = new Gestione_File();
	static Create_DB db = new Create_DB();
	static Query qy = new Query();
	static Create_Table crt_table = new Create_Table();
	static Mappa map = new Mappa();
	
    public static void main(String[] args) throws SQLException, ParseException, IOException {
    	
    Scanner in = new Scanner(System.in);	
    if ((conn==null)) {
    	conn = DriverManager.getConnection(jbdc_url);
		System.out.println("Connessione al database aperta !");
	} else {
		System.out.println("Connessione al database già aperta !");
	}
    //
    crt_table.new_table();
    //
    long count_line = gf.getLineCount();
    //
    for(int i = 0; i<count_line;i++) {
    crt_table.insert_value(i);
    }
    //
    
    System.out.print("Inserire Query : ");
    String query = in.nextLine();
 
    qy.run_query(query);
    map.show_stat(qy.poi_1, 1);
	map.show_stat(qy.poi_2, 2);
	map.show_stat(qy.poi_3, 3);
    }
    
}

