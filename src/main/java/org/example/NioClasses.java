package org.example;

import lombok.SneakyThrows;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.StandardOpenOption.*;

public class NioClasses {

    @SneakyThrows
    public static void main(String[] args) {

        randomAccessFiles("Hello, i was here!\n");


    }

    public static void randomAccessFiles(String content) throws IOException {

        Path file = Paths.get("D:/repositories/java-IO/text.txt");

        byte[] bytes = content.getBytes();

        ByteBuffer out = ByteBuffer.wrap(bytes);

        ByteBuffer copy = ByteBuffer.allocate(19);

        try (FileChannel fc = (FileChannel.open(file, READ, WRITE))) {

            int nread;

            do {
                nread = fc.read(copy);
            } while (nread != -1 && copy.hasRemaining());


            fc.position(0);

            while (out.hasRemaining()) {
                fc.write(out);
            }
            out.rewind();

            long length = fc.size();

            fc.position(length - 1);

            copy.flip();
            while (copy.hasRemaining()) {
                fc.write(copy);
            }

            while (out.hasRemaining()) {
                fc.write(out);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }




    }


}
