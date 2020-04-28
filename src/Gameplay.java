import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.*;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

	
		// TODO Auto-generated method stub
		private ImageIcon image;
		
		private int[] snakex=new int[750];
		private int[] snakey=new int[750];
		
		private boolean left=false, right=false, up=false, down=false;
		
		private ImageIcon mouth;
		private ImageIcon body;
		private ImageIcon enemyimage;
		private ImageIcon bomb;
		
		private int length=3;
		
		private Timer timer;
		private int delay =120;
		
		private int move=0;
		
		private int[] enemyx = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,
				425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
		private int[] enemyy = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,
				425,450,475,500,525,550,575,600,625};
		
		private int[] bomx = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,
				425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
		private int[] bomy = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,
				425,450,475,500,525,550,575,600,625};
		
		private Random random=new Random(); 
		
		private int xpos=random.nextInt(34);
		private int ypos=random.nextInt(23);
		
		private int xbom=0,ybom=0,xbom1=0,ybom1=0,xbom2=0,ybom2=0,xbom3=0,ybom3=0;
		
		
		private int scores;
		
		public Gameplay()
		{
			addKeyListener(this);
			setFocusable(true);
			setFocusTraversalKeysEnabled(false);
			timer = new Timer(delay,this);
			timer.start();
			
		}
		
		public void paint(Graphics g)    //draw everything in the panel
		{
			if(move==0)
			{
				snakex[2]=50;
				snakex[1]=75;
				snakex[0]=100;
				
				snakey[2]=100;
				snakey[1]=100;
				snakey[0]=100;
			}
			g.setColor(Color.white);
			g.drawRect(24, 10, 851, 55);
			
			image=new ImageIcon("snaketitl.jpg");
			image.paintIcon(this , g, 25, 11);
			
			g.setColor(Color.WHITE);
			g.drawRect(25, 74, 851, 577);
			
			g.setColor(Color.black);
			g.fillRect(25, 75, 850, 575);
			
			g.setColor(Color.WHITE);
			g.setFont(new Font("arial", Font.PLAIN, 14));
			g.drawString("Scores: "+scores, 780, 30);
			
			g.setColor(Color.WHITE);
			g.setFont(new Font("arial", Font.PLAIN, 14));
			g.drawString("Length: "+length, 780, 50);
			
			mouth=new ImageIcon("upmouth.png");
			mouth.paintIcon(this, g, snakex[0], snakey[0]);
			
			for(int i=0;i<length;i++)
			{
				if(i==0)
				{
					mouth=new ImageIcon("upmouth.png");
					mouth.paintIcon(this, g, snakex[i], snakey[i]);
				}
				else
				{
					body=new ImageIcon("snakeimage.png");
					body.paintIcon(this, g, snakex[i], snakey[i]);
				}
			}
			
			enemyimage=new ImageIcon("enemy.png");
			bomb=new ImageIcon("bomb.jpg");
			enemyimage.paintIcon(this, g, enemyx[xpos], enemyy[ypos]);
			if(scores>10 && scores<=20) 
			bomb.paintIcon(this, g, bomx[xbom], bomy[ybom]);
			else if(scores>20)
			{
				bomb.paintIcon(this, g, bomx[xbom], bomy[ybom]);
				bomb.paintIcon(this, g, bomx[xbom1], bomy[ybom1]);
				bomb.paintIcon(this, g, bomx[xbom2], bomy[ybom2]);
				bomb.paintIcon(this, g, bomx[xbom3], bomy[ybom3]);
				
			}
			
			if(enemyx[xpos] == snakex[0] && enemyy[ypos] == snakey[0])
			{
				scores++;
				length++;
				xpos=random.nextInt(34);
				ypos=random.nextInt(23);
				/*while((scores>30 && scores<=50) && (xpos!=0 && xpos!=33) && (ypos!=0 && ypos!=22))
				{
					xpos=random.nextInt(34);
					ypos=random.nextInt(23);
				}*/
					
				if(scores>10 && scores<=20)
				{ 
					xbom=random.nextInt(34);
					ybom=random.nextInt(23);
				}
				else if(scores>20)
				{
					xbom=random.nextInt(34);
					ybom=random.nextInt(23);
					xbom1=random.nextInt(34);
					ybom1=random.nextInt(23);
					xbom2=random.nextInt(34);
					ybom2=random.nextInt(23);
					xbom3=random.nextInt(34);
					ybom3=random.nextInt(23);
				}
			}
			if(xbom!=0) {
			if((bomx[xbom]==snakex[0] && bomy[ybom]==snakey[0]) || (bomx[xbom1]==snakex[0] && bomy[ybom1]==snakey[0]) || (bomx[xbom2]==snakex[0] && bomy[ybom2]==snakey[0]) || (bomx[xbom3]==snakex[0] && bomy[ybom3]==snakey[0]))
			{
				right=false;
				left=false;
				up=false;
				down=false;
				
				g.setColor(Color.GREEN);
				g.setFont(new Font("arial", Font.BOLD, 50));
				g.drawString("GAME OVER",300,300);
				
				g.setFont(new Font("arial", Font.BOLD, 30));
				g.drawString("Press spacebar to Restart",280,340);
			}
			}
			for(int i=1;i<length;i++)
			{
				if(snakex[i]==snakex[0] && snakey[i]==snakey[0])
				{
					right=false;
					left=false;
					up=false;
					down=false;
					
					g.setColor(Color.GREEN);
					g.setFont(new Font("arial", Font.BOLD, 50));
					g.drawString("GAME OVER",300,300);
					
					g.setFont(new Font("arial", Font.BOLD, 30));
					g.drawString("Press spacebar to Restart",280,340);
					
				}
			}
			
			
			
			if(scores>10 && scores<=20)
			{
				g.setColor(Color.red);
				g.setFont(new Font("arial", Font.PLAIN, 20));
				g.drawString("LEVEL 2 score(11-20)", 100, 47);
				
			}
			else if(scores>=0 && scores<=10)
			{
				g.setColor(Color.red);
				g.setFont(new Font("arial", Font.PLAIN, 20));
				g.drawString("LEVEL 1 score(0-10)", 100, 47);
			}
			else if(scores>20 && scores<=30)
			{
				g.setColor(Color.red);
				g.setFont(new Font("arial", Font.PLAIN, 20));
				g.drawString("LEVEL 3 score(21-30)", 100, 47);
			}
			else if(scores>30 && scores<=40)
			{
				g.setColor(Color.red);
				g.setFont(new Font("arial", Font.PLAIN, 20));
				g.drawString("LEVEL 4 score(31-40)", 100, 47);
				
				if(snakex[0]==850 || snakex[0]==25 || snakey[0]==75 || snakey[0]==625)
				{
					right=false;
					left=false;
					up=false;
					down=false;
					
					g.setColor(Color.GREEN);
					g.setFont(new Font("arial", Font.BOLD, 50));
					g.drawString("GAME OVER",300,300);
					
					g.setFont(new Font("arial", Font.BOLD, 30));
					g.drawString("Press spacebar to Restart",280,340);
				}
					
			}
			else if(scores>40 && scores<=50)
			{
				g.setColor(Color.red);
				g.setFont(new Font("arial", Font.PLAIN, 20));
				g.drawString("LEVEL 5 score(41-50)", 100, 47);
			}
			if(scores==50)
			{
				right=false;
				left=false;
				up=false;
				down=false;
				
				g.setColor(Color.blue);
				g.setFont(new Font("arial", Font.BOLD, 50));
				g.drawString("You WIN",310,300);
				
				g.setFont(new Font("arial", Font.BOLD, 30));
				g.drawString("Press spacebar to Restart",260,340);
			}
			g.dispose();
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(right)
			{
				int i=0;
				for(i=length-1;i>=0;i--)
				{
					snakey[i+1]=snakey[i];
				}
				for(i=length;i>=0;i--)
				{
					if(i==0)
					{
						snakex[i]=snakex[i] + 25;
					}
					else
					{
						snakex[i]=snakex[i-1];
					}
					if(snakex[i]>850)
					{
						snakex[i]=25;
					}
				}
				repaint();
			}
			if(left)
			{
				int i=0;
				for(i=length-1;i>=0;i--)
				{
					snakey[i+1]=snakey[i];
				}
				for(i=length;i>=0;i--)
				{
					if(i==0)
					{
						snakex[i]=snakex[i] - 25;
					}
					else
					{
						snakex[i]=snakex[i-1];
					}
					if(snakex[i]<25)
					{
						snakex[i]=850;
					}
				}
				repaint();
			}
			if(up)
			{
				int i=0;
				for(i=length-1;i>=0;i--)
				{
					snakex[i+1]=snakex[i];
				}
				for(i=length;i>=0;i--)
				{
					if(i==0)
					{
						snakey[i]=snakey[i] - 25;
					}
					else
					{
						snakey[i]=snakey[i-1];
					}
					if(snakey[i]<75)
					{
						snakey[i]=625;
					}
				}
				repaint();
			}
			if(down)
			{
				int i=0;
				for(i=length-1;i>=0;i--)
				{
					snakex[i+1]=snakex[i];
				}
				for(i=length;i>=0;i--)
				{
					if(i==0)
					{
						snakey[i]=snakey[i] + 25;
					}
					else
					{
						snakey[i]=snakey[i-1];
					}
					if(snakey[i]>625)
					{
						snakey[i]=75;
					}
				}
				repaint();
			}
			
		}
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getKeyCode()== KeyEvent.VK_SPACE)
			{
				move=0;
				length=3;
				scores=0;
				repaint();
			}
			if(e.getKeyCode()== KeyEvent.VK_RIGHT)
			{
				move++;
				right=true;
				if(!left)
				{
					right=true;
				}
				else
				{
					left=true;
					right=false;
				}
				up=false;
				down=false;
			}
			if(e.getKeyCode()== KeyEvent.VK_LEFT)
			{
				move++;
				left=true;
				if(!right)
				{
					left=true;
				}
				else
				{
					right=true;
					left=false;
				}
				up=false;
				down=false;
			}
			if(e.getKeyCode()== KeyEvent.VK_UP)
			{
				move++;
				up=true;
				if(!down)
				{
					up=true;
				}
				else
				{
					down=true;
					up=false;
				}
				left=false;
				right=false;
			}
			if(e.getKeyCode()== KeyEvent.VK_DOWN)
			{
				move++;
				down=true;
				if(!up)
				{
					down=true;
				}
				else
				{
					up=true;
					down=false;
				}
				left=false;
				right=false;
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	

}
