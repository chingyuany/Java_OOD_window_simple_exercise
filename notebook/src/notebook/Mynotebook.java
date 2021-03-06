package notebook;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class Mynotebook extends JFrame{
	private JButton open, save, saveas,newfile;
	private JTextArea editor;
	private File nowFile = null;
	public Mynotebook() {
		super("My notebook");
		setLayout(new BorderLayout());
		
		JPanel top = new JPanel(new FlowLayout());
		
		newfile = new JButton("New");top.add(newfile);
		open = new JButton("Open");top.add(open);
		save = new JButton("Save");top.add(save);
		saveas = new JButton("Save as");top.add(saveas);
		add(top, BorderLayout.NORTH);
		editor = new JTextArea();
		editor.setFont(new Font("",Font.PLAIN,24));
		editor.setTabSize(4);
		JScrollPane jsp = new JScrollPane(editor);
		add(jsp,BorderLayout.CENTER);

		setupButtons();
		
		setSize(640, 480);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	private void setupButtons() {
		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openFile();
			}
		});
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveFile();
			}
		});
		saveas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveasFile();
			}
		});
		newfile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
	}
	private void openFile() {
		JFileChooser jFileChooser = new JFileChooser();
//		approve_option ���S�����
		if (jFileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			nowFile = jFileChooser.getSelectedFile();
			if (nowFile.isFile() && nowFile.canRead()) {
				readFile();
			}else {
				nowFile = null;
			}
		}
	}
	private void readFile() {
		try {
		FileReader reader = new FileReader(nowFile);
		int len = 0; char[] buf = new char[4096];
		while((len = reader.read(buf)) != -1 ) {
			editor.append(new String(buf,0,len));
		}
		reader.close();
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		
	}
	private void saveFile() {
		if (nowFile != null) {
			try {
				editor.write(new FileWriter(nowFile));
				JOptionPane.showMessageDialog(this, "Save Successful");
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
	}
	private void saveasFile() {
		JFileChooser jFileChooser = new JFileChooser();
		if (jFileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			nowFile = jFileChooser.getSelectedFile();
			saveFile();
		}
	}
	private void clear() {
		nowFile = null;
		editor.setText("");
	}
	public static void main(String[] args) {
		new Mynotebook();
		
	}
}
