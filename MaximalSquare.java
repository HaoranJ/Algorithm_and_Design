import java.util.*;

public class MaximalSquare {
  /*
  LeetCode - 221
  DP, time = O(n^2), space = O(n^2)
  */
//   Well, this problem desires for the use of dynamic programming. They key to any DP problem is to come up with the state equation. In this problem, we define the state to be the maximal size of the square that can be achieved at point (i, j), denoted as P[i][j]. Remember that we use size instead of square as the state (square = size^2).
//
// Now let's try to come up with the formula for P[i][j].
//
// First, it is obvious that for the topmost row (i = 0) and the leftmost column (j = 0), P[i][j] = matrix[i][j]. This is easily understood. Let's suppose that the topmost row of matrix is like [1, 0, 0, 1]. Then we can immediately know that the first and last point can be a square of size 1 while the two middle points cannot make any square, giving a size of 0. Thus, P = [1, 0, 0, 1], which is the same as matrix. The case is similar for the leftmost column. Till now, the boundary conditions of this DP problem are solved.
//
// Let's move to the more general case for P[i][j] in which i > 0 and j > 0. First of all, let's see another simple case in which matrix[i][j] = 0. It is obvious that P[i][j] = 0 too. Why? Well, since matrix[i][j] = 0, no square will contain matrix[i][j]. According to our definition of P[i][j], P[i][j] is also 0.
//
// Now we are almost done. The only unsolved case is matrix[i][j] = 1. Let's see an example.
//
// Suppose matrix = [[0, 1], [1, 1]], it is obvious that P[0][0] = 0, P[0][1] = P[1][0] = 1, what about P[1][1]? Well, to give a square of size larger than 1 in P[1][1], all of its three neighbors (left, up, left-up) should be non-zero, right? In this case, the left-up neighbor P[0][0] = 0, so P[1][1] can only be 1, which means that it contains the square of itself.
//
// Now you are near the solution. In fact, P[i][j] = min(P[i - 1][j], P[i][j - 1], P[i - 1][j - 1]) + 1 in this case.
//
// Taking all these together, we have the following state equations.
//
// P[0][j] = matrix[0][j] (topmost row);
// P[i][0] = matrix[i][0] (leftmost column);
// For i > 0 and j > 0: if matrix[i][j] = 0, P[i][j] = 0; if matrix[i][j] = 1, P[i][j] = min(P[i - 1][j], P[i][j - 1], P[i - 1][j - 1]) + 1.
// Putting them into codes, and maintain a variable maxsize to record the maximum size of the square we have seen, we have the following (unoptimized) solution.
//
// int maximalSquare(vector<vector<char>>& matrix) {
//     int m = matrix.size();
//     if (!m) return 0;
//     int n = matrix[0].size();
//     vector<vector<int> > size(m, vector<int>(n, 0));
//     int maxsize = 0;
//     for (int j = 0; j < n; j++) {
//         size[0][j] = matrix[0][j] - '0';
//         maxsize = max(maxsize, size[0][j]);
//     }
//     for (int i = 1; i < m; i++) {
//         size[i][0] = matrix[i][0] - '0';
//         maxsize = max(maxsize, size[i][0]);
//     }
//     for (int i = 1; i < m; i++) {
//         for (int j = 1; j < n; j++) {
//             if (matrix[i][j] == '1') {
//                 size[i][j] = min(size[i - 1][j - 1], min(size[i - 1][j], size[i][j - 1])) + 1;
//                 maxsize = max(maxsize, size[i][j]);
//             }
//         }
//     }
//     return maxsize * maxsize;
// }
// Now let's try to optimize the above solution. As can be seen, each time when we update size[i][j], we only need size[i][j - 1], size[i - 1][j - 1] (at the previous left column) and size[i - 1][j] (at the current column). So we do not need to maintain the full m*n matrix. In fact, keeping two columns is enough. Now we have the following optimized solution.
//
// int maximalSquare(vector<vector<char>>& matrix) {
// 	int m = matrix.size();
// 	if (!m) return 0;
// 	int n = matrix[0].size();
// 	vector<int> pre(m, 0);
// 	vector<int> cur(m, 0);
// 	int maxsize = 0;
// 	for (int i = 0; i < m; i++) {
// 		pre[i] = matrix[i][0] - '0';
// 		maxsize = max(maxsize, pre[i]);
// 	}
// 	for (int j = 1; j < n; j++) {
// 		cur[0] = matrix[0][j] - '0';
// 		maxsize = max(maxsize, cur[0]);
// 		for (int i = 1; i < m; i++) {
// 			if (matrix[i][j] == '1') {
// 				cur[i] = min(cur[i - 1], min(pre[i - 1], pre[i])) + 1;
// 				maxsize = max(maxsize, cur[i]);
// 			}
// 		}
// 		swap(pre, cur);
// 		fill(cur.begin(), cur.end(), 0);
// 	}
// 	return maxsize * maxsize;
// }
// Now you see the solution is finished? In fact, it can still be optimized! In fact, we need not maintain two vectors and one is enough. If you want to explore this idea, please refer to the answers provided by @stellari below. Moreover, in the code above, we distinguish between the 0-th row and other rows since the 0-th row has no row above it. In fact, we can make all the m rows the same by padding a 0 row on the top (in the following code, we pad a 0 on top of dp). Finally, we will have the following short code :) If you find it hard to understand, try to run it using your pen and paper and notice how it realizes what the two-vector solution does using only one vector.
//
// int maximalSquare(vector<vector<char>>& matrix) {
//     if (matrix.empty()) return 0;
//     int m = matrix.size(), n = matrix[0].size();
//     vector<int> dp(m + 1, 0);
//     int maxsize = 0, pre = 0;
//     for (int j = 0; j < n; j++) {
//         for (int i = 1; i <= m; i++) {
//             int temp = dp[i];
//             if (matrix[i - 1][j] == '1') {
//                 dp[i] = min(dp[i], min(dp[i - 1], pre)) + 1;
//                 maxsize = max(maxsize, dp[i]);
//             }
//             else dp[i] = 0;
//             pre = temp;
//         }
//     }
//     return maxsize * maxsize;
// }
  public int maximalSquare(char[][] matrix) {
    assert matrix != null;
    int rows = matrix.length;
    if(rows == 0) { return 0; }
    int cols = matrix[0].length;
    int[][] dp = new int[rows][cols];
    int maxSide = 0;
    for (int i = 0; i < rows; i++) {
      dp[i][0] = matrix[i][0] - '0';
      maxSide = Math.max(dp[i][0], maxSide);
    }
    for (int i = 0; i < cols; i++) {
      dp[0][i] = matrix[0][i] - '0';
      maxSide = Math.max(dp[0][i], maxSide);
    }
    for (int r = 1; r < rows; r++) {
      for (int c = 1; c < cols; c++) {
        if (matrix[r][c] == '0') {
          dp[r][c] = 0;
        } else {
          dp[r][c] = Math.min(dp[r][c-1], Math.min(dp[r-1][c-1], dp[r-1][c])) + 1;
          maxSide = Math.max(dp[r][c], maxSide);
        }
      }
    }
    return maxSide*maxSide;
  }

  //better approach. We only need to keep track of one row plus the upright point of last row
  //time = O(n^2), space = O(n)
  public int maximalSquare(char[][] matrix) {
    assert matrix != null;
    int rows = matrix.length;
    if(rows == 0) { return 0; }
    int cols = matrix[0].length;
    int[][] dp = new int[rows][cols];
    int maxSide = 0;
    //add one more element to keep the leftmost poiter right of every row.
    int[] dp = new int[cols+1];
    Arrays.fill(dp, 0);
    int lastRowUpRight = 0;
    int maxSide = 0;
    for (int ir = 0; ir < rows; ir++) {
      for (int ic = 0; ic < cols; ic++) {
        int temp = dp[ic+1];
        if (matrix[ir][ic] == '0') {
          dp[ic+1] = 0;
        } else {
          dp[ic+1] = Math.min(dp[ic], Math.min(dp[ic+1], lastRowUpRight)) + 1;
        }
        maxSide = Math.max(maxSide, dp[ic+1]);
        lastRowUpRight = temp;
      }
    }
    return maxSide * maxSide;
  }

  public int maximalSquare(char[][] matrix) {
        assert matrix != null;
        int rows = matrix.length;
        if(rows == 0) { return 0; }
        int cols = matrix[0].length;
        int[][] dp = new int[rows][cols];
        int ans = 0;
        for(int side = 1; side <= Math.min(rows, cols); side++) {
          for (int ro = 0; ro <= rows-side; ro++) {
            for (int cl = 0; cl <= cols-side; cl++) {
              if (side == 1) {
                dp[ro][cl] = matrix[ro][cl] == '1' ? 1 : 0;
              } else {
                int preArea = (side-1) * (side-1);
                int minPreArea, maxPreArea;
                int min1 = Math.min(dp[ro][cl], dp[ro][cl+1]);
                int max1 = Math.max(dp[ro][cl], dp[ro][cl+1]);
                int min2 = Math.min(dp[ro+1][cl], dp[ro+1][cl+1]);
                int max2 = Math.max(dp[ro+1][cl], dp[ro+1][cl+1]);
                minPreArea = Math.min(min1, min2);
                maxPreArea = Math.max(max1, max2);
                dp[ro][cl] = (minPreArea == maxPreArea && minPreArea == preArea) ? side*side : maxPreArea;
              }
              if(dp[ro][cl] > ans) {
                ans = dp[ro][cl];
              }
            }
          }
        }
        return ans;
    }
}
