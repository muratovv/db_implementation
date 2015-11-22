package representation.types;

import store.Scheme;
import store.Storable;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Str extends BasicType
{
	public static Storable.StoreFactory factory = new Storable.StoreFactory()
	{
		@Override
		public Storable restore(byte[] repr, Scheme scheme)
		{
			//TODO 22.11.15
			throw new NotImplementedException();
		}
	};

	String repr;

	@Override
	public byte[] store()
	{
		//TODO 22.11.15
		throw new NotImplementedException();
	}

	@Override
	public int byteSize()
	{
		//TODO 22.11.15
		throw new NotImplementedException();
	}

	@Override
	public int compareTo(Object o)
	{
		if(o instanceof Str)
		{
			return repr.compareTo(((Str) o).repr);
		}
		return -1;
	}

	@Override
	int getStaticSize()
	{
		return -1;
	}
}
