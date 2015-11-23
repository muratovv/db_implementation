package representation;

import operations.QueryNameSelector;
import operations.Response;
import operations.RowPredicate;
import representation.scheme.NameScheme;
import representation.scheme.TypeScheme;
import representation.types.Tuple;

/**
 * insterface of table
 */
public abstract class AbstractTable
{
	NameScheme nameScheme;
	TypeScheme typeScheme;

	public AbstractTable(NameScheme nameScheme, TypeScheme typeScheme)
	{
		this.nameScheme = nameScheme;
		this.typeScheme = typeScheme;
	}

	public abstract Response select(QueryNameSelector selector, RowPredicate rowPredicate);

	public abstract Response insert(Tuple row);

	public abstract Response delete(RowPredicate rowPredicate);

	public abstract Response update(RowPredicate rowPredicate);

}
