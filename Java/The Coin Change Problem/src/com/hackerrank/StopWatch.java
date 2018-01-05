package com.hackerrank;

public class StopWatch {

    private long savedDuration = 0;
    private long startTime = System.nanoTime(); //automaticly started when object created

    protected enum StopWatchStatus {
        Running,
        Stoped
    }

    private StopWatchStatus status = StopWatchStatus.Running;

    public void reset() {
        savedDuration = 0;
        startTime = System.nanoTime();
    }

    public void resume() {
        status = StopWatchStatus.Running;
        startTime = System.nanoTime();
    }

    public long stop() {
        savedDuration += System.nanoTime() - startTime;
        status = StopWatchStatus.Stoped;
        return savedDuration;
    }

    public long getTime() {
        switch (status){
            case Running:{
                return savedDuration + (System.nanoTime() - startTime);
            }
            case Stoped:{
                return savedDuration;
            }
            default:{ //useless but java rules :/
                return 0;
            }
        }
    }

    public long getTimeMiliSec() {
        return (getTime() / 1000_000);
    }

    public long getTimeSec() {
        return (getTime() / 1000_000_000);
    }

    public long getTimeMin() {
        return (getTime() / 60_000_000_000L);
    }

    protected StopWatchStatus getStatus(){
        return status;
    }
}
