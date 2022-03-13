/**
 *
 *  @author Olesiński Wojciech S22368
 *
 */

package zad11;

@FunctionalInterface
public interface Mapper <T, U>{ // Uwaga: interfejs musi być sparametrtyzowany
	
	abstract U map(T value);
}  
