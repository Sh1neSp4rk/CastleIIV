/*Random Maze Generator
Mike L. Shane F. Crystal C. Eleanor K.
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RandomMazeGenerator{
	private int WIDTH=640;			//Get Size from computer
	private int HEIGHT=480;			//Get Size from computer
	
	private JFrame appFrame;
	private JPanel appPanel,mazePanel,introPanel;
	private JLabel appLabel,mazeLabel,picLabel,introLabel;
	private JTextField menuText;
	boolean n,s,e,w,haveStart,haveEnd;	
	int hSize=64,vSize=64;
	int xSolver,ySolver;
	final int floors = 5;
	int currentFloor=1;
	ImageIcon hiddenTile=new ImageIcon ("hiddenTile.jpg");
	private Canvas c;
	private Tile[][]tile= new Tile [hSize][vSize];
	Tile [] tiles= new Tile [16];
	JLabel [][] mazeBoard=new JLabel[hSize][vSize];
	public JButton start;		
	User user;
	
	
	
	//GUI
	public RandomMazeGenerator(){
						
		c=new Canvas();
		c.setSize(new Dimension(WIDTH,HEIGHT));
		
		appFrame= new JFrame ("Random Maze Generator");
		appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		appLabel= new JLabel ("Enter Your Name: ");
		menuText= new JTextField (20);
		//menuText.addActionListener(new GameStart());
		
		mazeLabel= new JLabel();
		mazeLabel.setPreferredSize (new Dimension(7,7));
		
		picLabel= new JLabel();
		picLabel.setPreferredSize (new Dimension(640,480));
		picLabel.addKeyListener(new Movement());	
		
		introLabel= new JLabel (new ImageIcon ("CastleTitle.jpg"));
		introLabel.addKeyListener(new Intro());
		
		
		start=new JButton();
		menuText.addKeyListener(new Movement());
		menuText.addActionListener(new NameListener());
		appPanel= new JPanel();
		appPanel.setPreferredSize (new Dimension(WIDTH,HEIGHT));
		appPanel.setBackground(Color.gray);
		appPanel.add(appLabel);
		appPanel.add(menuText);
		appPanel.add(picLabel);
	
		//appPanel.add(c);
		appPanel.add(mazeLabel);
		
		
		user= new User(false,false,true,false,0,0);
		
		tiles[0]= new Tile (true ,true ,true ,true,new ImageIcon("t00.jpg"),false,false,false );
		tiles[1]= new Tile (true ,true ,false,false,new ImageIcon("t01.jpg"),false,false,false);
		tiles[2]= new Tile (false,false,true ,true,new ImageIcon("t02.jpg"),false,false,false );
		tiles[3]= new Tile (true ,false,true ,true,new ImageIcon("t03.jpg"),false,false,false );
		tiles[4]= new Tile (true ,true ,false,true,new ImageIcon("t04.jpg"),false,false,false );
		tiles[5]= new Tile (true ,true ,true ,false,new ImageIcon("t05.jpg"),false,false,false );
		tiles[6]= new Tile (false,true ,true ,true,new ImageIcon("t06.jpg"),false,false,false );
		tiles[7]= new Tile (true ,false,true ,false,new ImageIcon("t07.jpg"),false,false,false);
		tiles[8]= new Tile (true ,false,false,true,new ImageIcon("t08.jpg"),false,false,false );
		tiles[9]= new Tile (false,true ,true ,false,new ImageIcon("t09.jpg"),false,false,false );
		tiles[10]=new Tile (false,true ,false,true,new ImageIcon("t10.jpg"),false,false,false );
		tiles[11]=new Tile (true ,false,false,false,new ImageIcon("t11.jpg"),false,false,false );
		tiles[12]=new Tile (false,true ,false,false,new ImageIcon("t12.jpg"),false,false,false );
		tiles[13]=new Tile (false,false,true ,false,new ImageIcon("t13.jpg"),false,false,false );
		tiles[14]=new Tile (false,false,false,true,new ImageIcon("t14.jpg"),false,false,false );
		tiles[15]=new Tile (false,false,false,false,new ImageIcon("t15.jpg"),false,false,false );
		
		//Set Up Array of Labels
		
		introPanel= new JPanel();
		introPanel.setPreferredSize(new Dimension(640,480));
		introPanel.add(introLabel);
		
		mazePanel= new JPanel (new GridLayout(hSize,vSize));
		mazePanel.setPreferredSize(new Dimension(hSize*7,vSize*7));
				
		for (int panelAcross = 0; panelAcross<hSize;panelAcross++){
			for (int panelDown = 0; panelDown<vSize;panelDown++){
				
				//mazeBoard[panelDown][panelAcross]=new JLabel(tile[panelAcross][panelDown].image);
				mazeBoard[panelAcross][panelDown]=new JLabel();
				mazePanel.add(mazeBoard[panelAcross][panelDown]);

			}
		}
		//appPanel.add(introPanel);
		appPanel.add(mazePanel);
		
		//appFrame.getContentPane().add(appPanel);	
		appFrame.getContentPane().add(introPanel);
		appFrame.requestFocus(); //Ask windows to move focus to the Frame
		appFrame.addKeyListener(new Intro());//add the keyListener to the Frame
				
	}

		


	//Displaying the App Frame
	
	public void display(){
		
		appFrame.pack();
		appFrame.show();
		
	}
		
	public void start(){
		//Graphics g=c.getGraphics();
		//g.drawString("Starting Game...",100,100);
		createWorld();
		fixMaze();
		selectStart();
		selectEnd();
		drawMap();
		
	}

	
	public void createWorld(){
		for (int down = 0; down<vSize;down++){
			for (int across = 0; across<hSize;across++){
				int tileNum=(int)(Math.random()*(16-0))+0;
				tile[across][down]=tiles[tileNum];
				while (down==0 && across>=0){
					if (across==0) {
						tile[0][0]=tiles[9];
						System.out.println("user tile"+"n"+ user.dirNorth+"s"+ user.dirSouth+"e"+ user.dirEast+"w"+ user.dirWest);
						break;
					}
					else if (tile[across-1][down].east() == tile[across][down].west())	break;
					else if (!tile [across-1][down].east() && !tile[across][down].west()) break;	
					
					tileNum=(int)(Math.random()*(16-0))+0;
					tile[across][down]=tiles[tileNum];
					

				}
				while (down>0 ){
					tileNum=(int)(Math.random()*(16-0))+0;
					tile[across][down]=tiles[tileNum];
					
					if(across==0){
						if((tile[across][down-1].south() && tile[across][down].north())) break;
						else if (!tile[across][down-1].south() && !tile[across][down].north() && !tile[across][down].west()
						&& (tile[across][down].east() || tile[across][down].south()) ){						
							break;
						}
						
					}else{ //if across > 0 i.e. not on left side of maze
						if ((tile[across-1][down].east() && tile[across][down].west()) ||
							 (tile[across][down-1].south() && tile[across][down].north())) break;
						else if (!tile [across-1][down].east() && !tile[across][down-1].south()
						&& (tile[across][down].south() || tile[across][down].east())) {
							break;
						}
					}
						
				}//End while
				
				
			}//End For
		}//Ending For2


	
	}//End Maze Setup


	public void fixMaze(){
		for (int tileX = 0; tileX < hSize; tileX++){
			for (int tileY = 0; tileY < vSize; tileY++){
				if (tileX==0){
					w=false;
				}
				if (tileY==0){
					n=false;
				}
				if (tileX==hSize-1){
					e=false;
				}
				if (tileY==vSize-1){
					s=false;
				}
				if (tileY>=1){
					if (tile[tileX][tileY-1].south()){	
						n=true;
					}else{
						n=false;
					}
				}
				if (tileY<vSize-1){
					if (tile[tileX][tileY+1].north()){
						s=true;
					}else{
						s=false;
					}
				}
				if (tileX<hSize-1){ 
					if (tile[tileX+1][tileY].west()){
						e=true;
					}else{
						e=false;
					}
				}
				if (tileX>=1){
					if (tile[tileX-1][tileY].east()){
						w=true;
					}else{
						w=false;
					}
				}
					
				tile[tileX][tileY]=tile[tileX][tileY].getFixTile(n,s,e,w,tiles);
			
			}
		}
	}

	public void selectEnd(){
		while (haveEnd==false){
			int tileAtX=(int)(Math.random()*(hSize-(hSize/2)))+(hSize/2);
			int tileAtY=(int)(Math.random()*(vSize-(vSize/2)))+(vSize/2);
			if ((tile[tileAtX][tileAtY].north() && !tile[tileAtX][tileAtY].south() &&
			!tile[tileAtX][tileAtY].east() && !tile[tileAtX][tileAtY].west())||(
			!tile[tileAtX][tileAtY].north() && tile[tileAtX][tileAtY].south() &&
			!tile[tileAtX][tileAtY].east() && !tile[tileAtX][tileAtY].west())||(
			!tile[tileAtX][tileAtY].north() && !tile[tileAtX][tileAtY].south() &&
			tile[tileAtX][tileAtY].east() && !tile[tileAtX][tileAtY].west())||(
			!tile[tileAtX][tileAtY].north() && !tile[tileAtX][tileAtY].south() &&
			!tile[tileAtX][tileAtY].east() && tile[tileAtX][tileAtY].west())){
				tile[tileAtX][tileAtY].end=true;
				haveEnd=true;
				System.out.println ("End is "+tileAtX+"  "+tileAtY+"");
			}
		}
	}
	public void selectStart(){
		while (haveStart==false){
			int tileAtX=(int)(Math.random()*(hSize/2-0))+0;
			int tileAtY=(int)(Math.random()*(vSize/2-0))+0;
			if ((tile[tileAtX][tileAtY].north() && !tile[tileAtX][tileAtY].south() &&
			!tile[tileAtX][tileAtY].east() && !tile[tileAtX][tileAtY].west())||(
			!tile[tileAtX][tileAtY].north() && tile[tileAtX][tileAtY].south() &&
			!tile[tileAtX][tileAtY].east() && !tile[tileAtX][tileAtY].west())||(
			!tile[tileAtX][tileAtY].north() && !tile[tileAtX][tileAtY].south() &&
			tile[tileAtX][tileAtY].east() && !tile[tileAtX][tileAtY].west())||(
			!tile[tileAtX][tileAtY].north() && !tile[tileAtX][tileAtY].south() &&
			!tile[tileAtX][tileAtY].east() && tile[tileAtX][tileAtY].west())){
				if (tile[tileAtX][tileAtY].north()){
					user.dirNorth=true;
					user.dirSouth=false;
					user.dirEast=false;
					user.dirWest=false;
				}else if (tile[tileAtX][tileAtY].south()){
					user.dirSouth=true;
					user.dirNorth=false;
					user.dirEast=false;
					user.dirWest=false;
				}else if (tile[tileAtX][tileAtY].east()){
					user.dirEast=true;
					user.dirSouth=false;
					user.dirNorth=false;
					user.dirWest=false;
				}else if (tile[tileAtX][tileAtY].west()){
					user.dirWest=true;
					user.dirSouth=false;
					user.dirEast=false;
					user.dirNorth=false;
				}
				tile[tileAtX][tileAtY].start=true;
				haveStart=true;
				System.out.println ("Start is "+tileAtX+"  "+tileAtY+" tile =" +tile[tileAtX][tileAtY]);
				user.x=tileAtX;
				user.y=tileAtY;
				tile[tileAtX][tileAtY].visited=true;
				
			}
		}
	}

	//maze output
	public void drawMap(){
		for (int panelAcross = 0; panelAcross<hSize;panelAcross++){
			for (int panelDown = 0; panelDown<hSize;panelDown++){		
				 if (user.x == panelAcross && user.y == panelDown){
					
					for (int tyle = 0; tyle < 15; tyle++){
						if (tile[panelAcross][panelDown]==tiles[tyle]){
							mazeBoard[panelAcross][panelDown].setIcon(new ImageIcon ("t"+tyle+"user.jpg"));
						}
					}
				}else if (tile[panelAcross][panelDown].end && !tile[panelAcross][panelDown].visited){
					mazeBoard[panelDown][panelAcross].setIcon(new ImageIcon ("hiddenEnd.jpg"));
					//System.out.println("end point: " + panelDown + ", "+panelAcross);
				}else if (!tile[panelAcross][panelDown].visited){
					if (panelAcross==0) mazeBoard[panelAcross][panelDown].setBackground(Color.blue);
					else	mazeBoard[panelAcross][panelDown].setIcon(hiddenTile);
				}else if (tile[panelAcross][panelDown].visited){
					mazeBoard[panelAcross][panelDown].setIcon(tile[panelAcross][panelDown].image);
				}	
			}
		}
	
	}
				
//				mazeBoard[panelDown][panelAcross].setIcon(tile[panelAcross][panelDown].image);
				
				
	public void drawMaze(){
		
			if (tile[user.x][user.y]==tiles[10] && user.dirEast ||tile[user.x][user.y]==tiles[7]&& user.dirWest||
			tile[user.x][user.y]==tiles[8]&& user.dirSouth || tile[user.x][user.y]==tiles[9]&& user.dirNorth){
				picLabel.setIcon(new ImageIcon("RightElbow.jpg"));
				
			}else if (tile[user.x][user.y]==tiles[10] && user.dirNorth ||tile[user.x][user.y]==tiles[7]&& user.dirSouth||
			tile[user.x][user.y]==tiles[8]&& user.dirEast || tile[user.x][user.y]==tiles[9]&& user.dirWest){
				picLabel.setIcon(new ImageIcon("LeftElbow.jpg"));
				
			}else if (tile[user.x][user.y]==tiles[3] && user.dirEast || tile[user.y][user.x]==tiles[4] && user.dirNorth ||
			tile[user.x][user.y]==tiles[5] && user.dirSouth || tile[user.x][user.y]==tiles[6] && user.dirWest ||
			tile[user.x][user.y]==tiles[7] && user.dirEast || tile[user.x][user.y]==tiles[8] && user.dirNorth ||
			tile[user.x][user.y]==tiles[9] && user.dirSouth || tile[user.x][user.y]==tiles[10] && user.dirWest){
				picLabel.setIcon(new ImageIcon("LeftTurn.jpg"));
				
			}else if (tile[user.x][user.y]==tiles[3] && user.dirWest || tile[user.y][user.x]==tiles[4] && user.dirSouth ||
			tile[user.x][user.y]==tiles[5] && user.dirNorth || tile[user.x][user.y]==tiles[6] && user.dirEast ||
			tile[user.x][user.y]==tiles[7] && user.dirNorth || tile[user.x][user.y]==tiles[8] && user.dirWest ||
			tile[user.x][user.y]==tiles[9] && user.dirEast || tile[user.x][user.y]==tiles[10] && user.dirSouth){
				picLabel.setIcon(new ImageIcon("RightTurn.jpg"));
				
			}else if (currentFloor==1 && (tile[user.x][user.y]==tiles[11] && user.dirSouth && tile[user.x][user.y].start==true || 
			tile[user.x][user.y]==tiles[12] && user.dirNorth && tile[user.x][user.y].start==true || 
			tile[user.x][user.y]==tiles[13] && user.dirWest && tile[user.x][user.y].start==true ||
			tile[user.x][user.y]==tiles[14] && user.dirEast && tile[user.x][user.y].start==true)){
				picLabel.setIcon(new ImageIcon("Start.jpg"));
				
				
			}else if (currentFloor>1 && (tile[user.x][user.y]==tiles[11] && user.dirSouth && tile[user.x][user.y].start==true || 
			tile[user.x][user.y]==tiles[12] && user.dirNorth && tile[user.x][user.y].start==true || 
			tile[user.x][user.y]==tiles[13] && user.dirWest && tile[user.x][user.y].start==true ||
			tile[user.x][user.y]==tiles[14] && user.dirEast && tile[user.x][user.y].start==true)){
				picLabel.setIcon(new ImageIcon("StairsDown.jpg"));
			
			}else if (currentFloor<floors && (tile[user.x][user.y]==tiles[11] && user.dirSouth && tile[user.x][user.y].end==true || 
			tile[user.x][user.y]==tiles[12] && user.dirNorth && tile[user.x][user.y].end==true || 
			tile[user.x][user.y]==tiles[13] && user.dirWest && tile[user.x][user.y].end==true ||
			tile[user.x][user.y]==tiles[14] && user.dirEast && tile[user.x][user.y].end==true)){
					picLabel.setIcon(new ImageIcon("StairsUp.jpg"));
				
			}else if (currentFloor==floors && (tile[user.x][user.y]==tiles[11] && user.dirSouth && tile[user.x][user.y].end==true || 
			tile[user.x][user.y]==tiles[12] && user.dirNorth && tile[user.x][user.y].end==true || 
			tile[user.x][user.y]==tiles[13] && user.dirWest && tile[user.x][user.y].end==true ||
			tile[user.x][user.y]==tiles[14] && user.dirEast && tile[user.x][user.y].end==true)){
					picLabel.setIcon(new ImageIcon("Finish.jpg"));
				
			}else if (tile[user.x][user.y]==tiles[11] && user.dirSouth && !tile[user.x][user.y].end==true && !tile[user.x][user.y].start==true || 
			tile[user.x][user.y]==tiles[12] && user.dirNorth && !tile[user.x][user.y].end==true && !tile[user.x][user.y].start==true || 
			tile[user.x][user.y]==tiles[13] && user.dirWest && !tile[user.x][user.y].end==true && !tile[user.x][user.y].start==true ||
			tile[user.x][user.y]==tiles[14] && user.dirEast && !tile[user.x][user.y].end==true && !tile[user.x][user.y].start==true){
				picLabel.setIcon(new ImageIcon("DeadEnd.jpg"));
				
			}else if (tile[user.x][user.y]==tiles[0] || (tile[user.x][user.y]==tiles[3] && user.dirNorth) ||
			(tile[user.x][user.y]==tiles[4] && user.dirWest) || (tile[user.x][user.y]==tiles[5] && user.dirEast) ||
			(tile[user.x][user.y]==tiles[6] && user.dirSouth)){
					picLabel.setIcon(new ImageIcon("X-Turn.jpg"));
				
			
			}else if (tile[user.x][user.y]==tiles[1] && (user.dirNorth||user.dirSouth) || tile[user.x][user.y]==tiles[2] && (user.dirEast||user.dirWest)||
			tile[user.x][user.y]==tiles[11] && user.dirNorth || tile[user.x][user.y]==tiles[12] && user.dirSouth ||
			tile[user.x][user.y]==tiles[13] && user.dirEast || tile[user.x][user.y]==tiles[14] && user.dirWest){
				picLabel.setIcon(new ImageIcon("StraightHall.jpg"));
				
			}else if (tile[user.x][user.y]==tiles[1] && (user.dirWest || user.dirEast) || tile[user.x][user.y]==tiles[2] && (user.dirNorth || user.dirSouth) ||
			 tile[user.x][user.y]==tiles[3] && user.dirSouth || tile[user.x][user.y]==tiles[4] && user.dirEast ||
			 tile[user.x][user.y]==tiles[5] && user.dirWest || tile[user.x][user.y]==tiles[6] && user.dirNorth ||
			(tile[user.x][user.y]==tiles[11] || tile[user.x][user.y]==tiles[12]) && (user.dirEast || user.dirWest) ||
			(tile[user.x][user.y]==tiles[13] || tile[user.x][user.y]==tiles[14]) && (user.dirNorth || user.dirSouth)){
				picLabel.setIcon(new ImageIcon("T-Turn.jpg"));


				}		
			}
		
		

	
	
		
	
	//User methods
	
	
	//Action Listeners//
	
	
	//KeyListener//
	public class Movement implements KeyListener{//Listener for Keys
	
		public void keyPressed(KeyEvent e){
			int input=e.getKeyCode();
			
			if (input==38){//KeyEvent.VK_UP){
				user.moveForward(tile);
				mazeBoard[user.x][user.y].setIcon(tile[user.x][user.y].image);				
				drawMaze();
				System.out.print("Current Tile="+tile[user.x][user.y]+" direction="+user.whatDirection()+" up");
			}
			else if (input==40){
				user.turnAround();
				drawMaze();
				System.out.print("Current Tile="+tile[user.x][user.y]+" direction="+user.whatDirection()+" down");
			}
			else if (input==37){
				user.turnLeft();
				drawMaze();
				System.out.print("Current Tile="+tile[user.x][user.y]+" direction="+user.whatDirection()+" left");
			}
			else if (input==39){
				user.turnRight();
				drawMaze();
				System.out.print("Current Tile="+tile[user.x][user.y]+" direction="+user.whatDirection()+" right");
			}
			else if (input==32){				
				drawMap();	
			}
			System.out.println(" ===== here("+user.x+","+user.y+")");
			
			
		}
		
		public void keyReleased (KeyEvent r){	}
		
		public void keyTyped (KeyEvent t){}
	}
																	class NameListener implements ActionListener{
																		public void actionPerformed(ActionEvent e){
																			//store users name
																			menuText.removeActionListener(this);
																		
																			
																		}
																	}
	
	public class Intro implements KeyListener{//Listener for Keys
	
		public void keyPressed(KeyEvent e){
			
			int input=e.getKeyCode();
			if (input==10){	
				System.out.print("enter");				
				appFrame.getContentPane().remove(introPanel);
				appFrame.getContentPane().add(appPanel);
				appFrame.removeKeyListener(this);
				appFrame.addKeyListener(new Movement());
				//appFrame.repaint();
				appFrame.show();
				start();
				drawMaze();

			}			
		}
		
		public void keyReleased (KeyEvent r){	}
		
		public void keyTyped (KeyEvent t){}
	}	
}
