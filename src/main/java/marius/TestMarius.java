package marius;

import static org.junit.Assert.*;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mockito;


public class TestMarius
{
	
	
	
	/*
	 * Mockito.mock() Créer un objet 'mocké' (pas d'appel ctor, propriétée nulles)
	 * verify() vérifie un prédicat exprimé par mockito
	 * times() compte le nombre d'appel a une méthode
	 * when() définit le comportement d'une méthode
	 * 
	 * Les methodes qui définisse le comportement des fonctions ne peuvent 
	 * être utilisé que sur des objets moqués.
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
		/* 
		 * Le but ici est de comparer le comportement d'un objet moqué a celui d'une instance de l'objet
		 * (voir si le contexte change de resultat de la fonction getIndex() )
		 * Ici, ce n'est pas le cas ! super :3
		 */
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
	
	/*  L'utilisation de Mockito.spy permet de simuler le comportement 
	 *  de méthodes à l'intérieur de la classe à tester.
	 * 
	 *  Ceci évite d'avoir à tester en une seule fois les méthodes de la même classe qui 
	 *  seraient appelées entre elles. Ceci permet de diminuer cette complexité et d'avoir
	 *  à gérer tous les cas de tests possibles.
	 */

	
	@Test
	public void defineMethodBehaviour()
	{
		MyClass myClass = Mockito.mock(MyClass.class);

		Mockito.doThrow(new IllegalArgumentException()).when(myClass).addToList(3);
		
		// myClass.addToList(3); L'appel a cette méthode avec 3 en paramètre soulèvera une exception.
		
		// Mockito.doNothing().when(myClass).addToList(1); --> empty instruction block.


	}
	
	@Test
	public void spyMethod()
	{
		List<String> list = new ArrayList<String>();
	    List<String> spyList = Mockito.spy(list);
	 
	    spyList.add("one");
	    spyList.add("two");
	    
	    // spy nous permet de conserver un 
	    // historique des appels ainsi que 
	    // de leur paramètres.
	    Mockito.verify(spyList).add("one"); 
	    Mockito.verify(spyList).add("two");
	 
	    assertEquals(2, spyList.size());
	}
	
	@Test
	public void methodOverride()
	{
		MyClass myClass = new MyClass();
		MyClass spyClass = Mockito.spy(myClass);
		
		/* Définit le resultat d'une méthode celon ces paramètres. */
		Mockito.doReturn(100).when(spyClass).getIndex();
		
		System.out.println(spyClass.getIndex());
		System.out.println(myClass.getIndex());
		assert(spyClass.getIndex() == 100);
	}
	/*
	 *   ArgumentCaptor allows us to capture an argument passed
	 *   to a method in order to inspect it. This is especially
	 *   useful when we can't access the argument outside of 
	 *   the method we'd like to test.
	 */
	@Test
	public void argumentCaptor() 
	{
	
		ArgumentCaptor<Integer> argCaptor = ArgumentCaptor.forClass(Integer.class);
		MyClass spyClass = Mockito.mock(MyClass.class);
		
		spyClass.addToList(3);
		
		Mockito.verify(spyClass).addToList(argCaptor.capture()); // Capture last call parameters.
		
		
		assertEquals(3, (int)argCaptor.getValue());
	}

	
}
