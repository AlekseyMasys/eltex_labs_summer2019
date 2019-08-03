package ru.masis;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.masis.entities.Call;
import ru.masis.entities.User;
import ru.masis.repositoryes.CallRepository;
import ru.masis.repositoryes.UserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(UserRepository crudRepository, CallRepository mongodbRepository) throws ParseException {
        ArrayList<User> users = new ArrayList<>();
        User user1 = new User("Ivanov", "Ivan", 1000, "05.11.1993");
        User user2 = new User("Peprov", "Petr", 2000, "11.12.1944");
        User user3 = new User("Alekseev", "Aleksey", 3000, "02.01.2001");
        System.out.println(user1);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        ArrayList<Call> calls = new ArrayList<>();
        calls.add(new Call(10, 11, 20, "05-11-1993"));
        calls.add(new Call(12, 13, 100, "11-12-1944"));
        calls.add(new Call(14, 15, 2000, "02-01-2001"));


        return (args) -> {
            users.forEach(crudRepository::save);
            crudRepository.findAll().forEach(System.out::println);
            System.out.println("----------------------------------");
            crudRepository.findByFname("Ivan").forEach(System.out::println); // ищем только по имени
            System.out.println("----------------------------------");
            crudRepository.findByFnameAndLname("Petr", "Peprov").forEach(System.out::println); // ищем по имени и по фамилии
            System.out.println("-----------------------------------");
            mongodbRepository.saveAll(calls);
            mongodbRepository.findAll().forEach(System.out:: println);
            mongodbRepository.findByDate(new SimpleDateFormat("dd-MM-yyyy").parse("05-11-1993")).forEach(System.out::println); // ищем по дате
        };
    }
}
