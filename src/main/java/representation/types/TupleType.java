package representation.types;

import exceptions.RestoreException;
import store.Scheme;
import store.Storable;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.nio.ByteBuffer;

/**
 * Represent tuple type of node
 */
public class TupleType implements Storable, Comparable
{
	public static Storable.StoreFactory factory = new StoreFactory()
	{
		@Override
		public Storable restore(byte[] repr, Scheme scheme) throws RestoreException
		{
			ByteBuffer buffer = ByteBuffer.wrap(repr);
			BasicType[] values = new BasicType[scheme.getSize()];
			byte[] dst;
			int len;
			for (int i = 0; i < values.length; i++)
			{
				//TODO 23.11.15
				throw new NotImplementedException();
			}
			TupleType tupleType = new TupleType();
			tupleType.values = values;
			return tupleType;
		}
	};

	BasicType[] values;
	int byteSize = 0;

	public TupleType(BasicType[] values)
	{
		this.values = values;
		for (BasicType value : values)
		{
			byteSize += value.byteSize();
		}
	}

	private TupleType()
	{
	}

	@Override
	public byte[] store()
	{
		ByteBuffer buffer = ByteBuffer.allocate(byteSize);
		for (BasicType value : values)
		{
			buffer.put(value.store());
		}
		return buffer.array();
	}

	@Override
	public int byteSize()
	{
		return byteSize;
	}

	@Override
	public int compareTo(Object obj)
	{
		if (obj instanceof TupleType)
		{
			TupleType o = (TupleType) obj;
			for (int i = 0; i < values.length; i++)
			{
				int compareTo = values[i].compareTo(o.values[i]);
				if (compareTo != 0)
					return compareTo;
			}
			return 0;
		}
		return -1;
	}
}
