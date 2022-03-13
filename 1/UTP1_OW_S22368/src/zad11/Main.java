/**
 *
 *  @author Olesiński Wojciech S22368
 *
 */

package zad11;



import java.util.*;

public class Main {
  public Main() {
    List<Integer> src1 = Arrays.asList(1, 7, 9, 11, 12);
    System.out.println(test1(src1));

    List<String> src2 = Arrays.asList("a", "zzzz", "vvvvvvv" );
    System.out.println(test2(src2));
  }

  public List<Integer> test1(List<Integer> src) {
    Selector<Integer> sel = new Selector<Integer>() {
    	@Override
		public boolean select(Integer value) {
    		return value < 10;
    	}
    };
    Mapper<Integer, Integer> map = new Mapper<Integer, Integer>() {
    	@Override
    	public Integer map(Integer value) {
    		return value + 10;
    	}
    };
    return   ListCreator.<Integer> collectFrom(src).when(sel).mapEvery(map);
  }

  public List<Integer> test2(List<String> src) {
	  Selector<String> sel = new Selector<String>() {
	    	@Override
			public boolean select(String value) {
	    		return value.length() > 3;
	    	}
	    };
	    Mapper<String, Integer> map = new Mapper<String, Integer>() {
	    	@Override
	    	public Integer map(String value) {
	    		return value.length() + 10;
	    	}
	    };
    return   ListCreator.<String> collectFrom(src).when(sel).mapEvery(map);
  }

  public static void main(String[] args) {
    new Main();
  }
}