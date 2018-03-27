package kr.carpediem0212.algorithm.algospot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class QUANTIZE {
	private int[] numbers;
	private int size;
	private int numOfQuantization;
	private int[] partialSum;	// 부분합
	private int[] partialSumSquare;	// 부분합 Square
	
	public QUANTIZE(int[] _numbers, int _numOfQuantization) {
		this.numbers = _numbers;
		this.numOfQuantization = _numOfQuantization;
		this.size = _numbers.length;
		this.partialSum = new int[size];
		this.partialSumSquare = new int[size];
		// 1. 정렬
		Arrays.sort(numbers);
		// 2. 부분합 및 부분합 Square계산 
		calculatePartialSum();
		// 3. 양자화 
		System.out.println(quantize(0, numOfQuantization));
		//System.out.println(Arrays.toString(partialSum));
		//System.out.println(Arrays.toString(partialSumSquare));
		//System.out.println(Arrays.toString(numbers));
		//System.out.println(Arrays.toString(numbers));
	}
	
	public int quantize(int from, int parts) {
		if(from == size) {
			return 0;
		}
		
		if(parts == 0) {
			return Integer.MAX_VALUE;
		}
		
		int ret = 999999999;
				
		//int maxPartSize = (size - from);
		for(int i = 1; (from + i) < size; i++) {
			ret = Integer.min(ret, minError(from, from + i - 1) + quantize(from + i, parts - 1));
		}
		
		return ret;
	}
	
	public int minError(int begin, int end) {
		int sum = partialSum[begin] - ((begin == 0) ? 0 : partialSum[begin - 1]);
		int squareSum = partialSumSquare[begin] - ((begin == 0) ? 0 : partialSumSquare[begin - 1]);
		
		int m = Math.round(sum / (end - begin + 1));
		
		int ret = squareSum - (2 * m * sum) + (m * m * (end - begin + 1));
		System.out.println(ret);
		return ret;
	}
	
	public void calculatePartialSum () {
		partialSum[0] = numbers[0];
		partialSumSquare[0] = numbers[0] * numbers[0];
		
		for (int i = 1; i < size; i++) {
			partialSum[i] = partialSum[i - 1] + numbers[i];
			partialSumSquare[i] = partialSumSquare[i - 1] + (numbers[i] * numbers[i]);
		}
	}

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int numOfTestCase = Integer.parseInt(br.readLine().trim());
			
			if(numOfTestCase > 50) {
				return;
			}
			
			while((numOfTestCase--) > 0) {
				String[] inputConditions = br.readLine().trim().split("\\s+");
				
				int sizeOfNumbers = Integer.parseInt(inputConditions[0]);
				int numOfQuantization = Integer.parseInt(inputConditions[0]);
				
				String[] inputNumbers = br.readLine().trim().split("\\s+");
				int[] numbers = new int[sizeOfNumbers];
				
				for(int i = 0; i < sizeOfNumbers; i++) {
					numbers[i] = Integer.parseInt(inputNumbers[i]);
				}
				
				QUANTIZE q = new QUANTIZE(numbers, numOfQuantization);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
