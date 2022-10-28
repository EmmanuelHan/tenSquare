import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

    @Test
    public void test1(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("到指定之间运行 "+ new Date(System.currentTimeMillis()));
            }
        }, new Date(System.currentTimeMillis()  + 2000L));
    }

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("到指定之间运行 "+ new Date(System.currentTimeMillis() + 20000L));
            }
        }, new Date(System.currentTimeMillis()  + 20000L));
    }

}
