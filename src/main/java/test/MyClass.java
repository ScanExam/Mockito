package test;

import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;

class MyClass 
{
	private int index = 0;
	
    public void incrementIndex() 
    { 
    	index++;
    }
    
	public int add (int a, int b) {
		return a+b;
	}
    
    public int getIndex()
    {
    	return index;
    }
    
}