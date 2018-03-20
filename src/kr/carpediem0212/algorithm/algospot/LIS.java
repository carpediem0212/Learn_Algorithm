package kr.carpediem0212.algorithm.algospot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 *  Written By : carpediem0212@google.com
 */
public class LIS {
	private int[] elements;
	private int lengthOfElements;
	private int[] cache;
	
	public LIS(int[] _elements) {
		this.elements = _elements;
		this.lengthOfElements = elements.length;
		this.cache = new int[lengthOfElements];
 	}
	
	public int maxSequence() {
		int max = 0;
		
		for(int i = 0; i < lengthOfElements; i++) {
			int ref = getLengthOfSequence(i);
			
			if(ref > max) {
				max = ref;
			}
		}
		
		return max;
	}
	
	public int getLengthOfSequence(int index) {
		for(int i = (index + 1); i < lengthOfElements; i++) {
			int length = 1;
			
			if(elements[index] < elements[i]) {
				if(cache[i] != 0) {
					length += cache[i];	//이미 해당 숫자의 순열이 구해졌다면 별도 재귀호출을 하지않음.
				} else {
					length += getLengthOfSequence(i);	//캐쉬에 저장된 값이 없다면 재귀 호출을 통해 증가 수열 계산
				}
				
				if(length > cache[index]) {
					cache[index] = length;	// 가장 큰 부분 순열에 자신을 포함한 길이를 저장
				}
			}
		}
		
		//해당 인덱스를 기준으로 증가 수열 계산을 했는지 여부 판단을 위해 1로 변경
		//0일 경우, 아직 계산하지 않은 인덱스
		if(cache[index] == 0) {
			cache[index] = 1;
		}

		return cache[index];
	}
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int numOfTestCase = Integer.parseInt(br.readLine().trim());
			
			if(numOfTestCase > 50) {
				return;
			}
			
			while((numOfTestCase--) > 0) {
				int numOfElements = Integer.parseInt(br.readLine().trim());
				
				String[] inputDatas = br.readLine().trim().split(" ");
				int[] elements = new int[numOfElements];
				
				for(int i = 0; i < numOfElements; i++) {
					elements[i] = Integer.parseInt(inputDatas[i]);
				}
				
				LIS l = new LIS(elements);
				System.out.println(l.maxSequence());
			}
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
