package mySignature;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MyDrawer extends JPanel{
	private LinkedList<LinkedList<HashMap<String,Integer>>> lines, recycler;
	
	public MyDrawer() {
		setBackground(Color.green);
		lines = new LinkedList<>();
		recycler = new LinkedList<>();
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				LinkedList<HashMap<String,Integer>> line = new LinkedList<>();
//				System.out.println(e.getX()+ " X " + e.getY());
				HashMap<String,Integer> point = new HashMap<>();
				point.put("x", e.getX());
				point.put("y", e.getY());
				line.add(point);
				lines.add(line);
				recycler.clear();
				repaint();
			}
			
		});
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseDragged(e);
//				System.out.println(e.getX()+ " X " + e.getY());
				HashMap<String,Integer> point = new HashMap<>();
				point.put("x", e.getX());
				point.put("y", e.getY());
				lines.getLast().add(point);

				repaint();
			}
		});
	}
	public void clear() {
		lines.clear();
		repaint();
	}
	public void undo() {
		if(lines.size() > 0) {
			recycler.add(lines.removeLast());
			repaint();
		}
	}
	public void redo() {
		if(recycler.size() > 0) {
			lines.add(recycler.removeLast());
			repaint();
		}
	}
	public void saveJPEG() throws Exception{
		BufferedImage image = new BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_INT_RGB);;
		Graphics2D g2d = image.createGraphics();
		paint(g2d);
		ImageIO.write(image, "jpeg", new File("savedImages/output.jpg"));
	}
	public void saveObj() throws Exception{
		BufferedImage image = new BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_INT_RGB);;
		Graphics2D g2d = image.createGraphics();
		paint(g2d);
		ImageIO.write(image, "obj", new File("savedImages/brad.obj"));
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.blue);
//		how bold is the pen
		g2d.setStroke(new BasicStroke(4));
//		g2d.drawLine(0,0,100,100);
		
		for (LinkedList<HashMap<String,Integer>> line : lines) {
			for (int i = 1; i < line.size(); i++) {
				HashMap<String, Integer> p0 = line.get(i-1);
				HashMap<String, Integer> p1 = line.get(i);
				g2d.drawLine(p0.get("x"), p0.get("y"),p1.get("x"), p1.get("y"));
			}
		}
		
	}
}
