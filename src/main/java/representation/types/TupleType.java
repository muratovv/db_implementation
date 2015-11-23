package representation.types;

import Utilities.DBUtil;
import exceptions.RestoreException;
import store.Scheme;
import store.Storable;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Optional;

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
			int len = 0;
			int currentSize = 0;
			for (int i = 0; i < values.length; i++)
			{
				int typeSize = scheme.getTypeSize(i);
				if (typeSize != -1)
					currentSize = typeSize;
				else
				{
					final int finalLen = len;
					Optional<Integer> first = allSep.stream().filter(sepPosition -> sepPosition > finalLen).findFirst();
					if (first.isPresent())
						currentSize = first.get() - len + 1;
					else
						currentSize = repr.length - len;
					len += currentSize;
				}
				dst = new byte[currentSize];
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
