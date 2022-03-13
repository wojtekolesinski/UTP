/**
 *
 *  @author Olesiński Wojciech S22368
 *
 */

package zad11;



import java.util.List;
import java.util.stream.Collectors;

public class ListCreator <T> { // Uwaga: klasa musi być sparametrtyzowana

	private List<T> list;

	private ListCreator(List<T> list) {
		this.list = list;
	}

	public static <T> ListCreator<T> collectFrom(List<T> list) {
		return new ListCreator<T>(list);
	}
	
	public ListCreator<T> when(Selector<T> selector) {
		return new ListCreator<T>(
				list.stream()
					.filter(selector::select)
					.collect(Collectors.toList())
			);
	}
	
	public <U> List<U> mapEvery(Mapper<T, U> mapper) {
		return list.stream()
					.map(mapper::map)
					.collect(Collectors.toList());
	}		
}  
