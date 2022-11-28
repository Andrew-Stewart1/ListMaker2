import java.util.ArrayList;
import java.util.Scanner;

public class ListMaker {
    static Scanner userChoice = new Scanner(System.in);

    public static void main(String[] args) {

        ArrayList<String> arrList = new ArrayList<>();

        String option;
        do {

            System.out.println("Select an option to proceed, A - Add item, D - Delete item, P - Print List, Q - Quit");

            option = userChoice.nextLine();
            option = option.toUpperCase();

            switch (option) {
                case "A":
                    addItem(arrList);
                    break;
                case "D":
                    deleteItem(arrList);
                    break;
                case "P":
                    printList(arrList);
                    break;
                case "Q":
                    quit();
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        } while (!option.equals("Q"));
    }
        private static void addItem(ArrayList < String > list){
            String userAdd = SafeInput.getNonZeroLenString(userChoice, "What would you like to add");
            System.out.println();
            list.add(userAdd);
        }
        public static void deleteItem (ArrayList < String > list) {
            int count = 1;
            for (String name : list) {
                System.out.println(count + ". " + name);
                count++;
            }
            list.remove(SafeInput.getRangedInt(userChoice, "What would you like to delete", 1, list.size())-1);
        }
        public static void printList (ArrayList < String > list) {
            int count = 1;
            for (String name : list) {
                System.out.println(count + ". " + name);
                count++;
            }
        }
        public static void quit () {
            System.out.println("You have unsaved data are you sure you want to quit");
            System.exit(0);
        }
    }

