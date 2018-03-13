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
	
	public void search() {
		int max = 0;
		
		for(int i = 0; i < lengthOfElements; i++) {
			int ref = getPermutation(i);
			if(ref > max) {
				max = ref;
			}
		}
		
		System.out.println(max);
	}
	
	public int getPermutation(int index) {
		for(int i = (index + 1); i < lengthOfElements; i++) {
			int ref = 1;
			
			if(elements[index] < elements[i]) {
				if(cache[i] != 0) {
					ref += cache[i];
				} else {
					ref += getPermutation(i);
				}
				
				if(ref > cache[index]) {
					cache[index] = ref;
				}
			}
		}
		
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
				l.search();
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
