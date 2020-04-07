package bomboane;

import garduri.Gard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Bomboane {
    public int players = 0;
    public int candies = 0;
    public ArrayList<Interval> intervals = new ArrayList<>();

    public static class Interval  {
        int left = 0;
        int right = 0;

        Interval(int left, int right) {
            this.left = left;
            this.right = right;
        }

        Boolean inInterval(Integer value) {
            return (value >= left && value <= right);
        }

        @Override
        public String toString() {
            return "Interval{" +
                    "left=" + left +
                    ", right=" + right +
                    '}';
        }
    }


    public void setUp() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] first_line = reader.readLine().split(" ");
        this.players = Integer.parseInt(first_line[0]);
        this.candies = Integer.parseInt(first_line[1]);
        intervals.add(new Interval(0, 0));  // for offsetting
        for (int i = 1; i <= players; i++) {
            String[] interval_string = reader.readLine().split(" ");
            intervals.add(new Bomboane.Interval(Integer.parseInt(interval_string[0]), Integer.parseInt(interval_string[1])));
        }
    }

    public void solve() throws IOException {
        setUp();

        double[] last_corelation_sums = new double[candies + 1];

        // Initialization
        Interval first = intervals.get(1);
        for (int i = 0; i <= candies; i++) {
            last_corelation_sums[i] = first.inInterval(i) ? 1.0 : 0.0;
        }

        for (int player = 2; player <= players; player++) {
            double[] current_corelation_sums = new double[candies + 1];
            Interval player_interval = intervals.get(player);

            double last_sum = 0.0;
            for (int sum_paid = player_interval.left; sum_paid <= candies; sum_paid++) {  // doar sumele astea le poate platii
                double save = last_sum;
//                last_sum += (last_corelation_sums[sum_paid - player_interval.left]) % 1000000007;
//                last_sum -=  (last_corelation_sums[Math.max(sum_paid - player_interval.right - 1, 0)]) % 1000000007;
                if (player_interval.left != player_interval.right) {
                    last_sum += (last_corelation_sums[sum_paid - player_interval.left]) % 1000000007;
                    last_sum -=  (last_corelation_sums[Math.max(sum_paid - player_interval.right - 1, 0)]) % 1000000007;
                } else {
                    last_sum = (last_corelation_sums[sum_paid - player_interval.left]) % 1000000007;
                }

                if (last_sum < 0) {
                    String res = "";
                    for (int jj = 0; jj <= candies; jj++) res += "last[" + jj + "] " + last_corelation_sums[jj] + " ";
                    System.out.println("player " + player + "   previous " + save  + "   sum_paid  " + sum_paid + "  +  " + ((last_corelation_sums[sum_paid - player_interval.left]) % 1000000007) + " - " + ((last_corelation_sums[Math.max(sum_paid - player_interval.right - 1, 0)]) % 1000000007));
                    System.out.println(res);
                    return;
                }
                current_corelation_sums[sum_paid] = last_sum % 1000000007;
            }
            last_corelation_sums = current_corelation_sums;
        }
        System.out.println((int)last_corelation_sums[candies]);



    }

    public static void main(String[] args) throws IOException {
        Bomboane THIS = new Bomboane();
        THIS.solve();
    }
}
