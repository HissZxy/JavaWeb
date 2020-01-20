package com.hbust.entity;
 
import java.awt.Color;
import java.awt.Graphics;
 
/**
 * A class used to describe a tank
 * analysis:
 * tank attributes:
 * tank functions:
 * 
 * @author gc*/
 
public class Tank {
//	define the four directions of the tank
		public static final int DIR_UP 		= 0;
		public static final int DIR_DOWN 	= 1;
		public static final int DIR_LEFT 	= 2;
		public static final int DIR_RIGHT 	= 3;
		
//		Several states of a tank
		public static final int STATE_STAND = 0;
		public static final int STATE_MOVE 	= 1;
		public static final int STATE_DIE 	= 2;
		public static final int STATE_FIRE 	= 3;
		private int x,y; // coordinate
		private int width = 30;//Width
		private int dir;//direction
		private int speed;//speed
		private int state;//States of tank
		private Color color = Color.ORANGE;//Color of tank
		
		//The Construction method of Tank
		public Tank(int x, int y, int dir) {
			super();
			this.x   = x;
			this.y   = y;
			this.dir = dir;
		}
		/**
		 * the drawing method of tank
		 * @param g*/
		public void draw(Graphics g) {
			//Sets the color of the brush to the specified color
			g.setColor(color);
			g.fillRect(x, y, width, width);
			
			//Draw a black border
			g.setColor(Color.BLACK);
			g.drawRect(x, y, width, width);
			
			//Draw a gun barrel(the half of tank's width)
			int w = width>>1;
			switch (dir) {
			case DIR_UP:
				g.drawLine(x+w, y-w, x+w, y+w);
				break;
			case DIR_DOWN:
				g.drawLine(x+w, y+w, x+w, y+3*w);
				break;
			case DIR_LEFT:
				g.drawLine(x-w, y+w, x+w, y+w);
				break;
			case DIR_RIGHT:
				g.drawLine(x+3*w, y+w, x+w, y+w);
				break;
			}
			
		}
		/**the method to set direction of tank
		 * @param dir
		 */
		public void setDir(int dir){
			this.dir = dir;
		}
		
		public void setX(int x){
			this.x = x;
		}
		public void setY(int y){
			this.y = y;
		}
		public int getX(){
			return x;
		}
		public int getY(){
			return y;
		}
		
 
		
}