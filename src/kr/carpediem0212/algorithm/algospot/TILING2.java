package kr.carpediem0212.algorithm.algospot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TILING2 {
	private boolean[][] board;
	private int width;
	private int cnt;
	private int[][] tileTypes = {
			{0, 1},
			{1, 0}
	};
	
	
	
	public TILING2(int width) {
		this.width = width;
		board = new boolean[2][width];
		cnt = 0;
		//System.out.println(Arrays.toString(board[0]));
		//System.out.println(Arrays.toString(board[1]));
		tiling();
		System.out.println(cnt);
	}
	
	public void tiling() {
		int x = 0;
		int y = 0;
		
		boolean isContinue = true;
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < width; j++) {
				if(board[i][j] == false) {
					isContinue = false;
					y = i;
					x = j;
					break;
				}
			}
			
			if(!isContinue) {
				break;
			}
		}
		
		if(isContinue) {
			cnt++;
			return;
		}

		for(int i = 0; i < 2; i++) {
			if(drawTile(i, y, x, true)) {
				tiling();
				drawTile(i, y, x, false);	
			}
		}
	}
	
	public boolean drawTile(int type, int y, int x, boolean set) {
		if(y >= 2 || x >= width || (y + tileTypes[type][0]) >= 2 || (x + tileTypes[type][1]) >= width) {
			return false;
		}
		
		board[y][x] = set;
		board[y + tileTypes[type][0]][x + tileTypes[type][1]] = set;
		
		return true;
	}
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int numOfTestCase = Integer.parseInt(br.readLine().trim());
			
			while((numOfTestCase--) > 0) {
				int width = Integer.parseInt(br.readLine().trim());
				TILING2 t = new TILING2(width);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
