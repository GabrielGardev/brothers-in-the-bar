import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private final static int consecutiveGlassesNeeded = 3;
    private final static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        System.out.println("Please insert numbers with single space between them:");
        int[] glasses;

        try {
            glasses = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        } catch (Exception e) {
            System.out.println("Invalid input!");
            return;
        }

        int rounds = 0;
        int countGlassesOfSameSize = 1;


        while (glasses.length >= consecutiveGlassesNeeded) {
            for (int glassPos = 0; glassPos < glasses.length - 1; glassPos++) {
                if (glasses[glassPos] == glasses[glassPos + 1]) {
                    countGlassesOfSameSize++;

                    if (countGlassesOfSameSize == consecutiveGlassesNeeded) {
                        rounds++;

                        //removing process
                        glasses = removeTheElements(glasses, glassPos - 1);

                        //starting over from the beginning of the array
                        break;
                    }
                } else {
                    //restart the counter if next glass is't the same size
                    countGlassesOfSameSize = 1;
                }
            }
            //end the program when there aren't any more consecutive glasses
            if (countGlassesOfSameSize < consecutiveGlassesNeeded) {
                break;
            }
            //restart the counter
            countGlassesOfSameSize = 1;
        }

        System.out.println(String.format("Tonight the brothers can drink in %d rounds only.", rounds));
    }

    private static int[] removeTheElements(int[] arr, int index) {

        int[] anotherArray = new int[arr.length - consecutiveGlassesNeeded];

        for (int arrPos = 0, newArrPos = 0; arrPos < arr.length; arrPos++) {
            //skip 3 positions from main array
            if (arrPos == index) {
                arrPos += 2;
                continue;
            }

            anotherArray[newArrPos++] = arr[arrPos];
        }
        return anotherArray;
    }
}
