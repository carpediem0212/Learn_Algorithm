package kr.carpediem0212.algorithm.algospot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class JUMPGAME {
	private int[][] board;
	private int sizeOfBoard;
	private int cache[][];
	public JUMPGAME(int[][] _board, int _sizeOfBoard) {
		this.board = _board;
		this.sizeOfBoard = _sizeOfBoard;
		this.cache = new int[sizeOfBoard][sizeOfBoard];
		for(int i = 0; i < board.length; i++) {
			Arrays.fill(cache[i], -1);
		}
	}
	
	public int jump(int y, int x) {
		//Base-Case 1
		if((y >= sizeOfBoard) || (x >= sizeOfBoard)) {
			return 0;
		}
		
		//Base-Case 2
		if((y == (sizeOfBoard - 1)) && (x == (sizeOfBoard - 1))) {
			return 1;
		}
		
		//Memorization
		if(cache[y][x] != -1) {
			return cache[y][x];
		}
		
		int sizeOfJump = board[y][x];
		
		if((jump(y + sizeOfJump, x) > 0) || (jump(y, x + sizeOfJump) > 0)) {
			cache[y][x] = 1;
		} else {
			cache[y][x] = 0;
		}
		
		return cache[y][x];
	}
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int numOfTestCase = Integer.parseInt(br.readLine().trim());
			
			if(numOfTestCase > 50) {
				return;
			}
			
			while((numOfTestCase--) > 0) {
				int sizeOfBoard = Integer.parseInt(br.readLine().trim());
				if((sizeOfBoard < 2) || (sizeOfBoard > 100)) {
					return;
				}
				
				int[][] board = new int[sizeOfBoard][sizeOfBoard];
				
				for(int i = 0; i < sizeOfBoard; i++) {
					String[] inputDatas = br.readLine().trim().split(" ");
					
					for(int j = 0; j < sizeOfBoard; j++) {
						board[i][j] = Integer.parseInt(inputDatas[j]);
					}
				}
				
				JUMPGAME j = new JUMPGAME(board, sizeOfBoard);
				int result = j.jump(0, 0);
				
				System.out.println((result > 0) ? "YES" : "NO");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
