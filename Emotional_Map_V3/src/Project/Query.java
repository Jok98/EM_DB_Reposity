package Project;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;



public class Query {
	static Emotional_Map em = new Emotional_Map();
	static Gestione_File gf = new Gestione_File();
	static Query qy = new Query();
	static Mappa map = new Mappa();
	
	static ArrayList<String> poi_1 = new ArrayList<String>();
	static ArrayList<String> poi_2 = new ArrayList<String>();
	static ArrayList<String> poi_3 = new ArrayList<String>();
	
	public static void main(String[] args) {
		
		//qy.run_query("SELECT * FROM EVENTI");
		
	
	}
	
	//esegue un comando select * from eventi
	public void run_query(String query) throws SQLException, IOException {
		//conversione della stringa query in una lista
		Scanner query_scan = new Scanner(query).useDelimiter(" ");
		ArrayList<String> query_list = new ArrayList<String>();
		int z = 0; 
		while(query_scan.hasNext())  {
		query_list.add(query_scan.next());
		
		}
		System.out.println(query_list.get(1));
		//
		long count_lines = gf.getLineCount();
		System.out.println("numero righe "+ count_lines);
		Statement stmt = em.conn.createStatement();
		ResultSet rslt_set = stmt.executeQuery(query);
		ResultSetMetaData rslt_set_mtdt = rslt_set.getMetaData();
		int clmn_lnght = rslt_set_mtdt.getColumnCount();
		for(int i = 1; i<=clmn_lnght;i++) {
			System.out.format("%20s", rslt_set_mtdt.getColumnName(i)+" |");
			
		}
		System.out.println("");
		
		switch (query_list.get(1)) {
		case "*" : 
			while(rslt_set.next()){
			System.out.print(rslt_set.getString(1)+" |");
			System.out.print(rslt_set.getString(2)+" |");
			System.out.print(rslt_set.getInt(3)+" |");
			System.out.print(rslt_set.getString(4)+ " |");
			System.out.print(rslt_set.getInt(5)+" |");
			System.out.print(rslt_set.getInt(6)+" |");
			System.out.println(rslt_set.getString(7));
			System.out.println("--------------------------------------------------------------");
			}
		break;
		
		//
		case "Data" : 
			while(rslt_set.next()){
				System.out.println(rslt_set.getInt("Data")+" |");	
			}
		break;
		//
		case "Data,Emotion" :
			while(rslt_set.next()){
				System.out.print(rslt_set.getInt("Data")+" |");
				System.out.println(rslt_set.getString("Emotion")+" |");
			}
		break;
		//
		case "Coor_X,Coor_Y,Emotion" :
			while(rslt_set.next()){
				int x = rslt_set.getInt("Coor_X");
				int y = rslt_set.getInt("Coor_Y");
				String emotion = rslt_set.getString("Emotion");
				
				System.out.print(x+" |");
				System.out.print(y +" |");
				System.out.println(emotion +" |");
				
				int poi_n = map.compareCoor(x, y,emotion);
				
				switch (poi_n) {
				case 1 :
					poi_1.add(emotion);
					//System.out.println("a : "+poi_1.get(0));
				break;
				
				case 2 :
					poi_2.add(emotion);
					
				break;
				
				case 3 :
					poi_3.add(emotion);
					
				break;

				
				}
				
			}	
		break;
		
		}//switch end

			
			  	
		//em.conn.close();
    }

}
