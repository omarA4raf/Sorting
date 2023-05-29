import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortArrayTestCorrectness {
    static SortArray sortArray;
    static String path = "src\\tests\\positive10.txt";
    @BeforeAll
    static void init(){
        sortArray = new SortArray(path);
    }
    static boolean checkSorting(int[] array){
        for (int i=0;i<array.length-1;i++){
            if(array[i] > array[i+1]){
                return false;
            }
        }
        return true;
    }
    @Test
    void testSimpleSort(){
        int result[] = sortArray.simpleSort(true);
        assertTrue(checkSorting(result));
    }
    @Test
    void testEfficientSort(){
        int result[] = sortArray.efficientSort(true);
        assertTrue(checkSorting(result));
    }
    @Test
    void testNonComparisonSort(){
        int result[] = sortArray.nonComparisonSort(true);
        assertTrue(checkSorting(result));
    }
}