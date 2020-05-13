package Project;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class Gestione_File extends JPanel{
	
	static Gestione_File gf = new Gestione_File();
	static File file;
	static String file_path;

	
	public static void main(String[] args) throws IOException {

	/*gf.get_file();
	gf.read_file_line(0);*/
		//gf.convert_date("10042035");
	
	}
	public void get_file() {
		
		JFileChooser fileChooser = new JFileChooser();
		  int path = fileChooser.showOpenDialog(Gestione_File.this);
		  File file = fileChooser.getSelectedFile();
		  String file_p = file.toString();
		  file_path = file_p.replaceAll("\\s+", "");
		  file_path = file_p.replaceAll(file.pathSeparator, "");
		
		  //System.out.println(file_path);

	}
	
	
	public static long getLineCount() throws IOException {
		File file = new File(file_path);
	    try (Stream<String> lines = Files.lines(file.toPath())) {
	    	
	        return lines.count();
	    }
	}
	
	
	public List read_file_line(int i) throws IOException {
		//i deve essere = 0 per prima run
		String file_line = Files.readAllLines(Paths.get(file_path)).get(i);
		Scanner rd_file = new Scanner(file_line).useDelimiter(" |\n");
		List<String> file_element = new ArrayList<String>();
		while(rd_file.hasNext()) {
			
			file_element.add(rd_file.next());
				
				
			}
		rd_file.close();
		//System.out.println(file_element+file_path);
		return file_element;
	
	}
	
	public int convert_date(String date) {
		String anno = date.substring(4, 8);
		String mese = date.substring(2, 4);
		String giorno = date.substring(0, 2);
		String tmp = anno+mese+giorno;
		
		int data = Integer.parseInt(tmp);
		System.out.println(data);
		return data;
	}


}