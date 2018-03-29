package kr.carpediem0212.algorithm.algospot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SNAIL {
	private int depth;
	private int day;
	private int[][] cache;
	
	public SNAIL(int depth, int day) {
		this.depth = depth;
		this.day = day;
		this.cache = new int[day][2 * day];
		
		System.out.println(clime(0, 0));
		//System.out.println(depth + "/" + day);
	}
	
	public double clime(int today, int distance) {
		if(today == day) {
			
			return (distance >= depth) ? 1 : 0;
		}

		if(cache[today][distance] != 0) {
			return cache[today][distance];
		}
		
		double ret = 0.75 * clime(today + 1, distance + 1) + 0.25 * clime(today + 1, distance + 2);
		
		return ret;
	}

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			int numOfTestCase = Integer.parseInt(br.readLine().trim());

			while ((numOfTestCase--) > 0) {
				String[] inputDatas = br.readLine().trim().split("\\s+");
				int depth = Integer.parseInt(inputDatas[0]);
				int day = Integer.parseInt(inputDatas[1]);
				
				SNAIL s = new SNAIL(depth, day);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
