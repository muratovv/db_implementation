package representation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.junit.Assert.*;

/**
 * Created by YagfarovRauf on 23.11.15.
 */
public class AVLTreeTest {




    @Test
    public void testInOrder() throws Exception {
        ArrayList<Integer> actual = new ArrayList<>();
        AVLTree<Integer> avlTree = new AVLTree<>();
        ArrayList<Integer> numbers = new ArrayList<>();
        Function<Integer, Void> map = t -> {
            numbers.add(t);
            return null;
        };
        for (int i = 0; i < 10; i++) {
            avlTree.add(i);
            actual.add(i);
        }
        avlTree.inOrder(avlTree.getRoot(), map);
        assertEquals(actual,numbers);
    }
}