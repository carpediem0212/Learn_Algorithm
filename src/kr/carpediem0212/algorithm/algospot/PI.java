package kr.carpediem0212.algorithm.algospot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PI {
	private int[] numbers;
	private int[] cache;
	private int sizeOfNumbers;
	
	public PI(int[] _numbers) {
		this.numbers = _numbers;
		this.sizeOfNumbers = numbers.length;
		this.cache = new int[sizeOfNumbers];
		Arrays.fill(cache, -1);
		
		System.out.println(getMaxLevel(0));
	}
	
	public int getMaxLevel(int begin) {
		// Base-Case
		if(begin == sizeOfNumbers) {
			return 0;
		}
		
		//Memorization
		if(cache[begin] != -1) {
			return cache[begin];
		}
		
		cache[begin] = 100000;
		
		for(int i = 3; i <= 5; i++) {
			if((begin + i) <= sizeOfNumbers) {
				cache[begin] = Integer.min(cache[begin], getMaxLevel(begin + i) + classify(begin, begin + i - 1));
			}
		}
		
		return cache[begin];
	}
	
	public int classify(int begin, int end) {
		//모든 숫자가 같을 때 (예: 333, 5555) 난이도: 1
		boolean isEquals = true;

		for(int i = (begin + 1); i <= end; i++) {
			if(numbers[begin] != numbers[i]) {
				isEquals = false;
			}
		}
		
		if(isEquals) {
			return 1;
		}
		
		boolean isProgressive = true;
		for(int i = begin; i < end; i++) {
			if((numbers[i + 1] - numbers[i]) != (numbers[begin + 1] - numbers[begin])) {
				isProgressive = false;
			}
		}
		
		//숫자가 1씩 단조 증가하거나 단조 감소할 때 (예: 23456, 3210) 난이도: 2
		if(isProgressive && (Math.abs(numbers[begin + 1] - numbers[begin]) == 1)) {
			return 2;
		}
		//숫자가 등차 수열을 이룰 때 (예: 147, 8642) 난이도: 5
		if(isProgressive) {
			return 5;
		}
		// 두 개의 숫자가 번갈아 가며 출현할 때 (예: 323, 54545) 난이도: 4
		for(int i = begin; i < end; i++) {
			if((numbers[i + 1] - numbers[i]) != (numbers[begin + 1] - numbers[begin])) {
				isProgressive = false;
			}
		}
		
		boolean isSwitching = true;
		for(int i = (begin + 2); i < end; i++) {
			if(numbers[i] != numbers[begin + (i % 2)]) {
				isSwitching = false;
			}
		}
		
		if(isSwitching) {
			return 4;
		}
		// 그 외의 경우 난이도: 10
		return 10;
		/*boolean isEquals = true;
		boolean isProgressive = true;
		boolean isAlternative = true;

		for(int i = begin; i <= end; i++) {
			if(numbers[begin] != numbers[i]) {
				isEquals = false;
			}
			

			if(numbers[i] != numbers[begin + (i % 2)]) {
				isAlternative = false;
			}
		}
		
		for(int i = begin; i < end; i++) {
			if((numbers[i + 1] - numbers[i]) != (numbers[begin + 1] - numbers[begin])) {
				isProgressive = false;
			}
		}
		
		//모든 숫자가 같을 때 (예: 333, 5555) 난이도: 1
		if(isEquals) {
			return 1;
		}
		
		//숫자가 1씩 단조 증가하거나 단조 감소할 때 (예: 23456, 3210) 난이도: 2
		if(isProgressive && (Math.abs(numbers[begin + 1] - numbers[begin]) == 1)) {
			return 2;
		}
		
		//두 개의 숫자가 번갈아 가며 출현할 때 (예: 323, 54545) 난이도: 4
		if(isAlternative) {
			return 4;
		}
		
		//숫자가 등차 수열을 이룰 때 (예: 147, 8642) 난이도: 5
		if(isProgressive) {
			return 5;
		}

		//그 외의 경우 난이도: 10
		return 10;*/
	}
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int numOfTestCase = Integer.parseInt(br.readLine().trim());
			
			while((numOfTestCase--) > 0) {
				String inputDatas = br.readLine().trim();
				
				int[] numbers = new int[inputDatas.length()];
				for(int i = 0; i < inputDatas.length(); i++) {
					numbers[i] = (int) inputDatas.charAt(i) - 48; 
				}
				
				PI p = new PI(numbers);
 			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
