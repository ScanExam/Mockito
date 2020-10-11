package test;

import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

class MyClass 
{
	private int index = 0;
	
	
	private ArrayList<Integer> list = new ArrayList<Integer>();
	
	public void addToList(int value)
	{
		list.add(value);
	}
	public int getFromList(int index)
	{
		return list.get(index);
	}
    public void incrementIndex() 
    { 
    	index++;
    }
    
    
	public int add (int a, int b) 
	{
		return a+b;
	}
   
    public int getIndex()
    {
    	return index;
    }
    
}