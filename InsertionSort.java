import java.io.*;
import java.lang.reflect.Array;
import java.math.*;
import java.security.*;
import java.text.*; 
import java.util.*;

public class InsertionSort <T>  
{
    private T[] a;
    private Comparator comparator;
    
    InsertionSort(T[]a, Comparator comparator)
    {
        this.a = a;
        this.comparator = comparator;
    }

    public void sort()
    {
        int size = a.length;
        for (int i = 1; i < size; i++)
        {
            for(int j = i; j > 0 && menos(a[j-1], a[j], comparator); j--)
            {
                inter(a, j, j-1);
            }
        }
        
    }

    public void inter(T[]a, int i, int j)
    {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public boolean menos(T a, T b, Comparator comparator)
    {
        return comparator.compare(b, a) < 0;
    }
}
