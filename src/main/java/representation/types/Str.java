package representation.types;

import Utilities.DBUtil;
import store.Scheme;
import store.Storable;

import java.nio.charset.StandardCharsets;

public class Str extends BasicType
{
	public static Storable.StoreFactory factory = new Storable.StoreFactory()
	{
		@Override
		public Storable restore(byte[] repr, Scheme scheme)
		{
			String string = new String(repr, 0, repr.length - 1, StandardCharsets.UTF_8);
			return new Str(string);
		}
	};

	int byteSize;

	public Str(String repr)
	{
		this.repr = repr.replace(((char) DBUtil.Separators.basic), ' ');
		byteSize = this.repr.getBytes(StandardCharsets.UTF_8).length + 1;
	}

	String repr;

	@Override
	public byte[] store()
	{
		return (repr + (char) DBUtil.Separators.basic).getBytes(StandardCharsets.UTF_8);
	}

	@Override
	public int byteSize()
	{
		return byteSize;
	}

	@Override
	public int compareTo(Object o)
	{
		if (o instanceof Str)
		{
			return repr.compareTo(((Str) o).repr);
		}
		return -1;
	}
}
