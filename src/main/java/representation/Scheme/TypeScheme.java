package representation.scheme;

import representation.types.Int;
import representation.types.Str;
import store.Storable;

import java.util.ArrayList;

/**
 * Represents row types
 * depends on types package
 */
public class TypeScheme
{
	ArrayList<Class> scheme; // types

	public TypeScheme(ArrayList<Class> scheme)
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
	public int getTypeSize(int index)
	{
		if (scheme.get(index).equals(Int.class))
			return Integer.BYTES;
		else if (scheme.get(index).equals(Str.class))
			return -1;
		return 0;
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
