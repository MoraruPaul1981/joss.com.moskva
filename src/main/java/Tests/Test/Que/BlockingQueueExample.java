package Tests.Test.Que;

import javax.inject.Named;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


@Named
public class BlockingQueueExample {
    private BlockingQueue<String> drop    = new ArrayBlockingQueue<String>(1, true);;

    private final String DONE = "done";
    private final String[] messages = {
            "Мама пошла готовить обед",
            "Мама позвала к столу",
            "Дети кушают молочную кашу",
            "А что ест папа?"};

    public BlockingQueueExample() {

    }

    public void BlockingQueueExample() {
       (new Thread(new Producer())).start();
       (new Thread(new Consumer())).start();
       System.out.println("ffffffffffffffffffffffffffff");
    }

    class Producer implements Runnable {
        public void run() {
            try {
                int cnt = 0;
                for (int i = 0; i < messages.length; i++) {
                    drop.put(messages[i]);

                        Thread.sleep(2000);
                }
                drop.put(DONE);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    class Consumer implements Runnable {
        public void run() {
            try {
                String msg = null;
                while (!((msg = drop.take()).equals(DONE)))
                    System.out.println(msg);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}