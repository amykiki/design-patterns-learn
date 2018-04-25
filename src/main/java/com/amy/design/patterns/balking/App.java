package com.amy.design.patterns.balking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author :  ZouShumin
 * @Project Name :  designpatternslearn
 * @Package Name :  com.amy.design.patterns.balking
 * @Description :  TODO
 * @Creation Date:  2018-04-25 13:30
 * --------  ---------  --------------------------
 */
public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        final WashingMachine washingMachine = new WashingMachine();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for(int i = 0; i < 3; i++) {
            executorService.execute(()->washingMachine.wash());
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            LOGGER.error("ERROR: Waiting on executor service shutdown!");
        }
    }
}
