package window_mouse_exercise;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class Mymouse extends JFrame
{
	private JCheckBox checkDrag;
	private JTextField textPosition;
	private JPanel testArea;

	public Mymouse() {
		super("Mouse detector");
		
		setLayout(new BorderLayout());
		JPanel top = new JPanel(new BorderLayout());
		checkDrag = new JCheckBox("Click mouse");
		textPosition = new JTextField(40);
		top.add(checkDrag,BorderLayout.EAST);
		top.add(textPosition,BorderLayout.WEST);
		add(top, BorderLayout.NORTH);
		
		testArea = new JPanel();
		add(testArea, BorderLayout.CENTER);
//		內部匿名類別
    	testArea.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mousePressed(MouseEvent e) {
    			super.mousePressed(e);
    			checkDrag.setSelected(true);
    		}
    		@Override
    		public void mouseReleased(MouseEvent e) {
    			super.mouseReleased(e);
    			checkDrag.setSelected(false);
    		}
    	});
    	testArea.addMouseMotionListener(new MouseAdapter() {
    		@Override
    		public void mouseDragged(MouseEvent e) {
    			super.mouseDragged(e);
    			int x = e.getX(), y= e.getY();
    			textPosition.setText("Mouse Poistion = "+x + " x " + y);
    		}
    	});
    	
    	
		setSize(800, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	public static void main(String[] args) {
		new Mymouse();
		
	}

}
