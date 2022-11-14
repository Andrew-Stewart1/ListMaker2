import java.util.Scanner;

public class SafeInput {

            public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
                int retVal = 0;
                String trash = "";
                boolean done = false;

                do {
                    System.out.print("\n" + prompt + "[" + low + "-" + high + "]: ");
                    if (pipe.hasNextInt()) {
                        retVal = pipe.nextInt();
                        pipe.nextLine();
                        if (retVal >= low && retVal <= high) {
                            done = true;
                        } else {
                            System.out.println("\nNumber is out of range [" + low + "-" + high + "]: " + retVal);
                        }
                    } else {
                        trash = pipe.nextLine();
                        System.out.println("You must enter an int: " + trash);
                    }
                } while (!done);

                return retVal;
            }

            public static boolean getYNConfirm(Scanner pipe, String prompt) {
                boolean retVal = true;
                String response = "";
                boolean gotAVal = false;

                do {
                    System.out.print("\n" + prompt + " [Y/N] ");
                    response = pipe.nextLine();
                    if (response.equalsIgnoreCase("Y")) {
                        gotAVal = true;
                        retVal = true;
                    } else if (response.equalsIgnoreCase("N")) {
                        gotAVal = true;
                        retVal = false;
                    } else {
                        System.out.println("You must answers [Y/N]! " + response);
                    }

                } while (!gotAVal);

                return retVal;
            }
    public static String getRegExString(Scanner pipe, String prompt, String regEx) {
        String ans = "";
        do {
            System.out.print("\n" + prompt + ": "); // show prompt add space
            if (pipe.hasNextLine())
                ans = pipe.nextLine();
        } while (ans == null||!ans.matches(regEx));
        return ans;

    }
    public static String getNonZeroLenString(Scanner pipe, String prompt)
    {
        String retString = "";
        do
        {
            System.out.print("\n" + prompt + ": ");
            retString = pipe.nextLine();
        }while(retString.length() == 0);

        return retString;

    }
        }
