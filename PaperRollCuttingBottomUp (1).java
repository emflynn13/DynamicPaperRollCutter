// Emma Flynn
// R00221673
// Non-Linear Assignment 2
// Bottom-up (recursion + memoization)

public class PaperRollCuttingBottomUp {

    // prices for each length of roll cuts
    static double[] prices = {1.2, 3, 5.8, 10.1};
    static int[] lengths = {1, 2, 3, 5};

    public static void getMaxRevenue(int n) {
        if (n < 1){
            System.out.println("Invalid number");
            return;
        }
        double[] maxRevenue = new double[n + 1]; // maximum revenue for each length
        int[] cutPos = new int[n + 1]; // this stores the cut positions that lead to the maximum revenue for each length
        maxRevenue[0] = 0;

        for (int j = 1; j <= n; j++) {
            int rollLength = -1;
            double q = Double.NEGATIVE_INFINITY; // current max revenue set to lowest possible value
            for (int i = 0; i < lengths.length; i++) {  // cut position less than current length
                if (j - lengths[i] >= 0) {
                    // Update max revenue and cut position if a better revenue is found
                    if (q < prices[i] + maxRevenue[j - lengths[i]]) {
                        q = prices[i] + maxRevenue[j - lengths[i]];
                        rollLength = lengths[i];
                        }
                }
            }
            // Store the max revenue and cut position for the current length
            maxRevenue[j] = q;
            cutPos[j] = rollLength;
        }
        int[] numPieces = new int[n + 1]; // number of pieces for each length
        int j = 0;

        for (int i = n; i > 0; i -= cutPos[i]) {
            numPieces[j] = cutPos[i];
            j++;
        }

        int[] rollCounts = new int[lengths.length]; // used to the number of times each cut is used

        for (int i = 0; i < numPieces.length; i++) {
            if (numPieces[i] != 0) {  // non-zero value means it is a valid roll length
                for (int rollIndex = 0; rollIndex < lengths.length; rollIndex++) {
                    if (numPieces[i] == lengths[rollIndex]) {
                        rollCounts[rollIndex]++;
                        break;
                    }
                }
            }
        }

        double maxRev = maxRevenue[n];
        System.out.printf("Best revenue for length " + n + ": €%.2f \n", maxRev);
        System.out.println("Roll lengths and counts used:");
        for (int i = 0; i < lengths.length; i++) {
            if (rollCounts[i] > 0) {
                System.out.println("Length " + lengths[i] + ": " + rollCounts[i]);
            }
        }
    }

    public static void main(String[] args) {
        int n = 33; // set length of the rod
        getMaxRevenue(n);

        getMaxRevenue(1);
        getMaxRevenue(2);
        getMaxRevenue(3);
        getMaxRevenue(4);
        getMaxRevenue(5);

    }
}

