package com.xinze.xinze.http;

public class ProgressBean {
    private long progress;
    private long total;
    private boolean done;

    public long getProgress() {
        return progress;
    }

    public long getTotal() {
        return total;
    }

    public boolean isDone() {
        return done;
    }

    public void setBytesRead(long progress) {
        this.progress = progress;
    }

    public void setContentLength(long total) {
        this.total = total;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
