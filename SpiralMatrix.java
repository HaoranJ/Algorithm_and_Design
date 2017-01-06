import java.util.*;

public class SpiralMatrix {
	//time = O(nm)
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> list = new ArrayList<>();
		int m = matrix.length;
		if (m == 0) {
			return list;
		}
		int n = matrix[0].length;
		int left = 0;
		int right = n-1;
		int top = 0;
		int bot = m-1;
		while (left <= right && top <= bot) {
			for (int j = left; j <= right; j++) {
				list.add(matrix[top][j]);
			}
			for (int j = top+1; j <= bot; j++) {
				list.add(matrix[j][right]);
			}
			if (bot-top > 0) {
				for (int j = right-1; j >= left; j--) {
					list.add(matrix[bot][j]);
				}
			}
			if (right-left > 0) {
				for (int j = bot-1; j > top; j--) {
					list.add(matrix[j][left]);
				}
			}
			left++;
			right--;
			top++;
			bot--;
		}
		return list;
	}

	public int[][] generateMatrix(int n) {
		assert n > 0;
		int[][] mat = new int[n][n];
		int val = 1;
		for (int i = 0; i < n; i++) {
			mat[0][i] = val++;
		}
		int step = n-1;
		int x = 0, y = n-1;
		while (step > 0) {
			for (int i = 0; i < step; i++) {
				mat[++x][y] = val++;
			}
			for (int i = 0; i < step; i++) {
				mat[x][--y] = val++;
			}
			step--;
			if(step == 0) { break; }
			for (int i = 0; i < step; i++) {
				mat[--x][y] = val++;
			}
			for (int i = 0; i < step; i++) {
				mat[x][++y] = val++;
			}
			step--;
		}
		return mat;
	}
}
