import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Pacman{
	private Image img; 	
	private Image open, closed;
	private AffineTransform tx;
	int width, height;
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	double scaleWidth = 0.08;		 //change to scale image
	double scaleHeight = 0.08; //change to scale image

	public Pacman(String filename) {
		img = getImage("/imgs/"+filename); //load the image for Tree

		open = getImage("/imgs/"+"pacmanOpen.png");
		closed = getImage("/imgs/"+"pacmanClosed.png");
		
		//alter these
		width = 0; 
		height = 0;
		x = -100;
		y = -100;
		vx = 10;
		vy = 0;
		
		
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		init(x, y); 				//initialize the location of the image
									//use your variables
	}
	
	public void changePicture(String newFileName) {
		img = getImage(newFileName);
		init(0, 0);
	}
	
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		
		
		x+=vx;
		y+=vy;
		
		
			
		
		
		
		init(x,y);
		
		if(x > 0 && x < 200) {
			g2.drawImage(open, tx, null);
		} else if(x > 200 && x < 400) {
			g2.drawImage(closed, tx, null);
		} else if(x > 400 && x < 600) {
			g2.drawImage(open, tx, null);
		} else if(x > 600 && x < 800) {
			g2.drawImage(closed, tx, null);
		}
	
		
		//g2.drawImage(img, tx, null);

	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Pacman.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
