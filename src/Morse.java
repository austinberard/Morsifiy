import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Morse {
	
	public static String Morsifiy(String text){
		String converted = convertToMorseCode(text);
		return converted;
	}
	
	public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException{
		try {
			System.out.println("Translating: " + args[0]);
			String morseCode = Morsifiy(args[0]);
			System.out.println(morseCode);
			Sound.morseToSound(morseCode);
		} catch(IndexOutOfBoundsException e){
			System.out.println("You must include text to decode");
			e.printStackTrace();
		}
	}
	
	public static String convertToMorseCode(String text){
		text = text.toLowerCase();
		String result = "";
		
		for(char c : text.toCharArray()){
			result += convert(c);
			if(!result.endsWith(",")){
				result += " ";
			}

		}
		
		return result;
		
	}
	
	private static String convert(char letter){
		
		switch(letter)
		{
		case 'a':
			return ".-";
		case 'b':
			return "-...";
		case 'c':
			return "-.-.";
		case 'd':
			return "-..";
		case 'e':
			return ".";
		case 'f':
			return "..-.";
		case 'g':
			return "--.";
		case 'h':
			return "....";
		case 'i':
			return "..";
		case 'j':
			return ".---";
		case 'k':
			return "-.-";
		case 'l':
			return ".-..";
		case 'm':
			return "--";
		case 'n':
			return "-.";
		case 'o':
			return "---";
		case 'p':
			return ".--.";
		case 'q':
			return "--.-";
		case 'r':
			return ".-.";
		case 's':
			return "...";
		case 't':
			return "-";
		case 'u':
			return ".--";
		case 'v':
			return "...-";
		case 'w':
			return ".--";
		case 'x':
			return "-..-";
		case 'y':
			return "-.--";
		case 'z':
			return "--..";
		case '.':
			return ".-.-.-";
		case ',':
			return "--..--";
		case '?':
			return "..--..";
		case '/':
			return "-..-.";
		case '@':
			return ".--.-.";
		case '0':
			return "-----";
		case '1':
			return ".----";
		case '2':
			return "..---";
		case '3':
			return "...--";
		case '4':
			return "....-";
		case '5':
			return ".....";
		case '6':
			return "-....";
		case '7':
			return "--...";
		case '8':
			return "---..";
		case '9':
			return "----.";
		case ' ':
			return ",";
		default:
			throw new IllegalArgumentException("This charecter: " + letter + " is not supported");
		}
	}
}
