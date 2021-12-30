/**
 *
 *  @author Olesiński Wojciech S22368
 *
 */

package zad1;



import java.util.List;
import java.util.stream.Collectors;
import java.util.function.*;

public class ListCreator <T> { // Uwaga: klasa musi być sparametrtyzowana

	private List<T> list;

	private ListCreator(List<T> list) {
		this.list = list;
	}

	public static <T> ListCreator<T> collectFrom(List<T> list) {
		return new ListCreator<T>(list);
	}
	
	public ListCreator<T> when(Predicate<T> predicate){
		return new ListCreator<T>(
				list.stream()
					.filter(predicate::test)
					.collect(Collectors.toList())
			);
	}
	
	public <U> List<U> mapEvery(Function<T, U> function) {
		return list.stream()
					.map(function::apply)
					.collect(Collectors.toList());
	}		
}  
