import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
//JFrame 視窗
public class GuessNumber extends JFrame{
	private JButton guess;
	private JTextField input;
	private JTextArea hist;
	private String answer = createAnswer(4);
	private int counter = 0;
	public GuessNumber() {
//		super 呼叫父類別Jframe建構式  標題
		super("Guess Number Game");
//		System.out.println(answer);
		guess = new JButton("Guess");
		input = new JTextField();
		hist = new JTextArea();
		
		input.setFont(new Font("Default",Font.PLAIN,24));
		hist.setFont(new Font("Default",Font.BOLD + Font.ITALIC,24));
		
		
		setLayout(new BorderLayout());
		
		JPanel top = new JPanel(new BorderLayout());
		top.add(guess, BorderLayout.EAST);
		top.add(input, BorderLayout.CENTER);
		
//		main window add top and bottom
		add(top, BorderLayout.NORTH);
		add(hist, BorderLayout.CENTER);
		
		guess.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doGuess();
//				answer = createAnswer(4);
//				System.out.println(answer);
			}
			
		});
		
		
		setSize(640,480);
		setVisible(true);
//		按下視窗關閉按鈕就真的關閉
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	private void doGuess() {
		counter++;
		String inputString = input.getText();
		String result = checkAB(inputString, answer);
		hist.append(counter + ":" + inputString + " => " + result +"\n");
		input.setText("");
		if (result.equals("4A0B")) {
			JOptionPane.showMessageDialog(null, "Congratulations! You got the answer");
		}
		else if (counter == 3) {
			JOptionPane.showMessageDialog(null, "You lose! The answer is "+ answer);
		}
	}
//	ans 用傳的  因為answer不是static 
	private static String checkAB(String g, String ans) {
		int a, b;
		a = b = 0;
		for (int i = 0; i < g.length(); i++) {
			if (g.charAt(i) == ans.charAt(i)) {
				a++;
			}
			else if(ans.indexOf(g.charAt(i)) != -1) {
				b++;
			}
		}
		return a+"A"+b+"B";
		
	}
	
	private static String createAnswer(int d) {
		int[] digits = new int[10];
		boolean isRepeat;
		int temp;
		for (int i = 0; i < d; i++) {
			do {
				temp = (int) (Math.random() * 10 );
//				check if repeat
				isRepeat = false;
				for (int j = 0; j < i; j++) {
					if (temp == digits[j]) {
						isRepeat= true;
						break;
					}
				}
				
			}while(isRepeat);
			digits[i] = temp;
		}
		String ret = "";
		for(int i = 0; i < d;i++) {
			ret += digits[i];
		}
		return ret;
	}
	public static void main(String[] args) {
		new GuessNumber();
		
	}

}
