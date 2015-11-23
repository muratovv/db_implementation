package representation.types;

import exceptions.RestoreException;
import representation.scheme.TypeScheme;
import store.Storable;
import utilities.DBUtil;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Represent tuple from row
 */
public class Tuple implements Storable, Comparable
{
	public static Storable.StoreFactory factory = new StoreFactory()
	{
		@Override
		public Storable restore(byte[] repr, TypeScheme typeScheme) throws RestoreException
		{
			ByteBuffer buffer = ByteBuffer.wrap(repr);
			ArrayList<Integer> allSep = DBUtil.Separators.findAllSep(repr, DBUtil.Separators.basic);
			BasicType[] values = new BasicType[typeScheme.getSize()];
			byte[] dst;
			int len = 0;
			int currentSize = 0;
			for (int i = 0; i < values.length; i++)
			{
				int typeSize = typeScheme.getTypeSize(i);
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
				values[i] = ((BasicType) typeScheme.getFactory(i).restore(dst, typeScheme));
			}
			Tuple tuple = new Tuple();
			tuple.values = values;
			return tuple;
		}
	};

	BasicType[] values;
	int byteSize = 0;

	public Tuple(BasicType[] values)
	{
		this.values = values;
		for (BasicType value : values)
		{
			byteSize += value.byteSize();
		}
	}

	private Tuple()
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
		if (obj instanceof Tuple)
		{
			Tuple o = (Tuple) obj;
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
