import java.awt.BorderLayout;
import java.awt.Color;
import java.util.LinkedList;

import javax.swing.JFrame;

public class MovingBall extends JFrame {
	private GamePanel panel;

	public MovingBall() {
		super("Moving Ball");
		setLayout(new BorderLayout());
	
		panel = new GamePanel();
		add(panel, BorderLayout.CENTER);
		setSize(640, 480);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new MovingBall();

	}

}
