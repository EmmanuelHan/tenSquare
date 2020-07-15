import com.sun.jersey.core.impl.provider.entity.XMLRootElementProvider;
import com.tensquare.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ObjectUtils;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTests.class)
public class ApplicationTests {

    @Test
    public void test() throws Exception {
        User user = new User();
        user.setState("1");

        log.info("结果：{}",ObjectUtils.isEmpty(user));

        user = null;

        log.info("结果：{}",ObjectUtils.isEmpty(user));

    }

}