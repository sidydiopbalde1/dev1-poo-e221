
package com.ecommerce.catalogue.demo;

import com.ecommerce.catalogue.domain.entity.Catalogue;
import com.ecommerce.catalogue.domain.entity.Product;
import com.ecommerce.catalogue.domain.valueobject.Money;
import com.ecommerce.catalogue.domain.valueobject.SKU;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class CatalogueDemo {

    public static void main(String[] args) {

        // --- Jeu de données ---
        Catalogue catalogue = new Catalogue();

        catalogue.add(new Product(new SKU("TEC-10204"), "Smartphone Galaxy A55",  new Money(new BigDecimal("350000"), "FCFA")));
        catalogue.add(new Product(new SKU("TEC-20011"), "Laptop Dell XPS 13",     new Money(new BigDecimal("850000"), "FCFA")));
        catalogue.add(new Product(new SKU("VET-1001"),  "Casque Sony WH-1000XM5", new Money(new BigDecimal("180000"), "FCFA")));
        catalogue.add(new Product(new SKU("ALI-5050"),  "Montre Apple Watch S9",  new Money(new BigDecimal("299.99"), "EUR")));
        catalogue.add(new Product(new SKU("ALI-5099"),  "iPad Pro M4",            new Money(new BigDecimal("1199.00"), "EUR")));

        // =====================================================================
        sep("filter — produits en FCFA");
        // =====================================================================
        List<Product> fcfaProducts = catalogue.findByCurrency("FCFA");
        fcfaProducts.forEach(p -> System.out.println("  " + p.getName() + " → " + p.getPrice()));

        // =====================================================================
        sep("sorted — par prix croissant");
        // =====================================================================
        catalogue.sortedByPriceAsc()
            .forEach(p -> System.out.printf("  %-25s %s%n", p.getName(), p.getPrice()));

        // =====================================================================
        sep("min / max — moins cher et plus cher");
        // =====================================================================
        catalogue.cheapest()
            .ifPresent(p -> System.out.println("  Moins cher : " + p.getName() + " → " + p.getPrice()));

        catalogue.mostExpensive()
            .ifPresent(p -> System.out.println("  Plus cher  : " + p.getName() + " → " + p.getPrice()));

        // =====================================================================
        sep("reduce — total FCFA");
        // =====================================================================
        catalogue.totalByCurrency("FCFA")
            .ifPresent(total -> System.out.println("  Total FCFA : " + total));

        // =====================================================================
        sep("groupingBy — grouper par devise");
        // =====================================================================
        Map<String, List<Product>> grouped = catalogue.groupByCurrency();
        grouped.forEach((currency, list) -> {
            System.out.println("  [" + currency + "]");
            list.forEach(p -> System.out.println("    - " + p.getName()));
        });

        // =====================================================================
        sep("map — extraire tous les SKUs");
        // =====================================================================
        System.out.println("  SKUs : " + catalogue.allSkus());

        // =====================================================================
        sep("anyMatch — SKU existe ?");
        // =====================================================================
        SKU search = new SKU("TEC-20011");
        System.out.println("  TEC-20011 présent : " + catalogue.skuExists(search));
        System.out.println("  ABC-9999  présent : " + catalogue.skuExists(new SKU("ABC-9999")));

        // =====================================================================
        sep("forEach — remise 20% sur tous les produits EUR");
        // =====================================================================
        catalogue.applyDiscountByCurrency("EUR", new BigDecimal("20"));
        catalogue.findByCurrency("EUR")
            .forEach(p -> System.out.println("  " + p.getName() + " après remise → " + p.getPrice()));
    }

    private static void sep(String title) {
        System.out.println("\n─── " + title + " ───");
    }
}





// package com.ecommerce.catalogue.demo;

// import com.ecommerce.catalogue.domain.entity.Product;
// import com.ecommerce.catalogue.domain.valueobject.Money;
// import com.ecommerce.catalogue.domain.valueobject.SKU;

// import java.math.BigDecimal;

// public class CatalogueDemo {

//     public static void main(String[] args) {

//         sep("CAS NOMINAL");
//         Product phone = new Product(
//             new SKU("TEC-10204"),
//             "Smartphone Galaxy A55",
//             new Money(new BigDecimal("15000.00"), "FCFA")
//         );
//         System.out.println("Créé    : " + phone);
//         phone.applyDiscount(new BigDecimal("10"));
//         System.out.println("- 10%   : " + phone.getPrice());

//         sep("PIRATAGE Money");
//         assertThrows("Montant négatif",       () -> new Money(new BigDecimal("-1"), "FCFA"));
//         assertThrows("Devise inconnue",        () -> new Money(BigDecimal.TEN, "XOF"));
//         assertThrows("Devise null",            () -> new Money(BigDecimal.TEN, null));
//         assertThrows("Montant null",           () -> new Money(null, "FCFA"));
//         assertThrows("Addition FCFA + EUR",    () -> new Money(BigDecimal.TEN, "FCFA").add(new Money(BigDecimal.ONE, "EUR")));

//         sep("PIRATAGE SKU");
//         assertThrows("SKU null",               () -> new SKU(null));
//         assertThrows("SKU vide",               () -> new SKU(""));
//         assertThrows("Minuscules tec-1234",    () -> new SKU("tec-1234"));
//         assertThrows("Trop peu ABC-123",       () -> new SKU("ABC-123"));
//         assertThrows("Trop de chiffres",       () -> new SKU("ABC-1234567"));

//         sep("PIRATAGE Product");
//         assertThrows("SKU null",               () -> new Product(null, "Test", new Money(BigDecimal.ONE, "EUR")));
//         assertThrows("Nom vide",               () -> new Product(new SKU("ABC-1234"), "", new Money(BigDecimal.ONE, "EUR")));
//         assertThrows("Prix null",              () -> new Product(new SKU("ABC-1234"), "Test", null));
//         assertThrows("Remise 0",               () -> phone.applyDiscount(BigDecimal.ZERO));
//         assertThrows("Remise 101",             () -> phone.applyDiscount(new BigDecimal("101")));

//         sep("TOUS LES INVARIANTS RESPECTÉS ✓");
//     }

//     @FunctionalInterface
//     private interface ThrowingAction { void execute(); }

//     private static void assertThrows(String scenario, ThrowingAction action) {
//         try {
//             action.execute();
//             System.out.printf("  [ÉCHEC] %s — aucune exception levée !%n", scenario);
//         } catch (Exception e) {
//             System.out.printf("  [OK] %-30s → %s%n", scenario, e.getMessage());
//         }
//     }

//     private static void sep(String title) {
//         System.out.println("\n--- " + title + " ---");
//     }
// }
