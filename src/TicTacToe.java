import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	
	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
	private static boolean game = true;
	
	public static void main(String[] args) {
		
		char [][] gameBoard = 
			{
				{'1','|','2','|','3'},
				{'-','+','-','+','-'},
				{'4','|','5','|','6'},
				{'-','+','-','+','-'},
				{'7','|','8','|','9'}
			};
		printGameBoard(gameBoard);
		
		
		while(game == true) {
			Scanner scan = new Scanner(System.in);
			
			System.out.println("Enter your placement (1-9):");
			int playerPos = scan.nextInt();
			
			
			while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPositions)) {
				System.out.println("Position "+playerPos+" taken! Enter a correct Position");
				playerPos = scan.nextInt();
			}
			
			placePiece(gameBoard,playerPos,"player");
			
			Random rand = new Random();
			int cpuPos = rand.nextInt(9)+1;
			
			while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPositions)) {
				System.out.println("the cpu tried to take your spot at "+cpuPos);
				cpuPos = rand.nextInt(9) + 1;
			}
			
			placePiece(gameBoard,cpuPos,"cpu");
			
			printGameBoard(gameBoard);
			
			String result = checkWinner();
			System.out.println(result);
		}
	}
	
	public static void printGameBoard(char[][] gameBoard) {
		for(char[] row: gameBoard) 
		{
			for(char c : row) 
			{
				System.out.print(c);
			}
			System.out.println();
		}
		
	}
	
	public static void placePiece(char[][] gameBoard, int pos, String user) {
		
		char symbol = ' ';
		if(user.equals("player")) {
			symbol = 'X';
			playerPositions.add(pos);
		}else if(user.equals("cpu")) {
			symbol = 'O';
			cpuPositions.add(pos);
		}
		switch(pos) {
			case 1:
				gameBoard[0][0] = symbol;
				break;
			case 2:
				gameBoard[0][2] = symbol;
				break;
			case 3:
				gameBoard[0][4] = symbol;
				break;
			case 4:
				gameBoard[2][0] = symbol;
				break;
			case 5:
				gameBoard[2][2] = symbol;
				break;
			case 6:
				gameBoard[2][4] = symbol;
				break;
			case 7:
				gameBoard[4][0] = symbol;
				break;
			case 8:
				gameBoard[4][2] = symbol;
				break;
			case 9:
				gameBoard[4][4] = symbol;
				break;
			default:
				break;
		}
		
	}
	
	public static String checkWinner() {
		
		List topRow = Arrays.asList(1,2,3);
		List midRow = Arrays.asList(4,5,6);
		List btmRow = Arrays.asList(7,8,9);
		
		List topCol = Arrays.asList(1,4,7);
		List midCol = Arrays.asList(2,5,8);
		List btmCol = Arrays.asList(3,6,9);
		
		List lDiag = Arrays.asList(1,5,9);
		List rDiag = Arrays.asList(3,5,7);
		
		List<List> winning = new ArrayList<List>();
		winning.add(topRow);
		winning.add(midRow);
		winning.add(btmRow);
		winning.add(topCol);
		winning.add(midCol);
		winning.add(btmCol);
		winning.add(lDiag);
		winning.add(rDiag);
		
		for(List l : winning) {
			if(playerPositions.containsAll(l)) {
				gameOver();
				return "Contratulations you won!";
			}else if(cpuPositions.containsAll(l)) {
				gameOver();
				return "CPU wins! Sorry :(";
			}else if(playerPositions.size() + cpuPositions.size()==9){
				return "TIE";
			}
		}
		
		return "";
	}
	public static void gameOver() {
		game = false;
	}

}
