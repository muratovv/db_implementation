package operations;

import representation.scheme.NameScheme;

/**
 * represents names for select
 */
public class QueryNameSelector
{
	NameScheme nameScheme;

	public QueryNameSelector(NameScheme nameScheme)
	{
		this.nameScheme = nameScheme;
	}

	public static boolean validateNewScheme(NameScheme tableScheme, NameScheme applicableScheme)
	{
		for (String name : tableScheme.names)
		{
			if (!applicableScheme.names.contains(name))
				return false;
		}
		return true;
	}
}
