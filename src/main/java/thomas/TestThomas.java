package thomas;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mockito;



public class TestThomas 
{

	@Test
    public void test_Order()
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
	
	
	
	//Test ArgumentCaptor
	
	@Test
	public void test_argumentCaptorADD() {
		 MyClass cMock = mock(MyClass.class);
		 //condition attendu pour add(1,2)
		 when(cMock.add(1, 2)).thenReturn(3);
		 
		 //Capteur de type Integer
		 ArgumentCaptor<Integer> addCaptor = ArgumentCaptor.forClass(Integer.class);
		 
		 ArgumentCaptor<String> addCaptorBis = ArgumentCaptor.forClass(String.class);
		 
		 //Verification du resultat
		 assertEquals(cMock.add(1, 2),3);
		
		 //Verifie que les parametre de add sont des Integer grace a capture()
		 verify(cMock).add(addCaptor.capture(), addCaptor.capture());
	}
	
	@Test
	public void test_argumentCaptorString() {
		 MyClass cMock = mock(MyClass.class);
		 //condition attendu pour add(1,2)
		 when(cMock.getMessage()).thenReturn("default");
		 
		 //Capteur de type String
		 ArgumentCaptor<String> addCaptorBis = ArgumentCaptor.forClass(String.class);
		 
		 //Verification du resultat
		 assertEquals(cMock.getMessage(),"default");
		 
		 doNothing().when(cMock).setMessage("test");
		 
		 cMock.setMessage("test");
		 
		 
		 //Verifie que le parametre de setMessage est un String grace a capture()
		 verify(cMock).setMessage(addCaptorBis.capture());
	}
	
	@Test
	public void test_argumentCaptorADDList() {
		 MyClass cMock = mock(MyClass.class);
		 //condition attendu pour add(1,2)
		 when(cMock.add(1, 2)).thenReturn(3);
		 
		 //Capteur de type Integer
		 ArgumentCaptor<Integer> addCaptor = ArgumentCaptor.forClass(Integer.class);
		 
		 //Verification du resultat
		 assertEquals(cMock.add(1, 2),3);
		
		 verify(cMock).add(addCaptor.capture(), addCaptor.capture());
		 
		 //Verifie que tout les element d'une list sont des Integer
		 List<Integer> list = new ArrayList();
		 list.add(1);
		 list.add(2);
		 assertEquals(list,addCaptor.getAllValues());
	}


}
