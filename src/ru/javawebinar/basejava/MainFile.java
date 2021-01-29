package ru.javawebinar.basejava;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainFile {
    public static void recursiveDirScan(String dir, int level) {
        try {
            Files.list(Paths.get(dir)).forEach(path -> {
                var fn = " ".repeat(level * 2) + path.toString().replace(dir + '/', "");
                if (Files.isDirectory(path)) {
                    System.out.println(fn + " -->");
                    recursiveDirScan(path.toString(), level + 1);
                } else {
                    System.out.println(fn);
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
