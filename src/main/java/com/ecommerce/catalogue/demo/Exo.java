package com.ecommerce.catalogue.demo;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.ecommerce.catalogue.domain.entity.Product1;
import com.ecommerce.catalogue.domain.entity.Student;
import com.ecommerce.catalogue.domain.entity.Transaction;
import com.ecommerce.catalogue.domain.entity.User;

public class Exo {
        public static void main(String[] args) {

            System.out.println("NIVEAU 1 : Les Bases (Lambdas & Interfaces Fonctionnelles) :\n");
            //Exo 1
            System.out.println("Exo 1 : Vérifier si un nombre est positif.");            
            Predicate<Integer> isPostive = n -> n > 0;
            System.out.println("Est positive ? " + isPostive.test(5));


            //Exo 2: Le consommateur
            System.out.println("Exo 2 : Afficher une chaîne de caractères en majuscules avec le préfixe '[LOG]'.");
            Consumer<String> logConsumer = s -> System.out.println("[LOG] " + s.toUpperCase());
            logConsumer.accept("Hello world");


            //Ex 3 : La Transformation (Function) 
            System.out.println("Exo 3 : Transformer une chaîne représentant un nombre en sa valeur entière multipliée par 2.");
            Function<String, Integer> doubleValue = s -> Integer.parseInt(s) * 2;
            System.out.println("Valeur double : " + doubleValue.apply("10"));


            //Ex 4 : Le Fournisseur (Supplier)
            System.out.println("Créez un `Supplier<Double>` qui génère un taux d'intérêt aléatoire entre 0.0 et 5.0");

            Supplier<Double> interestRateSupplier = () -> Math.random() * 5.0;
            System.out.println("Taux d'intérêt aléatoire : " + interestRateSupplier.get());


            //Ex 5 : Interface Personnalisée*
            System.out.println("Exo 5 : Créez une instance qui valide si une chaîne n'est pas nulle et contient le caractère \"@\"");
            @FunctionalInterface 
            interface Validator {
                 boolean validate(String s); 
            }
            Validator emailValidator = s -> s != null && s.contains("@");
            System.out.println("Email valide ? " + emailValidator.validate("sidy@gmail.com"));


            System.out.println("\n---------------------------------------\n");

            System.out.println("\nNIVEAU 2 : Stream API - Filtrage & Transformation\n");

            //Ex 6 : Filtrage simple*
            List<String> fruits= List.of("Pomme", "Banane", "Cerise", "Avocat", "Ananas");

            List<String> result = fruits.stream().filter(f -> f.startsWith("A")).toList();
            System.out.println("Fruits commençant par 'A' : " + result);

            //Ex 7 : Extraction de données

            List<User> users = List.of(
                new User("SIdy", "sidydiop@gmail.com", "password123", true, "USER"),
                new User("Aminata", "aminatadiop@gmail.com", "password456", false, "USER"),
                new User("Moussa", "moussadiop@gmail.com", "password789", true, "USER")
            );

            List<String>emails = users.stream().map(User::getLogin).toList();
            System.out.println("Adresses email : " + emails);

            //Ex 8 : Calcul d'agrégation
            List<Integer> prices = List.of(100, 200, 300, 400, 500);
            Predicate<Integer> estSup100 = p -> p > 100;
            int total = prices.stream().filter(estSup100).reduce(0, Integer::sum);
            System.out.println("Prix total sup à 100 : " + total);

            //Ex 9 : Unicité et Tri
            List<String> names = List.of("Ali", "Bobo", "Sidy", "Ali", "Bobo", "Ndeye");
            List<String> uniqueNames = names.stream().distinct().sorted().toList();
            System.out.println("Noms uniques triés : " + uniqueNames);

            //Ex 10 : Recherche ciblée*

            Optional<User> admin = users.stream().filter(u -> u.getRole().equals("ADMIN")).findAny();
            System.out.println("Utilisateur avec rôle ADMIN : " + admin.map(User::getNom).orElse("Aucun trouvé"));

            System.out.println("\n---------------------------------------\n");

            System.out.println("\nNIVEAU 3 : Avancé (Collecteurs & Optional)\n");

            //Ex 11 : Groupement de données
            //À partir d'une `List<Transaction> transactions`, créez une `Map<String, List<Transaction>>` où les transactions sont regroupées par leur devise (ex: "EUR", "XOF").
            List<Transaction> transactions = List.of(
                new Transaction("T1", "EUR", 100.0),
                new Transaction("T2", "USD", 200.0),
                new Transaction("T3", "EUR", 150.0),
                new Transaction("T4", "USD", 50.0)
            );
            Map<String, List<Transaction>> byCurrency = transactions.stream().collect(Collectors.groupingBy(Transaction::getDevise));
            byCurrency.forEach((currency, list) -> {
                System.out.println("Devise : " + currency);
                list.forEach(t -> System.out.println("  - " + t.getId() + " : " + t.getAmount()));
            });

            //Ex 12 : Statistiques descriptives
            List<Product1> products = List.of(
              new Product1("Produit A", 100.0),
              new Product1("Produit B", 200.0),
              new Product1("Produit C", 300.0)
            );

            DoubleSummaryStatistics stats = products.stream().mapToDouble(Product1::getPrice).summaryStatistics();
            System.out.println("Prix - Min: " + stats.getMin() + ", Max: " + stats.getMax() + ", Moyenne: " + stats.getAverage() + ", Total: " + stats.getSum());

            //Ex 13 : Partitionnement
            List<Student> students = List.of(
                new Student("Sidy", 10.0),
                new Student("Baba", 20.0),
                new Student("Penda", 9.0)
            );
            Map<Boolean, List<Student>> partitioned = students.stream().collect(Collectors.partitioningBy(s -> s.getNote() >= 10));
            System.out.println("Étudiants avec note >= 10 : " + partitioned.get(true).stream().map(Student::getName).toList());
            System.out.println("Étudiants avec note < 10 : " + partitioned.get(false).stream().map(Student::getName).toList());

            //Ex 14 : Aplatissement (FlatMap)
            
        }

}
