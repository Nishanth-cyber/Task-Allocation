import java.util.*;

public class TaskAllocation {


    static int solveAssignmentProblem(int[][] costMatrix) {
        int n = costMatrix.length;
        boolean[] assigned = new boolean[n];
        int[] result = new int[n];

        Arrays.fill(result, -1);
        return branchAndBound(costMatrix, assigned, result, 0, 0);
    }


    private static int branchAndBound(int[][] costMatrix, boolean[] assigned, int[] result, int level, int currentCost) {
        int n = costMatrix.length;


        if (level == n) {
            return currentCost;
        }

        int minCost = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if (!assigned[i]) {
                assigned[i] = true; 
                result[level] = i;


                int cost = branchAndBound(costMatrix, assigned, result, level + 1, currentCost + costMatrix[level][i]);

                minCost = Math.min(minCost, cost);


                assigned[i] = false;
            }
        }

        return minCost;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of drivers/customers: ");
        int n = scanner.nextInt();


        int[][] costMatrix = new int[n][n];
        System.out.println("Enter the cost matrix (row by row):");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                costMatrix[i][j] = scanner.nextInt();
            }
        }


        int minCost = solveAssignmentProblem(costMatrix);


        System.out.println("Minimum cost of task allocation: " + minCost);

    }
}