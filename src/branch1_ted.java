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

public class branch1_ted {

	public static void main(String[] args) throws IOException {

		FileWriter write = null;
		File file = new File("/Users/tedlin/Desktop/csv smaller.txt"); // file location could change
		FileReader fr = new FileReader("/Users/tedlin/Desktop/csv smaller.txt");
		char[] characters = new char[(int)file.length()];
			
		fr.read(characters);
		String doc = new String(characters);
		
		Pattern p = Pattern.compile("([0-9]+),\"([a-z A-Z]+)\",\"(-?[0-9]+\\.?[0-9]+)\"");
		Matcher m = p.matcher(doc);

		int time = 0;
		ArrayList<String> keys = new ArrayList();
		ArrayList<String> data = new ArrayList();
		
		while(m.find()) {
			time = Integer.parseInt(m.group(1));
			if(!keys.contains(m.group(2))){
				keys.add(m.group(2));
			}		
		}
		m.reset();

		String[] data2 = new String[keys.size()];
		int lastTime = 0;
		while(m.find()){
			time = Integer.parseInt(m.group(1));
			if (lastTime != time){
				System.out.println("data2 " + Arrays.toString(data2));
			}
			data2[keys.indexOf(m.group(2))] = m.group(3);
			lastTime = time;
		}
		System.out.println("data2 " + Arrays.toString(data2));
	}
}

/*	reference code
 * public class branch1_ted {

	public static void main(String[] args) throws IOException {

		FileWriter write = null;
		
			//File file2 = new File("/Users/tedlin/Desktop/Organized Data.txt");
			//file2.createNewFile();
			//write = new FileWriter(file2); // assign write to file2
			File file = new File("/Users/tedlin/Desktop/csv smaller.txt"); // file location could change
			FileReader fr = new FileReader("/Users/tedlin/Desktop/csv smaller.txt");
			char[] characters = new char[(int)file.length()];
			

			fr.read(characters);
			String doc = new String(characters);
		
		Pattern p = Pattern.compile("([0-9]+),\"([a-z A-Z]+)\",\"(-?[0-9]+\\.?[0-9]+)\"");
		Matcher m = p.matcher(doc);
				6194,"Yaw","53.119998931884766"
				6194,"Displacement X","145.60186767578125"
				6194,"Displacement Y","-331.35760498046875"
				8395,"Yaw","53.119998931884766"
				8495,"Yaw","53.11000061035156"
				8695,"Yaw","53.099998474121094"
				9998,"Yaw","53.08000183105469"
				9998,"Displacement X","145.6016387939453"
				9998,"Displacement Y","-331.3576965332031");
				
//		boolean b = m.find();
//		System.out.println(m.group(3));
		
		int time = 0;
		ArrayList<String> keys = new ArrayList();
		ArrayList<String> data = new ArrayList();
		
		while(m.find()) {
			
			time = Integer.parseInt(m.group(1));
			if(!keys.contains(m.group(2))){
				keys.add(m.group(2));
			}		
		}
		m.reset();

		//int count = 0;
		String[] data2 = new String[keys.size()];
		int lastTime = 0;
		while(m.find()){
			time = Integer.parseInt(m.group(1));

			//if(Integer.parseInt(m.group(1)) == time){
			data2[keys.indexOf(m.group(2))] = m.group(3);
			if (lastTime != time){

				//System.out.println(m.group(0));
				//System.out.println("time: " + time);
				//System.out.println("keys " + keys);
				System.out.println("data2 " + Arrays.toString(data2));
				//System.out.println(doc);
				
				//count += 1;
				//System.out.println(count);
				
			}
			data2[keys.indexOf(m.group(2))] = m.group(3);
			lastTime = time;
			} else{
				try {
					write.write(time);
					write.write("\t");
					
					for(int i = 0; i < data2.length;i++){
						System.out.println(i + data2[i]);
						write.write(data2[i]);
						write.write("\t");
					}
					write.write("\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//System.out.println("time: " + time);
		//System.out.println("keys " +keys);
		//System.out.println("data array " + data);
		System.out.println("data2 " + Arrays.toString(data2));
		//System.out.println(doc);
		//System.out.println(count);
		}
	}
}*/

