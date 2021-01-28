package ru.javawebinar.basejava;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MainFile {

    public static void printFile(File path, String indent) throws IOException {
        for (String f : Objects.requireNonNull(path.list())) {
            File fn = new File(path.getCanonicalPath() + "/" + f);
            if (fn.isDirectory()) {
                System.out.println(indent + "Directory: " + fn.getName());
                printFile(fn, indent + "    ");
            } else {
                System.out.println(indent + "\u22A2File: " + fn.getName());
            }
        }

    }

    public static void main(String[] args) {
        try {
            printFile(new File("."), "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
