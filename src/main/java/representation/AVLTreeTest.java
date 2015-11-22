package representation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.*;

/**
 * Created by YagfarovRauf on 23.11.15.
 */
public class AVLTreeTest {




    @Test
    public void testInOrder() throws Exception {
        String actual ="0 1 2 3 4 5 6 7 8 9 ";
        AVLTree<Integer> avlTree = new AVLTree<Integer>();
        String num="";
        Function<Integer, String> map = new Function<Integer, String>() {

            public String apply(Integer t) {
                return t.toString();
            }

        };
        for (int i = 0; i < 10; i++) {
            avlTree.add(i);
        }
        avlTree.inOrder(avlTree.getRoot(), map);
        String res = avlTree.getInorderToText();
        assertEquals(actual, res);

    }
}