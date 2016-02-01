import java.lang.Math; 

public class Deepest_pit{ 
	public static int solution(int[] A){
		int deep_pit = 0;
		int P = 0, Q = -2, R = -1; 
		int N = A.length; 
		int count = 1; 

		while(count < N){
			//If Q isn't set yet, we dont compute deepest pit even if sequence starts to decrease
			if(Q < 0){
				if(A[count] >= A[count-1] && P == count-1){
					P = count; 
				}else if(A[count] > A[count-1]){
					Q = count-1; 
				}
			}
			//If Q is set, then we can compute deepest pit when sequence starts to decrease again
			if(Q > 0){
				if(A[count] <= A[count-1] || (A[count] > A[count-1] && count == N -1)){
					R = count-1;
					if(count == N-1){
						R = count; 
					} 
					deep_pit = Math.max(Math.min(A[P]-A[Q], A[R]-A[Q]), deep_pit);
					Q = -1; 
					P = count-1; 
				}
			}
			count++;
		}
		//Return -1 if Q's value never changed, aka no pits found
		if(Q == -2){
			deep_pit = -1; 
		}
		return deep_pit; 
	}
	public static void main(String args[]){
		int[] A = {1,2,3,4,3,2,1,-2,3,4,5,6,77,7,7,7,5};
		int answer = solution(A);
		System.out.println("My answer: " + answer);
	}
}