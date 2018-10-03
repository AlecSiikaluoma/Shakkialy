package test;

import main.data.structures.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by alecsiikaluoma on 3.10.2018.
 */
public class ArrayListTest {

    @Test
    public void add() {
        ArrayList<Integer> ints = new ArrayList<>();
        assertTrue(ints.isEmpty());
        assertTrue(ints.size() == 0);
        ints.add(5);
        assertEquals(ints.size(), 1);
        assertFalse(ints.isEmpty());
        ints.add(6);
        assertTrue(ints.get(1) == 6);

        ints = new ArrayList<>();
        int count = 0;
        for(int i = 0; i < 25; i++) {
            ints.add(count);
            count++;
        }
        assertTrue(ints.get(24) == 24);
    }

}
