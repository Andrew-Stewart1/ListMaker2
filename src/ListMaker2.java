import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ListMaker2{
    static Scanner userChoice = new Scanner(System.in);

    public static void main(String[] args) {

        ArrayList<String> arrList = new ArrayList<>();

        String option;
        do {

            System.out.println("Select an option to proceed, A - Add item, D - Delete item, V - View List, O - Open File, S - Write and Save data,  Q - Quit");

            option = userChoice.nextLine();
            option = option.toUpperCase();

            switch (option) {
                case "A":
                    addItem(arrList);
                    break;
                case "D":
                    deleteItem(arrList);
                    break;
                case "C":
                    clearItems(arrList);
                    break;
                case "V":
                    printList(arrList);
                    break;
                case "O":
                    openFile(arrList);
                    break;
                case "S":
                    saveFile(arrList);
                    break;
                case "Q":
                    ListMaker.quit();
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        } while (!option.equals("Q"));
    }

    private static void addItem(ArrayList<String> list) {
        String userAdd = SafeInput.getNonZeroLenString(userChoice, "What would you like to add");
        System.out.println();
        list.add(userAdd);
    }

    public static void deleteItem(ArrayList<String> list) {
        int count = 1;
        for (String name : list) {
            System.out.println(count + ". " + name);
            count++;
        }
        list.remove(SafeInput.getRangedInt(userChoice, "What would you like to delete", 1, list.size()) - 1);
    }
    public static void clearItems(ArrayList<String> list) {
        int count = 1;
        for (String name : list) {
            System.out.println(count + ". " + name);
            count++;
        }
        list.clear();
    }

    public static void printList(ArrayList<String> list) {
        int count = 1;
        for (String name : list) {
            System.out.println(count + ". " + name);
            count++;
        }
    }

    public static void openFile(ArrayList<String> list) {
            Throwable e;

                JFileChooser chooser = new JFileChooser();
                File selectedFile;
                String line;
                ArrayList<String> lines = new ArrayList<>();

                try {
                    File workingDirectory = new File(System.getProperty("user.dir"));

                    chooser.setCurrentDirectory(workingDirectory);

                    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        //if the user selects a file
                        selectedFile = chooser.getSelectedFile();
                        Path file = selectedFile.toPath();
                        Path path = Paths.get("");

                        InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                        int lineNumber = 0;
                        while (reader.ready()) {
                            line = reader.readLine();
                            lines.add(line);
                            lineNumber++;
                            System.out.printf("\n %-4d ---> %-60s", lineNumber, line);
                        }

                        String line4 = lines.get(3);
                        String[] wordsInLine4 = line4.split(", ");

                        System.out.println("");
                        for (String word : wordsInLine4) {
                            System.out.println(word);
                        }

                        FileInputStream fileInputStream = new FileInputStream(selectedFile);
                        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                        int words = 0;
                        int characters = 0;
                        int paragraphs = 0;
                        int whiteSpaces = 0;
                        int sentences = 0;

                        while ((line = bufferedReader.readLine()) != null) {
                            if (line.equals("")) {
                                paragraphs += 1;
                            } else {
                                characters += line.length();
                                String wordsCount[] = line.split("\\s+");
                                words += wordsCount .length;
                                whiteSpaces += words - 1;
                                String sentence[] = line.split("[!?.:]+");
                                sentences += sentence.length;
                            }
                        }
                        if (sentences >= 1) {
                            paragraphs++;
                        }
                        System.out.println("Total word count:  " + words);
                        System.out.println("Total number of sentences:  " + sentences);
                        System.out.println("Total number of characters: " + characters);
                        System.out.println("Number of paragraphs:  " + paragraphs);
                        System.out.println("Total number of whitespaces:  " + whiteSpaces);
                    } else {
                        System.out.println("Failed to choose a file to process");
                        System.out.println("Run the program again");
                        System.exit(0);


                    }
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex){
                    throw new RuntimeException(ex);
                }
            }
    public static void saveFile(ArrayList<String> args) {
        ArrayList<String> records = new ArrayList<>();
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\randomData.txt");

        try {

            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for (String rec : records) {
                writer.write(rec, 0, rec.length());
                writer.newLine();
            }
            writer.close();

        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }
    public static void quit() {
                System.out.println("You have unsaved data are you sure you want to quit");
                System.exit(0);
            }
        }