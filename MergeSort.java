import java.io.*;
import java.lang.reflect.Array;
import java.math.*;
import java.security.*;
import java.text.*;  
import java.util.*;

public class MergeSort <T>
{
    private T[] array; 
    private Comparator comparator;
    private int start;
    private int end;

    MergeSort(T[]array, Comparator comparator,int start,int end)
    {
        this.array = array;
        this.comparator = comparator;
        this.start=start;
        this.end=end;
    }   
    
   public void merge(T[] array, int leftFirst, int leftLast, int rightFirst, int rightLast){
        T[] tempArray = Arrays.copyOf(array,array.length);
        int index = leftFirst;
        int saveFirst = leftFirst;

        while((leftFirst <= leftLast) && (rightFirst <= rightLast)){
            
            if(comparator.compare(array[leftFirst], array[rightFirst]) < 0){

                
                tempArray[index] = array[leftFirst];
                leftFirst++;
            }else{
                tempArray[index] = array[rightFirst];
                rightFirst++;
            }
            index++;
        }
        while(leftFirst <= leftLast){
            tempArray[index] = array[leftFirst];
            leftFirst++;
            index++;
        }
        while(rightFirst <= rightLast){
            tempArray[index] = array[rightFirst];
            rightFirst++;
            index++;
        }
        for(index = saveFirst; index <= rightLast;index++){
            array[index] = tempArray[index];
        }
    }
    public void mergeSort(T[] array,Comparator comparator,int first, int last){
        if(first < last){
            int middle = (first + last) / 2;
            mergeSort(array,comparator,first,middle);
            mergeSort(array,comparator,middle+1,last);
            merge(array,first,middle,middle+1,last);
        }
    }
    
}
