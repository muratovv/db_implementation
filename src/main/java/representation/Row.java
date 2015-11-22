package representation;

import Utilities.DBUtil;
import exceptions.RestoreException;
import store.Scheme;
import store.Storable;

import java.nio.ByteBuffer;

/**
 * Row contains two node:
 * primary key,
 * and tail part
 */
public class Row implements Storable, Comparable<Row>
{
	public static final Storable.StoreFactory factory = new StoreFactory()
	{
		@Override
		public Storable restore(byte[] repr, Scheme scheme) throws RestoreException
		{
			int pos = DBUtil.Separators.findSep(repr, DBUtil.Separators.row);
			if (pos < 1)
				throw new RestoreException();
			Row row = new Row();
			row.primaryNode = ((Node) Node.factory.restore(ByteBuffer.wrap(repr, 0, pos).array(), scheme));
			row.tail = ((Node) Node.factory.restore(ByteBuffer.wrap(repr, pos + 1, repr.length).array(), scheme));
			row.computeByteSize();
			return row;
		}
	};

	Node primaryNode;
	Node tail;
	private int byteSize;

	public Row(Node primaryNode, Node tail)
	{
		this.primaryNode = primaryNode;
		this.tail = tail;
		computeByteSize();
	}

	private Row()
	{

	}

	private void computeByteSize()
	{
		byteSize = primaryNode.byteSize() + 1 + (tail != null ? tail.byteSize() : 0);
	}

	public Row(Node primaryNode)
	{
		this(primaryNode, null);
	}

	@Override
	public byte[] store()
	{

		ByteBuffer buffer = ByteBuffer.allocate(byteSize());
		buffer.put(primaryNode.store());
		buffer.put(primaryNode.byteSize(), DBUtil.Separators.row);
		if (tail != null)
		{
			buffer.put(tail.store(), primaryNode.byteSize() + 1, tail.byteSize());
		}
		return buffer.array();
	}

	@Override
	public int byteSize()
	{
		return byteSize;
	}


	@Override
	public int compareTo(Row o)
	{
		return primaryNode.compareTo(o.primaryNode);
	}
}
