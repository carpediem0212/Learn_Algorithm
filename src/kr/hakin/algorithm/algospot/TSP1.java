package kr.hakin.algorithm.algospot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 *  Written By : carpediem0212@google.com
 */
public class TSP1 {
	private double[][] distanceMatrix;	
	private boolean[] isVisit;			// For checking city visit 
	private int cityNum;                // For checking current visited city
	private double minDistance;
	
	public TSP1(double[][] distanceMatrix, int cityNum) {
		this.distanceMatrix = distanceMatrix;
		this.cityNum = cityNum;
		this.isVisit = new boolean[cityNum];
	}
	
	public void tsp() {
		minDistance = Double.MAX_VALUE;
		
		for(int i = 0; i < cityNum; i++) {
			isVisit[i] = true;
			getDistanceOfPath(i, 0, 1);
			isVisit[i] = false;
		}
		
		System.out.println(minDistance);
	}
	
	public void getDistanceOfPath(int to, double totalOfDistance ,int numOfVistedCity) {
		// Base Case : if Current City equal Last City
		if(numOfVistedCity == cityNum) {
			if(minDistance > totalOfDistance) {
				minDistance = totalOfDistance;
				return;
			}
		}
		
		for(int i = 0; i < cityNum; i++) {
			//if already visit city [i], pass 
			if(isVisit[i]){
				continue;
			}
			
			isVisit[i] = true;
			// Recursive
			getDistanceOfPath(i, totalOfDistance + distanceMatrix[to][i], numOfVistedCity + 1);
			isVisit[i] = false;
		}
	}
	
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			int testCaseNum = Integer.parseInt(br.readLine().trim());

			if ((testCaseNum < 0) || (testCaseNum > 50)) {
				return;
			}

			while ((testCaseNum--) > 0) {
				int cityNum = Integer.parseInt(br.readLine().trim());
				double[][] distanceMatrix = new double[cityNum][cityNum];

				if((cityNum < 3) || (cityNum > 8)) {
					return;
				}
				
				for (int i = 0; i < cityNum; i++) {
					String[] inputData = br.readLine().trim().split("  ");
					
					// Create distance matrix
					for(int j = 0; j < cityNum; j++) {
						distanceMatrix[i][j] = Double.parseDouble(inputData[j]);
					}
				}
				
				TSP1 t = new TSP1(distanceMatrix, cityNum);
				t.tsp();
				
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
