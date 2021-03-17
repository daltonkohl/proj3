import java.util.*;
import java.io.*;

public class MSS 
{
	public static void main (String []args)
	{
		boolean running = true;
		while (running)
		{
			Scanner input = new Scanner(System.in);
			System.out.println("Welcome to MSS project.");
			int [] numbers = createArray();
			//System.out.println(Arrays.toString(numbers));
			double mss1Start, mss1End, mss2Start, mss2End, mss3Start, mss3End, mss4Start, mss4End; 
			boolean choosing= true;
			while (choosing)
			{	
			System.out.println("(a) MSS One - Triple loop.");
			System.out.println("(b) MSS Two - Double loop.");
			System.out.println("(c) MSS Three - Recursive function.");
			System.out.println("(d) MSS Four - Single loop.");
			System.out.println("(e) All of the above.");
			String inputChoice = input.nextLine();

			switch (inputChoice)
			{		
			
			case "a":
				//time
				mss1Start = System.nanoTime();
				mssOne(numbers);
				mss1End = System.nanoTime();
				choosing=false;
				System.out.printf("%-15s %-2s %15.4f\n","MSS One Time", "=" , (mss1End-mss1Start));			
				//System.out.println("MSS One Time = "+ (mss1End - mss1Start));
				break;
			case "b":
				mss2Start = System.nanoTime();
				mssTwo(numbers);
				mss2End = System.nanoTime();
				System.out.printf("%-15s %-2s %15.4f\n","MSS Two Time", "=" , (mss2End-mss2Start));
			//	System.out.println("MSS Two Time = " + (mss2End - mss2Start));
				choosing=false;
				break;
			case "c":
				mss3Start = System.nanoTime();
				mssThree(numbers, 0, numbers.length-1);
				mss3End = System.nanoTime();
				System.out.printf("%-15s %-2s %15.4f\n","MSS Three Time", "=" , (mss3End-mss3Start));			
				//System.out.println("MSS Three Time = "+ (mss3End- mss3Start));
				choosing=false;
				break;
			case "d":
				mss4Start = System.nanoTime();
				mssFour(numbers);
				mss4End = System.nanoTime();
				System.out.printf("%-15s %-2s %15.4f\n","MSS Four Time", "=" , (mss4End-mss4Start));
				//System.out.println("MSS Four Time = " + (mss4End - mss4Start));
				choosing=false;
				break;
			case "e":
				mss1Start = System.nanoTime();
				mssOne(numbers);
				mss1End = System.nanoTime();

				mss2Start = System.nanoTime();
				mssTwo(numbers);
				mss2End = System.nanoTime();

				mss3Start = System.nanoTime();
				mssThree(numbers, 0, numbers.length-1);
				mss3End = System.nanoTime();

				mss4Start = System.nanoTime();
				mssFour(numbers);
				mss4End = System.nanoTime();
				System.out.printf("%-15s %-2s %15.4f\n","MSS One Time", "=" , (mss1End-mss1Start));
				System.out.printf("%-15s %-2s %15.4f\n","MSS Two Time","=", (mss2End-mss2Start));
				System.out.printf("%-15s %-2s %15.4f\n","MSS Three Time","=", (mss3End-mss3Start));
				System.out.printf("%-15s %-2s %15.4f\n","MSS Four Time","=", (mss4End-mss4Start));

				double totalTime = ((mss1End+mss2End+mss3End+mss4End)-(mss1Start+mss2Start+mss3Start+mss4Start));
				System.out.printf("%-15s %-2s %15.4f\n","MSS Total Time", "=", totalTime);				

				choosing=false;
				break;
			default	: 
				System.out.println("Invalid input. Please try Again.");
				break;			
			}
			}
		System.out.println("Would you like to run the program again?(y or n)");
		String playAgain= input.nextLine();
		if (playAgain.equalsIgnoreCase("n"))
		{
			running = false;
		}	
		}	




		//int [] nums = {-2};
		//System.out.println(mssThree(nums, 0, nums.length-1));
	}	
	public static int [] createArray()
	{
		try{
		Scanner kb = new Scanner(System.in);
		System.out.println("Enter a file name: ");
		String fileName = kb.nextLine();
		BufferedReader file = new BufferedReader(new FileReader(fileName));
		String fileLine = file.readLine();
		StringTokenizer ints = new StringTokenizer(fileLine,",");
		int [] array = new int [ints.countTokens()];
		int counter =0;
		while (ints.hasMoreTokens())
		{
		 array[counter] = Integer.parseInt(ints.nextToken());
		 counter++;		
		}
		
		return array;	
		}
		catch (FileNotFoundException f)
		{
			System.out.println(f.getMessage());
			System.exit(1);
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
			System.exit(1);
		}
		return null;
	}
		
	public static void mssOne(int [] nums) 
	{
		int maxSum=0;
		int sum;
		for (int i=0; i<nums.length; i++)
		{
			for (int j=i; j<nums.length; j++)
			  {
				  sum=0;
				  for (int k=i; k <= j; k++)
				  {
						sum += nums[k];
				  }
				  if (sum > maxSum)
				  {	  	  
					  maxSum = sum; 
			  	  }
			  } 
		}
		
	}


	public static void mssTwo(int [] nums)
	{
		int maxSum=0;
		int sum;
		for (int i =0; i < nums.length; i++)
			{
				sum=0;
				for (int j = i; j < nums.length; j++)
				{
					sum += nums[j];	
				}	
				if (sum>maxSum)
				{
					maxSum = sum;
				}	
			}	
	}	

	//recursive MSS
	public static int mssThree (int [] nums, int left, int right)
	{
		if (left == right)
		{
			return Math.max(nums[left], 0);
		}
		
		int mid = (left+right) / 2;
		int leftSum = mssThree(nums,left, mid);
		int rightSum = mssThree(nums, mid+1, right);
		
		int sum =0;
		int leftBound = 0;
		for (int i = mid; i >= 0; i--)
			{
				sum += nums[i];
				leftBound = Math.max(leftBound, sum);
			}
		// testing: System.out.println("LEFTBOUNDEDSUM: " +leftBound);
		
		sum=0;
		int rightBound =0;
		for(int i =mid+1; i<= right; i++)
			{
			sum += nums[i];
			rightBound = Math.max(rightBound, sum);		
			}
		//testing: System.out.println("RIGHTBOUNDSUM: " +rightBound);	
		
		int boundSum = leftBound + rightBound;
		
		//testing: System.out.println("RIGHT: " + rightSum + " LEFT: " + leftSum + " BOUNDED: " + boundSum);
		int tempMax = Math.max(leftSum,rightSum);
		return Math.max (tempMax, boundSum);	
					
	}	



	public static void mssFour (int [] nums)
	{
		int maxSum=0;
		int sum =0;
		for (int i=0; i<nums.length; i++)
		{
			sum += nums[i];
			if (sum >maxSum)
			{
				maxSum = sum;
			}	
			else
			{
				if (sum < 0)
				{
					sum =0;
				}	
			}	
		}	
	}	





}	
