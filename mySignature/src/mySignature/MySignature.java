package mySignature;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MySignature extends JFrame {
	private JButton clear, undo, redo, saveJPEG,
	saveObj, loadobj, changeColor;
	private MyDrawer myDrawer;
	
	public MySignature() {
		super("Signature Application");
		setLayout(new BorderLayout());
		
		JPanel top = new JPanel(new FlowLayout());
		clear = new JButton("clear");top.add(clear);
		undo = new JButton("undo");top.add(undo);
		redo = new JButton("redo");top.add(redo);
		saveJPEG = new JButton("saveJPEG");top.add(saveJPEG);
		saveObj = new JButton("saveObj");top.add(saveObj);
		loadobj = new JButton("loadobj");top.add(loadobj);
		changeColor = new JButton("changeColor");top.add(changeColor);
		add(top, BorderLayout.NORTH);
		
		myDrawer = new MyDrawer();
		add(myDrawer,BorderLayout.CENTER);
		
		initFunc();
		
		setSize(640, 480);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	private void initFunc() {
		clear.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			myDrawer.clear();
		}
		});
		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myDrawer.undo();
			}
		});
		redo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myDrawer.redo();
			}
		});
		saveJPEG.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					myDrawer.saveJPEG();
					JOptionPane.showMessageDialog(MySignature.this, "Saved JPEG ok");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(MySignature.this, "Saved JPEG Failed");
				}
			}
		});
	}
	public static void main(String[] args) {
		new MySignature();

	}

}
