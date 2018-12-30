import java.util.concurrent.*;

/**
 * Created by fs845 on 2018/12/10.
 */
public class Test {
    public static void main(String[] args){
        for(int i=0;i<10;i++) {
            FutureTask<Boolean> task = new FutureTask(new Callable<Boolean>() {
                public Boolean call() throws Exception {
                    int i = 0;
                    while (true) {
                        Thread.sleep(100);
                        System.out.println(i);
                        i++;
                    }
                }
            });
            Thread thread = new Thread(task);
            thread.start();
            System.out.println("你是不是单线程啊"+i);
            try {
                task.get(10000, TimeUnit.MILLISECONDS);
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
}
