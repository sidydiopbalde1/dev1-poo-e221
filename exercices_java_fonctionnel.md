# SÉANCE 2 : Programmation Fonctionnelle & API Stream
## Cahier d'Exercices (100% Pratique)

**Objectif :** Maîtriser les expressions Lambdas, l'API Stream et l'utilisation de `Optional<T>` pour écrire un code Java moderne, lisible et robuste.

**Instructions :**
- Remplacez les commentaires `// TODO` par l'implémentation correspondante.
- **Règle d'or :** L'utilisation de boucles classiques (`for`, `while`) et de blocs `if` traditionnels est interdite. Utilisez uniquement les Streams et les Lambdas.

---

### 🟢 NIVEAU 1 : Les Bases (Lambdas & Interfaces Fonctionnelles)

**Ex 1 : Le Prédicat Simple**
Créez un `Predicate<Integer>` nommé `isPositive` qui vérifie si un nombre est strictement supérieur à zéro.
```java
Predicate<Integer> isPositive = // TODO
```

**Ex 2 : Le Consommateur**
Créez un `Consumer<String>` qui affiche une chaîne de caractères en majuscules avec le préfixe "[LOG]".
```java
Consumer<String> logger = // TODO
```

**Ex 3 : La Transformation (Function)**
Créez une `Function<String, Integer>` qui prend une chaîne représentant un nombre (ex: "10") et retourne sa valeur entière multipliée par 2.
```java
Function<String, Integer> doubleValue = // TODO
```

**Ex 4 : Le Fournisseur (Supplier)**
Créez un `Supplier<Double>` qui génère un taux d'intérêt aléatoire entre 0.0 et 5.0.
```java
Supplier<Double> interestRateSupplier = // TODO
```

**Ex 5 : Interface Personnalisée**
Soit l'interface `@FunctionalInterface interface Validator { boolean validate(String s); }`. Créez une instance qui valide si une chaîne n'est pas nulle et contient le caractère "@".
```java
Validator emailValidator = // TODO
```

---

### 🟡 NIVEAU 2 : Stream API - Filtrage & Transformation

**Ex 6 : Filtrage simple**
À partir d'une `List<String> fruits`, récupérez une liste contenant uniquement les fruits qui commencent par la lettre "A".
```java
List<String> result = fruits.stream(). // TODO
```

**Ex 7 : Extraction de données**
Soit une `List<User> users`. Récupérez une `List<String>` contenant uniquement les adresses emails de ces utilisateurs.
```java
List<String> emails = users.stream(). // TODO
```

**Ex 8 : Calcul d'agrégation**
Soit une `List<Integer> prices`. Calculez la somme totale de tous les prix supérieurs à 100.
```java
int total = prices.stream(). // TODO
```

**Ex 9 : Unicité et Tri**
À partir d'une liste de noms contenant des doublons, retournez une liste de noms uniques, triés par ordre alphabétique.
```java
List<String> uniqueNames = names.stream(). // TODO
```

**Ex 10 : Recherche ciblée**
Trouvez n'importe quel utilisateur (`findAny`) dont le rôle est égal à "ADMIN". Le résultat doit être un `Optional<User>`.
```java
Optional<User> admin = users.stream(). // TODO
```

---

### 🟠 NIVEAU 3 : Avancé (Collecteurs & Optional)

**Ex 11 : Groupement de données**
À partir d'une `List<Transaction> transactions`, créez une `Map<String, List<Transaction>>` où les transactions sont regroupées par leur devise (ex: "EUR", "XOF").
```java
Map<String, List<Transaction>> byCurrency = transactions.stream(). // TODO
```

**Ex 12 : Statistiques descriptives**
Sur une `List<Product>`, obtenez en une seule opération le prix maximum, le prix minimum et la moyenne des prix (Utilisez `Collectors.summarizingDouble`).
```java
DoubleSummaryStatistics stats = products.stream(). // TODO
```

**Ex 13 : Partitionnement**
Séparez une liste d'étudiants en deux groupes : ceux qui ont une note supérieure ou égale à 10 (moyenne) et les autres.
```java
Map<Boolean, List<Student>> results = students.stream(). // TODO
```

**Ex 14 : Aplatissement (FlatMap)**
Soit une `List<Order>`, où chaque commande contient une `List<Item>`. Récupérez une liste unique (`List<Item>`) contenant tous les articles de toutes les commandes passées.
```java
List<Item> allItems = orders.stream(). // TODO
```

**Ex 15 : Chaînage sécurisé (Optional)**
Récupérez le nom de la rue (`street`) d'un utilisateur. Si l'utilisateur est nul, ou si son adresse est nulle, ou si la rue est nulle, retournez par défaut la chaîne "Rue inconnue".
```java
String street = Optional.ofNullable(user). // TODO
```

---

### 🔴 NIVEAU 4 : Expert (Résilience & Cas Réels)

**Ex 16 : Composition de Prédicats**
Créez un prédicat complexe pour une transaction qui est valide **SI** (le montant est positif **ET** la devise est "XOF") **OU BIEN SI** le type de l'utilisateur est "PREMIUM".
```java
Predicate<Transaction> isXof = // TODO
Predicate<Transaction> isPremium = // TODO
Predicate<Transaction> complexValidator = // TODO (utilisez .and() et .or())
```

**Ex 17 : Nettoyage de flux (Optional + Stream)**
À partir d'une `List<String> ids`, cherchez les utilisateurs correspondants en base de données via `repository.findById(id)` (qui retourne un `Optional<User>`). Produisez une `List<User>` ne contenant que les utilisateurs réellement trouvés.
```java
List<User> existingUsers = ids.stream(). // TODO
```

**Ex 18 : Challenge Final - L'Importateur Résilient**
Vous recevez une `List<String>` où chaque ligne représente un employé au format CSV : `"nom;age;salaire"`. 
**Contrainte :** Certaines lignes peuvent être mal formées ou contenir des erreurs de type (ex: un âge qui n'est pas un nombre).
**Objectif :** Transformez ce flux en `List<Employee>` en ignorant silencieusement les lignes corrompues.

```java
List<Employee> employees = csvLines.stream(). // TODO
```
