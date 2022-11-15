import org.junit.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureTest {

    @Test
    public void test1() throws InterruptedException {
        CompletableFuture<List> objectCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List list = null;
            return list;
        });

        Thread.sleep(10);

        System.out.println(System.currentTimeMillis());

        List list = null;
        try {
            list = objectCompletableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(list);

    }


}
