package zad1;

public class StringTask implements Runnable {
	
	private String toConcat;
	private int howManyTimes;
	private volatile TaskState state;
	private String result;
	private Thread thread;

	
	public StringTask(String toConcat, int howManyTimes) {
		this.toConcat = toConcat;
		this.howManyTimes = howManyTimes;
		this.result = "";
		this.state = TaskState.CREATED; 
	}

	public String getResult() {
		return result;
	}
	
	public TaskState getState() {
		return state;
	}
	
	public void start() {
		thread = new Thread(this);
		thread.start();
	}
	
	public void abort() {
		if (state == TaskState.RUNNING) {
			thread.interrupt();
		}
		state = TaskState.ABORTED;
	}
	
	public boolean isDone() {
		return state == TaskState.READY || state == TaskState.ABORTED;
	}
	
	@Override
	public void run() {
		state = TaskState.RUNNING;

		for (int i = 0; i < howManyTimes && !thread.isInterrupted(); i++) {
			result = result + toConcat;
		}
		
		if (state != TaskState.ABORTED)
			state = TaskState.READY;
	}
	
	

}
