import java.util.*;
import java.io.*;

public class filemaker{
	public static void main(String[] args){
		Scanner kb = new Scanner(System.in);
		System.out.println("Enter amount of numbers");
		String stramt = kb.nextLine();
		int amt = Integer.parseInt(stramt);
		System.out.println("Enter output file name");
		String output = kb.nextLine();
		try{
		PrintWriter out = new PrintWriter(new FileOutputStream(output));
		Random rand = new Random();
		for(int i=0;i<amt;i++){
			if(i!=(amt-1))
			out.print(rand.nextInt(99)+",");
			else
			out.print(rand.nextInt(99)+ "\n");
		}
		out.close();
		}
		catch(IOException e){
			System.exit(1);
		}
	}
}

