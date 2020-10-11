package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;


public class Program 
{
	/*
	 * We can also define mock like this (auto instantiated)
	@Mock
	private MyClass mock; 
	*/ 

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
	
	@Test
    public void list_test()
	{
        MyClass cMock = mock(MyClass.class);
        MyClass cMock2 = mock(MyClass.class);

        InOrder order = Mockito.inOrder(cMock);
        InOrder order2 = Mockito.inOrder(cMock2);

        cMock.addToList(1);

        cMock.addToList(2);
        cMock.addToList(2);
        cMock2.addToList(1);
        cMock2.addToList(1);
        cMock2.addToList(2);

        when(cMock.getFromList(0)).thenReturn(1);
        when(cMock.getFromList(3)).thenThrow(new RuntimeException());

        System.out.println(cMock.getFromList(0));
        InOrder order3 = inOrder(cMock, cMock2);
        //Verifie l'ordre et le nombres d'appels
        order3.verify(cMock, times(1)).addToList(1);
        order3.verify(cMock, times(2)).addToList(2);
        order3.verify(cMock2, times(2)).addToList(1);
        order3.verify(cMock2, times(1)).addToList(2);

        verify(cMock, times(0)).addToList(0);
    }
	
	 
	
}
