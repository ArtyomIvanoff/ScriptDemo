package step33;

import java.applet.Applet;
import java.io.*;
import java.util.Scanner;

class CypherA implements Cypherer {
    private char masEn[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '!', '+' };
    private char masRu[] = { 'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я', 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ы', 'Э', 'Ю', 'Я' };
    private int keyArr[];
    private boolean isChanged;

    CypherA(String pathKey) throws FileNotFoundException {
        try(Scanner scan = new Scanner(new File(pathKey))) {
           keyArr = new int[64];
           for(int i = 0; scan.hasNextInt(); i++)
               keyArr[i] = scan.nextInt();
        }
    }

    @Override
    public void script(String pathFrom) throws IOException {
        File checkFile = new File("D:\\src\\text.txt");
        if(checkFile.exists()) {
            isChanged = false;
            try (PrintWriter writeScripted = new PrintWriter("D:\\src\\cyphered.txt");
                 BufferedReader scanText = new BufferedReader(new InputStreamReader(new FileInputStream(pathFrom), "CP1251"))) {
                 String curLine = scanText.readLine();

                 while (curLine != null) {
                      char mas[] = curLine.toCharArray();
                      int p = 0;
                      int k;

                      while (p < mas.length) {
                         for (int i = 0; i < 64; i++) {
                            if (mas[p] == masEn[i]) {
                                k = keyArr[i];
                                mas[p] = masRu[k];
                                isChanged = true;
                            }
                         }

                         if (isChanged == false) {
                            for (int i = 0; i < 64; i++) {
                                if (mas[p] == masRu[i]) {
                                    for (int j = 0; j < 64; j++) {
                                        if (i == keyArr[j]) {
                                            mas[p] = masEn[j];
                                            isChanged = true;
                                        }
                                    }
                                }
                            }
                         }

                         isChanged = false;
                         p++;
                       }

                       writeScripted.println(mas);
                       curLine = scanText.readLine();
                }
            }

            System.out.println("File was scripted.");
        }

        else
            System.out.println("Error! File isn't found!");

        checkFile = null;
    }

    @Override
    public void descript(String pathTo) throws IOException {
        File checkFile2 = new File("D:\\src\\decyphered.txt");
        if (checkFile2.exists()) {
            isChanged = false;
            int s = 0;
            try (PrintWriter writeDescripted = new PrintWriter(pathTo);
                 BufferedReader scanScript = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\src\\cyphered.txt"), "UTF-8"))) {
                   String curLine = scanScript.readLine();
                   while (curLine != null) {
                      char mas[] = curLine.toCharArray();
                      int p = 0;
                      int k;

                      while (p < mas.length) {
                        for (int i = 0; i < 64; i++) {
                            if (mas[p] == masRu[i]) {
                                for (int j = 0; j < 64; j++) {
                                    if (i == keyArr[j]) {
                                        mas[p] = masEn[j];
                                        isChanged = true;
                                    }
                                }
                            }
                        }

                        if (isChanged == false) {
                            for (int i = 0; i < 64; i++) {
                                if (mas[p] == masEn[i]) {
                                    k = keyArr[i];
                                    mas[p] = masRu[k];
                                }
                            }
                        }

                        isChanged = false;
                        p++;
                    }

                    writeDescripted.println(mas);
                    curLine = scanScript.readLine();
                }
            }
            System.out.println("Scripted file was descripted.");
        }

        else
            System.out.println("Error! File isn't found!");

        checkFile2 = null;
    }
}

public class ScriptDemo extends Applet {
    static void showMenu()  {
    /*CypherA cyph = new CypherA("D:\\src\\key.txt");

        while(!isExiting) {
            System.out.println("\n\n ***MENU***");
            System.out.println("1.Script a file.");
            System.out.println("2.Descript a scripted file.");
            System.out.println("3.Information.");
            System.out.println("4.Exit");

            int choice = Integer.parseInt(reader.readLine());
            switch (choice) {
                case 1 :
                    cyph.script("D:\\src\\text.txt");
                    break;
                case 2 :
                    cyph.descript("D:\\src\\decyphered.txt");
                    break;
                case 3 :
                    System.out.println("This program was created by Ivanov Artyom on August 4th, 2015.");
                    break;
                case 4 :
                    System.out.println("Exiting...");
                    isExiting = true;
                    break;
                default :
                    System.out.println("Wrong choice, try again, please.");
                    break;
            }
        }*/
    }

    public void init() {

            ScriptDemo.showMenu();
    }
}