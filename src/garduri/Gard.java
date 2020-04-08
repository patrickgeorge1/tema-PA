package garduri;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Gard {
    public int n = 0;
    public int result = 0;
    public ArrayList<Gardulet> gardulete = new ArrayList<>();

    public static class Gardulet implements Comparable<Gardulet> {
        int left = 0;
        int right = 0;

        Gardulet(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Gardulet o) {
            return this.left - o.left;
        }

        @Override
        public String toString() {
            return "Gardulet{" +
                    "left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    public void setUp() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("gard.in"));
        this.n = Integer.parseInt(reader.readLine());
        for (int i = 1; i <= n; i++) {
            String[] gard_string = reader.readLine().split(" ");
            gardulete.add(new Gardulet(Integer.parseInt(gard_string[0]), Integer.parseInt(gard_string[1])));
        }
        Collections.sort(gardulete);
    }

    public void solve() throws IOException {
        setUp();
        int lastStart = gardulete.get(0).left;
        int lastEnd = gardulete.get(0).right;
        for (int i = 1; i < gardulete.size(); i++) {
            Gardulet now = gardulete.get(i);
            if (now.left == lastStart) {
                if (now.right <= lastEnd) result++;
                else {
                    result++;
                    lastEnd = now.right;
                }
            }
            else
            {
                if(now.left > lastEnd)
                {
                    lastStart = now.left;
                    lastEnd = now.right;
                }
                else
                {
                    if (now.right <= lastEnd) result++;
                    else {
                        lastStart = now.left;
                        lastEnd = now.right;
                    }
                }

            }
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter("gard.out"));
        writer.write(Integer.toString(result));
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        Gard THIS = new Gard();
        THIS.solve();
    }
}