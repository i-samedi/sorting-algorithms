import java.io.*;
import java.lang.reflect.Array;
import java.math.*;
import java.security.*;
import java.text.*; 
import java.util.*; 

public class HeapSort<T> 
{
    private T[] array;
    private Comparator comparator;

    HeapSort(T[]array, Comparator comparator)
    {
        this.array = array;
        this.comparator = comparator;
    }

    void heapSort()
    {
        int size = array.length;

        for (int i = size / 2 - 1; i >= 0; i--)
            heapify(array, size, i, comparator);

        for (int i = size - 1; i >= 0; i--)
        {
            T temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapify(array, i, 0, comparator);
        }
    }

    void heapify(T[] array, int size, int i, Comparator comparator)
    {
        int max   = i; 
        int left  = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < size && comparator.compare(array[left], array[max]) > 0)
            max = left;

        if (right < size && comparator.compare(array[right], array[max]) > 0)
            max = right;

        if (max != i)
        {
            T temp = array[i];
            array[i] = array[max];
            array[max] = temp;

            heapify(array, size, max, comparator);
        }
    }
}
