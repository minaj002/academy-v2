package com.weststein.email;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class EmailTextSource {

    public String getTemplate() {

        ApplicationContext context = new ClassPathXmlApplicationContext();
        Resource templateResource = context.getResource("email.html");

        String emailTemplate = "";

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(templateResource.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                emailTemplate += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
            return emailTemplate;
    }

    public String getBody(String text, String language) {

        try {
            ApplicationContext context = new ClassPathXmlApplicationContext();
            Resource textResource = context.getResource("email/" + text + "-" + language + ".html");

            BufferedReader textBufferedReader = new BufferedReader(new InputStreamReader(textResource.getInputStream()));
            String emailText = "";
            String line;
            while ((line = textBufferedReader.readLine()) != null) {
                emailText += line;
            }
            return emailText;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
