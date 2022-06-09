package com.ic1101.middle.async;

import com.ic1001.middle.async.async.AsyncTask;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SpringBootTest
public class AsyncTest {
    private static final Logger log = LoggerFactory.getLogger(AsyncTest.class);
    @Autowired
    private AsyncTask asyncTask;

    @Test
    public void testAsync() throws InterruptedException, ExecutionException {
        asyncTask.dealNoReturnTask();

        Future<String> f = asyncTask.dealHaveReturnTask(5);

        log.info("主线程执行finished");

        log.info(f.get());
    }

}
