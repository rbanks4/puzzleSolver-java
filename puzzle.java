import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//Java Code to solve a 8-tile puzzle
//Reginald Banks

//Goals: Take in a string at row intervals, convert to int,

public class puzzle
{
//sends back true if the puzzle is at goal state
	public static boolean isSolved(int[] catrix)
	{
		for (int i = 0; i<9; i++)
		{
			if (catrix[i] != (i+1))
			{
				return false;
			}
		}
		return true;
	}
//rewrites the puzzle in string form 
	public static String[] writePuzzle(int[] catrix)
	{
	System.out.print("\n");
		System.out.print("\n");
		System.out.println("Re-Writing Puzzle");
		String[] puzzle = new String[9];
		int block;
		boolean working = true;
		for(int i = 0; i<9; i++)
        {
                //System.out.println("Give me slot #" +i);
                //figure out how to make a " :" later at the end.
                //block = get.nextInt();
                block = catrix[i];
                if ((block == 0)||(block == 9))
                {
                    //System.out.println("ok!");
                    puzzle[i] = " ";

                }
                else if ((block <9) && (block >= 0))
                {
                    puzzle[i] = Integer.toString(catrix[i]);
                }
                else
                {
                    System.out.print("I'm sorry, slot #" +(i+1));
                    System.out.print(" is out of range. ");
                    //i--;
                    working = false;
                    break;
                }
        }
        if (working)
        {
            System.out.println("Input, Successful.");
        }
        else
        {
            System.out.println("Invalid 8-puzzle entered!");
        }
		System.out.println("Inversions = " +invCount(catrix));
		printString(puzzle);
        System.out.print("\n");
        System.out.print("\n");
		return puzzle;
	}
//tells you if the matrix is solvable
    public static boolean isSolvable(int [] p)
        {
            int i, j, n, inversions = 0;
            n = p.length;
            for(i = 0; i < n - 1; i++)
                for(j = i+1; j < n; j++)
                    if(p[i] > p[j]) {
                        //System.out.println("("+p[i]+", "+p[j]+")");
                        inversions++;
                    }
            //System.out.println("Number of inversions are: "+inversions);
			if (inversions == 0)
			{
				return true;
			}
			else
				return ((inversions > 0) && (inversions % 2 == 0));
        }
//counts the number of inverses
		public static int invCount(int [] p)
        {
            int i, j, n, inversions = 0;
            n = p.length;
            for(i = 0; i < n - 1; i++)
			{
                for(j = i+1; j < n; j++)
                    if(p[i] > p[j]) {
                        //System.out.println("("+p[i]+", "+p[j]+")");
                        inversions++;
                    }
            //System.out.println("Number of inversions are: "+inversions);
			}

            return inversions;
        }
//switches two ints in a int array...this isn't working as intended just yet.
	public static int[] switchSlot(int[] puz, int a, int b)
	{
		int c = puz[a];
		puz[a] = puz[b];
		puz[b] = c;
		System.out.print("-Switch-");
		return puz;
	}
	/*public static int ifswitch(int[] puzzle, int a, int b)
	{
		int c;
		int[] pzl = new int[9];
		pzl = puzzle;
		if ((puzzle[a] > puzzle[b])&&a<b)
		{
			System.out.println(a+ " was less than " +b);
			System.out.println(puzzle[a]+ " was greater than " +puzzle[b]);
			c = invCount(puzzle)-invCount(switchSlot(pzl,a,b));
			//switchSlot(puzzle,a,b);
			return c;
		}
			
		else if ((puzzle[a] > puzzle[b])&&a>b)
		{
			System.out.println(a+ " was greater than " +b);
			System.out.println(puzzle[a]+ " was greater than " +puzzle[b]);
			return 0;
		}
		else
			return 0;
	}*/
//returns the address of an array location with the value of 0 or " "
	public static int findEmpty(int[] matrix)
	{
		for (int i=0; i<9; i++)
		{
			if (matrix[i] == 9)
			{
				return i;
			}
		}
			return 0;
	}
//this prints out the int version of the matrix
    public static void printMatrix(int[] matrix)
    {
        for(int i = 0; i<9; i++)
        {
            if ((i==3)||(i==6))
            {
                System.out.print("\n");
                System.out.print(+matrix[i]);
                System.out.print(" ");

            }
            else
            {
                System.out.print(+matrix[i]);
                System.out.print(" ");

            }
        }
    }
//looks for where the 0 is in the matrix and turns it into a 9
public static void fixZeroSlot(int[] imatrix)
{
	for (int i = 0; i<9; i++)
	{
		//changes 0 to 9 so it can be solvable
		if ((imatrix[i] == 0))
		{
			imatrix[i] = 9;
		}
		else if (imatrix[i] == -1)
		{
			//if an error shows via "-1" let it be unsolvable
			imatrix[i] = 99;
		}
	}
}
//changes 9 back into 0 in the matrix
public static void returnZeroSlot(int[] imatrix)
{
	for (int i = 0; i<9; i++)
	{
		if ((imatrix[i] == 9))
		{
			imatrix[i] = 0;
		}
	}
}
//prints the string version of the matrix
public static void printString(String[] smatrix)
    {
        for(int i = 0; i<9; i++)
        {
            if ((i==3)||(i==6))
            {
                System.out.print("\n");
                System.out.print(smatrix[i]);
                System.out.print(" ");

            }
            else
            {
                System.out.print(smatrix[i]);
                System.out.print(" ");

            }
        }
    }

	public static void main(String[] args)
	{
	    ////
	    //Get input from the user as a string
		////
		System.out.print("\n");
		System.out.print("Give 8-puzzle as 3x3 matrix (a space between digits, hit Enter after each line): \n");
		System.out.println("Example: \n1 2 3\n");
		System.out.print("     Note: Unsolvable combinations will be rejected. \n");


        Scanner rows = new Scanner(System.in);
        char[] row1, row2, row3;
        row1 = new char[5];
        row2 = row1;
        row3 = row1;
        int[] Matrix = new int[9];
		int[] OldMatrix = new int[9];
		//String[] puzzle = new String[9];
        char hold;
        int m = 0; //for the matrix loop
        String r1,r2,r3;
        //how to change a character array into a string
        ////String hold = new String(row1); //brilliant
        //make a loop to change the string input into a row of inputs
        //System.out.println("Please give me the first row!:");
        r1 = rows.nextLine();
        r2 = rows.nextLine();
        r3 = rows.nextLine();
        int max = r1.length()+r2.length()+r3.length();
        //int limit = r1.length();
        int foo = 0;
		//int[] MatrixPrt = new int[9];
		
        for(int i = 0; i<max; i++)
        {

            
			//separate the loop into sections for the matrix
			//
			/////This section is row 1////
			//
			if (i<5)//(m<3)
			{
				hold = r1.charAt(foo);

				if (i==1||i==3)
				{
					foo++;
					foo = foo %5;
				}
				else
				{
					
					if (Character.isWhitespace(hold))
					{
						Matrix[m] = 0;
						if (m<9){m++;}
						foo ++;
						foo = foo%5;
					}
					else if (m<9)
					{
						Matrix[m] = Character.digit(r1.charAt(foo),9);
						m++;
						foo++;
						foo = foo %5;
					}
				}
            }
			//		
			/////This section is Row 2/////
			//
			else if (i<10)
			{
				hold = r2.charAt(foo);
				if(i==6||i==8)
				{
					foo++;
					foo = foo %5;
				}
				else
				{
					if (Character.isWhitespace(hold))
					{
						Matrix[m] = 0;
						if (m<8){m++;}
						foo ++;
						foo = foo%5;
					}
					else if (m<8)
					{
						Matrix[m] = Character.digit(r2.charAt(foo),8);
						m++;
						foo++;
						foo = foo %5;
					}
				}
			}
			//		
			/////This section is Row 3/////
			//
			else 
			{
				hold = r3.charAt(foo);

				if(i==11||i==13)
				{
					foo++;
					foo = foo %5;
				}
				
				else
				{
					//System.out.print("\n");
					//System.out.print("r3 at slot: " +foo);
					//System.out.print(" =    " +hold);
					//System.out.print("\n");
					//System.out.print("\n");
					
					if (Character.isWhitespace(hold))
					{
						Matrix[m] = 0;
						if (m<9){m++;}
						foo ++;
						foo = foo%5;
					}
					else if (m<9)
					{
						Matrix[m] = Character.digit(r3.charAt(foo),9);
						m++;
						foo++;
						foo = foo %5;
					}
				}
				
            }
			//This writes what loop I'm on
			//System.out.print("Loop" +i);
			//System.out.print(", m = " +m);
			//System.out.print("\n");
			//System.out.print("\n"); 
        }
				//fixZeroSlot(Matrix);
                System.out.print("\n");
				printMatrix(Matrix);
				fixZeroSlot(Matrix);
				
		////
		//Use the Loop to set up an array and print out a matrix
		////

		//Scanner get = new Scanner(System.in);
		System.out.print("\n");
		System.out.print("\n");
		System.out.println("Let's make a puzzle!");
		String[] puzzle = new String[9];
		int block;
		boolean working = true;
		for(int i = 0; i<9; i++)
        {
                //System.out.println("Give me slot #" +i);
                //figure out how to make a " :" later at the end.
                //block = get.nextInt();
                block = Matrix[i];
                if ((block == 0)||(block == 9))
                {
                    //System.out.println("ok!");
                    puzzle[i] = " ";

                }
                else if ((block < 9) && (block > 0))
                {
                    puzzle[i] = Integer.toString(Matrix[i]);
                }
                else
                {
                    System.out.print("I'm sorry, slot #" +(i+1));
					System.out.print("Where slot = " +block);
                    System.out.print(" is out of range. ");
                    //i--;
                    working = false;
                    break;
                }
        }
        if (working)
        {
            System.out.println("Thank you for your successful input!");
        }
        else
        {
            System.out.println("Invalid 8-puzzle entered!");
        }
		System.out.println("Inversions = " +invCount(Matrix));
        
        printString(puzzle);
        System.out.print("\n");
        System.out.print("\n");
        if (isSolvable(Matrix))
        {
                System.out.println("This matrix is solvable");
				System.out.println("Inversions = " +invCount(Matrix));

        }
        else
        {
            System.out.println("The given puzzle: ");
            printString(puzzle);
            System.out.println(" is not solvable");
        }
		
		System.out.println("Empty slot is at slot: " +findEmpty(Matrix));

		int empty = findEmpty(Matrix);
		int prevSwitch= -1;
		int opt1,opt2,opt3,opt4;
		opt1 = 99;
		opt2 = 99;
		opt3 = 99;
		opt4 = 99;
		int[] renew = new int[9];
		OldMatrix = Matrix;
		int count = invCount(Matrix);
		int step = 0;
		//renew = Matrix;
		if (isSolvable(Matrix)){
		while (!isSolved(Matrix))  //(step < count)(!isSolved(Matrix))
		{
			step++;
			System.out.println("Empty slot is at: " +empty);
						
			//if empty = 0, check for best option
			//slot 0 only has 2 options...
			if (empty == 0) //this says it is empty at slot 0
			{
				//comparing slot 0 with slot 1
				opt1 = -invCount(Matrix) + invCount(switchSlot(Matrix, empty, 1));
				switchSlot(Matrix, empty, 1);
				//comparing slot 0 with slot 3
				opt2 = -invCount(Matrix) + invCount(switchSlot(Matrix, empty, 3));
				switchSlot(Matrix, empty, 3);

				if ((opt1<=opt2))
				{
					if (prevSwitch != 1)
					{
					  Matrix = switchSlot(Matrix,empty,1);
					//writePuzzle(Matrix);
					// = 1;
					}
					else
						continue;
				}
				else if ((opt2 <= opt1))
				{
					if (prevSwitch != 3)
					{
					  Matrix = switchSlot(Matrix,empty,3);
					//writePuzzle(Matrix);
					// = 3;
					}
					else
						Matrix = switchSlot(Matrix,empty,1);
				}
				empty = findEmpty(Matrix);

			}
			//slot 1 has 3 options
			else if (empty == 1) // this says it is empty at slot 1
			{
				//printMatrix(Matrix);
				opt1 = -invCount(Matrix) + invCount(switchSlot(Matrix, empty, 0));
				switchSlot(Matrix,empty,0);
				
				opt2 = -invCount(Matrix) + invCount(switchSlot(Matrix, empty, 2));
				switchSlot(Matrix,empty,2);

				opt3 = -invCount(Matrix) + invCount(switchSlot(Matrix, empty, 4));
				switchSlot(Matrix,empty,4);
				System.out.print("/n");

				if ((opt1<=opt2) && (opt1 <= opt3))
				{
					if (prevSwitch != 0)
					{
					  Matrix = switchSlot(Matrix,empty,0);
					//writePuzzle(Matrix);
					// 0;
					}
					else 
						continue;
				}
				else if ((opt2 <= opt1) && (opt2 <= opt3))
				{
					if (prevSwitch != 2)
					{
					  Matrix = switchSlot(Matrix,empty,2);
					//writePuzzle(Matrix);
					System.out.print("you chose this one!!!");
					//printString(puzzle);
					// 4;
					}
					//else
						//continue;
				}
				else if ((opt3 <= opt1) && (opt3 <= opt2))
				{
					  Matrix = switchSlot(Matrix,empty,4);
					//writePuzzle(Matrix);
					// 2;
				}
				prevSwitch = 1;
				empty = findEmpty(Matrix);
			}
			else if (empty == 2)
			{
			
				opt1 = -invCount(Matrix) + invCount(switchSlot(Matrix, empty, 1));
				switchSlot(Matrix, empty, 1);
				opt2 = -invCount(Matrix) + invCount(switchSlot(Matrix, empty, 5));
				switchSlot(Matrix, empty, 5);
				System.out.println("opt 1 = " +opt1+ " | opt 2 = " +opt2+ " | opt 3 = " +opt3+ " | opt 4 = " +opt4);
				
				if ((opt1<=opt2))
				{
					if(prevSwitch != 1)
					{
					  Matrix = switchSlot(Matrix,empty,1);
					//writePuzzle(Matrix);
					// 1;
					}
					else
						continue;
				}
				else if ((opt2 <= opt1))
				{
					  Matrix = switchSlot(Matrix,empty,5);
					//writePuzzle(Matrix);
					// 5;
				}
				prevSwitch = 2;
				empty = findEmpty(Matrix);
			}
			else if (empty == 3)
			{
				opt1 = -invCount(Matrix) + invCount(switchSlot(Matrix, empty, 0));
				switchSlot(Matrix, empty, 0);
				opt2 = -invCount(Matrix) + invCount(switchSlot(Matrix, empty, 4));
				switchSlot(Matrix, empty, 4);
				opt3 = -invCount(Matrix) + invCount(switchSlot(Matrix, empty, 6));
				switchSlot(Matrix, empty, 6);
				
				if ((opt1<=opt2) && (opt1 <= opt3))
				{
					  Matrix = switchSlot(Matrix,empty,0);
					//writePuzzle(Matrix);
					// 0;
				}
				else if ((opt2 <= opt1) && (opt2 <= opt3))
				{
					  Matrix = switchSlot(Matrix,empty,4);
					//writePuzzle(Matrix);
					// 4;
				}
				else if ((opt3 <= opt1) && (opt3 <= opt2))
				{
					  Matrix = switchSlot(Matrix,empty,6);
					//writePuzzle(Matrix);
					// 6;
				}
				prevSwitch = 3;
				empty = findEmpty(Matrix);
			}
			else if (empty == 4)
			{
				opt1 = -invCount(Matrix) + invCount(switchSlot(Matrix, empty, 1));
				switchSlot(Matrix, empty, 1);
				opt2 = -invCount(Matrix) + invCount(switchSlot(Matrix, empty, 3));
				switchSlot(Matrix, empty, 3);
				opt3 = -invCount(Matrix) + invCount(switchSlot(Matrix, empty, 5));
				switchSlot(Matrix, empty, 5);
				opt4 = -invCount(Matrix) + invCount(switchSlot(Matrix, empty, 7));
				switchSlot(Matrix, empty, 7);
				
				if ((opt1<=opt2) && (opt1 <= opt3) && (opt1 <= opt4))
				{
					if (prevSwitch != 1)
					{
					  Matrix = switchSlot(Matrix,empty,1);
					//writePuzzle(Matrix);
					// 1;
					}
					else if ((opt2 <= opt3) && (opt2 <= opt4))
					{
						 Matrix = switchSlot(Matrix,empty,3);
					}
					else if ((opt3 <= opt2) && (opt3 <= opt4))
					{
						Matrix = switchSlot(Matrix,empty,5);
					}
					else if ((opt4 <= opt2) && (opt4 <= opt3))
					{
						Matrix = switchSlot(Matrix,empty,7);
					}
				}
				else if ((opt2 <= opt1) && (opt2 <= opt3) && (opt2 <= opt4))
				{
					  Matrix = switchSlot(Matrix,empty,3);
					//writePuzzle(Matrix);
					// 3;
				}
				else if ((opt3 <= opt1) && (opt3 <= opt2) && (opt3 <= opt4))
				{
					  Matrix = switchSlot(Matrix,empty,5);
					//writePuzzle(Matrix);
					// 5;
				}
				else if ((opt4<=opt1) && (opt4 <= opt2) && (opt4 <= opt3))
				{
					  Matrix = switchSlot(Matrix,empty,7);
					//writePuzzle(Matrix);
					// 7;
				}
				prevSwitch = 4;
				empty = findEmpty(Matrix);
			}
			else if (empty == 5)
			{
				opt1 = -invCount(Matrix) + invCount(switchSlot(Matrix, empty, 2));
				switchSlot(Matrix, empty, 2);
				opt2 = -invCount(Matrix) + invCount(switchSlot(Matrix, empty, 4));
				switchSlot(Matrix, empty, 4);
				opt3 = -invCount(Matrix) + invCount(switchSlot(Matrix, empty, 8));
				switchSlot(Matrix, empty, 8);

				
				if ((opt1<=opt2) && (opt1 <= opt3))
				{
					if (prevSwitch != 2)
					{
					  Matrix = switchSlot(Matrix,empty,2);
					//writePuzzle(Matrix);
					// 2;
					}
					else
						continue;
				}
				else if ((opt2 <= opt1) && (opt2 <= opt3))
				{
					if (prevSwitch != 4)
					{
					  Matrix = switchSlot(Matrix,empty,4);
					
					//writePuzzle(Matrix);
					// 4;
					}
					else
						continue;
				}
				else if ((opt3 <= opt1) && (opt3 <= opt2))
				{
					if (prevSwitch != 8)
					{
					  Matrix = switchSlot(Matrix,empty,8);
					
					//writePuzzle(Matrix);
					// 8;
					}
					else if (opt1 <= opt2)
						Matrix = switchSlot(Matrix,empty,2);
					else if (opt2 <= opt1)
						Matrix = switchSlot(Matrix,empty,4);
						
				}
				prevSwitch = 5;
				empty = findEmpty(Matrix);
			}
			else if (empty == 6)
			{
				opt1 = -invCount(Matrix) + invCount(switchSlot(Matrix, empty, 3));
				switchSlot(Matrix, empty, 3);
				opt2 = -invCount(Matrix) + invCount(switchSlot(Matrix, empty, 7));
				switchSlot(Matrix, empty, 7);

				
				if ((opt1<=opt2))
				{
					  Matrix = switchSlot(Matrix,empty,3);
					//writePuzzle(Matrix);
					// 3;
				}
				else if ((opt2 <= opt1))
				{
					  Matrix = switchSlot(Matrix,empty,7);
					//writePuzzle(Matrix);
					// 7;
				}
				prevSwitch = 6;
				empty = findEmpty(Matrix);
			}
			else if (empty == 7)
			{
				opt1 = -invCount(Matrix) + invCount(switchSlot(Matrix, empty, 0));
				switchSlot(Matrix, empty, 0);
				opt2 = -invCount(Matrix) + invCount(switchSlot(Matrix, empty, 6));
				switchSlot(Matrix, empty, 6);
				opt3 = -invCount(Matrix) + invCount(switchSlot(Matrix, empty, 8));
				switchSlot(Matrix, empty, 8);
				
				if ((opt1<=opt2) && (opt1 <= opt3))
				{
					if (prevSwitch != 0)
					{
					  Matrix = switchSlot(Matrix,empty,0);
					//writePuzzle(Matrix);
					// 0;
					}
					else
						continue;
				}
				else if ((opt2 <= opt1) && (opt2 <= opt3))
				{
					if (prevSwitch != 6)
					{
					  Matrix = switchSlot(Matrix,empty,6);
					//writePuzzle(Matrix);
					// 6;
					}
					else
						continue;
				}
				else if ((opt3 <= opt1) && (opt3 <= opt2))
				{
					if (prevSwitch != 5)
					{
					  Matrix = switchSlot(Matrix,empty,8);
					//writePuzzle(Matrix);
					// 8;
					}
					else if (opt1 <= opt2)
						Matrix = switchSlot(Matrix,empty,0);
					else if (opt2 <= opt1)
						Matrix = switchSlot(Matrix,empty,6);
				}
				prevSwitch = 7;
				empty = findEmpty(Matrix);
			}
			else if (empty == 8)
			{
				opt1 = -invCount(Matrix) + invCount(switchSlot(Matrix, empty, 5));
				switchSlot(Matrix, empty, 5);
				opt2 = -invCount(Matrix) + invCount(switchSlot(Matrix, empty, 7));
				switchSlot(Matrix, empty, 7);
				
				if ((opt1<=opt2))
				{
					if (prevSwitch != 5)
					{
					  Matrix = switchSlot(Matrix,empty,5);
					//writePuzzle(Matrix);
					// 5;
					}
					else
						continue;
				}
				else if ((opt2 <= opt1))
				{
					if (prevSwitch != 7)
					{
					  Matrix = switchSlot(Matrix,empty,7);
					//writePuzzle(Matrix);
					// 7;
					}
					else
						//continue;
						Matrix = switchSlot(Matrix,empty,5);
				}
				prevSwitch = 8;
				empty = findEmpty(Matrix);
			}
			System.out.println("opt 1 = " +opt1+ " | opt 2 = " +opt2+ " | opt 3 = " +opt3+ " | opt 4 = " +opt4);
			writePuzzle(Matrix);
		}
		}
		//else{}
       // }
	}
	

}



////Make an Array for 9 slots
////Ask user for a random set of non repeating numbers between 1 and 8 with one slot being a space (or 0)

//First check to see if the puzzle is solvable

//If not solvable tell the user that it isn't solvable
//If solvable execute A* heuristics
