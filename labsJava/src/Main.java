public class Main {
    public static void main(String[] args) {
        String[] months = {"January", "February", "March", "April", "May",
        "June", "July", "August", "September", "October", "November", "December"};

        double[] num = new double[12];
        double midNum = 0;
        for (int i = 0; i < num.length; i++){
            double rand = Math.random() * 100.0;
            num[i] = rand;
            midNum += rand;
            if (i == 11)
                midNum /= 12;
        }
        for (int month = 0; month < months.length; month++){
            System.out.printf("%s = %.1f", months[month], num[month]);
            System.out.println(" ");
        }
        System.out.println("Middle value of 12 numbers: " + midNum);

    }
}