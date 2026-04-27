package com.ecommerce.catalogue.demo;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.ecommerce.catalogue.domain.entity.User;

public class UserDemo {

        public static void main(String[] args) {
        
            // List<User> users = List.of(
            //     new User("SIdy", "sidydiop", "password123", true),
            //     new User("Aminata","aminatadiop", "password456", true),
            //     new User("Moussa", "moussadiop", "password789", false)
            // );

            

            // Predicate<User> isActiveUser = u-> u.getIsActive();
            // List<User> activeUsers = users.stream().filter(isActiveUser).collect(Collectors.toList());

            //     System.out.println("\nActive Users:");
            //     activeUsers.forEach(u -> System.out.println("  - " + u.getNom() + " (" + u.getLogin() + ")"));

        }

}
