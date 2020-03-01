package com.company.database;


import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;


public class Filemanager {

    private static volatile Filemanager instance = null;

    private Filemanager() {
    }

    public static Filemanager getInstance() {
        if (instance == null) {
            synchronized (Filemanager.class) {
                if (instance == null) {
                    instance = new Filemanager();
                }
            }
        }
        return instance;
    }

    public ArrayList readFile(String file, Class klass) {
        ArrayList accountList = new ArrayList<>();
        try (FileReader fr = new FileReader("Javabanken");
             BufferedReader br = new BufferedReader(fr)){
            String line;
            while ((line = br.readLine()) != null) {

                String[] currentAccount = line.split("/");

                Object account = klass.getConstructor().newInstance();
                for (int i = 0; i < currentAccount.length; i++) {
                    Field f = klass.getDeclaredFields()[i];
                    f.setAccessible(true);
                    f.set(account, currentAccount[i]);
                }
                accountList.add(account);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return accountList;
    }

    public static void edit(String path, String oldString, String newString) {
        File fileToBeModified = new File(path);
        File ftmp = new File("Jbank");
        try (BufferedReader br = new BufferedReader(new FileReader(String.valueOf(fileToBeModified)));
             BufferedWriter bw = new BufferedWriter(new FileWriter(ftmp))) {
            String ln;
            while ((ln = br.readLine()) != null) {
                bw.write(ln
                        .replace(oldString, newString)
                );
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Files.move(ftmp.toPath(), fileToBeModified.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
























