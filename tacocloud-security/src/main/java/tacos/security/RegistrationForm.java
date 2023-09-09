/*
package tacos.security;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.Data;
import tacos.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationForm {
  //DTO
  private String username;
  private String password;
  private String fullName;
  private String street;
  private String city;
  private String state;
  private String zip;
  private String phone;
  private String email;
  
  public User toUser(PasswordEncoder passwordEncoder) {
    return new User(
        username, passwordEncoder.encode(password),
        fullName, street, city, state, zip, phone, email);
  }
  
}
*/
