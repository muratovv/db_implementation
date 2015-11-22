package representation.types;

import Utilities.DBUtil;
import exceptions.RestoreException;
import store.Scheme;
import store.Storable;

import java.nio.ByteBuffer;
import java.util.ArrayList;

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
			ArrayList<Integer> allSep = DBUtil.Separators.findAllSep(repr, DBUtil.Separators.basic);
			BasicType[] values = new BasicType[scheme.getSize()];
			byte[] dst;
			for (int i = 0; i < values.length; i++)
			{
				int typeSize = scheme.getTypeSize(i);
				if (typeSize != -1)
					dst = new byte[typeSize];
				else
				{
					final int finalI = i;
					int len = allSep.stream().filter(integer -> integer > allSep.get(finalI)).findFirst().get();
					dst = new byte[len - allSep.get(i)];
				}
				buffer.get(dst);
				values[i] = ((BasicType) scheme.getFactory(i).restore(dst, scheme));
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
