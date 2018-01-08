package com.hackerrank;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * @author amhar11
 */
public class TaskStopWatch extends StopWatch {

    private final HashMap<String, Long> list = new HashMap<>();
    private String currTask;
    private long currTaskStartTime;

    public void setTask(String taskName) {
        if (!taskName.equals(currTask)) {
            saveCurrentTaskTime();
            if (!list.containsKey(taskName)) {
                list.put(taskName, 0L);
            }
            setCurrentTask(taskName);
        }
    }

    public String getCurrTask() {
        return currTask;
    }

    public void setTaskAndResume(String taskName) {
        switch (super.getStatus()) {
            case Running:
                setTask(taskName);
                break;
            case Stoped:
                setTask(taskName);
                super.resume();
                break;
        }
    }

    public long getTaskTime(String taskName) {

        switch (super.getStatus()) {
            case Running:
                if (taskName.equals(currTask)) {
                    return list.get(taskName) + (System.nanoTime() - currTaskStartTime);
                } else {
                    if (list.containsKey(taskName)) {
                        return list.get(taskName);
                    } else {
                        return 0L;
                    }
                }
            case Stoped:
                if (list.containsKey(taskName)) {
                    return list.get(taskName);
                } else {
                    return 0L;
                }
            default:
                return 0L;
        }
    }

    @Override
    public long stop() {
        long stopValue = super.stop();
        saveCurrentTaskTime();
        return stopValue;
    }

    @Override
    public void reset() {
        super.reset();
        list.clear();
        currTaskStartTime = System.nanoTime();
    }

    @Override
    public void resume() {
        super.resume();
        currTaskStartTime = System.nanoTime();
    }

    public TaskStopWatch(String FirstTaskName) {
        super();
        list.put(FirstTaskName, 0L);
        setCurrentTask(FirstTaskName);
    }

    private void setCurrentTask(String taskName) {
        currTask = taskName;
        currTaskStartTime = System.nanoTime();
    }

    private void saveCurrentTaskTime() {
        list.put(currTask, list.get(currTask) + (System.nanoTime() - currTaskStartTime));
    }

    public String getReport() {
        //in case the timer is running save current task and set a new starttime
        saveCurrentTaskTime();
        setCurrentTask(currTask);

        long totalTime = 0;
        for (Entry<String, Long> ee : list.entrySet()) {
            totalTime += ee.getValue();
        }

        String r = "";
        for (Entry<String, Long> ee : list.entrySet()) {
            r += ee.getKey() + ": " + (ee.getValue() / 1000_000_000L) + " sec " + ((ee.getValue() * 100L) / totalTime) + "% \n";
        }
        return r;
    }
}
