import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class RandomUtilTest {
	/**
	 * 伪随机
	 * @param args
	 */
    public static void main(String[] args) {
        Random random = new Random(10);

        int randomArr[] = new int[100];
        for (int i = 0; i < 100; i++) {
            randomArr[i] = random.nextInt(100);
        }


        Stream.of(randomArr).forEach(System.out::println);
    }

    /**
     * flapMap：拆解流
     */
    @Test
    public void testFlapMap1() {
        String[] arr1 = {"a", "b", "c", "d"};
        String[] arr2 = {"e", "f", "c", "d"};
        String[] arr3 = {"h", "j", "c", "d"};
        // Stream.of(arr1, arr2, arr3).flatMap(x -> Arrays.stream(x)).forEach(System.out::println);
        Stream.of(arr1, arr2, arr3).flatMap(Arrays::stream).forEach(System.out::println);
    }
}
