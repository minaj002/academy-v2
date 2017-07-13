package com.weststein.email;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class EmailTextSource {

    public String getBody(String template, String language) {

        try {


            ApplicationContext context =new ClassPathXmlApplicationContext();
            Resource resource = context.getResource("email.html");

//            FileReader reader = new FileReader(template);
            BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
//            BufferedReader br = new BufferedReader(reader);
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
