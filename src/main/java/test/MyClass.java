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
    public int getIndex()
    {
    	return index;
    }
    
}