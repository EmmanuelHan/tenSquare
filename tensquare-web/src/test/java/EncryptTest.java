import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EncryptTest.class)
public class EncryptTest {

//    @Autowired
//    private RsaService rsaService;

    @Before
    public void before() throws Exception{
    }


    @After
    public void after() throws Exception {
    }
    @Test
    public void genEncryptDataByPubKey() {
        //此处可替换为你自己的请求参数json字符串
        String data = "{\"labelname\":\"java\"}";
        try {
//            String encData = rsaService.RSAEncryptDataPEM(data, RsaKeys.getServerPubKey());
            System.out.println("data: " + data);
//            System.out.println("encData: " + encData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
