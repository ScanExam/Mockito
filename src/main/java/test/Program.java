package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;


public class Program 
{
	@Test
	/* Test index incrementation */
	public void testIncrementation()
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
	@Test
	public void testAdd()
	{
		MyClass instance = new MyClass();
		MyClass mock = mock(MyClass.class);
		
		int value = 10;
		
		instance.addToList(value);
		when(mock.getFromList(0)).thenReturn(10);
		assertEquals(mock.getFromList(0),instance.getFromList(0));
	}
	
	@Test
	public void anotherTestOfIncrementation()
	{
		MyClass instance = new MyClass();
		MyClass mock = mock(MyClass.class);
		
		when(mock.getIndex()).thenReturn(1,2,3,4);
		
		for (int i = 0;i < 4;i++)
		{
			instance.incrementIndex();
			assertEquals(instance.getIndex(),mock.getIndex());
		}
	}
}
