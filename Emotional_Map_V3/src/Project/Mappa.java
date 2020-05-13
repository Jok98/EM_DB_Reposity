package Project;

import java.awt.Frame;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Mappa {
	
	static Frame message = new Frame();
	static Mappa map = new Mappa();
	
	public static void main(String[] args) {
	

	}
	
	public int[][] setMap() {
		int[] [] Maps = new int[5][5];
		//contatore
		int c= 0;
		//caricamento array bi
		for (int j= 0; j<Maps.length; j++){
			
			for(int k = 0; k< Maps.length;k++) {
				
			Maps[j][k] = c++;

		}
			
		}
		return Maps;
	}
	
	public int compareCoor( int coorX, int coorY, String emotion) {
		
		int[] [] Maps = map.setMap();
		
		int z = 0;
		
		//verifica range 1^Point
		if((Maps[coorX][coorY]== 0) || (Maps[coorX][coorY]== 1) || (Maps[coorX][coorY]== 5) ||(Maps[coorX][coorY]== 6)) {
			z = 1;
			
		}
		
		//verifica range 2^Point
		if((Maps[coorX][coorY]== 10) || (Maps[coorX][coorY]== 11) || (Maps[coorX][coorY]== 15) ||(Maps[coorX][coorY]== 16)) {
			z = 2;
			
		}
		
		//verifica range 3^Point
		if((Maps[coorX][coorY]== 8) || (Maps[coorX][coorY]== 9) || (Maps[coorX][coorY]== 13) ||(Maps[coorX][coorY]== 14)) {
			z = 3;
			
		}
		
		
		switch(z) {
		case 0 :
			System.out.println("Non sei nel range di nessun point");
			break;
			
		case 1 :
			System.out.println("Primo Point");
			break;
			
		case 2 :
			System.out.println("Secondo Point");
			break;
			
		case 3 : 
			System.out.println("Terzo Point");
			break;


		}
		return z;
	}
	
	public void show_stat(ArrayList<String> poi_n, int z) {
		int F = 0;
		int A = 0;
		int S = 0;
		int T = 0;
		int N = 0;
		for(int i = 0; i < poi_n.size(); i++) {
		switch (poi_n.get(i)) {
		case "F" :
			F++;
			break;
			
		case "A" : 
			A++;
			break;
			
		case "S" :
			S++;
			break;
			
		case "T" : 
			T++;
			break;
			
		case "N" : 
			N++;
			break;
			
		
		}
		
		}
		try {
			JOptionPane.showMessageDialog(message, "POI_"+z+"\n"+
					"F : "+100*F/poi_n.size()+"% | "+
					"A : "+100*A/poi_n.size()+"% | "+
					"S : "+100*S/poi_n.size()+"% | "+
					"T : "+100*T/poi_n.size()+"% | "+
					"N : "+100*N/poi_n.size()+"% | ");
		}catch(ArithmeticException e) {
			
			JOptionPane.showMessageDialog(message,"Non ci sono elementi"+"\n"+
					"----------------------");
		}
	}

}