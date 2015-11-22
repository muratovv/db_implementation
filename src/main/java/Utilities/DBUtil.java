package Utilities;

import java.util.ArrayList;

/**
 * Usefull functions
 */
public class DBUtil
{

	/**
	 * Assert that all separators are different
	 */
	public static class Separators
	{
		public static byte basic = '\t';
		public static byte row = '\n';

		public static int findSep(byte[] arr, byte sep)
		{
			ArrayList<Integer> allSep = findAllSep(arr, sep);
			return allSep.size() > 0 ? allSep.get(0) : -1;
		}

		public static ArrayList<Integer> findAllSep(byte[] arr, byte sep)
		{
			ArrayList<Integer> positions = new ArrayList<>();
			for (int i = 0; i < arr.length; i++)
			{
				if (arr[i] == sep)
					positions.add(i);
			}
			return positions;
		}
	}
}
