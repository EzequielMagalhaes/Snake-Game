import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Snake extends JFrame implements Thread{
	private int posEsqSnake [] = { 100 }; //primeira posição do array
	private int posTopSnake [] = { 100 };
	private int posEsqMaca = 200;
	private int posTopMaca = 200;
	
	public Snake() {
		super("Cobrinha");
		this.getContentPane().setBackground(Color.GREEN);
		this.setSize(500,500);
		//this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				System.out.println(e.getKeyCode());
			}
		});
		this.repaint(); // prevenir bugs, se nao aparecer nada;
	}
	public void paint(Graphics g) {
		super.paint(g);
		g.fillRect(posEsqSnake[0],posTopSnake[0],10,10); // coord, coord, largura, altura
		g.setColor(Color.RED);
		g.fillOval(posEsqMaca,posTopMaca,15,15);
	}
	public static void main(String[] args) {
		new Snake();
	}
}