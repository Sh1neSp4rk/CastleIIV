public static void createWorld(){
	
	private int[][]worldArray;
	for (int across = 0; across<=hSize;across++){
		for (int down = 0; down<=vSize;down++){
			tileNum=(int)(Math.random()*(16-0))+0;
			map[across][down]=tiles[tileNum];
			while (down==0){
				if (tile[across-1][down].east && tile[across][down].west);
				break;							
			}
			while (down>0){
				if ((tile[across-1][down].east && tile[across][down].west) || (tile[across][down-1].south && tile[across][down].north));
				break;
				else if (!tile [across-1][down].east && !tile[across][down-1]);
				tile[across][down]=Tiles[15];
		}
	}
}
