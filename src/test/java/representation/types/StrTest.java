package representation.types;

import org.junit.Assert;
import org.junit.Test;
import representation.scheme.TypeScheme;

public class StrTest
{
	
	@Test
	public void testStore() throws Exception
	{
		Str abc = new Str("abc");
		Assert.assertEquals("abc", ((Str) Str.factory.restore(abc.store(), new TypeScheme(null))).repr);
	}

	@Test
	public void testByteSize() throws Exception
	{
		Str abc = new Str("abc");
		Assert.assertEquals(4, abc.store().length);
	}
}