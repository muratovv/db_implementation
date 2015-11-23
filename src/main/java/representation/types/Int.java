package representation.types;

import representation.scheme.TypeScheme;
import store.Storable;

import java.nio.ByteBuffer;

public class Int extends BasicType implements Comparable
{

	public static Storable.StoreFactory factory = new StoreFactory()
	{
		@Override
		public Storable restore(byte[] repr, TypeScheme typeScheme)
		{
			ByteBuffer buffer = ByteBuffer.wrap(repr, 0, 4);
			return new Int(buffer.getInt());
		}
	};

	Integer repr;

	public Int(int repr)
	{
		this.repr = repr;
	}

	@Override
	public int compareTo(Object o)
	{
		if (o instanceof Int)
		{
			return repr.compareTo(((Int) o).repr);
		}
		return -1;
	}

	@Override
	public byte[] store()
	{
		ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
		buffer.putInt(repr);
		return buffer.array();
	}

	@Override
	public int byteSize()
	{
		return Integer.BYTES;
	}

	@Override
	public Object data()
	{
		return repr;
	}

	@Override
	public void setData(Object data)
	{
		repr = ((int) data);
	}
}
