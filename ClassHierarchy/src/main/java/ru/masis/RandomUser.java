package ru.masis;

import java.util.Random;

public class RandomUser {
    private static Integer userId = 0;
    User user;
    public User generateRandomUser() {
        int i = (int) (Math.random() * 2);
        switch (i) {
            case 0:
                Developer developer = new Developer();
                developer.setId(String.valueOf(userId++));
                developer.setEmail(generaneRandomString());
                developer.setFio(generaneRandomString());
                developer.setNumber(generaneRandomNumber());
                String[] laguages = {generaneRandomString(), generaneRandomString()};
                developer.setLanguages(laguages);
                user =  developer;
                break;
            case 1:
                Manager manager = new Manager();
                manager.setId(String.valueOf(userId++));
                manager.setEmail(generaneRandomString());
                manager.setFio(generaneRandomString());
                manager.setNumber(generaneRandomNumber());
                user = manager;
        }
        return user;
    }
    private String generaneRandomString() {
        Random r = new Random();
        StringBuilder sb = new StringBuilder(5);
        for (int i = 0; i < 5; i++) {
            char tmp =(char) ('a' + r.nextInt('z' - 'a'));
            sb.append(tmp);
        }
        if (userId == 1000)  userId=0;
        return sb.toString();
    }
    private String generaneRandomNumber() {
        int randomLength = new Random().nextInt(8);
        StringBuilder randomNumber = new StringBuilder();
        for (int i = 0; i < randomLength; i++) {
            randomNumber.append(new Random().nextInt(8));
        }
        return randomNumber.toString();
    }
}
