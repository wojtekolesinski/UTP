package zad1;

import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Magazyn {
	private Stack<Towar> stack = new Stack<>();
	private boolean _oczekujeWiecejDostaw = true;
	
	public void zaladuj(Towar zaladunek) {
		stack.add(zaladunek);
	}
	
	
	
	public Towar rozladuj() {
		while (stack.isEmpty()) {
			try {
				TimeUnit.MILLISECONDS.sleep(10);
			} catch (InterruptedException e) {}
		}
		return stack.pop();
	}
	
	public void zakonczDostawe() {
		_oczekujeWiecejDostaw = false;
	}
	
	public boolean oczekujeWiecejDostaw() {
		return _oczekujeWiecejDostaw;
	}
	
	public boolean pusty() {
		return stack.empty();
	}

}
