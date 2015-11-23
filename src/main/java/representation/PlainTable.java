package representation;

import operations.QueryNameSelector;
import operations.Response;
import operations.RowPredicate;
import representation.scheme.NameScheme;
import representation.scheme.TypeScheme;
import representation.types.Tuple;

public class PlainTable extends AbstractTable
{
	public PlainTable(NameScheme nameScheme, TypeScheme typeScheme)
	{
		super(nameScheme, typeScheme);
	}

	@Override
	public Response select(QueryNameSelector selector, RowPredicate rowPredicate)
	{
		return null;
	}

	@Override
	public Response insert(Tuple row)
	{
		return null;
	}

	@Override
	public Response delete(RowPredicate rowPredicate)
	{
		return null;
	}

	@Override
	public Response update(RowPredicate rowPredicate)
	{
		return null;
	}
}
