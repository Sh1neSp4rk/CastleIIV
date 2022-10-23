import java.awt.*;
import javax.swing.*;
public class User{
		boolean dirNorth, dirSouth, dirEast, dirWest;
		int x, y;
		
	public User(boolean dirNorth, boolean dirSouth, boolean dirEast, boolean dirWest, int x, int y){
		this.dirNorth=dirNorth;
		this.dirSouth=dirSouth;
		this.dirEast=dirEast;
		this.dirWest=dirWest;
		this.x=x;
		this.y=y;
	}
		public void moveForward(Tile[][]tile){
		if (dirNorth==true && tile[x][y].north()){
			tile[x][y].visited=true;
			y--;		
		}else if (dirSouth==true && tile[x][y].south()){
			tile[x][y].visited=true;
			y++;
		}else if(dirEast==true && tile[x][y].east()){
			tile[x][y].visited=true;
			x++;
		}else if(dirWest==true && tile[x][y].west()){
			tile[x][y].visited=true;
			x--;
		}
	}
	public void turnAround(){
		if (dirNorth==true){
			dirNorth=false;
			dirSouth=true;
		}else if (dirSouth==true){
			dirSouth=false;
			dirNorth=true;
		}else if(dirEast==true){
			dirEast=false;
			dirWest=true;
		}else if(dirWest==true){
			dirWest=false;
			dirEast=true;
		}
	}
	public void turnLeft(){
		if (dirNorth==true){
			dirNorth=false;
			dirWest=true;
		}else if (dirSouth==true){
			dirSouth=false;
			dirEast=true;
		}else if(dirEast==true){
			dirEast=false;
			dirNorth=true;
		}else if(dirWest==true){
			dirWest=false;
			dirSouth=true;
		}
	}
	public void turnRight(){
		if (dirNorth==true){
			dirNorth=false;
			dirEast=true;
		}else if (dirSouth==true){
			dirSouth=false;
			dirWest=true;
		}else if(dirEast==true){
			dirEast=false;
			dirSouth=true;
		}else if(dirWest==true){
			dirWest=false;
			dirNorth=true;
		}
	}
	
	public String whatDirection(){
		if (dirNorth==true){
			return "north";
		}else if (dirSouth==true){
			return "south";
		}else if(dirEast==true){
			return "east";
		}else if(dirWest==true){
			return "west";
		}
		return "huh?";
	}

}


