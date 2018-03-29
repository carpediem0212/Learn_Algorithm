package kr.carpediem0212.algorithm.algospot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TRIPATHCNT {
	private int[][] triangle;
	private int heightOfTriangle;
	private int cache[][];
	private int distance[][];
	
	public TRIPATHCNT(int[][] _triangle, int _heightOfTriangle) {
		this.triangle = _triangle;
		this.heightOfTriangle = _heightOfTriangle;
		this.cache = new int[heightOfTriangle][heightOfTriangle];
		this.distance = new int[heightOfTriangle][heightOfTriangle];
	}

	public int getMaxDistance(int y, int x) {
		if(y == (heightOfTriangle - 1)) {
			cache[y][x] = triangle[y][x]; 
			return cache[y][x];
		}
		
		if(cache[y][x] != 0) {
			return cache[y][x]; 
		}
		
		int ret = triangle[y][x] + Integer.max(getMaxDistance(y + 1, x), getMaxDistance(y + 1, x + 1));
		
		cache[y][x] = ret;
		
		return ret;
	}
	
	public int countPath(int y, int x) {
		if(y == (heightOfTriangle - 1)) {
			return 1;
		}
		
		int ret = distance[y][x];
		
		if(ret != 0) {
			return distance[y][x];
		}
		
		if(cache[y + 1][x] >= cache[y + 1][x + 1]) {
			ret += countPath(y + 1, x);
		}
		
		if(cache[y + 1][x] <= cache[y + 1][x + 1]) {
			ret += countPath(y + 1, x + 1);
		}
		
		distance[y][x] = ret;
		
		return ret;
	}
	
	public int max(int num1, int num2) {
		int ret = (num1 > num2) ? num1 : num2;
		
		return ret;
	}
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			int numOfTestCase = Integer.parseInt(br.readLine().trim());

			while ((numOfTestCase--) > 0) {
				int heightOfTriangle = Integer.parseInt(br.readLine().trim());

				int[][] triangle = new int[heightOfTriangle][heightOfTriangle];

				for (int i = 0; i < heightOfTriangle; i++) {
					String[] inputDatas = br.readLine().trim().split("\\s+");

					int[] rowData = new int[inputDatas.length];
					for (int j = 0; j < inputDatas.length; j++) {
						rowData[j] = Integer.parseInt(inputDatas[j]);
					}

					triangle[i] = rowData;
				}

				TRIPATHCNT t = new TRIPATHCNT(triangle, heightOfTriangle);
				t.getMaxDistance(0, 0);
				//System.out.println(t.getMaxDistance(0, 0));
				System.out.println(t.countPath(0, 0));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
