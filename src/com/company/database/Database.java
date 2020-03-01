package com.company.database;

import java.io.*;
import java.nio.file.*;
import java.util.Collections;

public class Database {
    private static volatile Database instance = null;

    private Database() {
    }

    public static Database getInstance() {
        if (instance == null) {
            synchronized (Database.class) {
                if (instance == null) {
                    instance = new Database();
                }
            }
        }
        return instance;
    }

    public void write(String filePath, String data, boolean overwrite) {
        Path path = Paths.get(filePath);
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            Files.write(
                    path,
                    Collections.singleton(data),
                    overwrite ? StandardOpenOption.TRUNCATE_EXISTING : StandardOpenOption.APPEND

            );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void delete(String filePath) {
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            try {
                Files.delete(Paths.get(filePath));
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
    }





}














