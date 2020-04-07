package trezorerie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Bani {

    public HashMap<Integer, ArrayList<Integer>> dependenciesType;
    public Integer type;
    public Integer n;
    public int result;

    public void setUp() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        this.type = Integer.parseInt(input[0]);
        this.n = Integer.parseInt(input[1]);
        this.result = 0;
        dependenciesType = new HashMap<>();
        dependenciesType.put(1, new ArrayList<>(){{add(2); add(3); add(5);}});
        dependenciesType.put(2, new ArrayList<>(){{add(1); add(4);}});
        dependenciesType.put(3, new ArrayList<>(){{add(1); add(3); add(4);}});
        dependenciesType.put(4, new ArrayList<>(){{add(2); add(5);}});
        dependenciesType.put(5, new ArrayList<>(){{add(4);}});

    }



    public void backtracking(Integer solution, Integer last_bank) {
        if (solution == this.n) {
            this.result = (this.result + 1) %  1000000007;
            return;
        }

        for (Integer correct_dependency : dependenciesType.get(last_bank)) {
            solution++;
            backtracking(solution, correct_dependency);
            solution--;
        }
    }

    public void dynamicType2() {
        Double[] before_ways_counter = {1.0, 1.0, 1.0, 1.0, 1.0, 1.0};

        for (int i = 2; i <= this.n; i++) {
            Double[] ways_counter = { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
            for (int banknote = 1; banknote <= 5; banknote++) {
                for (Integer dependency : dependenciesType.get(banknote)) {
                    ways_counter[banknote] += before_ways_counter[dependency] % 1000000007;
                }
            }
            before_ways_counter = ways_counter;
        }

        // finish
        for (Double ways_for_banknote : before_ways_counter) {
            result = (int) ((result + ways_for_banknote) % 1000000007);
        }
    }

    public void dynamicType1() {
        result = 5;
        for (int i = 1; i < this.n; i++) {
            result = result * 2 % 1000000007;
        }
    }

    public void solve() throws IOException {
        setUp();
//        backtracking(0, 0);
        if (type == 1) dynamicType1();
        else dynamicType2();
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        Bani THIS = new Bani();
        THIS.solve();
    }
}
