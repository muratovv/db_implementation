package representation.types;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import store.Scheme;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

public class TupleTypeTest
{
	TupleType tupleType = null;
	Scheme scheme = null;

	@Before
	public void setUp()
	{
		ArrayList<Class> classes = new ArrayList<>();
		classes.add(Int.class);
		classes.add(Int.class);
		scheme = new Scheme(classes);
		tupleType = new TupleType(new BasicType[]{
				new Int(5),
				new Int(99999)
		});
	}


	@org.junit.Test
	public void testByteSize() throws Exception
	{
		Assert.assertEquals(10, tupleType.byteSize);

	}

	@Test
	public void testStoreRestore() throws Exception
	{
		byte[] store = tupleType.store();
		TupleType restore = ((TupleType) TupleType.factory.restore(store, scheme));
		Assert.assertTrue(restore.compareTo(tupleType) == 0);
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