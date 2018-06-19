import javax.swing.JFrame;


import java.awt.Dimension;
public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		JFrame Gameplay = new JFrame();
		Dimension Mama = new Dimension();
		Board Snakey = new Board();
		
		
		Mama.setSize(750, 772+40);
		Gameplay.setMaximumSize(Mama);
		Gameplay.setMinimumSize(Mama);
		Gameplay.setResizable(false);
		Gameplay.setLocation(450, 100);
		Gameplay.setSize(Mama);
		Gameplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Gameplay.setVisible(true);
		Gameplay.add(Snakey);
		Gameplay.addKeyListener(Snakey.ObSnake);
		
		
	Gameplay.pack();
		
		
		while(true) {
			if (Snakey.HomeScreen == false && Snakey.ObSnake.pause > 0 && Snakey.gameOver == 0) {
				Snakey.calc();
				System.out.println("CALCULAING");
			}
			else {
				if (Snakey.HomeScreen == true)
				Snakey.menuCalc();
			}
			Snakey.repaint();
			if (Snakey.gameOver > 0)
				Thread.sleep(75);
			else {
				if (Snakey.Difficulty == 1)
					Thread.sleep(100);
				if (Snakey.Difficulty == 2)
					Thread.sleep(75);
				if (Snakey.Difficulty == 3)
					Thread.sleep(50);
			}
			
		}
		
	}

}
