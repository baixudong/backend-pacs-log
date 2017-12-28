package com.radida.pacs.log.thread;

public abstract class AbstractThread extends Thread {

    static Long SAVETIME = 100l;

    public static void sleep() {
        try {
            Thread.sleep(SAVETIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public abstract void init();

    private ThreadPool threadPool;

    public ThreadPool getThreadPool() {
        return threadPool;
    }

    public void setThreadPool(ThreadPool threadPool) {
        this.threadPool = threadPool;
    }

    public void doStop() {
        runing = false;
    }

    private boolean runing = true;

    public abstract void execute() throws Exception;

    @Override
    public void run() {
        while (runing) {
            try {
                execute();
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
    }

}
