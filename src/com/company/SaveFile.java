package com.company;

import java.io.FileWriter;
import java.io.IOException;

public class SaveFile {

    public void saveText(String text, String link) {
        try (FileWriter writer = new FileWriter(link, false)) {
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
