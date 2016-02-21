import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CSV {

	public static void main(String[] args) throws IOException {
		JFrame frame = new JFrame("File Chooser");
		JFileChooser fileChooser = new JFileChooser();
		File readFile = null;
		File writeFile = null;
		int returnVal = fileChooser.showOpenDialog(frame);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
		       readFile = fileChooser.getSelectedFile();
	    	}
		returnVal = fileChooser.showSaveDialog(frame);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
		       writeFile = fileChooser.getSelectedFile();
	    	}
		writeFile.createNewFile();
		FileWriter fw = new FileWriter(writeFile);
		

		FileReader fr = new FileReader(readFile);
		char[] characters = new char[(int)readFile.length()];
			
		fr.read(characters);
		String doc = new String(characters);
		
		Pattern p = Pattern.compile("([0-9]+),\"([a-z A-Z]+)\",\"(-?[0-9]+\\.?[0-9]+)\"");
		Matcher m = p.matcher(doc);

		int time = 0;
		ArrayList<String> keys = new ArrayList<>();
		ArrayList<String> data = new ArrayList<>();
		
		while(m.find()) {
			time = Integer.parseInt(m.group(1));
			if(!keys.contains(m.group(2))){
				keys.add(m.group(2));
			}		
		}
		m.reset();
		String[] data2 = new String[keys.size()];
		int lastTime = 0;
		fw.write("Time , " + keys.toString().replaceAll("[\\[\\]]", "") + "\r\n");
		boolean firstTime = false;
		while(m.find()){
			
			time = Integer.parseInt(m.group(1));
			if (lastTime != time){
				System.out.println("data2 " + Arrays.toString(data2));
				if(firstTime){
					fw.write(time + " , " + Arrays.toString(data2).replaceAll("[\\[\\]]", "")+"\r\n");
				}
				//data2 = new String[keys.size()];
			}
			data2[keys.indexOf(m.group(2))] = m.group(3);
			lastTime = time;
			firstTime = true;
		}
		System.out.println("data2 " + Arrays.toString(data2));
		fw.write(time + " , " + Arrays.toString(data2).replaceAll("[\\[\\]]", "")+"\r\n");
		fw.close();
		JOptionPane.showMessageDialog(frame, "File Successfully Converted");
	}
}
