/**
	 * ���������ҵ������-1
	 * ����̹�˵�X����ķ���
	 * @param x
	 */
	public void setX(int x){
 
		if(x < 0){//̹�˵�����߽�
			x = 0;
		}
 
		//�ұ߽�
		if(x >= Constant.FRAME_WIDTH-TANK_WIDTH){
			//̹�˵�x ���� x = Constant.FRAME_WIDTH-TANK_WIDTH  ���ֵ��
			x = Constant.FRAME_WIDTH-TANK_WIDTH-1;
		}
		//����ûײǽ֮ǰ��x ����
		int oldX = this.x;
		this.x = x;
 
		//���ݷ��򣬵õ�����������꣬Ȼ��ʹ��������������꣬���μ���Ƿ������ײ�ˡ�
		//ֻҪ��һ�����������ײ����ô�����껹ԭ����������ײ���Ͳ���Ҫ����
		int collideX,collideY;
		boolean isCollide;
		switch(dir){
		case DIR_UP:
			//��һ����
			collideX = this.x;
			collideY = this.y;
			isCollide = collideWall(collideX, collideY);
			if(isCollide){
				this.x = oldX;
				return;
			}
			//�ڶ�����
			collideX = this.x+TANK_WIDTH/2;
			collideY = this.y;
			isCollide = collideWall(collideX, collideY);
			if(isCollide){
				this.x = oldX;
				return;
			}
			//��������
			collideX = this.x+TANK_WIDTH;
			collideY = this.y;
			isCollide = collideWall(collideX, collideY);
			if(isCollide){
				this.x = oldX;
				return;
			}
			break;
		case DIR_DOWN:
			//��һ����
			collideX = this.x;
			collideY = this.y+TANK_WIDTH;
			isCollide = collideWall(collideX, collideY);
			if(isCollide){
				this.x = oldX;
				return;
			}
			//�ڶ�����
			collideX = this.x+TANK_WIDTH/2;
			collideY = this.y+TANK_WIDTH;
			isCollide = collideWall(collideX, collideY);
			if(isCollide){
				this.x = oldX;
				return;
			}
			//��������
			collideX = this.x+TANK_WIDTH;
			collideY = this.y+TANK_WIDTH;
			isCollide = collideWall(collideX, collideY);
			if(isCollide){
				this.x = oldX;
				return;
			}
			break;
		case DIR_LEFT:
			//��һ����
			collideX = this.x;
			collideY = this.y;
			isCollide = collideWall(collideX, collideY);
			if(isCollide){
				//����̹�˵����껹ԭ�ˣ���������Ϊ��ײ��ı�Ե������
				//�Ƿ���֪����ײ�Ŀ���кź��кţ���
				//��ͼ���е�Ԫ
				int mapColTile = collideX/Map.TILE_WIDTH;
				int mapColTileX = (mapColTile+1)*Map.TILE_WIDTH;
 
				this.x = mapColTileX;
				return;
			}
			//�ڶ�����
			collideX = this.x;
			collideY = this.y+TANK_WIDTH/2;
			isCollide = collideWall(collideX, collideY);
			if(isCollide){
				int mapColTile = collideX/Map.TILE_WIDTH;
				int mapColTileX = (mapColTile+1)*Map.TILE_WIDTH;
 
				this.x = mapColTileX;
				return;
			}
			//��������
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
			//��һ����
			collideX = this.x+TANK_WIDTH;
			collideY = this.y;
			isCollide = collideWall(collideX, collideY);
			if(isCollide){
				int mapColTile = collideX/Map.TILE_WIDTH;
				int mapColTileX = mapColTile*Map.TILE_WIDTH;
 
				this.x = mapColTileX-Tank.TANK_WIDTH-1;
				return;
			}
			//�ڶ�����
			collideX = this.x+TANK_WIDTH;
			collideY = this.y+TANK_WIDTH/2;
			isCollide = collideWall(collideX, collideY);
			if(isCollide){
 
				int mapColTile = collideX/Map.TILE_WIDTH;
				int mapColTileX = mapColTile*Map.TILE_WIDTH;
 
				this.x = mapColTileX-Tank.TANK_WIDTH-1;
				return;
			}
			//��������
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