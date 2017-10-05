/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author amharb
 */
public class StopWatch {

    private long create = System.currentTimeMillis();
    private long start, stop, pauseAt;

    public void start() {
        start = System.currentTimeMillis();
        pauseAt = 0;
    }

    public long stop() {
        stop = System.currentTimeMillis();
        pauseAt = 0;
        return stop - start;
    }

    public long pause() {
        stop = System.currentTimeMillis();
        pauseAt += stop - start;
        return pauseAt;
    }

    public void resume() {
        start = System.currentTimeMillis();
    }

    public long getPeriod() {
        return pauseAt;
    }

    public long stopAndStart() {
        stop = System.currentTimeMillis();
        long s = start;
        start = System.currentTimeMillis();
        return stop - s;
    }

}
