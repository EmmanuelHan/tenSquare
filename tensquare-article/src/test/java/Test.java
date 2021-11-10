import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode("my_client_secret");
        System.out.println(encode);

        boolean flag = encoder.matches("my_client_secret", "$2a$10$sXWJGpUEQI3o9DfZ1CLnzeMYf2vh.UTLQey8KYftNYK8jIgw1dRZa");

        System.out.println(flag);
    }
}

