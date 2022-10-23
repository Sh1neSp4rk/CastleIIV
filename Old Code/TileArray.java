public static void main (string[]args){ 

/*
*      N      N                  N       N        N                 N        N                           N              
*   W+E    +     W+E  W+E  W+         +E  W+E     +E   W+        +E   W+      +         +       +E     W+    +
*      S      S                            S         S       S                            S        S                 S         
*   t00      t01     t02     t03     t04     t05     t06     t07     t08     t09     t10     t11     t12     t13     t14   15
*
*                         Tile Name        N     S     E     W
*/		
		Tile [] tiles= new Tile [15];
			tiles[0]=  new Tile (true ,true ,true ,true,new ImageIcon(t00.jpg) );
			tiles[1]=  new Tile (true ,true ,false,false,new ImageIcon(t01.jpg));
			tiles[2]=  new Tile (false,false,true ,true,new ImageIcon(t02.jpg) );
			tiles[3]=  new Tile (true ,false,true ,true,new ImageIcon(t03.jpg) );
			tiles[4]=  new Tile (true ,true ,false,true,new ImageIcon(t04.jpg) );
			tiles[5]=  new Tile (true ,true ,true ,false,new ImageIcon(t05.jpg) );
			tiles[6]=  new Tile (false,true ,true ,true,new ImageIcon(t06.jpg) );
			tiles[7]=  new Tile (true ,false,true ,false,new ImageIcon(t07.jpg) );
			tiles[8]=  new Tile (true ,false,false,true,new ImageIcon(t08.jpg) );
			tiles[9]=  new Tile (false,true ,true ,false,new ImageIcon(t09.jpg) );
			tiles[10]=new Tile (false,true ,false,true,new ImageIcon(t10.jpg) );
			tiles[11]=new Tile (true ,false,false,false,new ImageIcon(t11.jpg) );
			tiles[12]=new Tile (false,true ,false,false,new ImageIcon(t12.jpg) );
			tiles[13]=new Tile (false,false,true ,false,new ImageIcon(t13.jpg) );
			tiles[14]=new Tile (false,false,false,true,new ImageIcon(t14.jpg) );
			tiles[15]=new Tile (false,false,false,false,new ImageIcon(t15.jpg) );
		}
	}
}
