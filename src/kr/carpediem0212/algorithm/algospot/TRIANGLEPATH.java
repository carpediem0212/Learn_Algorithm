package kr.carpediem0212.algorithm.algospot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TRIANGLEPATH {
	private int[][] triangle;
	private int heightOfTriangle;
	private int cache[][];

	public TRIANGLEPATH(int[][] _triangle, int _heightOfTriangle) {
		this.triangle = _triangle;
		this.heightOfTriangle = _heightOfTriangle;
		this.cache = new int[heightOfTriangle][heightOfTriangle];
	}

	public int getMaxDistance(int y, int x) {
		if (y == (heightOfTriangle - 1)) {
			return triangle[y][x];
		}

		if (cache[y][x] != 0) {
			return cache[y][x];
		}

		int path1 = getMaxDistance(y + 1, x);
		int path2 = getMaxDistance(y + 1, x + 1);

		int max = triangle[y][x] + ((path1 > path2) ? path1 : path2);
		cache[y][x] = max;

		return max;
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

				TRIANGLEPATH t = new TRIANGLEPATH(triangle, heightOfTriangle);
				System.out.println(t.getMaxDistance(0, 0));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
