import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTests.class)
public class ApplicationTests {

    @Test
    public void test() throws Exception {

        User user = (User) User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("user")
                .build();

    }


    @Test
    public void createPassword(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode("123456");
        System.out.println(encode);
    }

}