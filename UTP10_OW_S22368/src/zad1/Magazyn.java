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
	private volatile Queue<Towar> kolejka = new ConcurrentLinkedQueue<>();
	private volatile Stack<Towar> stack = new Stack<>();
	
	private Lock lock = new ReentrantLock();
	private Condition towarDostarczony = lock.newCondition();
	private Condition towarZliczony = lock.newCondition();
	
	private Towar towar = null;
	private boolean nowyTowar = false;
	private boolean _oczekujeWiecejDostaw = true;
	
	public void zaladuj(Towar zaladunek) {
		lock.lock();
		try {
			stack.add(zaladunek);
			towarDostarczony.signal();
		} finally {
			ReentrantLock l = (ReentrantLock) lock;
			if (l.isHeldByCurrentThread()) lock.unlock();
		}
//		kolejka.add(zaladunek);
//		towarDostarczony.signal();
		/*
		 * lock.lock(); try { while (nowyTowar) towarZliczony.await(); towar =
		 * zaladunek; nowyTowar = true; towarDostarczony.signal(); } catch
		 * (InterruptedException e) { } finally { lock.unlock(); }
		 */
	}
	
	
	
	public Towar rozladuj() {
		lock.lock();
		try {
			while (stack.empty()) {
				try {
					towarDostarczony.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return stack.pop();
		} finally {
			ReentrantLock l = (ReentrantLock) lock;
			if (l.isHeldByCurrentThread()) lock.unlock();
		}
		
		
//		while (kolejka.isEmpty()) {
//			try {
//				TimeUnit.MILLISECONDS.sleep(10);
////				towarDostarczony.await();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		return kolejka.poll();
		
		/*
		 * lock.lock(); try { while (!nowyTowar) towarDostarczony.await(); nowyTowar =
		 * false; towarZliczony.signal(); return towar; } catch (InterruptedException e)
		 * { return null; } finally { lock.unlock(); }
		 */
		
	}
	
	public void zakonczDostawe() {
		_oczekujeWiecejDostaw = false;
	}
	
	public boolean oczekujeWiecejDostaw() {
		return _oczekujeWiecejDostaw;
	}
	
	public boolean pusty() {
		return stack.empty();
//		return kolejka.isEmpty();
	}

}
