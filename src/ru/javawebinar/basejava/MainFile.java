package ru.javawebinar.basejava;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainFile {
    public static void recursiveDirScan(String dir, int level) {
        try {
            Files.list(Paths.get(dir)).forEach(path -> {
                System.out.println(" ".repeat(level * 2) + path.getFileName().toString());
                if (Files.isDirectory(path)) {
                    recursiveDirScan(path.toString(), level + 1);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        recursiveDirScan(".", 0);
    }
}
