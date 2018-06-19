import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListeners implements KeyListener {
	
	
	boolean J = false;
	boolean R = false;
	boolean A = false;
	boolean H = false;
	boolean D = false;
	int O = 0;
	int showCC=0;
	
	
	boolean lowkey = false;
	boolean highkey = false;
	
	
	int x = 24; 
	int y = 24;
	
	int speedx = 0;
	int speedy = 0;
	int start = 0;
	int pause = 1;
	int pass = 0;
	int length = 1;
	int solo = 0; //allow one click at a time #bug fix
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		
		if (e.getKeyCode() == KeyEvent.VK_W) {
			A = false;
			R = false;
			J = false;
			D = false;
			O = 0;
			H = false;
			if (pause > 0 && length == 1) {
				System.out.println("1U");
				speedx = 0;
				speedy = -1;
			}
			if (pause > 0 && speedy == 0 && solo == 0) {
				System.out.println("U");
				speedx = 0;
				speedy = -1;
				solo = 1;
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_S) {
			A = false;
			R = false;
			J = false;
			D = false;
			O = 0;
			H = false;
			if (pause > 0 && length == 1) {
				System.out.println("1D");
				speedx = 0;
				speedy = 1;
			}
			if (pause > 0 && speedy == 0 && solo == 0) {
				System.out.println("D");
				speedx = 0;
				speedy = 1;
				solo = 1;
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_D) {
			A = false;
			R = false;
			J = false;
			D = true;
			if (pause > 0 && length == 1) {
				System.out.println("1R");
				speedy = 0;
				speedx = 1;
			}
			if (pause > 0 && speedx == 0 && solo == 0) {
				System.out.println("R");
				speedy = 0;
				speedx = 1;
				solo = 1;
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_A) {
			D = false;
			O = 0;
			H = false;
			if (A == true) {
				R = false;
				A = false;
			}
			A = false;
			J = false;
			A = true;
			if (pause > 0 && length == 1) {
				System.out.println("1L");
				speedy = 0;
				speedx = -1;
			}
			if (pause > 0 && speedx == 0 && solo == 0) {
				System.out.println("L");
				speedy = 0;
				speedx = -1;
				solo = 1;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			A = false;
			R = false;
			J = false;
			D = false;
			O = 0;
			H = false;
			if (pause > 0 && length == 1) {
				System.out.println("1U");
				speedx = 0;
				speedy = -1;
			}
			if (pause > 0 && speedy == 0 && solo == 0) {
				System.out.println("U");
				speedx = 0;
				speedy = -1;
				solo = 1;
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			A = false;
			R = false;
			J = false;
			D = false;
			O = 0;
			H = false;
			if (pause > 0 && length == 1) {
				System.out.println("1D");
				speedx = 0;
				speedy = 1;
			}
			if (pause > 0 && speedy == 0 && solo == 0) {
				System.out.println("D");
				speedx = 0;
				speedy = 1;
				solo = 1;
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			A = false;
			R = false;
			J = false;
			D = false;
			O = 0;
			H = false;
			if (pause > 0 && length == 1) {
				System.out.println("1R");
				speedy = 0;
				speedx = 1;
			}
			if (pause > 0 && speedx == 0 && solo == 0) {
				System.out.println("R");
				speedy = 0;
				speedx = 1;
				solo = 1;
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			A = false;
			R = false;
			J = false;
			D = false;
			O = 0;
			H = false;
			if (pause > 0 && length == 1) {
				System.out.println("1L");
				speedy = 0;
				speedx = -1;
			}
			if (pause > 0 && speedx == 0 && solo == 0) {
				System.out.println("L");
				speedy = 0;
				speedx = -1;
				solo = 1;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			A = false;
			R = false;
			J = false;
			D = false;
			O = 0;
			H = false;
			System.out.println("Enter");
			start = 1000;
			pass = 1;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (pass > 0) {
				if ((speedx == 1 || speedy == 1) || (speedx == -1 || speedy == -1))
					pause *= -1;
					System.out.println(pause);
			}
			
		}
		
		if (e.getKeyCode() == KeyEvent.VK_R) {
			if (R == true)
				R = false;
			A = false;
			R = false;
			J = false;
			D = false;
			O = 0;
			H = false;
			R = true;
			showCC+=3;
			
			
		}
		if (e.getKeyCode() == KeyEvent.VK_J) {
			D = false;
			O = 0;
			H = false;
			J = true;
		}
		if (e.getKeyCode() != KeyEvent.VK_J && e.getKeyCode() != KeyEvent.VK_R && e.getKeyCode() != KeyEvent.VK_A && e.getKeyCode() != KeyEvent.VK_UP && e.getKeyCode() != KeyEvent.VK_DOWN && e.getKeyCode() != KeyEvent.VK_RIGHT && e.getKeyCode() != KeyEvent.VK_LEFT && e.getKeyCode() != KeyEvent.VK_W && e.getKeyCode() != KeyEvent.VK_S && e.getKeyCode() != KeyEvent.VK_D) {		
			R = false;
			A = false;
		}
		
		if (e.getKeyCode() != KeyEvent.VK_O && e.getKeyCode() != KeyEvent.VK_H && e.getKeyCode() != KeyEvent.VK_A && e.getKeyCode() != KeyEvent.VK_UP && e.getKeyCode() != KeyEvent.VK_DOWN && e.getKeyCode() != KeyEvent.VK_RIGHT && e.getKeyCode() != KeyEvent.VK_LEFT && e.getKeyCode() != KeyEvent.VK_W && e.getKeyCode() != KeyEvent.VK_S && e.getKeyCode() != KeyEvent.VK_D) {
			O = 0;
			H = false;
		}
		
			
			
		if (e.getKeyCode() == KeyEvent.VK_O) {
			if (O > 2) {
				H = false;
				O = 0;
			}
			
			if (O < 2)
				O ++;
			D = false;
			
		}
		
		if (e.getKeyCode() == KeyEvent.VK_H) {
			if (H == true)
				H = false;
			O = 0;
			H = false;
			D = false;
			H = true;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
