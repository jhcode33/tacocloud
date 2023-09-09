/*
package tacos.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import tacos.User;
import tacos.data.UserRepository;

@RestController
@RequestMapping("/customLogin")
//@CrossOrigin("*")
public class LoginController {

    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostMapping(consumes="application/json")
    public ResponseEntity<User> processLogin(@RequestBody User user) {
        try {
            // 회원가입한 유저인지 확인
            User loginUser = loginUser(user);
            if (loginUser != null) {
                // Security 인증 처리 -> 다른 Controller에서 @Authentication, @Principal 등으로 받아올 수 있음
                Authentication authentication = new UsernamePasswordAuthenticationToken(loginUser, loginUser.getPassword(), loginUser.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                return ResponseEntity.ok(loginUser);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(user);
    }

    private User loginUser(User user){
        User loginUser = null;

        loginUser = userRepository.findByUsername(user.getUsername()).block();
        if (loginUser != null && loginUser.getPassword().equals(user.getPassword())) {
            return loginUser;
        }
        return loginUser;
    }
}
*/
