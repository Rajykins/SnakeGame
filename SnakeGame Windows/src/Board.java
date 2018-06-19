import java.awt.Color;
import java.awt.Font;
import java.util.Random;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Board extends JPanel{
	boolean goBack = false;
	boolean HomeScreen = true;
	boolean flash = false;
	int Difficulty = 2;
	KeyListeners ObSnake = new KeyListeners();
	Random randomGenerator = new Random();
	UnitGrid XY [][] = new UnitGrid[50][50];
	UnitGrid Fake [][] = new UnitGrid[50][50];
	{
		for (int x=0; x<50; x++) {
			for (int y=0; y<50; y++) {
				XY [x][y] = new UnitGrid();
			}
		}		
		for (int x=0; x<50; x++) {
			for (int y=0; y<50; y++) {
				Fake [x][y] = new UnitGrid();
			}
		}		
	}
	int length = 1;
	int cherryX = randomGenerator.nextInt(50);
	int cherryY = randomGenerator.nextInt(50);
	int gameOver = 0;
	double display;
	int display2;
	int snakex = 25;
	int snakey = 25;
	int cycle = 0; //home snake direction change cycle
	int speedx = 0;
	int speedy = 0;
	int direction;
	int teleport = 0;
	int middlekey = 0;
	int timer = 250; // Fruit timer
	int timer2=0;
	int score = 0;
	int cherryNum = 0;
	int cycle2 = 0; //moving eyes cycle
	int eyes = 0; //eye direction
	int eyes2 = 0; //second eye direction
	int stablex; //eyes not moving
	int stabley; //eyes not moving
	int cycle3 = 0; //AI moving eyes
	int eyes3 = 0;
	int eyes4 = 0;
	
	public void calc() {
		ObSnake.x += ObSnake.speedx;
		ObSnake.y += ObSnake.speedy;
		
		ObSnake.solo = 0;
		
		if (ObSnake.J == true && ObSnake.A == true && ObSnake.R == true) {
			ObSnake.lowkey = true;
			ObSnake.J = false;
			ObSnake.A = false;
			ObSnake.R = false;
			
		}
		
		if (ObSnake.H == true && ObSnake.D == true && ObSnake.O == 2) {
			ObSnake.highkey = true;
			ObSnake.H = false;
			ObSnake.D = false;
			ObSnake.O = 0;
			
		}
		
		
		if (timer > 0 && (ObSnake.speedx != 0 || ObSnake.speedy != 0))
			timer --;
		
		if ((ObSnake.x > 49 || ObSnake.x < 0 || ObSnake.y > 49 || ObSnake.y < 0) && ObSnake.highkey == false) {
			gameOver = 6*14;
			if (ObSnake.x > 49)
				ObSnake.x = 49;
			if (ObSnake.x < 0)
				ObSnake.x = 0;
			if (ObSnake.y > 49)
				ObSnake.y = 49;
			if (ObSnake.y < 0)
				ObSnake.y = 0;
		}
		if ((ObSnake.x > 49 || ObSnake.x < 0 || ObSnake.y > 49 || ObSnake.y < 0) && ObSnake.highkey == true && middlekey == 0) {
			if (ObSnake.x > 49)
				ObSnake.x = 0;
			if (ObSnake.x < 0)
				ObSnake.x = 49;
			if (ObSnake.y > 49)
				ObSnake.y = 0;
			if (ObSnake.y < 0)
				ObSnake.y = 49;
			middlekey = 2;
		}
		if (middlekey > 0)
		middlekey--;
		
		if (XY [ObSnake.x][ObSnake.y].tailTimer < length - 2 && XY [ObSnake.x][ObSnake.y].tailTimer > 0 && ObSnake.lowkey == false)
			gameOver = 6*14;
		
		XY [ObSnake.x][ObSnake.y].tailTimer = length;
		for (int x=0; x<50; x++) {
			for (int y=0; y<50; y++) {
				if (XY [x][y].tailTimer > 0) {
					XY [x][y].tailTimer -= 1;
				}
			}
		}	
		if (ObSnake.x == cherryX && ObSnake.y == cherryY) {
			length += 3*Difficulty;
			cherryNum ++;
			score += 50 + timer;
			timer = 250;
			ObSnake.length = 2;
			cherryX = randomGenerator.nextInt(50);
			cherryY = randomGenerator.nextInt(50);
			flash = true;
			for (int x=0; x<50; x++) {
				for (int y=0; y<50; y++) {
					if (XY [x][y].tailTimer > 0) {
						XY [x][y].tailTimer += 3*Difficulty;
						
					}
				}
			}	
			while ((cherryX == ObSnake.x && cherryY ==  ObSnake.y) || XY[cherryX][cherryY].tailTimer > 0) {
				cherryX = randomGenerator.nextInt(50);
				cherryY = randomGenerator.nextInt(50);
			}
		}
	}
	public void menuCalc() {
		timer2++;
		
			if(ObSnake.showCC>0&&timer2%2==0)
				ObSnake.showCC--;
			
			
		if (ObSnake.speedx > 0) {
			if (Difficulty == 2)
				Difficulty = 3;
			if (Difficulty == 1)
				Difficulty = 2;
		ObSnake.speedx = 0;
		ObSnake.speedy = 0;
		}
		if (ObSnake.speedx < 0) {
			if (Difficulty == 2)
				Difficulty = 1;
			if (Difficulty == 3)
				Difficulty = 2;
		ObSnake.speedx = 0;
		ObSnake.speedy = 0;
		}
		if (ObSnake.start != 0) {
			HomeScreen = false;
			ObSnake.pause = 1;
			ObSnake.start = 0;
			gameOver = 0;
		}	
		
	}
	
	
	public void paint(Graphics g) {
		super.paint(g);
		if (HomeScreen == true) {  //---------------------------------HOMESCREEN TRUE
			g.fillRect(0, 0, 1000, 1000);
			if (snakex >= 49 && teleport == 0) {
				snakex = -1;
				speedx = 1;
				speedy = 0;
				cycle = 0;
				teleport = 3;
			}
			else {
				if (snakex == 0 && teleport == 0) {
					snakex = 50;
					speedx = -1;
					speedy = 0;
					cycle = 0;
					teleport = 3;
				}
			}
			
			if (snakey >= 49 && teleport == 0) {
				snakey = -1;
				speedy = 1;
				speedx = 0;
				cycle = 0;
				teleport = 3;
			}
			
			else {
				if (snakey == 0 && teleport == 0) {
					snakey = 50;
					speedy = -1;
					speedx = 0;
					cycle = 0;
					teleport = 3;
				}
			}
			
			snakex += speedx;
			snakey += speedy;
			
			if (teleport > 0)
				teleport --;
			
			if (cycle >= randomGenerator.nextInt(7)+7) {
				direction = randomGenerator.nextInt(4);
				if (direction == 0 && speedx == 0) { // right = 0 
					speedx = 1;
					speedy = 0;
				}
				if (direction == 1 && speedy == 0) { // up = 1 
					speedy = -1;
					speedx = 0;
				}
				if (direction == 2 && speedx == 0) { // left = 2 
					speedx = -1;
					speedy = 0;
				}
				if (direction == 3 && speedy == 0) { // down = 3
					speedy = 1;
					speedx = 0;
				}
				cycle = 0;
			}
			cycle ++;
			
			if (cycle3 > 0)
				cycle3 --;
			
			if (cycle3 == 0) {
				eyes3 = randomGenerator.nextInt(4);
				eyes4 = randomGenerator.nextInt(4);
				cycle3 = 10;
			}	
			
			Fake [snakex][snakey].tailTimer = 6;
			g.setColor(Color.GREEN);
			for (int x=0; x<50; x++) {
				for (int y=0; y<50; y++) {
					if (Fake[x][y].tailTimer > 0)
						Fake [x][y].tailTimer --;
					if (Fake[x][y].tailTimer > 0)
						g.fillRect(x*15+1, y*15+1, 14, 14);
				}
			}	
			
			
			//RIGHT --->>> EYES
			if (speedx > 0) { 			//Snake eyes3
				g.setColor(Color.WHITE);
				g.fillRect(snakex*15+1+8, snakey*15+1+2, 4, 4); //His first eye
				g.fillRect(snakex*15+1+8, snakey*15+1+2+6, 4, 4);	 //His second eye
				g.setColor(Color.BLACK);
				if (eyes3 == 0)
					g.fillRect(snakex*15+1+8, snakey*15+1+2, 2, 2);
				if (eyes3 == 1)
					g.fillRect(snakex*15+1+8+2, snakey*15+1+2, 2, 2);
				if (eyes3 == 2)
					g.fillRect(snakex*15+1+8, snakey*15+1+2+2, 2, 2);
				if (eyes3 == 3)
					g.fillRect(snakex*15+1+8+2, snakey*15+1+2+2, 2, 2);
				if (eyes4 == 0)
					g.fillRect(snakex*15+1+8, snakey*15+1+2+6, 2, 2);
				if (eyes4 == 1)
					g.fillRect(snakex*15+1+8+2, snakey*15+1+2+6, 2, 2);
				if (eyes4 == 2)
					g.fillRect(snakex*15+1+8, snakey*15+1+2+6+2, 2, 2);
				if (eyes4 == 3)
					g.fillRect(snakex*15+1+8+2, snakey*15+1+2+6+2, 2, 2);
			}
			
			//UP --->>> eyes3
			if (speedy < 0) { 			//Snake eyes3 
				g.setColor(Color.WHITE);
				g.fillRect(snakex*15+1+2, snakey*15+1+2, 4, 4); //His first eye
				g.fillRect(snakex*15+1+8, snakey*15+1+2, 4, 4);	 //His second eye
				g.setColor(Color.BLACK);
				if (eyes3 == 0)
					g.fillRect(snakex*15+1+8, snakey*15+1+2, 2, 2);
				if (eyes3 == 1)
					g.fillRect(snakex*15+1+8+2, snakey*15+1+2, 2, 2);
				if (eyes3 == 2)
					g.fillRect(snakex*15+1+8, snakey*15+1+2+2, 2, 2);
				if (eyes3 == 3)
					g.fillRect(snakex*15+1+8+2, snakey*15+1+2+2, 2, 2);
				if (eyes4 == 0)
					g.fillRect(snakex*15+1+8-6, snakey*15+1+2, 2, 2);
				if (eyes4 == 1)
					g.fillRect(snakex*15+1+8+2-6, snakey*15+1+2, 2, 2);
				if (eyes4 == 2)
					g.fillRect(snakex*15+1+8-6, snakey*15+1+2+2, 2, 2);
				if (eyes4 == 3)
					g.fillRect(snakex*15+1+8+2-6, snakey*15+1+2+2, 2, 2);
			}
			
			//DOWN --->>> eyes3
			if (speedy > 0) { 			//Snake eyes3 
				g.setColor(Color.WHITE);
				g.fillRect(snakex*15+1+2, snakey*15+1+3+5, 4, 4); //His first eye
				g.fillRect(snakex*15+1+8, snakey*15+1+3+5, 4, 4);	 //His second eye
				g.setColor(Color.BLACK);
				if (eyes3 == 0)
					g.fillRect(snakex*15+1+8, snakey*15+1+3+5, 2, 2);
				if (eyes3 == 1)
					g.fillRect(snakex*15+1+8+2, snakey*15+1+3+5, 2, 2);
				if (eyes3 == 2)
					g.fillRect(snakex*15+1+8, snakey*15+1+2+3+5, 2, 2);
				if (eyes3 == 3)
					g.fillRect(snakex*15+1+8+2, snakey*15+1+2+3+5, 2, 2);
				if (eyes4 == 0)
					g.fillRect(snakex*15+1+8-6, snakey*15+1+3+5, 2, 2);
				if (eyes4 == 1)
					g.fillRect(snakex*15+1+8+2-6, snakey*15+1+3+5, 2, 2);
				if (eyes4 == 2)
					g.fillRect(snakex*15+1+8-6, snakey*15+1+2+3+5, 2, 2);
				if (eyes4 == 3)
					g.fillRect(snakex*15+1+8+2-6, snakey*15+1+2+3+5, 2, 2);
			}
			
			//LEFT --->>> eyes3
			if (speedx < 0) { 			//Snake eyes3
				g.setColor(Color.WHITE);
				g.fillRect(snakex*15+1+8-6, snakey*15+1+2, 4, 4); //His first eye
				g.fillRect(snakex*15+1+8-6, snakey*15+1+2+6, 4, 4);	 //His second eye
				g.setColor(Color.BLACK);
				if (eyes3 == 0)
					g.fillRect(snakex*15+1+8-6, snakey*15+1+2, 2, 2);
				if (eyes3 == 1)
					g.fillRect(snakex*15+1+8+2-6, snakey*15+1+2, 2, 2);
				if (eyes3 == 2)
					g.fillRect(snakex*15+1+8-6, snakey*15+1+2+2, 2, 2);
				if (eyes3 == 3)
					g.fillRect(snakex*15+1+8+2-6, snakey*15+1+2+2, 2, 2);
				if (eyes4 == 0)
					g.fillRect(snakex*15+1+8-6, snakey*15+1+2+6, 2, 2);
				if (eyes4 == 1)
					g.fillRect(snakex*15+1+8+2-6, snakey*15+1+2+6, 2, 2);
				if (eyes4 == 2)
					g.fillRect(snakex*15+1+8-6, snakey*15+1+2+6+2, 2, 2);
				if (eyes4 == 3)
					g.fillRect(snakex*15+1+8+2-6, snakey*15+1+2+6+2, 2, 2);
			}
			
			
			
			Color myGreen = new Color(0, 229, 0);
			g.setColor(myGreen);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 86)); 
			g.drawString("Snake Game", 158, 200);
			g.setColor(Color.WHITE);
			
			g.fillRect(0, 750, 750, 62); //credits
			g.setColor(Color.BLACK);
			g.setFont(new Font("Helvatica", Font.BOLD, 20));
			g.drawString("Created By: Rajessen Sanassy", 230, 777);
			
			
			
			g.setFont(new Font("Helvatica", Font.PLAIN, 50));
			g.drawString("Difficulty", 270, 590);
			g.setFont(new Font("Helvatica", Font.PLAIN, 18));
			g.drawString("CLICK ENTER TO START", 277, 700);
			g.setFont(new Font("Helvatica", Font.PLAIN, 50));
			g.setColor(Color.WHITE);
			g.fillRect(snakex*15+1+8+2-6, snakey*15+1+2+6+2, 2, 2);
			if (Difficulty == 1) {
				g.setColor(Color.GREEN);
				g.drawString("Easy", 320, 650);
				g.setColor(Color.WHITE);
				g.setFont(new Font("Helvatica", Font.PLAIN, 30));
				g.drawString(">", 492, 642);
				Fake [snakex][snakey].tailTimer = 6;
				g.setColor(Color.GREEN);
				}
			if (Difficulty == 2) {
				g.setColor(Color.YELLOW);
				g.drawString("Medium", 280, 650);
				g.setColor(Color.WHITE);
				g.setFont(new Font("Helvatica", Font.PLAIN, 30));
				g.drawString(">", 492, 642);
				g.drawString("<", 232, 642);
				Fake [snakex][snakey].tailTimer = 12;
				g.setColor(Color.GREEN);
				}
				if (Difficulty == 3) {
					g.setColor(Color.RED);
					g.drawString("Hard", 315, 650);
					g.setColor(Color.WHITE);
					g.setFont(new Font("Helvatica", Font.PLAIN, 30));
					g.drawString("<", 232, 642);
					Fake [snakex][snakey].tailTimer = 18;
					g.setColor(Color.GREEN);
					}	
				if(ObSnake.showCC>25)
					ObSnake.showCC=25;
				if(ObSnake.showCC>=10) {
					g.fillRect(0, 750, 750, 62);
					g.setColor(Color.BLACK);
					g.setFont(new Font("Helvatica", Font.BOLD, 20));
					g.drawString("CHEAT CODES  Self Invincibility: 'R A J'  Border Teleportation 'H O O D'", 6, 777);
				}
		}
		if (HomeScreen == false) {	//------------------------------------HOMESCREEN FALSE
			if (gameOver > 0)
				gameOver--;
			if (ObSnake.pause < 0 && goBack == false) {
				ObSnake.start = 0;
				goBack = true;
			}
			if (ObSnake.pause > 0)
				goBack = false;
			if (gameOver == 1 || (ObSnake.start > 0 && ObSnake.pause == -1) || (ObSnake.start > 0 && gameOver > 0)) {
				for (int x=0; x<50; x++) {
					for (int y=0; y<50; y++) {
						XY [x][y].tailTimer = 0;
					}
				}	
				HomeScreen = true;
				goBack = false;
				flash = false;
				length = 1;
				timer = 250;
				score = 0;
				cherryNum = 0;
				cherryX = randomGenerator.nextInt(50);
				cherryY = randomGenerator.nextInt(50);
				ObSnake.x = 24;
				ObSnake.y = 24;
				ObSnake.speedx = 0;
				ObSnake.speedy = 0;
				ObSnake.start = 0;
				ObSnake.pause = 1;
				ObSnake.pass = 0;
				ObSnake.length = 1;
				ObSnake.solo = 0;
				ObSnake.lowkey = false;
				ObSnake.highkey = false;
				ObSnake.showCC = 0;
				gameOver = 0;
			}
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, 750, 750);
				g.setColor(Color.GREEN);
				g.fillRect(ObSnake.x*15+1, ObSnake.y*15+1, 14, 14);
				if (cycle2 == 0 && gameOver == 0) {
					eyes = randomGenerator.nextInt(4);
					eyes2 = randomGenerator.nextInt(4);
					cycle2 = 10;
				}
				
				if (cycle2 > 0)
					cycle2 --;
				
				g.setColor(Color.GREEN);
				for (int x=0; x<50; x++) {
					for (int y=0; y<50; y++) {
						if (XY [x][y].tailTimer > 0) {
							
							if (flash == true) {
								g.setColor(Color.white);
								flash = false;
							}	
							
							if (ObSnake.lowkey == true)
								g.setColor(Color.BLUE);
							
							if (ObSnake.highkey == true)
								g.setColor(Color.ORANGE);
							if (ObSnake.highkey == true && ObSnake.lowkey == true && XY [x][y].tailTimer%2 == 0)
							g.setColor(Color.BLUE);
							
							if (gameOver > 0)
								g.setColor(Color.RED);
						g.fillRect(x*15+1, y*15+1, 14, 14);
						}
					}
				}		
				
				if (gameOver == 0) {
				stablex = ObSnake.speedx;
				stabley = ObSnake.speedy;
				}
				
				if (gameOver > 0) {
					ObSnake.speedx = stablex;
					ObSnake.speedy = stabley;
					
				}
				
				//RIGHT --->>> EYES
				if (ObSnake.speedx > 0) { 			//Snake Eyes
					g.setColor(Color.WHITE);
					g.fillRect(ObSnake.x*15+1+8, ObSnake.y*15+1+2, 4, 4); //His first eye
					g.fillRect(ObSnake.x*15+1+8, ObSnake.y*15+1+2+6, 4, 4);	 //His second eye
					g.setColor(Color.BLACK);
					if (eyes == 0)
						g.fillRect(ObSnake.x*15+1+8, ObSnake.y*15+1+2, 2, 2);
					if (eyes == 1)
						g.fillRect(ObSnake.x*15+1+8+2, ObSnake.y*15+1+2, 2, 2);
					if (eyes == 2)
						g.fillRect(ObSnake.x*15+1+8, ObSnake.y*15+1+2+2, 2, 2);
					if (eyes == 3)
						g.fillRect(ObSnake.x*15+1+8+2, ObSnake.y*15+1+2+2, 2, 2);
					if (eyes2 == 0)
						g.fillRect(ObSnake.x*15+1+8, ObSnake.y*15+1+2+6, 2, 2);
					if (eyes2 == 1)
						g.fillRect(ObSnake.x*15+1+8+2, ObSnake.y*15+1+2+6, 2, 2);
					if (eyes2 == 2)
						g.fillRect(ObSnake.x*15+1+8, ObSnake.y*15+1+2+6+2, 2, 2);
					if (eyes2 == 3)
						g.fillRect(ObSnake.x*15+1+8+2, ObSnake.y*15+1+2+6+2, 2, 2);
				}
				
				//UP --->>> EYES
				if (ObSnake.speedy < 0) { 			//Snake Eyes 
					g.setColor(Color.WHITE);
					g.fillRect(ObSnake.x*15+1+2, ObSnake.y*15+1+2, 4, 4); //His first eye
					g.fillRect(ObSnake.x*15+1+8, ObSnake.y*15+1+2, 4, 4);	 //His second eye
					g.setColor(Color.BLACK);
					if (eyes == 0)
						g.fillRect(ObSnake.x*15+1+8, ObSnake.y*15+1+2, 2, 2);
					if (eyes == 1)
						g.fillRect(ObSnake.x*15+1+8+2, ObSnake.y*15+1+2, 2, 2);
					if (eyes == 2)
						g.fillRect(ObSnake.x*15+1+8, ObSnake.y*15+1+2+2, 2, 2);
					if (eyes == 3)
						g.fillRect(ObSnake.x*15+1+8+2, ObSnake.y*15+1+2+2, 2, 2);
					if (eyes2 == 0)
						g.fillRect(ObSnake.x*15+1+8-6, ObSnake.y*15+1+2, 2, 2);
					if (eyes2 == 1)
						g.fillRect(ObSnake.x*15+1+8+2-6, ObSnake.y*15+1+2, 2, 2);
					if (eyes2 == 2)
						g.fillRect(ObSnake.x*15+1+8-6, ObSnake.y*15+1+2+2, 2, 2);
					if (eyes2 == 3)
						g.fillRect(ObSnake.x*15+1+8+2-6, ObSnake.y*15+1+2+2, 2, 2);
				}
				
				//DOWN --->>> EYES
				if (ObSnake.speedy > 0) { 			//Snake Eyes 
					g.setColor(Color.WHITE);
					g.fillRect(ObSnake.x*15+1+2, ObSnake.y*15+1+3+5, 4, 4); //His first eye
					g.fillRect(ObSnake.x*15+1+8, ObSnake.y*15+1+3+5, 4, 4);	 //His second eye
					g.setColor(Color.BLACK);
					if (eyes == 0)
						g.fillRect(ObSnake.x*15+1+8, ObSnake.y*15+1+3+5, 2, 2);
					if (eyes == 1)
						g.fillRect(ObSnake.x*15+1+8+2, ObSnake.y*15+1+3+5, 2, 2);
					if (eyes == 2)
						g.fillRect(ObSnake.x*15+1+8, ObSnake.y*15+1+2+3+5, 2, 2);
					if (eyes == 3)
						g.fillRect(ObSnake.x*15+1+8+2, ObSnake.y*15+1+2+3+5, 2, 2);
					if (eyes2 == 0)
						g.fillRect(ObSnake.x*15+1+8-6, ObSnake.y*15+1+3+5, 2, 2);
					if (eyes2 == 1)
						g.fillRect(ObSnake.x*15+1+8+2-6, ObSnake.y*15+1+3+5, 2, 2);
					if (eyes2 == 2)
						g.fillRect(ObSnake.x*15+1+8-6, ObSnake.y*15+1+2+3+5, 2, 2);
					if (eyes2 == 3)
						g.fillRect(ObSnake.x*15+1+8+2-6, ObSnake.y*15+1+2+3+5, 2, 2);
				}
				
				//LEFT --->>> EYES
				if (ObSnake.speedx < 0) { 			//Snake Eyes
					g.setColor(Color.WHITE);
					g.fillRect(ObSnake.x*15+1+8-6, ObSnake.y*15+1+2, 4, 4); //His first eye
					g.fillRect(ObSnake.x*15+1+8-6, ObSnake.y*15+1+2+6, 4, 4);	 //His second eye
					g.setColor(Color.BLACK);
					if (eyes == 0)
						g.fillRect(ObSnake.x*15+1+8-6, ObSnake.y*15+1+2, 2, 2);
					if (eyes == 1)
						g.fillRect(ObSnake.x*15+1+8+2-6, ObSnake.y*15+1+2, 2, 2);
					if (eyes == 2)
						g.fillRect(ObSnake.x*15+1+8-6, ObSnake.y*15+1+2+2, 2, 2);
					if (eyes == 3)
						g.fillRect(ObSnake.x*15+1+8+2-6, ObSnake.y*15+1+2+2, 2, 2);
					if (eyes2 == 0)
						g.fillRect(ObSnake.x*15+1+8-6, ObSnake.y*15+1+2+6, 2, 2);
					if (eyes2 == 1)
						g.fillRect(ObSnake.x*15+1+8+2-6, ObSnake.y*15+1+2+6, 2, 2);
					if (eyes2 == 2)
						g.fillRect(ObSnake.x*15+1+8-6, ObSnake.y*15+1+2+6+2, 2, 2);
					if (eyes2 == 3)
						g.fillRect(ObSnake.x*15+1+8+2-6, ObSnake.y*15+1+2+6+2, 2, 2);
				}
				
				
				
				g.setColor(Color.BLACK);
				for (int x=0; x<51; x++) {
					g.drawLine(x*15, 0, x*15, 750);
					g.drawLine(0, x*15, 750, x*15);
				}
				
				//RIGHT --->>> TOUGUE
				if (ObSnake.speedx > 0) { //when in 8 black radius of cherry
					if (ObSnake.x >= cherryX - 10 && ObSnake.x <= cherryX + 10 && ObSnake.y >= cherryY - 10 && ObSnake.y <= cherryY + 10) {
						g.setColor(Color.RED);
						g.fillRect(ObSnake.x*15+1+13, ObSnake.y*15+1+6, 6, 2);
						g.fillRect(ObSnake.x*15+1+13+6, ObSnake.y*15+1+6-1, 2, 1);
						g.fillRect(ObSnake.x*15+1+13+6, ObSnake.y*15+1+6+2, 2, 1);
					}
				}
				
				//UP --->>> TOUGUE
				if (ObSnake.speedy < 0) { //when in 8 black radius of cherry
					if (ObSnake.x >= cherryX - 10 && ObSnake.x <= cherryX + 10 && ObSnake.y >= cherryY - 10 && ObSnake.y <= cherryY + 10) {
						g.setColor(Color.RED);
						g.fillRect(ObSnake.x*15+1+6, ObSnake.y*15+1-6, 2, 6);
						g.fillRect(ObSnake.x*15+1+6-1, ObSnake.y*15+1-8, 1, 2);
						g.fillRect(ObSnake.x*15+1+6+2, ObSnake.y*15+1-8, 1, 2);
					}
				}
				
				//DOWN --->>> TOUGUE
				if (ObSnake.speedy > 0) { //when in 8 black radius of cherry
					if (ObSnake.x >= cherryX - 10 && ObSnake.x <= cherryX + 10 && ObSnake.y >= cherryY - 10 && ObSnake.y <= cherryY + 10) {
						g.setColor(Color.RED);
						g.fillRect(ObSnake.x*15+1+6, ObSnake.y*15+1+13, 2, 6);
						g.fillRect(ObSnake.x*15+1+6-1, ObSnake.y*15+1+6+13, 1, 2);
						g.fillRect(ObSnake.x*15+1+6+2, ObSnake.y*15+1+6+13, 1, 2);
						}
				}
				
				//LEFT --->>> TOUGUE
				if (ObSnake.speedx < 0) { //when in 8 black radius of cherry
					if (ObSnake.x >= cherryX - 10 && ObSnake.x <= cherryX + 10 && ObSnake.y >= cherryY - 10 && ObSnake.y <= cherryY + 10) {
						g.setColor(Color.RED);
						g.fillRect(ObSnake.x*15+1-6, ObSnake.y*15+1+6, 6, 2);
						g.fillRect(ObSnake.x*15+1-8, ObSnake.y*15+1+6-1, 2, 1);
						g.fillRect(ObSnake.x*15+1-8, ObSnake.y*15+1+6+2, 2, 1);
					}
				}
				
				
				g.setColor(Color.RED);
				g.fillRoundRect(cherryX*15+1, cherryY*15+1, 15, 15, 15, 15);
				g.setColor(Color.BLACK);
				g.setFont(new Font("Helvatica", Font.BOLD, 20));
				g.drawString("Cherries Eaten: " + cherryNum, 25, 777);
				g.drawString("Length: " + (length-1), 237, 777);
				g.drawString("Score: " + score, 600, 777);
				g.drawString("Cherry Value: " + (50+timer), 370, 777);
				if (ObSnake.pause < 1 && gameOver == 0) {
					Color c=new Color((64/256f),(64/256f),(64/256f),.5f);    //RGB Colors plus Opacity / by 256 for percentage
					g.setColor(c);
					g.fillRect(0, 0, 750, 750);
					g.setColor(Color.WHITE);
					g.setFont(new Font("Dialog", Font.BOLD, 20)); 
					g.drawString("PAUSED", 338, 300);
					g.setFont(new Font("Dialog", Font.BOLD, 14));
					g.drawString("CLICK ENTER TO RETURN TO MAIN MENU", 230, 325);
			}
				if (gameOver > 0) {
					display = gameOver/14;
					display2 = (int)display;
					g.setColor(Color.RED);
					g.setFont(new Font("Symbol", Font.BOLD, 72)); 
					g.drawString("GAME OVER", 180, 300);
					g.setColor(Color.WHITE);
					g.setFont(new Font("Symbol", Font.PLAIN, 32)); 
					g.drawString("BACK TO MAIN MENU IN... " + display2, 180, 350);
					g.setFont(new Font("Symbol", Font.PLAIN, 18)); 
					g.drawString("OR CLICK ENTER", 322, 373);
					ObSnake.speedx = 0;
					ObSnake.speedy = 0;
				}
				if (ObSnake.speedx == 0 && ObSnake.speedy == 0 && gameOver == 0) {
					Color c=new Color((64/256f),(64/256f),(64/256f),.5f);    //RGB Colors plus Opacity / by 256 for percentage
					g.setColor(c);
					g.fillRoundRect(285, 100, 180, 180, 50, 50);
					Color w=new Color((1f),(1f),(1f),.7f);
					g.setColor(w);
					g.setFont(new Font("Dialog", Font.BOLD, 15)); 
					g.drawString("CONTROLS:", 302, 128);
					g.setFont(new Font("Dialog", Font.BOLD, 14)); 
					g.drawString("⇦⇧⇨⇩ or  W A S D", 300, 149);
					g.drawRoundRect(387, 136, 16, 16, 5, 5); 		//Squares around the letters
					g.drawRoundRect(387+18, 136, 13, 16, 5, 5);
					g.drawRoundRect(387+33, 136, 12, 16, 5, 5);
					g.drawRoundRect(387+47, 136, 13, 16, 5, 5);
					g.setFont(new Font("Dialog", Font.BOLD, 14)); 
					g.drawString("SPACE  TO PAUSE", 307, 175);
					g.drawRoundRect(304, 161, 52, 18, 5, 5);
					g.setFont(new Font("Dialog", Font.BOLD, 16)); 
					g.drawString("MOVE TO START", 307, 250);
					
				}
		}
		}
	}
