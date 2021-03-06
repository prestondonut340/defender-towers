import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Background {
	public Image img, easy, medium, hard, menu, help, start, left, right; 	
	private AffineTransform tx;
	public Background(int x, int y) {
		img = getImage("/imgs/homescreen.png"); //load the image for Tree
		easy = getImage("/imgs/easy.png");
		medium = getImage("/imgs/medium.png");
		help = getImage("/imgs/help.png");
		hard = getImage("/imgs/hard.png");
		start = null;
		menu = null;
		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y); 				//initialize the location of the image
									//use your variables
	}

	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		
		//call update to update the actualy picture location
		update();
		
		
		
		g2.drawImage(img, tx, null);
		g2.drawImage(easy, 100, 300, 100, 50, null);
		g2.drawImage(medium, 240, 300, 120, 50, null);
		g2.drawImage(hard, 400, 300, 100, 50, null);
		g2.drawImage(menu, 10, 10, 100, 50, null);
		g2.drawImage(help, 250, 370, 100, 50, null);
		g2.drawImage(start, 10, 430, 120, 50, null);
		

	}
	/* update the picture variable location */
	private void update() {

		
		
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(5.85, 5.65);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Background.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	public void switchEasy() {
		easy = getImage("/imgs/easy-hover.png");
	}
	public void returnEasy() {
		easy = getImage("/imgs/easy.png");
	}
	public void switchMed() {
		medium = getImage("/imgs/medium-hover.png");
	}
	public void returnMed() {
		medium = getImage("/imgs/medium.png");
	}
	public void switchHard() {
		hard = getImage("/imgs/hard-hover.png");
	}
	public void returnHard() {
		hard = getImage("/imgs/hard.png");
	}
	public void switchMenu() {
		menu = getImage("/imgs/menu-hover.png");
	}
	public void returnMenu() {
		menu = getImage("/imgs/menu.png");
	}
	public void switchHelp() {
		help = getImage("/imgs/help-hover.png");
	}
	public void returnHelp() {
		help = getImage("/imgs/help.png");
	}
	public void switchStart() {
		start = getImage("/imgs/start-hover.png");
	}
	public void returnStart() {
		start = getImage("/imgs/start.png");
	}
	public void enterHelp() {
		easy = null;
		medium = null;
		hard = null;
		help = null;
		start = null;
		menu = getImage("/imgs/menu.png");
	}
	public void leaveHelp() {
		img = getImage("/imgs/homescreen.png");
		easy = getImage("/imgs/easy.png");
		medium = getImage("/imgs/medium.png");
		hard = getImage("imgs/hard.png");
		help = getImage("/imgs/help.png");
		menu = null;
	}
	public void setBackground(String imageName) {
		img = getImage(imageName);
		start = getImage("/imgs/start.png");
		easy = null;
		medium = null;
		hard = null;
		help = null;
	}
	public void returnToMenu() {
		img = getImage("/imgs/homescreen.png");
		easy = getImage("/imgs/easy.png");
		medium = getImage("/imgs/medium.png");
		hard = getImage("/imgs/hard.png");
		help = getImage("/imgs/help.png");
		start = null;
		menu = null;
	}
}
