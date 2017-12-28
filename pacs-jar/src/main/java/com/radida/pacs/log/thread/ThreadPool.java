package com.radida.pacs.log.thread;

import java.util.LinkedList;

public class ThreadPool {
    private int max;
    private int min;
    private int startLength;
    private String name;
    private int index = 0;
    private Class<? extends AbstractThread> cls;
    private final LinkedList<AbstractThread> threads = new LinkedList<AbstractThread>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getStartLength() {
        return startLength;
    }

    public void setStartLength(int startLength) {
        this.startLength = startLength;
    }

    public Class<? extends AbstractThread> getCls() {
        return cls;
    }

    public void setCls(Class<? extends AbstractThread> cls) {
        this.cls = cls;
    }

    public LinkedList<AbstractThread> getThreads() {
        return threads;
    }

    public void start() {
        for (int i = 0; i < this.startLength; i++) {
            try {
                addThread();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void check() {
        int index = 0;
        synchronized (this) {
            for (int i = this.threads.size() - 1; i >= 0; i--) {
                if (!threads.get(i).isAlive()) {
                    threads.remove(i);
                    index++;
                }
            }

            int tl = this.threads.size();
            if (tl > max) {
                index = max - tl;
            } else if (tl < min) {
                index = min - tl;
            } else if ((tl + index) > max) {
                index = max - tl;
            }
            if (index > 0) {
                for (int i = 0; i < index; i++) {
                    try {
                        add();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            } else if (index < 0) {
                index = index * -1;
                for (int i = 0; i < index; i++) {
                    this.threads.removeFirst().doStop();
                }
            }
        }
    }

    public void addThread() throws InstantiationException,
            IllegalAccessException {
        synchronized (this) {
            int index = this.threads.size() + 1;
            if (index > max) {
                throw new RuntimeException("index超过最大值");
            }
            add();
        }
    }

    private void add() throws InstantiationException, IllegalAccessException {
        AbstractThread thread = cls.newInstance();
        thread.init();
        thread.setThreadPool(this);
        thread.start();
        thread.setName(name + "_" + index);
        threads.add(thread);
        index++;
    }

    public void removeThread() {
        synchronized (this) {
            if (this.threads.size() > 0) {
                int index = this.threads.size() - 1;
                if (index < min) {
                    throw new RuntimeException("index小于最小值");
                }
                this.threads.removeFirst().doStop();
            }
        }
    }

    public void stop() {
        synchronized (this) {
            for (int i = this.threads.size() - 1; i >= 0; i--) {
                this.threads.removeFirst().doStop();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Thread t = new Thread() {
            @Override
            public void run() {
                throw new RuntimeException();
            }
        };
        t.start();
        Thread t2 = new Thread() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
            }
        };
        t2.start();

        Thread t3 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t3.start();
        Thread.sleep(1000);
        System.out.println(t.getState());
        System.out.println(t.isAlive());

        System.out.println(t2.getState());
        System.out.println(t2.isAlive());

        System.out.println(t3.getState());
        System.out.println(t3.isAlive());
    }
}
