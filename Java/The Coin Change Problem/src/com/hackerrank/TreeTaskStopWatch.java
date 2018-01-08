package com.hackerrank;

public class TreeTaskStopWatch extends TaskStopWatch {
    String lastTask = "";
    public TreeTaskStopWatch(String RootTaskName) {
        super(RootTaskName);
        lastTask = RootTaskName;
    }

    @Override
    public void setTask(String taskName) {
        lastTask = super.getCurrTask();
        super.setTask(taskName);
    }

    public void returnToLastTask(){
        super.setTask(lastTask);
    }
}
