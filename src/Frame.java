import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.Random;
public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	//Timer related variables
	int waveTimer = 20;
	long ellapseTime = 0;
	Font timeFont = new Font("Courier", Font.BOLD, 70);
	
	int score = 0;
	Font scoreCount = new Font("Courier", Font.BOLD, 70);
	
	
	//Font myFont = new Font("Courtier")
	Music backgroundMusic = new Music("Pac-Man-Theme-Song.wav",false);
	Music shootMusic = new Music("shoot2.wav", false);
	Music loseMusic = new Music("lose2.wav", false);
	Music winMusic = new Music("win2.wav", false);
	//frame width/height
	//int width = 900;
	//int height = 600;6
	int width = 1000;
	int height = 934;
	
	
	//Add your object declaration and instantiations here
	Background b = new Background("pacManMaprevamp.png");
	
	Ghost clydeLeft = new Ghost("clydeLeft.png");
	Ghost clydeRight = new Ghost("clydeRight.png");
	Ghost clydeUp = new Ghost("clydeUp.png");
	Ghost clydeDown = new Ghost("clydeDown.png");
	Ghost clyde = new Ghost("clydeRight.png");
	
	Pacman open = new Pacman("pacmanOpen.png");
	Pacman closed = new Pacman("pacmanClosed.png");
	
	orb orb = new orb("orb.png");
	orb orb2 = new orb("orb.png");
	orb orb3 = new orb("orb.png");
	orb orb4 = new orb("orb.png");
	orb orb5 = new orb("orb.png");
	orb orb6 = new orb("orb.png");
	orb orb7 = new orb("orb.png");
	orb orb8 = new orb("orb.png");
	orb orb9 = new orb("orb.png");
	
	boolean played = false;
	
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		//Call the paint method of your objects here
		b.paint(g);
		
		orb.paint(g);
		orb2.paint(g);
		orb3.paint(g);
		orb4.paint(g);
		orb5.paint(g);
		orb6.paint(g);
		orb7.paint(g);
		orb8.paint(g);
		orb9.paint(g);
		
		clydeRight.paint(g);
		
		open.paint(g);
		
		
		//Wave timer
		g.setFont(timeFont);
		g.setColor(Color.WHITE);
		g.drawString(""+waveTimer, 450, 100);
		
		//score count
		g.setFont(scoreCount);
		g.setColor(Color.WHITE);
		g.drawString("Score: "+score, 550, 850);
		
		//ghost doesn't work until passed level 0!
		
		// update time
		ellapseTime +=20;
		if(ellapseTime % 1000 == 0) {
			if(waveTimer > 0) {
			waveTimer--;
			}
			else if(waveTimer == 0) {
				waveTimer = 0;
				clydeRight.x = 0;
				clydeRight.y = 1000;
			}
		}
		if(waveTimer <= 0 && score >= 2000) {
			g.drawString("YOU WIN", 350, 450);
			if(!played) {
				winMusic.play();
				played = true;
				clydeRight.x = -1000;
				clydeRight.vx = 0;
			}
				if(open.x > 1000) {
					open.x = 0;
				}
		}
		if(waveTimer <= 0 && score < 2000) {
			g.drawString("YOU LOSE", 350, 450);
			if(!played) {
				loseMusic.play();
				played = true;
				clydeRight.x = -1000;
				clydeRight.vx = 0;
			}
			if(open.x > 1000) {
				open.x = 0;
			}
		}
		
	}
	
	public static void main(String[] arg) {
		Frame f = new Frame();
	}
	
	public Frame() {
		JFrame f = new JFrame("Duck Hunt");
		f.setSize(new Dimension(width, height));
		f.setBackground(Color.blue);
		f.add(this);
		f.setResizable(false);
 		f.addMouseListener(this);
		f.addKeyListener(this);
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		//backgroundMusic.play();
		
		orb2.x = 450;
 		orb3.x = 850;
 		orb4.y = 400;
 		orb5.x = 450;
 		orb5.y = 400;
 		orb6.x = 850;
 		orb6.y = 400;
 		orb7.y = 800;
 		orb8.x = 450;
 		orb8.y = 800;
 		orb9.x = 850;
 		orb9.y = 800;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent m) {
		shootMusic.play();
		System.out.println("press event block  of code");
		System.out.println(m.getX() + ":" + m.getY());
		
		System.out.println(clydeRight.x + ":" + clydeRight.y);;
		System.out.println(clydeRight.width + ":" + clydeRight.height);
		
		//rectangle collision
		Rectangle rectA = new Rectangle(m.getX() - 75, m.getY() - 75, 50, 50); // x, y, width, height
		
		//rectangle representation of my ghost object
		Rectangle rectB = new Rectangle(clydeRight.x - 20, clydeRight.y, clydeRight.width + 50, clydeRight.height + 60);
		
		//check for collision using the intersect method
		if(rectA.intersects(rectB)) {
			
			System.out.println("oof");
			score+=200;
			//let's "fake" respawning by teleporting the object back to some starting value
			//reset: will vary per gameplay/student.
			Random r = new Random();
			int i = r.nextInt(0, 9);
			if (i == 0){
				clydeRight.y = 100;
				clydeRight.x = 50;
				open.x = 0;
				open.y = 400;
			} else if (i == 1) {
				clydeRight.y = 100;
				clydeRight.x = 450;
				open.x = 0;
				open.y = 400;
			} else if (i == 2) {
				clydeRight.y = 100;
				clydeRight.x = 850;
				open.x = 0;
				open.y = 400;
			} else if (i == 3) {
				clydeRight.y = 400;
				clydeRight.x = 850;
				open.x = 0;
				open.y = 400;
			} else if (i == 4) {
				clydeRight.y = 400;
				clydeRight.x = 450;
				open.x = 0;
				open.y = 400;
			} else if (i == 5) {
				clydeRight.y = 400;
				clydeRight.x = 850;
				open.x = 0;
				open.y = 400;
			} else if (i == 6) {
				clydeRight.y = 800;
				clydeRight.x = 850;
				open.x = 0;
				open.y = 400;
			} else if (i == 7) {
				clydeRight.y = 800;
				clydeRight.x = 450;
				open.x = 0;
				open.y = 400;
			} else if (i == 8) {
				clydeRight.y = 800;
				clydeRight.x = 850;
				open.x = 0;
				open.y = 400;
			}
			
			if(score == 200) {
				clydeRight.vx = 15;
				clydeRight.vy = 25;
			}
			else if(score == 400) {
				clydeRight.vx = 20;
				clydeRight.vy = 30;
			}
			else if(score == 600) {
				clydeRight.vx = 25;
				clydeRight.vy = 35;
			}
			else if(score == 800) {
				clydeRight.vx = 30;
				clydeRight.vy = 40;
			}
			else if(score == 1000) {
				clydeRight.vx = 35;
				clydeRight.vy = 45;
			}
			else if(score == 1200) {
				clydeRight.vx = 40;
				clydeRight.vy = 50;
			}
			else if(score == 1400) {
				clydeRight.vx = 45;
				clydeRight.vy = 55;
			}
			else if(score == 1600) {
				clydeRight.vx = 50;
				clydeRight.vy = 60;
			}
			else if(score == 1800) {
				clydeRight.vx = 55;
				clydeRight.vy = 65;
			}else {
				clydeRight.vx = 100;
				clydeRight.vy = 100;
			}
			


		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyCode());
		if(arg0.getKeyCode() == 83) {
			clydeDown.paint(getGraphics());
		}
		if(arg0.getKeyCode() == 87) {
			clydeUp.paint(getGraphics());
		}
		if(arg0.getKeyCode() == 65) {
			clydeLeft.paint(getGraphics());
		}
		if(arg0.getKeyCode() == 68) {
			clydeRight.paint(getGraphics());
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
