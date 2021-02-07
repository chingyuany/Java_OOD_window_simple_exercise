import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel{
	private Timer timer;
//	private BallTask ballTask;
	private int viewW, viewH, ballW, ballH;
	private BufferedImage ball;
	private LinkedList<BallTask> balls = new LinkedList<>();
	public GamePanel() {
		setBackground(Color.white);
		timer = new Timer();
		try {
			ball = ImageIO.read(new File("img/ball.jpg"));
			ballW = ball.getWidth();
			ballH = ball.getHeight();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		0秒就出現 每0.006秒刷新螢幕
		timer.schedule(new ViewTask(), 0, 60);
		addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				addBall(e.getX() - (int) (ballW/2f),
						e.getY() - (int) (ballH/2f));
			}
		});
//		ballTask = new BallTask(0,0);
	}
	private void addBall(int x, int y) {
		BallTask ballTask = new BallTask(x,y);
////	0.5秒後才出現球, 每0.008秒刷新球的座標
		timer.schedule(ballTask, 500, 80);
		balls.add(ballTask);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		viewW = getWidth(); viewH = getHeight();
		for(BallTask ballTask : balls) {
			g2d.drawImage(ball, ballTask.x, ballTask.y, null);
		}
		
 	}
	private class ViewTask extends TimerTask{
		@Override
		public void run() {
//			this is for paintComponent
			repaint();
		}
		
	}
	
	private class BallTask extends TimerTask{
		int x, y, dx, dy;
		BallTask(int x, int y){
			this.x = x;
			this.y = y;
			dx = dy = 8;
		}
		@Override
		public void run() {
			if (x < 0 || x + ballW > viewW) {
				dx *= -1;
			}
			if (y < 0 || y + ballH > viewH) {
				dy *= -1;
			}
			x += dx;
			y += dy;
	
		}
		
	}
}
