package com.example.journeythroughfx;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

public class FileHolder {
    private static final File usersFile = new File("users.txt");
    private static final File contactsFile = new File("contacts.txt");

    static {
        try {
            if (!usersFile.exists()) usersFile.createNewFile();
            if (!contactsFile.exists()) contactsFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isUserRegistered(String email){
        try(RandomAccessFile raf = new RandomAccessFile(usersFile, "rw")){
            raf.seek(0);
            String line;
            while ((line = raf.readLine()) != null){
                String[] parts = line.split(",");
                if(parts.length>=2 && email.equalsIgnoreCase(parts[1].trim())){
                    return true;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public static void saveUser(String name, String email, String password){
        String record = name + "," + email + "," + password + "\n";
        try(RandomAccessFile raf = new RandomAccessFile(usersFile, "rw")){
            raf.seek(raf.length());
            raf.writeBytes(record);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static boolean verifyUser(String email, String password) {
        try (RandomAccessFile raf = new RandomAccessFile("users.txt", "r")) {
            String line;
            while ((line = raf.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) { // e.g. name,email,password
                    String storedEmail = parts[1].trim().toLowerCase();
                    String storedPass = parts[2].trim();
                    if (email.equals(storedEmail) && password.equals(storedPass)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public static boolean isValidMobile(String mobile) {
        if (mobile == null || mobile.isEmpty()) return false;
        char[] chars = mobile.toCharArray();

        if (chars.length == 11 && chars[0] == '0' && chars[1] == '1') {
            return true;
        }
        if (chars.length == 14 &&
                chars[0] == '+' && chars[1] == '8' && chars[2] == '8' && chars[3] == '0' && chars[4] == '1') {
            return true;
        }

        return false;
    }


    public static boolean isMobileExists(String mobile) {
        try(RandomAccessFile raf = new RandomAccessFile(contactsFile, "rw")){
            String line;
            while ((line = raf.readLine()) != null){
                String[] parts = line.split(",");
                if (parts.length >= 3 && mobile.equals(parts[2].trim())) {
                    return true;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public static void writeContact(String name, String address, String mobile) {
        String record = name + "," + address + "," + mobile + "\n";
        try (RandomAccessFile raf = new RandomAccessFile(contactsFile, "rw")) {
            raf.seek(raf.length());
            raf.writeBytes(record);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
