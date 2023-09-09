package tacos;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tacos.Ingredient.Type;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;
import tacos.data.UserRepository;

@Profile("!prod")
@Configuration
public class DevelopmentConfig {

  @Bean
  public CommandLineRunner dataLoader(IngredientRepository repo,
        UserRepository userRepo, PasswordEncoder encoder, TacoRepository tacoRepo) { // user repo for ease of testing with a built-in user
    return args -> {
      Set<String> uniqueIngredientIds = new HashSet<>();

      Ingredient flourTortilla = new Ingredient("FLTO", "Flour Tortilla", Type.WRAP);
      Ingredient cornTortilla = new Ingredient("COTO", "Corn Tortilla", Type.WRAP);
      Ingredient groundBeef = new Ingredient("GRBF", "Ground Beef", Type.PROTEIN);
      Ingredient carnitas = new Ingredient("CARN", "Carnitas", Type.PROTEIN);
      Ingredient tomatoes = new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES);
      Ingredient lettuce = new Ingredient("LETC", "Lettuce", Type.VEGGIES);
      Ingredient cheddar = new Ingredient("CHED", "Cheddar", Type.CHEESE);
      Ingredient jack = new Ingredient("JACK", "Monterrey Jack", Type.CHEESE);
      Ingredient salsa = new Ingredient("SLSA", "Salsa", Type.SAUCE);
      Ingredient sourCream = new Ingredient("SRCR", "Sour Cream", Type.SAUCE);

      Flux<Ingredient> ingredients = Flux.just(
              flourTortilla, cornTortilla, groundBeef, carnitas,
              tomatoes, lettuce, cheddar, jack, salsa, sourCream);

      ingredients
              .filter(ingredient -> uniqueIngredientIds.add(ingredient.getId())) // Filter out duplicates
              .flatMap(repo::save) // Save unique ingredients
              .then()
              .subscribe();

      //== Create Sample User data ==//
      User user = new User("habuma", encoder.encode("password"),
              "Craig Walls", "123 North Street", "Cross Roads", "TX",
              "76227", "123-123-1234", "test@test.com");

      userRepo.save(user).subscribe();


      Taco taco1 = new Taco();
      taco1.setId("taco1");
      taco1.setName("Carnivore");
      taco1.setIngredients(Arrays.asList(flourTortilla, groundBeef, carnitas, sourCream, salsa, cheddar));

      Taco taco2 = new Taco();
      taco2.setId("taco2");
      taco2.setName("Bovine Bounty");
      taco2.setIngredients(Arrays.asList(cornTortilla, groundBeef, cheddar, jack, sourCream));

      Taco taco3 = new Taco();
      taco3.setId("taco3");
      taco3.setName("Veg-Out");
      taco3.setIngredients(Arrays.asList(flourTortilla, cornTortilla, tomatoes, lettuce, salsa));

      Flux<Taco> tacos = Flux.just(taco1, taco2, taco3);

      tacos
        .flatMap(taco -> {
            if (!uniqueIngredientIds.contains(taco.getId())) {
              return tacoRepo.save(taco); // Save unique tacos
            } else {
              return Mono.empty(); // Skip duplicates
            }
        })
        .then()
        .subscribe();
    };
  }
}
