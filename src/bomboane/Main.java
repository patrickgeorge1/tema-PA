package bomboane;

public class Main {
    public static void main(String[] args) {
        int i = 1;
        while (true) {
            i += i;
            System.out.println(i);
            if (i < 0) break;
        }
    }
}
