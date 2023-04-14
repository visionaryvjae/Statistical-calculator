import java.util.Scanner;

public class menu extends numbers {
    public menu() {
    }

    public void menu(){
        boolean stop = false;
        int choice;
        numbers nm = new numbers();

        do {
            System.out.println("Choose an option. In order to add values to option 1 and 2 you must do it one at a time");
            System.out.println("0. Exit");
            System.out.println("1. add frequency");
            System.out.println("2. add Midpoint");
            System.out.println("3. Calculate mean");
            System.out.println("4. Calculate percentiles");
            System.out.println("5. Calculate Mode");
            System.out.println("6. Calculate variance");
            System.out.println("7. Calculate standard deviation and coefficient of S.D");
            System.out.println("8. get culmutive Frequency");
            System.out.println("9. Clear lists");

            Scanner reed = new Scanner(System.in);
            choice = reed.nextInt();

            switch(choice){
                case 1:
                    Scanner richards = new Scanner(System.in);
                    double input = richards.nextDouble();
                    nm.setFreq(input);
                    break;

                case 2:
                    System.out.println("please enter the your lower frequency value and higher frequency value seperated by a colon \ne.g: 10=<20 is written as \'10,20\'");
                    Scanner Sue = new Scanner(System.in);
                    String line = Sue.next();
                    nm.setNums(line);
                    break;

                case 3:
                    nm.setFmid();
                    System.out.print("Please enter \'g\' if you want to calculate grouped mean and \'u\' for ungrouped mean: ");
                    Scanner mean = new Scanner(System.in);
                    nm.calcMean(mean.next());
                    break;

                case 4:
                    nm.setCmFrequency();
                    nm.setGroup();
                    System.out.println();
                    Scanner que = new Scanner(System.in);
                    System.out.print("Please enter \'g\' if you want to calculate grouped percentile/median and \'u\' for ungrouped mean: ");
                    String grouped = que.next();
                    System.out.println("Please enter the quartile you would like to calculate\nplease note: Lower = 25, Upper = 754");
                    int percent = que.nextInt();
                    System.out.println("\nHere is your percentile value: "+nm.calcQ1(percent, grouped));
                    break;

                case 5:
                    nm.findMode();
                    break;

                case 6:
                    Scanner group = new Scanner(System.in);
                    System.out.println("What kind of data are you using?\nGrouped (g)\nUngrouped (u)");
                    nm.calcVariance(group.next());
                    break;

                case 7:
                    double answer = Math.sqrt(nm.getVariance())/nm.getMean();
                    System.out.println("Your standard variation is: "+ Math.sqrt(nm.getVariance()) +"\n\nYour coefficient of variable is: "+answer*100);
                    break;

                case 8:
                    nm.setCmFrequency();
                    nm.setGroup();
                    System.out.println(nm.getCmFrequency());
                    break;

                case 9:
                    Scanner cut = new Scanner(System.in);
                    System.out.println("\nWhich list you would like to clear: \n(n) for midpoints\n(f) for regular list\n(m) for frequency X midpoints\n(c) for cumulative frequency\n(g) list of frequencies\n(a) all");
                    String x = cut.next();
                    nm.clearList(x);
                    break;

                case 0:
                    System.out.println("Thank you. bye :D");
                    stop = true;
                    break;

                default:
                    System.out.println("Invalid value entered");
                    break;
            }

        }while(stop == false);
    }
}
