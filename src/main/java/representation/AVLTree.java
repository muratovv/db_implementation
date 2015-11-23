package representation;

import java.util.function.Function;

public class AVLTree<T extends Comparable<T>>
{
	private MyNode<T> root;

	public int size()
	{
		return sizeRef(root);
	}

	public int height()
	{
		return heightRef(root);
	}

	private int heightRef(MyNode<T> root)
	{
		return root != null ? root.height : 0;
	}

	private int sizeRef(MyNode<T> current)
	{
		if (current == null)
			return 0;
		else
			return 1 + sizeRef(current.left) + sizeRef(current.right);
	}

	public void add(T value)
	{
		root = addRef(root, value);
	}

	private MyNode<T> addRef(MyNode<T> current, T value)
	{
		if (current == null)
			return new MyNode<T>(value);
		if (current.value.compareTo(value) <= 0)
			current.right = addRef(current.right, value);
		else
			current.left = addRef(current.left, value);
		return TreeUtil.balance(current);
	}

	public void remove(T value)
	{
		root = removeRef(root, value);
	}

	private MyNode<T> removeRef(MyNode<T> current, T value)
	{
		if (current == null)
			return null;
		if (current.value.compareTo(value) < 0)
			current.right = removeRef(current.right, value);
		else if (current.value.compareTo(value) > 0)
			current.left = removeRef(current.left, value);
		else
		{
			MyNode<T> left = current.left;
			MyNode<T> right = current.right;
			if (left == null)
				return right;
			MyNode<T> max = TreeUtil.maximum(left);
			max.left = TreeUtil.removeMax(left);
			max.right = right;
			return TreeUtil.balance(max);
		}
		return TreeUtil.balance(current);
	}

	public MyNode<T> find(T value)
	{
		return findRef(root, value);
	}

	public MyNode<T> getRoot()
	{
		return root;
	}

	private MyNode<T> findRef(MyNode<T> current, T value)
	{
		if (current == null)
			return null;
		if (current.value.compareTo(value) == 0)
		{
			return current;
		} else if (current.value.compareTo(value) <= 0)
			return findRef(current.right, value);
		else return findRef(current.left, value);
	}

	private MyNode<T> successor(MyNode<T> node)
	{
		if (node == null)
		{
			return null;
		}
		MyNode<T> x = node;

		if (x.right != null)
			return TreeUtil.minimum(x.right);

		MyNode<T> y = findParent(x.value, getRoot());
		while (y != null && x == y.right)
		{
			x = y;
			y = findParent(y.value, getRoot());
		}

		return y;
	}

	private MyNode<T> findParent(T x, MyNode<T> root)
	{
		return findParent(x, root, null);
	}

	private MyNode<T> findParent(T x, MyNode<T> node, MyNode<T> parent)
	{
		if (node == null)
		{
			return null;
		} else if (!node.getData().equals(x))
		{
			parent = findParent(x, node.getLeft(), node);
			if (parent == null)
			{
				parent = findParent(x, node.getRight(), node);
			}
		}
		return parent;
	}


	void inOrder(MyNode<T> root, Function<T, Void> map)
	{

		if (root == null) return;
		inOrder(root.getLeft(), map);
		map.apply(root.value);
		inOrder(root.getRight(), map);

	}

	public static class MyNode<T>
	{
		public T value;
		public MyNode<T> left;
		public MyNode<T> right;
		public int height = 1;

		public MyNode(T value)
		{
			this.value = value;
		}

		public MyNode<T> getLeft()
		{
			return left;
		}

		public MyNode<T> getRight()
		{
			return right;
		}

		public T getData()
		{
			return value;
		}

		@Override
		public String toString()
		{
			return "MyNode{" +
					"value=" + value +
					'}';
		}
	}


	private static class TreeUtil
	{
		private static <T> int height(MyNode<T> node)
		{
			return node != null ? node.height : 0;
		}

		private static <T> int bFactor(MyNode<T> node)
		{
			if (node == null)
				return 0;
			return height(node.left) - height(node.right);
		}

		private static <T> void fixHeight(MyNode<T> node)
		{
			if (node != null)
				node.height = Math.max(height(node.left), height(node.right)) + 1;
		}

		private static <T> MyNode<T> rotateRight(MyNode<T> node)
		{
			if (node.left != null)
			{
				MyNode<T> temp = node.left;
				node.left = temp.right;
				temp.right = node;
				fixHeight(temp);
				fixHeight(node);
				return temp;
			} else return node;
		}

		private static <T> MyNode<T> rotateLeft(MyNode<T> node)
		{
			if (node.right != null)
			{
				MyNode<T> temp = node.right;
				node.right = temp.left;
				temp.left = node;
				fixHeight(node);
				fixHeight(temp);
				return temp;
			} else return node;
		}

		private static <T> MyNode<T> balance(MyNode<T> node)
		{
			if (node == null)
				return null;
			fixHeight(node);
			int bFactor = bFactor(node);
			if (bFactor == -2)
			{
				if (bFactor(node.right) > 0)
					node.right = rotateRight(node.right);
				return rotateLeft(node);
			}
			if (bFactor == 2)
			{
				if (bFactor(node.left) < 0)
					node.left = rotateLeft(node.left);
				return rotateRight(node);
			}
			return node;
		}

		private static <T> MyNode<T> minimum(MyNode<T> node)
		{
			if (node == null)
				return null;
			return node.left == null ? node : minimum(node.left);
		}

		private static <T> MyNode<T> removeMin(MyNode<T> node)
		{
			if (node.left == null)
				return node.right;
			node.left = removeMin(node.left);
			return balance(node);
		}

		private static <T> MyNode<T> maximum(MyNode<T> node)
		{
			if (node == null)
				return null;
			return node.right == null ? node : maximum(node.right);
		}

		private static <T> MyNode<T> removeMax(MyNode<T> node)
		{
			if (node.right == null)
				return node.left;
			node.right = removeMax(node.right);
			return balance(node);
		}
	}
}
