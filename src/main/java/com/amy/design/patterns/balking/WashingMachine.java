package com.amy.design.patterns.balking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author :  Amy
 * @Project Name :  designpatternslearn
 * @Package Name :  com.amy.design.patterns
 * @Description :  TODO
 * @Creation Date:  2018-04-24 13:45
 * --------  ---------  --------------------------
 */
public class WashingMachine {
    private static final Logger LOGGER = LoggerFactory.getLogger(WashingMachine.class);
    private WashingMachineState washingMachineState;

    public WashingMachine() {
        washingMachineState = WashingMachineState.ENABLED;
    }

    public WashingMachineState getWashingMachineState() {
        return washingMachineState;
    }

    /**
     * 通过改变机器状态来结束washing
     */
    public synchronized void endofWashing() {
        washingMachineState = WashingMachineState.ENABLED;
        LOGGER.info("{}: Washing completed.", Thread.currentThread().getName());
    }

    /**
     * 当前机器处于合适状态， 该方法负责washing
     */
    public void wash() {
        synchronized (this) {
            LOGGER.info("{}: Actual machine state: {}", Thread.currentThread().getName(), getWashingMachineState());
            if (washingMachineState == WashingMachineState.WASHING) {
                LOGGER.error("ERROR: {} Cannot wash if the machine hash been already washing!", Thread.currentThread().getName());
                return;
            }
            washingMachineState = WashingMachineState.WASHING;
        }
        LOGGER.info("{}: Doing the washing", Thread.currentThread().getName());
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        endofWashing();
    }
}
