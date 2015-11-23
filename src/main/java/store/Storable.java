package store;

import exceptions.RestoreException;
import representation.scheme.TypeScheme;

import java.nio.charset.StandardCharsets;

public interface Storable
{
	/**
	 * {@link StandardCharsets#UTF_8} is needed
	 *
	 * @return binary representation
	 */
	byte[] store();

	int byteSize();

	abstract class StoreFactory
	{
		public abstract Storable restore(byte[] repr, TypeScheme typeScheme) throws RestoreException;
	}
}
