import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {

	// Code for playing sound files from http://stackoverflow.com/a/577926
	private static void playClip(File clipFile)
			throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
		class AudioListener implements LineListener {
			private boolean done = false;

			@Override
			public synchronized void update(LineEvent event) {
				Type eventType = event.getType();
				if (eventType == Type.STOP || eventType == Type.CLOSE) {
					done = true;
					notifyAll();
				}
			}

			public synchronized void waitUntilDone() throws InterruptedException {
				while (!done) {
					wait();
				}
			}
		}
		AudioListener listener = new AudioListener();
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(clipFile);
		try {
			Clip clip = AudioSystem.getClip();
			clip.addLineListener(listener);
			clip.open(audioInputStream);
			try {
				clip.start();
				listener.waitUntilDone();
			} finally {
				clip.close();
			}
		} finally {
			audioInputStream.close();
		}
	}
	
	
	public static void morseToSound(String text) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException{
		URL dotPath = Sound.class.getResource("dot.wav");
		File dot = new File(dotPath.getFile());
		
		URL dashPath = Sound.class.getResource("dash.wav");
		File dash = new File(dashPath.getFile());

		for(char c: text.toCharArray()){
			switch(c)
			{
			case '.':
				playClip(dot);
				break;
			case '-':
				playClip(dash);
				break;
			case ' ':
				Thread.sleep(100);
				break;
			case ',':
				Thread.sleep(400);
				break;
			}
		}
	}

}
