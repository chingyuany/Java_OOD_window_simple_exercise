package racing;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Racing extends JFrame {
	private JButton go;
	private JLabel[] lanes= new JLabel[8];
	private Horse[] horses = new Horse[8];
	private int rank = 0;
	public Racing() {
		super("Racing");
		setLayout(new GridLayout(9,1));
		go = new JButton("Go!");
		add(go);
		go.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				go();
				
			}
		});
		for (int i = 0; i < lanes.length; i++) {
			lanes[i] = new JLabel((i+1) + ".");
			add(lanes[i]);
		}
		setSize(1024, 480);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	private void go() {
//		disable go button until one race finished
		go.setEnabled(false);
		rank = 0;
		for (int i = 0; i < horses.length; i++) {
			lanes[i].setText((i+1) + ".");
			horses[i] = new Horse(i);
			horses[i].start();
		}
	}
	private void stopGame() {
		for (int i = 0; i < horses.length; i++) {

			horses[i].interrupt();
		}
		go.setEnabled(true);
	}
	private class Horse extends Thread{
		int lane;
		
		Horse(int lane){this.lane = lane;}
		@Override
		public void run() {
			super.run();
			for (int i = 0; i < 100; i++) {
				lanes[lane].setText(lanes[lane].getText()+">");
				if (i == 99) {
					lanes[lane].setText(lanes[lane].getText()+ ++rank);
//					once the first horse arrive end point, stop other horses;
					stopGame();
				}
				try {
					Thread.sleep(50 + (int)(Math.random()*150));
				} catch (InterruptedException e) {
					break;
				}
			}
		}
	}
	public static void main(String[] args) {
		new Racing();

	}

}
