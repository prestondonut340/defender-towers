import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.MouseInfo;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JFrame; 
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener{
	JFrame f = new JFrame("Tower Defense");
	Background back = new Background(0, 0);
	ArrayList<Soap> soap = new ArrayList<Soap>();
	ArrayList<Bleach> bleach = new ArrayList<Bleach>();
	ArrayList<Flamethrower> flame = new ArrayList<Flamethrower>();
	ArrayList<Sanitizer> sanitizer = new ArrayList<Sanitizer>();
	Point p = MouseInfo.getPointerInfo().getLocation();
	Virus v1 = new Virus(0, 435, 5);
	Picture game = new Picture("bigBackground.png");
	Pixel[][] pixel = game.getPixels2D();
	Pixel[][] cursor = new Pixel[40][40];
	public int difficulty; // 0 = easy, 1 = medium, 2 = hard
	public int cursorX, cursorY;
	public int money = 100;
	public boolean isOnHomescreen = true;
	public boolean isPointerActive = false;
	public boolean placementError = false;
	public boolean fundError = false;
	public long start = System.currentTimeMillis();
	public void paint(Graphics g) {
		pointerSet();
		setCursor();
		checkHover();
		super.paint(g);
		back.paint(g);
		v1.paint(g);
		if (!isOnHomescreen) {
			v1.setGameStarted();
			v1.spawn1();
			for (Soap s : soap) {
				s.paint(g);
				s.placeHover(cursorX-40, cursorY-40);
			}
			for (Bleach b : bleach) {
				b.paint(g);
				b.placeHover(cursorX-40, cursorY-40);
			}
			for (Flamethrower fl : flame) {
				fl.paint(g);
				fl.placeHover(cursorX-40, cursorY-40);
			}
			for (Sanitizer st : sanitizer) {
				st.paint(g);
				st.placeHover(cursorX-40, cursorY-40);
			}
			g.setFont(new Font("Arial", Font.PLAIN, 20));
			g.drawString("$"+money, 10, 95);
			g.drawString("$" + soap.get(0).getCost(), 160, 95);
			g.drawString("$" + sanitizer.get(0).getCost(), 255, 95);
			g.drawString("$" + bleach.get(0).getCost(), 360, 95);
			g.drawString("$" + flame.get(0).getCost(), 460, 95);
		}
		
		if (placementError) {
			long time = System.currentTimeMillis() - start;
			if (time <= 500) {
				g.setColor(new Color(235, 236, 239));
				g.fillRect(250, 300, 100, 35);
				g.setColor(Color.black);
				g.drawRect(250, 300, 100, 35);
				g.setFont(new Font("Arial", Font.PLAIN, 20));
				g.drawString("Invalid", 273, 325);
			}
			else {
				placementError = false;
			}
		}
		if (fundError) {
			long time2 = System.currentTimeMillis() - start;
			if (time2 <= 500) {
				g.setColor(new Color(235, 236, 239));
				g.fillRect(200, 300, 200, 35);
				g.setColor(Color.black);
				g.drawRect(200, 300, 200, 35);
				g.setFont(new Font("Arial", Font.PLAIN, 20));
				g.drawString("Not Enough Money!", 215, 325);
			}
			else {
				fundError = false;
			}
		}
		p = MouseInfo.getPointerInfo().getLocation();
	}
	public static void main(String[] arg) {
		Frame f = new Frame();
		
;	}
	public Frame() {
		Timer t = new Timer(16, this);
		f.setSize(new Dimension(600, 600));
		f.setBackground(Color.blue);
		f.add(this);
		f.setResizable(true);
		f.setLayout(new GridLayout(1,2));
		f.addMouseListener(this);
		f.addKeyListener(this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//		if(e.getKeyCode() == 37) {
//			if(!isOnHomescreen) {
//				v1.spawn1();
//			}
//		}
		
		
	} 
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		start = System.currentTimeMillis();
		if (e.getButton() == 3) {
			System.out.println(cursorX + "," + cursorY);
		}
		if(e.getButton() == 1) {
			if (isOnHomescreen) {
				back.menu = null;
				if (cursorX >= 105 && cursorX <= 210 && cursorY >= 340 && cursorY <= 385) {
					difficulty = 0;
					back.setBackground("/imgs/Background.png");
					back.returnMenu();
					isOnHomescreen = false;
					soap.add(new Soap(150, 10, 2.5, false, difficulty));
					bleach.add(new Bleach(350, 10, 2.7, false, difficulty));
					flame.add(new Flamethrower(450, 10, 3, false, difficulty));
					sanitizer.add(new Sanitizer(250, 10, 2.8, false, difficulty));
				}
				if (cursorX >= 250 && cursorX <= 365 && cursorY >= 340 && cursorY <= 385) {
					difficulty = 1;
					back.setBackground("/imgs/Background.png");
					back.returnMenu();
					isOnHomescreen = false;
					soap.add(new Soap(150, 10, 2.5, false, difficulty));
					bleach.add(new Bleach(350, 10, 2.7, false, difficulty));
					flame.add(new Flamethrower(450, 10, 3, false, difficulty));
					sanitizer.add(new Sanitizer(250, 10, 2.8, false, difficulty));
				}
				if (cursorX >= 410 && cursorX <= 505 && cursorY >= 340 && cursorY <= 385) {
					difficulty = 2;
					back.setBackground("imgs/Background.png");
					back.returnMenu();
					isOnHomescreen = false;
					soap.add(new Soap(150, 10, 2.5, false, difficulty));
					bleach.add(new Bleach(350, 10, 2.7, false, difficulty));
					flame.add(new Flamethrower(450, 10, 3, false, difficulty));
					sanitizer.add(new Sanitizer(250, 10, 2.8, false, difficulty));
				}
			}
			else { 
				
				if (cursorX >= 20 && cursorX <= 115 && cursorY >= 50 && cursorY <= 95) {
					back.returnToMenu();
					soap.clear();
					bleach.clear();
					sanitizer.clear();
					flame.clear();
					money = 100;
					isOnHomescreen = true;
					v1.homescreenVirus();
				}
				
				if (!isPointerActive) {
					if (cursorX >= 155 && cursorX <= 215 && cursorY >= 45 && cursorY <= 100) {
						buyDefender(1);
					}
					if (cursorX >= 270 && cursorX <= 295 && cursorY >= 45 && cursorY <= 105) {
						buyDefender(2);
					}
					if (cursorX >= 365 && cursorX <= 410 && cursorY >= 45 && cursorY <= 105) {
						buyDefender(3);
					}
					if (cursorX >= 450 && cursorX <= 525 && cursorY >= 65 && cursorY <= 95) {
						buyDefender(4);
					}
				}
				else {
					if (cursorY > 130 && !isInNoZone()) {
						for (Soap s : soap) {
							s.setHover(false);
							isPointerActive = false;
						}
						for (Bleach b : bleach) {
							b.setHover(false);
							isPointerActive = false;
						}
						for (Flamethrower fl : flame) {
							fl.setHover(false);
							isPointerActive = false;
						}
						for (Sanitizer st : sanitizer) {
							st.setHover(false);
							isPointerActive = false;
						}
					}
					else {
						placementError = true;
					}
				}
			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
		
	}
	public void checkHover() {
		if (isOnHomescreen) {
			if (cursorX >= 105 && cursorX <= 210 && cursorY >= 340 && cursorY <= 385) {
				back.switchEasy();
			}
			else {
				back.returnEasy();
			}
			if (cursorX >= 250 && cursorX <= 365 && cursorY >= 340 && cursorY <= 385) {
				back.switchMed();
			}
			else {
				back.returnMed();
			}
			if (cursorX >= 410 && cursorX <= 505 && cursorY >= 340 && cursorY <= 385) {
				back.switchHard();
			}
			else {
				back.returnHard();
			}
		}
		else {
			if (cursorX >= 20 && cursorX <= 115 && cursorY >= 50 && cursorY <= 95) {
				back.switchMenu();
			}
			else {
				back.returnMenu();
			}
		}
		
	}
	public boolean isInNoZone() {
		try {
			for (Pixel[] r : cursor) {
				for (Pixel p : r) {
					if (p.getBlue() == 241 && p.getRed() == 236 && p.getGreen() == 239) {
						return true;
					}
				}
			}
		}
		catch(NullPointerException e) {}
		return false;
	}
	public void setCursor() {
		if (cursorX >= 40 && cursorX <= 570 && cursorY >= 40 && cursorY <= 570) {
			for (int r = cursorY-40, i = 0; r < cursorY; r++, i++) {
				for (int c = cursorX-40, j = 0; c < cursorX; c++, j++) {
					cursor[i][j] = pixel[r][c];
				}
			}
		}
	}
	public void pointerSet() {
		int winX = f.getX(), winY = f.getY();
		p.x -= winX;
		p.y -= winY;
		if (p.x <= 600 && p.x >= 0 && p.y <= 600 && p.y >= 0) {
			cursorX = p.x;
			cursorY = p.y;
		}
		else {
			cursorX = 0;
			cursorY = 0;
		}
	}
	public void buyDefender(int num) {
		if (num == 1 && money >= soap.get(0).getCost()) {
			soap.add(new Soap(cursorX-40, cursorY-40, 2.5, true, difficulty));
			isPointerActive = true;
			money -= soap.get(0).getCost();
		}
		else if (num == 2 && money >= sanitizer.get(0).getCost()) {
			sanitizer.add(new Sanitizer(cursorX-40, cursorY-40, 2.5, true, difficulty));
			isPointerActive = true;
			money -= sanitizer.get(0).getCost();
		}
		else if (num == 3 && money >= bleach.get(0).getCost()) {
			bleach.add(new Bleach(cursorX-40, cursorY-40, 2.5, true, difficulty));
			isPointerActive = true;
			money -= bleach.get(0).getCost();
		}
		else if (num == 4 && money >= flame.get(0).getCost()) {
			flame.add(new Flamethrower(cursorX-40, cursorY-40, 2.5, true, difficulty));
			isPointerActive = true;
			money -= flame.get(0).getCost();
		}
		else {
			fundError = true;
		}
	}
}
