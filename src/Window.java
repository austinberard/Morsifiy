import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Window {
	
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;

	public Window() {
		prepareGUI();
	}

	private void prepareGUI() {
		mainFrame = new JFrame("Morse Code Translator");
		mainFrame.setSize(400, 400);
		mainFrame.setLayout(new GridLayout(3, 1));
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		headerLabel = new JLabel("", JLabel.CENTER);
		statusLabel = new JLabel("", JLabel.CENTER);
		
		statusLabel.setSize(350, 100);
		
		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		
		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.add(statusLabel);
		mainFrame.setVisible(true);

	}

	private void showTextField(){
		headerLabel.setText("Morsifiy in Action!");
		
		JLabel  textlabel = new JLabel("Text to translate: ", JLabel.RIGHT);
		final JTextField userText = new JTextField(6);
		JButton translateButton = new JButton("Morsifiy!");
		
		translateButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {     
	            String text = userText.getText();
	            try {
	            	statusLabel.setText(Morse.MorsifiyForText(text));
					Sound.morseToSound(Morse.MorsifiyForSound(text));
				} catch (IOException | UnsupportedAudioFileException | LineUnavailableException
						| InterruptedException e1) {
					e1.printStackTrace();
				}
	         }
	      });
		
		controlPanel.add(textlabel);
		controlPanel.add(userText);
		controlPanel.add(translateButton);
		mainFrame.setVisible(true);
		
	}
	
	public static void main(String[] args){
		Window window = new Window();
		window.showTextField();
	}
}
