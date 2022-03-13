package zad2;

import java.util.concurrent.Callable;

class SumTask implements Callable<Integer> {

    private int taskNum,
            limit;

    public SumTask(int taskNum, int limit) {
        this.taskNum = taskNum;
        this.limit = limit;
    }

    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 1; i <= limit; i++) {
            if (Thread.currentThread().isInterrupted()) return null;
            sum+=i;
            Thread.sleep(1000);
        }
        return sum;
    }
    
    @Override
    public String toString() {
    	return "task"+taskNum;
    }
};