package Project;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Gestione_File {
	static Gestione_File gf = new Gestione_File();
	public static void main(String[] args) throws IOException {

		
	gf.read_file_line(0);
	
	}
	
	
	public static long getLineCount() throws IOException {
		File file = new File("C:\\Users\\jokmo\\git\\EM_DB_Reposity\\Emotional_Map_V3\\eventi.txt");
	    try (Stream<String> lines = Files.lines(file.toPath())) {
	    	
	        return lines.count();
	    }
	}
	
	
	public List read_file_line(int i) throws IOException {
		//i deve essere = 0 per prima run
		String file_line = Files.readAllLines(Paths.get("C:\\Users\\jokmo\\git\\EM_DB_Reposity\\Emotional_Map_V3\\eventi.txt")).get(i);
		Scanner rd_file = new Scanner(file_line).useDelimiter(" |\n");
		List<String> file_element = new ArrayList<String>();
		while(rd_file.hasNext()) {
			
			file_element.add(rd_file.next());
				
				
			}
		rd_file.close();
		return file_element;
	
	}

}
