public void eat(ArrayList<Fish> list) {
		 //Music musiceat = new Music();
		System.out.println(">>>>>"+list.size());
		for (int i = 0; i < list.size(); i++) {
			// 矩形相交就判断size
			Fish fish1 = this;
			Fish fish2 = list.get(i);
			if (fish1 != fish2
					&& ((fish1.x >= fish2.x && fish1.x <= fish2.x + fish2.size && fish1.y >= fish2.y
							&& fish1.y <= fish2.y + fish2.size)
					|| (fish1.x >= fish2.x && fish1.x <= fish2.x + fish2.size && fish1.y + fish1.size >= fish2.y
							&& fish1.y + fish1.size <= fish2.y + fish2.size)
					|| (fish1.x + fish1.size >= fish2.x && fish1.x + fish1.size <= fish2.x + fish2.size
							&& fish1.y >= fish2.y && fish1.y <= fish2.y + fish2.size)
					|| (fish1.x + fish1.size >= fish2.x && fish1.x + fish1.size <= fish2.x + fish2.size
							&& fish1.y + fish1.size >= fish2.y && fish1.y + fish1.size <= fish2.y + fish2.size))) {

				if (fish1.size > fish2.size) {
					fish1.size += fish2.size / 4;
					fish2.size = 0;
					//musiceat.playEatMusic();
				} else if (fish1.size < fish2.size) {
					fish2.size += fish1.size / 4;
					fish1.size = 0;
					 //musiceat.playEatMusic();
				}
			}
		}
	}