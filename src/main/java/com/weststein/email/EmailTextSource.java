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

        StringBuilder emailTemplate = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(templateResource.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                emailTemplate.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
            return emailTemplate.toString();
    }

    public String getBody(String text, String language) {

        try {
            ApplicationContext context = new ClassPathXmlApplicationContext();
            Resource textResource = context.getResource("email/" + text + "-" + language + ".html");

            BufferedReader textBufferedReader = new BufferedReader(new InputStreamReader(textResource.getInputStream()));
            StringBuilder emailText = new StringBuilder();
            String line;
            while ((line = textBufferedReader.readLine()) != null) {
                emailText.append(line);
            }
            return emailText.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
