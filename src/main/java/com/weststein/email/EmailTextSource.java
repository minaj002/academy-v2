package com.weststein.email;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Component
public class EmailTextSource {

    public String getBody(String template, String language) {

        try {
            FileReader reader = new FileReader(template);
            BufferedReader br = new BufferedReader(reader);
            String sCurrentLine = "";

            while (br.readLine() != null) {
                sCurrentLine += br.readLine();
            }
            return sCurrentLine;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

}
