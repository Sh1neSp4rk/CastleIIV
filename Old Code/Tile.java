import java.awt.*;
import javax.swing.*;
public class Tile{
		private boolean north, south, east, west;
		boolean start, end, visited;
		ImageIcon image;

	public Tile(boolean north, boolean south, boolean east, boolean west, ImageIcon image, boolean end, boolean start, boolean visited){
		this.north=north;
		this.south=south;
		this.east=east;
		this.west=west;
		this.image=image;
		this.end=end;
		this.start=start;
		this.visited=visited;
	}

	public boolean equals (Tile t){
		if(this.north==t.north&&
			this.south==t.south&&
			this.east==t.east&&
			this.west==t.west){
		
			return true;
		}else{
			return false;
		}
	}

	public Tile getFixTile(boolean north, boolean south, boolean east, boolean west, Tile[] choices){
		for(int i=0; i<choices.length; i++){
			if (choices[i].north == north && choices[i].south==south && choices[i].east==east && choices[i].west==west){
			
				return choices[i];
			}
		}
		System.out.println("error");
		return this;
	}
	public String toString (){
		return "n="+north+" e="+east+" s="+south +"  w="+west +" end =" +end+ " start= "+start;
	}
	
	public boolean north(){
		return north;
	}
	
	public boolean south(){
		return south;
	}
	
	public boolean east(){
		return east;
	}
	
	public boolean west(){
		return west;
	}

}

			