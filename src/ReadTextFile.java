import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class ReadTextFile {
    private static Throwable e;

    public static void main(String[] args) {
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
                System.out.println("Total word count = " + words);
                System.out.println("Total number of sentences = " + sentences);
                System.out.println("Total number of characters = " + characters);
                System.out.println("Number of paragraphs = " + paragraphs);
                System.out.println("Total number of whitespaces = " + whiteSpaces);
            } else {
                System.out.println("Failed to choose a file to process");
                System.out.println("Run the program again");
                System.exit(0);


            }
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    }