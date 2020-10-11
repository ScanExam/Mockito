package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.istic.tools.scanexam.gui.Perso;

public class MyTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test1() {
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
	public void classPersoTest1(){
		//Perso perso  = new Perso();
		MyClass cMock = mock(MyClass.class);
		
		when(cMock.add(1,2)).thenReturn(3);
		
		int resMock = cMock.add(1, 2);
		
		verify(cMock).add(1, 2);
		assertEquals(resMock,3);
	}
	

}
