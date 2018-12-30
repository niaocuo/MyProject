import java.util.concurrent.*;

/**
 * Created by fs845 on 2018/12/10.
 */
public class ThreadTest {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        FutureTask<Boolean> task = new FutureTask(new Callable<Boolean>() {
            public Boolean call() throws Exception {
                int i = 0;
                while (true) {
                    System.out.println(i);
                    i++;
                }
            }
        });
        Thread thread = new Thread(task);
        executor.submit(thread);

        try {
            task.get(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
            Boolean result = task.cancel(true);
            if (result) {
                thread.stop();
            }
            System.out.println(result);
        }
    }
}
