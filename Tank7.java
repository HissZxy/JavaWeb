/**
	 * 增加了向右的坐标的-1
	 * 设置坦克的X坐标的方法
	 * @param x
	 */
	public void setX(int x){
 
		if(x < 0){//坦克到了左边界
			x = 0;
		}
 
		//右边界
		if(x >= Constant.FRAME_WIDTH-TANK_WIDTH){
			//坦克的x 坐标 x = Constant.FRAME_WIDTH-TANK_WIDTH  最大值。
			x = Constant.FRAME_WIDTH-TANK_WIDTH-1;
		}
		//备份没撞墙之前的x 坐标
		int oldX = this.x;
		this.x = x;
 
		//根据方向，得到三个点的坐标，然后使用这三个点的坐标，依次检测是否产生碰撞了。
		//只要有一个点产生了碰撞，那么就坐标还原，后续的碰撞检测就不需要做了
		int collideX,collideY;
		boolean isCollide;
		switch(dir){
		case DIR_UP:
			//第一个点
			collideX = this.x;
			collideY = this.y;
			isCollide = collideWall(collideX, collideY);
			if(isCollide){
				this.x = oldX;
				return;
			}
			//第二个点
			collideX = this.x+TANK_WIDTH/2;
			collideY = this.y;
			isCollide = collideWall(collideX, collideY);
			if(isCollide){
				this.x = oldX;
				return;
			}
			//第三个点
			collideX = this.x+TANK_WIDTH;
			collideY = this.y;
			isCollide = collideWall(collideX, collideY);
			if(isCollide){
				this.x = oldX;
				return;
			}
			break;
		case DIR_DOWN:
			//第一个点
			collideX = this.x;
			collideY = this.y+TANK_WIDTH;
			isCollide = collideWall(collideX, collideY);
			if(isCollide){
				this.x = oldX;
				return;
			}
			//第二个点
			collideX = this.x+TANK_WIDTH/2;
			collideY = this.y+TANK_WIDTH;
			isCollide = collideWall(collideX, collideY);
			if(isCollide){
				this.x = oldX;
				return;
			}
			//第三个点
			collideX = this.x+TANK_WIDTH;
			collideY = this.y+TANK_WIDTH;
			isCollide = collideWall(collideX, collideY);
			if(isCollide){
				this.x = oldX;
				return;
			}
			break;
		case DIR_LEFT:
			//第一个点
			collideX = this.x;
			collideY = this.y;
			isCollide = collideWall(collideX, collideY);
			if(isCollide){
				//不让坦克的坐标还原了，而是设置为碰撞块的边缘的坐标
				//是否能知道碰撞的块的行号和列号？？
				//地图的列单元
				int mapColTile = collideX/Map.TILE_WIDTH;
				int mapColTileX = (mapColTile+1)*Map.TILE_WIDTH;
 
				this.x = mapColTileX;
				return;
			}
			//第二个点
			collideX = this.x;
			collideY = this.y+TANK_WIDTH/2;
			isCollide = collideWall(collideX, collideY);
			if(isCollide){
				int mapColTile = collideX/Map.TILE_WIDTH;
				int mapColTileX = (mapColTile+1)*Map.TILE_WIDTH;
 
				this.x = mapColTileX;
				return;
			}
			//第三个点
			collideX = this.x;
			collideY = this.y+TANK_WIDTH;
			isCollide = collideWall(collideX, collideY);
			if(isCollide){
				int mapColTile = collideX/Map.TILE_WIDTH;
				int mapColTileX = (mapColTile+1)*Map.TILE_WIDTH;
 
				this.x = mapColTileX;
				return;
			}
			break;
		case DIR_RIGHT:
			//第一个点
			collideX = this.x+TANK_WIDTH;
			collideY = this.y;
			isCollide = collideWall(collideX, collideY);
			if(isCollide){
				int mapColTile = collideX/Map.TILE_WIDTH;
				int mapColTileX = mapColTile*Map.TILE_WIDTH;
 
				this.x = mapColTileX-Tank.TANK_WIDTH-1;
				return;
			}
			//第二个点
			collideX = this.x+TANK_WIDTH;
			collideY = this.y+TANK_WIDTH/2;
			isCollide = collideWall(collideX, collideY);
			if(isCollide){
 
				int mapColTile = collideX/Map.TILE_WIDTH;
				int mapColTileX = mapColTile*Map.TILE_WIDTH;
 
				this.x = mapColTileX-Tank.TANK_WIDTH-1;
				return;
			}
			//第三个点
			collideX = this.x+TANK_WIDTH;
			collideY = this.y+TANK_WIDTH;
			isCollide = collideWall(collideX, collideY);
			if(isCollide){
				int mapColTile = collideX/Map.TILE_WIDTH;
				int mapColTileX = mapColTile*Map.TILE_WIDTH;
 
				this.x = mapColTileX-Tank.TANK_WIDTH-1;
				return;
			}
			break;
		}
	}