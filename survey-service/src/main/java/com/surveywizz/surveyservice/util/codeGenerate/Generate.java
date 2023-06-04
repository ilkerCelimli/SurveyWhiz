package com.surveywizz.surveyservice.util.codeGenerate;

import java.util.Random;

public class Generate {

    public Generate{
        generateCode();
    }

    public String generateCode{
        String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
        int codeLength = 30;

        StringBuilder codeBuilder = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < codeLength; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            codeBuilder.append(randomChar);
        }

        String generatedCode = codeBuilder.toString();
        return generatedCode;
    }

}
