package com.company.app;

import com.company.database.Filemanager;

import java.io.IOException;

public class AccountnumberGenerator {

    public int randomNumber(){
        return 1000000000 + (int) (Math.random() * 999999999);
    }


    public int generateUniqueAccountNr() throws IOException {
        boolean unique = false;
        int randomNr = randomNumber();

        while (unique == false){
            if (Filemanager.getInstance().readFile("Javabanken", Object.class).contains(randomNr) ){
                randomNr = randomNumber();
            }else {
                unique = true;
                return randomNr;
            }
        }
        return 0;
    }



}
