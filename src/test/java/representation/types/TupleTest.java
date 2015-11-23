package representation.types;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import representation.scheme.TypeScheme;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

public class TupleTest
{
	Tuple tuple = null;
	TypeScheme typeScheme = null;

	@Before
	public void setUp()
	{
		ArrayList<Class> classes = new ArrayList<>();
		classes.add(Int.class);
		classes.add(Int.class);
		typeScheme = new TypeScheme(classes);
		tuple = new Tuple(new BasicType[]{
				new Int(5),
				new Int(99999)
		});
	}


	@org.junit.Test
	public void testByteSize() throws Exception
	{
		Assert.assertEquals(8, tuple.byteSize);

	}

	@Test
	public void testStoreRestoreEasy() throws Exception
	{
		byte[] store = tuple.store();
		Tuple restore = ((Tuple) Tuple.factory.restore(store, typeScheme));
		Assert.assertTrue(restore.compareTo(tuple) == 0);
	}

	@Test
	public void testStoreRestoreHard() throws Exception
	{
		ArrayList<Class> classes = new ArrayList<>();
		classes.add(Str.class);
		classes.add(Str.class);
		typeScheme = new TypeScheme(classes);
		tuple = new Tuple(new BasicType[]{
				new Str("abc"),
				new Str("99999")
		});
		byte[] store = tuple.store();
		Tuple restore = ((Tuple) Tuple.factory.restore(store, typeScheme));
		Assert.assertTrue(restore.compareTo(tuple) == 0);
	}

	@Test
	public void testByteSize1() throws Exception
	{
		ByteBuffer buffer = ByteBuffer.allocate(2);
		buffer.put(((byte) 'a'));
		System.out.println(Arrays.toString(buffer.array()));
		buffer.put(((byte) 'b'));
		System.out.println(Arrays.toString(buffer.array()));

		buffer.position(0);
		byte[] bytes = new byte[2];
		buffer.get(bytes);
		System.out.println(Arrays.toString(bytes));
	}
}