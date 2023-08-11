package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        String path = "text.txt";
        String out = "out.txt";

        try {
            byteStream(path, out);
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

            int c;

            while ((c = in.read()) != -1) {
                out.write(c);

            }

        } finally {
            in.close();
            out.close();
        }


    }

    public static void createFile(String path, String name, String extension) {

        try {
            FileWriter fileWriter = new FileWriter(path + name + extension);
            fileWriter.append("");
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}