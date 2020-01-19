package com.hbust.frame;
import com.hbust.constant.Constant;
import com.hbust.entity.Tank;
 
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
 
/**
 * Game main window class, in which all drawing functions are completed
 * @author gc
 * 
 * */
//Inheriting the Frame class provided by Java
public class GameFrame extends Frame{
	private Tank myTank;
	//Constructor to initialize the window
	public GameFrame() {
		initFrame();
		initWindowListener();
		initMyTank();
		}
	//the method to initialize MyTank
	private void initMyTank() {
		myTank = new Tank(100, 100, Tank.DIR_UP);
	}
	
	private void initFrame(){
		//Title of window
		setTitle(Constant.GAME_TITLE);
		//Set window size does not change
//		setResizable(false);
		
		//Set the initial size of the window
		setSize(Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);
		//Set the starting position of the window(mediate)
		setLocation(Constant.SYS_SCREEN_W-Constant.FRAME_WIDTH>>1,
				Constant.SYS_SCREEN_H-Constant.FRAME_HEIGHT>>1);
		
		//Because the window is still hidden after it's been created.
		//Display the current game window
		setVisible(true);		
	}
 
	/**
	 * Add listening events to the game window
	 * 
	 * */
	private void initWindowListener(){
		//Adding an event method to a window
		addWindowListener(new WindowAdapter() {
			//Processing window events
			public  void windowClosing(WindowEvent e) {
				//When the close button is clicked, the method is executed
				//Click close virtual machine
				System.exit(0);
			}
		});
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
//			called when a key is released
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
//			 called when a key is pressed
			public void keyPressed(KeyEvent e) {
				// e saves information about pressed keys
				// get the virtual key value of the pressed key
				int code = e.getKeyCode();
				switch (code) {
				case KeyEvent.VK_A://left
					myTank.setDir(Tank.DIR_LEFT);
					myTank.setX(myTank.getX()-5);
					repaint();
					break;
 
				case KeyEvent.VK_D://right
					myTank.setDir(Tank.DIR_RIGHT);
					myTank.setX(myTank.getX()+5);
					repaint();
					break;
					
				case KeyEvent.VK_W://up
					myTank.setDir(Tank.DIR_UP);
					myTank.setY(myTank.getY()-5);
					repaint();
					break;
					
				case KeyEvent.VK_S://down
					myTank.setDir(Tank.DIR_DOWN);
					myTank.setY(myTank.getY()+5);
					repaint();
					break;
				}
				
				
			}
		});
	}
	private int x = 500, y = 500;
	/**
	 *  All plotted work is to be completed within this method .
	 * g is a brush object . The instantiation of g is system - assisted .
	 * g is the brush that the system provides to us and can be used directly
	 * paint cannot call .repaint () directly:
	 * */
	public void paint(Graphics g) {
		myTank.draw(g);
	}
 
}