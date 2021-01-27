package ru.javawebinar.basejava;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MainFile {

    public static void printFile(File path) throws IOException {
        if (path.isDirectory()) {
            for (String f : Objects.requireNonNull(path.list())) {
                File fn = new File(path.getCanonicalPath() + "/" + f);
                if (fn.isDirectory()) {
                    printFile(fn);
                } else {
                    System.out.println(fn.getCanonicalFile());
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            printFile(new File("."));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
