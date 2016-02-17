public class CountBoundedSlices{ 
	public static int solution(int K, int[] A, int N){
		int numSlices = N; 
		int index_lastSlice = -1; 
		int current = 0, i = 1;  
		int min = 1, max = 0;  
		boolean countedSlice = false; 

		if(A[0] < A[1]){
			min = 0; 
			max = 1; 
		}
		//Speical case
		if(N == 0){
			return 0; 
		}
		while(i < N || !countedSlice){
			if(A[max] - A[min] > K || i == N){
				//Calculate sub-slices of previous slice (A[current] to A[i-1]) that satisfied condition A[max] - A[min] <= K
				numSlices += ((i-current)*(i-current-1))/2;

				//Remove slices that were already counted from previous slices to avoid counting them twice
				int overCount = 0;
				if (index_lastSlice > current){
					overCount = index_lastSlice - current + 1;  
					numSlices -= ((overCount)*(overCount-1))/2;
				}
				index_lastSlice = i-1; 
				
				//Move current 
				current++; 
				if(min < current){
					min = current; 
				}else if(max < current){
					max = current; 
				}
				countedSlice = true; 
			}else{
				i++;
				countedSlice = false; 
				if(i < N){
					if(A[i] > A[max]){
						max = i; 
					}else if(A[min] > A[i]){
						min = i; 
					}
				}
			}
		}
		return numSlices; 
	}
	public static void main(String args[]){
		int[] A = {3,5,7,6,3};
		// int[] A = {3,3,5,7,6,3,4,4,5}; //gives 21
		// int[] A = {3,3,3}; //gives 6
		int K = 2; 
		int A_length = A.length; 
		int answer = solution(K,A,A_length);
		System.out.println("My answer: " + answer);
	}
}