package zad1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class XList <T> extends ArrayList<T>{

	public XList(T... elements) {
		Collections.addAll(this, elements);
	}
	
	public XList(Collection<T> collection) {
		super(collection);
	}
	
	public static <T> XList<T> of(T... elements) {
		return new XList<T>(elements);
	}
	
	public static <T> XList<T> of(Collection<T> collection) {
		return new XList<T>(collection);
	}
	
	
	public static XList<String> charsOf(String chars) {
		return XList.of(chars.split(""));
	}
	
	public static XList<String> tokensOf(String chars, String sep) {
		return XList.of(chars.split(sep));
	}
	
	public static XList<String> tokensOf(String chars) {
		return XList.tokensOf(chars, " ");
	}
	
	public XList<T> union(Collection<T> collection) {
		XList<T> result = new XList<>(this);
		result.addAll(collection);
//		this.forEach(result::add);
		return result;
	}
	
	public XList<T> union(T...el) {
		return this.union(XList.of(el));
	}
	
	public XList<T> diff(Collection<T> collection) {
		return new XList<T>(this.stream()
								.filter(el -> !collection.contains(el))
								.collect(Collectors.toList()));
	}
	
	public XList<T> unique() {
		return XList.of(new LinkedHashSet<>(this));
	}
	
	public XList<XList<T>> combine() {
		 XList<XList<T>> copy = new XList<XList<T>>();
		 XList<T> tempList;
		 for (int i = 0; i < size(); i++) {
			 tempList = new XList<T>();
			 
			 ((List<T>) get(i)).forEach(tempList::add);
			 copy.add(tempList);
		 }
		 
		 int n = copy.size();
		 int[] indexes = new int[n];
		 XList<XList<T>> result = new XList<XList<T>>();
		 
		 int currentIndex = 0;
		 
		 boolean end;
		 while (true) {
			 
			 
			 tempList = new XList<T>();
			 
			 for (int i = 0; i < copy.size(); i++) {
				 tempList.add(copy.get(i).get(indexes[i]));
			 }
			 result.add(tempList);
			 
			 end = true;
			 for (int i = 0; i < copy.size() && end; i++) {
				 if (indexes[i] < copy.get(i).size() - 1) end = false;
			 }
			 
			 if (end) break;
			 
			 if (indexes[currentIndex] == copy.get(currentIndex).size() - 1) {
				 while (indexes[currentIndex] == copy.get(currentIndex).size() - 1) {
					 indexes[currentIndex] = 0;
					 currentIndex++;
				 }
				 
					 indexes[currentIndex]++;
					 currentIndex = 0;
				 
			 } else {
				 indexes[currentIndex]++;
			 }

		 }
		 return result;
	 }
	
	public <R> XList<R> collect(Function<T, R> function) {
		return new XList<R>(this.stream()
								.map(function)
								.collect(Collectors.toList()));
	}
	
	public String join(String sep) {
		return this.stream()
					.map(Object::toString)
					.collect(Collectors.joining(sep));
	}
	
	public String join() {
		return this.join("");
	}
	
	public void forEachWithIndex(BiConsumer<T, Integer> consumer) {
		for (int i = 0; i < this.size(); i++) {
			consumer.accept(get(i), i);
		}
	}
	
	
	
}
