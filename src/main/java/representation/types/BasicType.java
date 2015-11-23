package representation.types;

import store.Storable;

public abstract class BasicType implements Storable, Comparable
{
	public abstract Object data();

	public abstract void setData(Object data);
}
