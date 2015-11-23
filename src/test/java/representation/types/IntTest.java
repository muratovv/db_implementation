package representation.types;


import org.junit.Assert;
import org.junit.Test;
import representation.scheme.TypeScheme;

public class IntTest
{
	Int anInt1 = new Int(1);
	Int anInt2 = new Int(2);

	@Test
	public void testCompareTo() throws Exception
	{
		Assert.assertTrue(anInt1.compareTo(anInt2) != 0);
		Assert.assertTrue(anInt1.compareTo(anInt1) == 0);
		Assert.assertTrue(anInt1.compareTo(new Int(1)) == 0);
	}

	@Test
	public void testStoreRestore() throws Exception
	{
		byte[] store = anInt1.store();
		Int restore = ((Int) Int.factory.restore(store, new TypeScheme(null)));
		Assert.assertTrue(restore.compareTo(new Int(1)) == 0);
	}

	@Test
	public void testByteSize() throws Exception
	{
		Assert.assertTrue(anInt1.store().length == Integer.BYTES);
	}
}