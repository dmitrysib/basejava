package ru.javawebinar.basejava;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MainFile {

    public static void printFile(File path, int level) throws IOException {
        for (String f : Objects.requireNonNull(path.list())) {
            File fn = new File(path.getPath() + "/" + f);
            if (fn.isDirectory()) {
                System.out.println(" ".repeat(level * 2) + "Directory: " + fn.getName());
                printFile(fn, level + 1);
            } else {
                System.out.println(" ".repeat(level * 2) + "File: " + fn.getName());
            }
        }

    }

    public static void main(String[] args) {
        try {
            printFile(new File("."), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
