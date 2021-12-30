package zad2;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe <T> {
	private T value;
	
	public Maybe() {}
	
	public Maybe(T value) {
		this.value = value;
	}
	
	public static <R> Maybe<R> of(R value) {
		if (value == null) return new Maybe();
		
		return new Maybe<R>(value);
	}
	
	void ifPresent(Consumer cons) {
		if (value != null) cons.accept(value);
	}
	
	public <R> Maybe<R> map(Function<T, R> func) {
		if (value != null) {
			return new Maybe<R>(func.apply(value));
		}
		return new Maybe();
	}
	
	public T get() throws NoSuchElementException {
		if (value != null) return value;
		throw new NoSuchElementException("maybe is empty");
	}
	
	public boolean isPresent() {
		return value != null;
	}
	
	public T orElse(T defVal) {
		if (value != null) return value;
		return defVal;
	}
	
	public Maybe<T> filter(Predicate<T> pred) {
		if (value == null || pred.test(value)) return this;
		return new Maybe();
	}
	
	@Override
	public String toString() {
		if (value == null) 
			return "Maybe is empty";
		else
			return "Maybe has value " + value;
	}
}
