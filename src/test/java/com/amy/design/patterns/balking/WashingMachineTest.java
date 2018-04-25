package com.amy.design.patterns.balking;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @author :  ZouShumin
 * @Project Name :  designpatternslearn
 * @Package Name :  com.amy.design.patterns.balking
 * @Description :  TODO
 * @Creation Date:  2018-04-25 13:51
 * --------  ---------  --------------------------
 */
public class WashingMachineTest {
    private volatile WashingMachineState machineStateGlobal;

    @Disabled
    @Test
    public void wash() throws Exception {
        WashingMachine washingMachine = new WashingMachine();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(washingMachine::wash);
        executorService.execute(() -> {
            washingMachine.wash();
            machineStateGlobal = washingMachine.getWashingMachineState();
        }) ;
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
        assertEquals(WashingMachineState.WASHING, machineStateGlobal);
    }

    @Test
    public void endofWashing() throws Exception {
        WashingMachine washingMachine = new WashingMachine();
        washingMachine.wash();
        assertEquals(WashingMachineState.ENABLED, washingMachine.getWashingMachineState());
    }


}