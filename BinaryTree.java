import java.io.*;
import java.lang.reflect.Array;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;

public class BinaryTree<K,T> 
{
	private BinaryNode<K,T> root;

	public BinaryTree()
	{
		root = null;
	}
	
	Comparator<K> comparator = new Comparator<K>()
	{
		public int compare(K key1, K key2)
		{
			return key1.toString().compareTo(key2.toString());
		}
	};

	public void put(K key, T data)
	{	
		BinaryNode<K,T> newNode = new BinaryNode<K,T>(key, data);
		if(root == null)
		{
			root = newNode;
		}
		else
		{
			BinaryNode<K,T> current = root;
			BinaryNode<K,T> parent;
			while(true)
			{
				parent = current;
				if(comparator.compare(key, current.getKey()) < 0)
				{
					current = current.getLeft();
					if(current == null)
					{
						parent.setLeft(newNode);
						return;
					}
				}
				else
				{
					current = current.getRight();
					if(current == null)
					{
						parent.setRight(newNode);
						return;
					}
				}
			}
		}
	}

	public void print()
	{
		print(root);
	}
	private void print(BinaryNode<K,T> node)
	{
		if(node != null)
		{
			print(node.getLeft());
			System.out.println(node.getKey());
			print(node.getRight());
		}
	}
	
	public T search(K key)
	{
		BinaryNode<K,T> current = root;
		while(current != null)
		{
			if(comparator.compare(current.getKey(), key) == 0)
			{
				return current.getData();
			}
			if(comparator.compare(current.getKey(), key) > 0)
			{
				current = current.getLeft();
			}
			else
			{
				current = current.getRight();
			}
		}
		return null;
	}
	
	public boolean remove(K key)
	{
		BinaryNode<K,T> parent = root;
		BinaryNode<K,T> current = root;
		boolean hijo = false;
		while(comparator.compare(current.getKey(), key) != 0)
		{
			parent = current;
			if(comparator.compare(current.getKey(), key) > 0)
			{
				hijo = true;
				current = current.getLeft();
			}
			else
			{
				hijo = false;
				current = current.getRight();
			}
			if(current == null)
			{
				return false;
			}
		}
		if(current.getLeft() == null && current.getRight() == null)
		{
			if(current == root)
			{
				root = null;
			}
			else if(hijo == true)
			{
				parent.setLeft(null);
			}
			else
			{
				parent.setRight(null);
			}
		}
		else if(current.getRight() == null)
		{
			if(current == root)
			{
				root = current.getLeft();
			}
			else if(hijo)
			{
				parent.setLeft(current.getLeft());
			}
			else
			{
				parent.setRight(current.getLeft());
			}
		}
		else if(current.getLeft()== null)
		{
			if(current == root)
			{
				root = current.getRight();
			}
			else if(hijo)
			{
				parent.setLeft(current.getRight());
			}
			else
			{
				parent.setRight(current.getRight());
			}
		}
		return true;
	}

	public Iterable range(K start, K end)
	{
		if(comparator.compare(start, end) > 0)
		{
			return null;
		}
		Queue<K> queue = new LinkedList<K>();
		inOrderRange(root, queue, start, end);
		return queue;
	}
	private void inOrderRange(BinaryNode<K,T> node, Queue<K> queue, K start, K end)
	{
		if(node == null)
		{
			return;
		}
		inOrderRange(node.getLeft(), queue, start, end);
		if(comparator.compare(node.getKey(), start) >= 0 && comparator.compare(node.getKey(), end) <= 0)
		{
			queue.add(node.getKey());
		}
		inOrderRange(node.getRight(), queue, start, end);
	}
	
}
