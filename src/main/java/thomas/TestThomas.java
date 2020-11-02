package thomas;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.timeout;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mockito;



public class TestThomas 
{
	
	

	
	//Grace a mockito ,on peut verfier le nombre d'execution d'une fonction, grace a times ou nevers
	//verify permet de verifier une condition ici le nombre d'execution
	@Test
    public void test_Number()
	{
		MyClass cMock = mock(MyClass.class);
		
        cMock.addToList(1);

        cMock.addToList(2);
        cMock.addToList(2);
     
        cMock.addToList(4);

   
        verify(cMock, times(1)).addToList(1);
        verify(cMock, times(2)).addToList(2);
        verify(cMock, times(0)).addToList(3);
        verify(cMock, times(1)).addToList(4);
        
        verify(cMock, never()).addToList(3);
    }
	

	//Grace a mockito ,on peut verfier l'ordre d'execution
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
	
	
	//Verifie que la fonction est appeler deux fois sous 100 ms
	@Test
    public void test_timeOut()
	{
		MyClass cMock = mock(MyClass.class);
		
        cMock.addToList(2);
        cMock.addToList(2);
 
        verify(cMock, timeout(100).times(2)).addToList(2);
    }
	
	
	@Test
	public void test_AppelDirect_AppelSuccessifs(){
		MyClass cMock = mock(MyClass.class);
		
		//then Return defini directement la valeur retourner
		when(cMock.add(1,2)).thenReturn(3);
		assertEquals(cMock.add(1, 2) , 3);
		
		
		//Appels successifs
		//add return sussessivement 3 , 4 puis 3 lorsqu'il est appeler
		when(cMock.add(1,2)).thenReturn(3,4,3);
		
		assertEquals(cMock.add(1, 2) , 3);//3
		//Pour le second appeler le resultat attendu par when est 4 donc add ne sera pas egal a 3 ici
		assertNotEquals(cMock.add(1, 2) , 3);//4
		assertEquals(cMock.add(1, 2) , 3);//3
	}
	
	//Comportement Dynamique attendu par une methode 
		@Test
		public void test_AppelDynamique(){
			MyClass cMock = mock(MyClass.class);
			
			//Contrairement  thenReturn , thenAnswer retourne dynamiquement un resultat en fonction du contexte de la fonction
			when(cMock.add(1,2)).thenAnswer(new Answer<Object>() {
				public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
					return 3;
				}
			});
			assertEquals(cMock.add(1, 2) , 3);
		}
		
		//Il est aussi possible d'avoir une erreur attendu lors de l'appelle d'une fonction
		/*
		@Test
		public void test_AppelError(){
			MyClass cMock = mock(MyClass.class);
			
			//Contrairement  thenReturn , thenAnswer retourne dynamiquement un resultat en fonction du contexte de la fonction
			when(cMock.add(1,2)).thenThrow(new NullPointerException());
			
			assertThrows(NullPointerException.class, cMock.add(1,2) );
		}
		*/
		


	//Test ArgumentCaptor
	//Ils permet de verifier que le type des parametres de la fonction appeler sont les bon
		
	//ArgumentCaptor sur des parametre de fonction
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
	
	
	//Argument Captor sur une liste
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

	/*
	 Note :
	 Certain e chose sont a evité comme le reset(mock) ou le verifyZeroInteractions par exemple car il complique les test et il peut etre tre facile de faire une erreur 
	 pour verifyZeroInteractions il faut préférer never
	 */

}
