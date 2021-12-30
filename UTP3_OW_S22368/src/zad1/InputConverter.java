package zad1;


import java.util.function.Function;

public class InputConverter <T> {
	private T input;
	
	public InputConverter(T input) {
		this.input = input;
	}
	
	public <R> R convertBy(Function... functions) {
		Object input = this.input;
		Object result = null;
		
		for (Function function: functions) {
			result = function.apply(input);
			input = result;
		}
		
		return (R) result;
	}
}
