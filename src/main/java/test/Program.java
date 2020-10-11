package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;

import java.util.List;

import org.mockito.stubbing.OngoingStubbing;



public class Program 
{
	
	public static void main(String[] args)
	{
		MyClass instance = new MyClass();
		MyClass mock = mock(MyClass.class);
		
		int index = instance.getIndex()+1;
		instance.incrementIndex();
		when(mock.getIndex()).thenReturn(index);
		mock.incrementIndex();
		verify(mock, times(1)).incrementIndex();
		assertEquals(mock.getIndex(),instance.getIndex());
		
		
		
		
	}
}
