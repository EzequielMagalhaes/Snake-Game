import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Snake extends JFrame implements Runnable{
	private int posEsqSnake [] = { 100 }; //primeira posição do array
	private int posTopSnake [] = { 100 };
	private int posEsqMaca = 200;
	private int posTopMaca = 200;
	private byte pos = 1;
	private Thread th = new Thread(this);
	private int scoreboard = 0;
	
	public Snake() {
		this.setTitle("Snake - Scoreboard" + scoreboard);
		this.getContentPane().setBackground(Color.GREEN);
		this.setSize(500,500);
		//this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				// System.out.println(e.getKeyCode()); //pegar tecla apertada e exibir no console.
				switch(e.getKeyCode()){
					case 37:
						pos = -1;break;
					case 38:
						pos = 2;break;
					case 39:
						pos = 1;break;
					case 40:
						pos = -2;break;
					}
				}
			});
		this.repaint(); // prevenir bugs, se nao aparecer nada;
		th.start();
		this.setVisible(true);
	}

	public void retry(){
		posEsqSnake = new int[]{ 100 };
        posTopSnake = new int[]{ 100 };
        posEsqMaca = 200;
        posTopMaca = 200;
        pos = 1;
        th = new Thread(this);
        scoreboard = 0;
        this.setTitle("Snake - Scoreboard " + scoreboard);
		th.start();
	}
	
	public void run(){
		while(posEsqSnake[0] > 0 && posEsqSnake[0] < 500 &&
				posTopSnake[0] > 30 && posTopSnake[0] < 500){
			if(posEsqSnake[0] >= posEsqMaca && posEsqSnake[0] <= posEsqMaca + 20 &&
					posTopSnake[0] >= posTopMaca && posTopSnake[0] <= posTopMaca + 20) {
				growSnake();
				scoreboard++;
				this.setTitle("Snake - Scoreboard" + scoreboard);
				posTopMaca = (int)(Math.random()* 400 + 50);
				posEsqMaca = (int)(Math.random()* 450 + 10);
				repaint();
			}
			for(int i = posEsqSnake.length - 1; i > 0; i--){
				posEsqSnake[i] = posEsqSnake[i-1];
			}
			for(int i = posTopSnake.length - 1; i > 0; i--){
				posTopSnake[i] = posTopSnake[i-1];
			}
			switch(pos){
				case 1: posEsqSnake[0]+=20;break;
				case -1:posEsqSnake[0]-=20;break;
				case 2: posTopSnake[0]-=20;break;
				case -2:posTopSnake[0]+=20;break;
			}
			repaint();
			try {
				th.sleep(60);
			}catch (InterruptedException e ){
			}
		}
		int reply = JOptionPane.showConfirmDialog(
				this, "Deseja jogar novamente?", "Cobrinha", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
	            retry();
        }else{
            JOptionPane.showMessageDialog(this, "Terminou");
           //System.exit(0);
        }
	}
	
	private void growSnake(){
		int posEsqBkp [] = posEsqSnake; 
		int posTopBkp [] = posTopSnake;
		
		posEsqSnake = new int[posEsqSnake.length + 1];
		posTopSnake = new int[posTopSnake.length + 1];
	
		for(int i = 0; i < posEsqBkp.length; i++){
			posEsqSnake[i] = posEsqBkp[i];
		}
		for(int i = 0; i < posTopBkp.length; i++){
			posTopSnake[i] = posTopBkp[i];
		}
		posEsqSnake[posEsqSnake.length-1] = posEsqSnake[posEsqSnake.length-2];
		posEsqSnake[posEsqSnake.length-1] = posEsqSnake[posEsqSnake.length-2];
		walk();
	}
	
	private void walk(){
		for(int i = posEsqSnake.length - 1; i > 0; i--){
			posEsqSnake[i] = posEsqSnake[i-1];
		}
		for(int i = posTopSnake.length - 1; i > 0; i--){
			posTopSnake[i] = posTopSnake[i-1];
		}
		if(posEsqSnake.length > 1){
			switch(pos){
				case 1: posEsqSnake[0] = posEsqSnake[1] + 20;break;
				case -1:posEsqSnake[0] = posEsqSnake[1] - 20;break;
				case 2: posTopSnake[0] = posTopSnake[1] + 20;break;
				case -2:posTopSnake[0] = posTopSnake[1] - 20;break;
			}
		}
	}
		
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.WHITE);
		for(int i = 0; i < posEsqSnake.length; i++) {
			g.fillOval(posEsqSnake[i],posTopSnake[i],20,20); // coord, coord, largura, altura
		}
		g.setColor(Color.RED);
		g.fillOval(posEsqMaca,posTopMaca,20,20);
	}
		
	public static void main(String[] args) {
		new Snake();
	}
}