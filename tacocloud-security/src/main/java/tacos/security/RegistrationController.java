/*
package tacos.security;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tacos.User;
import tacos.data.UserRepository;

@Slf4j
@RestController
@RequestMapping("/register")
//@CrossOrigin("*")
public class RegistrationController {
  
  private UserRepository userRepo;
  private PasswordEncoder passwordEncoder;

  public RegistrationController(
      UserRepository userRepo, PasswordEncoder passwordEncoder) {
    this.userRepo = userRepo;
    this.passwordEncoder = passwordEncoder;
  }
  
  @GetMapping
  public String registerForm() {
    return "registration";
  }

  @PostMapping(consumes="application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public User processRegistration(@RequestBody RegistrationForm form) {
    log.info("등록");
    return userRepo.save(form.toUser(passwordEncoder)).block();
   // return "redirect:/login";
  }

}
*/
