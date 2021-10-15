import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.model.TestTimedOutException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTest.class)
public class ApplicationTest {


    @Test(timeout = 1000,expected = TestTimedOutException.class)
    public void test() throws Exception {
        TimeUnit.SECONDS.sleep(2);
    }


    @Test(expected = NullPointerException.class)
    public void testException(){
        throw new NullPointerException();
    }

    @Test
    public void testNull(){
        Integer num = null;
        Assert.assertNotNull(num);
    }



}



