import java.io.*;
import java.lang.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;

public class BinaryNode<K,T>
{
    T data;
    K key;
    BinaryNode<K,T> left, right;

    BinaryNode(K key, T data)
    {
        this.key = key;
        this.data = data;
        left = right = null;
    }
    
    public String toString()
    {
        return data.toString();
    }

    //getters and setters
    public T getData() {return data;}
    public void setData(T data) {this.data = data;}
    public BinaryNode<K,T> getLeft() {return left;}
    public void setLeft(BinaryNode<K,T> left) {this.left = left;}
    public BinaryNode<K,T> getRight() {return right;}
    public void setRight(BinaryNode<K,T> right) {this.right = right;}
    public K getKey() {return key;}
    public void setKey(K key) {this.key = key;}
 
}
