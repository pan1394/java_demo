package think.practice;

/**
 *
 * <b>A class for print arguments of java command</b>
 * @author  Pan Yilin
 * @version  1.0
 *
 */
public class ArgsPrinters {

	/**
	 *
	 * <pre>print all arguments of Class</pre> @See ArgsPrinters.
	 * @param args command arguments
	 *
	 */
	public static void main(String[] args) {

		int count = 0;
		for (String arg : args) {
			count ++;
			System.out.printf("argument %d: %s\n", count, arg);
		}
	}
}
