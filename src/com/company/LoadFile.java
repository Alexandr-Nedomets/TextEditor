package com.company;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LoadFile {

    String loadText(String fileName)  {
        String result = null;
        try {
            result  = new String(Files.readAllBytes(Paths.get(fileName)));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}