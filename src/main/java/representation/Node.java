package representation;


import exceptions.RestoreException;
import representation.types.TupleType;
import store.Scheme;
import store.Storable;

import java.util.Comparator;

/**
 * Represent part of tuple from row
 */
public class Node implements Comparable<Node>, Storable
{
	public static Storable.StoreFactory factory = new Storable.StoreFactory()
	{
		@Override
		public Storable restore(byte[] repr, Scheme scheme) throws RestoreException
		{
			return null;
		}
	};

	Comparator comparator;
	TupleType data;
	int byteSize;

	public Node(TupleType data, Comparator comparator)
	{
		this.data = data;
		this.comparator = comparator;
		byteSize = data.byteSize() + 1;
	}

	TupleType data()
	{
		return data;
	}

	public Node(TupleType data)
	{
		this(data, null);
	}

	@Override
	public int compareTo(Node o)
	{
		if (comparator != null)
			return comparator.compare(this, o);
		else
			throw new UnsupportedOperationException();
	}


	@Override
	public byte[] store()
	{
		return new byte[0];
	}

	@Override
	public int byteSize()
	{
		return 0;
	}
}
