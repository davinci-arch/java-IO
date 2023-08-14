package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void createFile(String path, String name, String extension) {

        try {

            FileWriter fileWriter = new FileWriter(path + name + extension);
            fileWriter.append("");
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void main(String[] args) throws IOException {
        String path = "text.txt";
        String out = "out.txt";
        try {
            scannerDoubleReader(path, out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void byteStream(String path, String outPath) throws IOException {

        FileInputStream in = null;

        FileOutputStream out = null;

        try {
            in = new FileInputStream(path);
            out = new FileOutputStream(outPath);
            out.flush();
            int c;

            while ((c = in.read()) != -1) {
                out.write(c);

            }

        } finally {
            in.close();
            out.close();
        }
    }

    public static void characterStreamBasedOnReader(String path, String outPath) throws IOException {

        FileReader in = null;
        FileWriter out = null;

        try {
            in = new FileReader(path);
            out = new FileWriter(outPath);

            int c;

            while ((c = in.read()) != -1) {
                out.write(c);
            }
        } finally {
            if (in != null) in.close();
            if (out != null) out.close();
        }

    }

    public static void characterBufferedStream(String path, String outPath) throws IOException {

        BufferedReader in = null;

        BufferedWriter out = null;

        try {

            in = new BufferedReader(new FileReader(path));
            out = new BufferedWriter(new FileWriter(outPath));

            String line = "";

            int i = 0;
            while ((line = in.readLine()) != null) {
                System.out.println(i);
                i++;
                out.write(line + "\n");

            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }

    }

    public static void scannerIO(String path, String outpath) throws IOException {

        Scanner scanner = null;


        try {
            scanner = new Scanner( new BufferedReader(new FileReader(path)));

            while (scanner.hasNext()) {
                scanner.useDelimiter("\n");
                String line = scanner.next();

                System.out.println(line);
            }
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    public static void scannerDoubleReader(String path, String outpath) throws IOException {

        Scanner scanner = null;
        double result = 0;
        try {

            scanner = new Scanner(new BufferedReader(new FileReader(path)));
            scanner.useLocale(Locale.UK);
            while (scanner.hasNext()) {

                if (scanner.hasNextDouble()) {
                    result += scanner.nextDouble();
                } else {
                    scanner.next();
                }
            }
        } finally {

            if (scanner != null) {
                scanner.close();
            }
        }

        System.out.println(result);

    }

}