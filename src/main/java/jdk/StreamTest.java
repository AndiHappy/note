package jdk;

import java.util.Arrays;
import java.util.List;

/**
 * @author guizhai
 *
 */
public class StreamTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> myList =Arrays.asList("a1", "a2", "b1", "c2", "c1");

		myList
		    .stream()
		    .filter(s -> s.startsWith("c"))
		    .map(String::toUpperCase)
		    .sorted()
		    .forEach(System.out::println);

	}

}