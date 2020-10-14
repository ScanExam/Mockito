package thomas;

import static org.junit.Assert.*;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;



public class TestThomas 
{

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
