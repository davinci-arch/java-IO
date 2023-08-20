package org.example;

import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static java.nio.file.StandardOpenOption.READ;
import static java.nio.file.StandardOpenOption.WRITE;

public class NioClasses {

    @SneakyThrows
    public static void main(String[] args) {

        Path from = Paths.get("D:/repositories/java-IO");
        Path to = Paths.get("C:\\Users\\User\\Desktop\\to");


        copyAllFilesToDirectory(".java$", from, to);

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


    public static void copyAllFilesToDirectory(String pattern, Path from, Path to) throws IOException {

        List<Path> files = new ArrayList<>();

        if (Files.exists(to) && Files.isDirectory(to)) {

            Files.walkFileTree(from, new FileVisitorEx(pattern, files));

            for (Path file : files) {
                Files.copy(file, to.resolve(file.getFileName()), StandardCopyOption.REPLACE_EXISTING);

            }

        }
    }

}

class FileVisitorEx extends SimpleFileVisitor<Path> {

    private final String pattern;
    private final List<Path> paths;

    public FileVisitorEx(String pattern, List<Path> paths) {
        this.pattern = pattern;
        this.paths = paths;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return super.preVisitDirectory(dir, attrs);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        Pattern pattern1 = Pattern.compile(pattern);

        if (pattern1.matcher(file.getFileName().toString()).find()) {

            paths.add(file);
        }

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return super.visitFileFailed(file, exc);
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return super.postVisitDirectory(dir, exc);
    }
}
