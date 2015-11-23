package operations;

import representation.types.Tuple;

/**
 * predicate applicable for row in table
 */
public abstract class RowPredicate
{
	public abstract boolean apply(Tuple tuple);
}
