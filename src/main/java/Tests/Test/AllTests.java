package Tests.Test;

import Tests.Test.Que.BlockingQueueExample;
import org.junit.Test;

import javax.inject.Inject;

public class AllTests {




    @Test
public  void starttestQue(){
try{
    BlockingQueueExample blockingQueueExample=new BlockingQueueExample();
    blockingQueueExample.BlockingQueueExample();
    System.out.println("ffffffffffffffffffffffffffff");
} catch ( Exception e) {
        System.err.println(e.getMessage());
    }
}

}
