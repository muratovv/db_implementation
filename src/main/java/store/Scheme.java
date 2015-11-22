package store;

import representation.types.BasicType;
import representation.types.Int;
import representation.types.Str;

import java.util.ArrayList;

/**
 * Represents row types
 * depends on types package
 */
public class Scheme
{
	ArrayList<Class> scheme; // types

	public Scheme(ArrayList<Class> scheme)
	{
		this.scheme = scheme;
	}

	public Storable.StoreFactory getFactory(int index)
	{
		if (scheme.get(index).equals(Int.class))
			return Int.factory;
		else if (scheme.get(index).equals(Str.class))
			return Str.factory;
		else return null;
	}

	/**
	 * @return static size of type(if exist) else -1
	 */
	public int getStaticSize(int index)
	{
		if (scheme.get(index).equals(Int.class))
			return Integer.BYTES;
		else if (scheme.get(index).equals(Str.class))
			return -1;
		else return -1;
	}

	public Class getClass(int index)
	{
		return scheme.get(index);
	}

	public int getSize()
	{
		return scheme.size();
	}
}
