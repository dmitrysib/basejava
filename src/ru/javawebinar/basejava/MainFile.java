package ru.javawebinar.basejava;

import java.io.File;
import java.util.Objects;

public class MainFile {

    public static void printFile(File path, int level) {
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
        printFile(new File("."), 0);
    }
}
