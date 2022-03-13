/**
 *
 *  @author Olesiński Wojciech S22368
 *
 */

package zad11;

@FunctionalInterface
public interface Selector <T> { // Uwaga: interfejs musi być sparametrtyzowany
	
	abstract boolean select(T value);
}  
