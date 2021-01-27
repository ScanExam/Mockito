package thomas;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.timeout;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;


public class TestThomas 
{

	/**
	 * Note :
	 * Certaines choses sont a evité comme le reset(mock) ou le verifyZeroInteractions par exemple car il complique les test et il peut etre tres facile de faire une erreur 
	 * pour verifyZeroInteractions il faut préférer never
	 */
	
	/**
	 * Initialisation de Mockito
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}


	/**
	 * La création d'un mock se fait simplement 
	 */
	@Test
	public void testChangementValeur(){

		MyClass mock = mock(MyClass.class);

		assertEquals(mock.getMessage(), null); // null
		mock.setMessage("Message");
		assertEquals(mock.getMessage(), null); // toujours null
	}

	/**
	 * Pas de possibiliter de changer la valeur du message avec set vu que when defini le retour de getMessage
	 */
	@Test
	public void testChangementValeurAvecDefinition(){

		MyClass mock = mock(MyClass.class);
		when(mock.getMessage()).thenReturn("Ceci est un message non changeable");

		assertEquals(mock.getMessage(), "Ceci est un message non changeable");
		mock.setMessage("Nouveaux massage pas possible");
		assertEquals(mock.getMessage(), "Ceci est un message non changeable");
	}


	/**
	 * Pas de possibiliter de changer la valeur du message avec set vu que when defini le retour de getMessage
	 */
	@Test
	public void testChangementEnfinPossible(){

		MyClass mock = mock(MyClass.class);

		//On retablie le comportement normale d'une methode
		when(mock.getMessage()).thenCallRealMethod();
		//On le retablie ausi pour set (la forme de when et diferente entre les fonction void et les autres)
		Mockito.doCallRealMethod().when(mock).setMessage(Mockito.anyString());

		assertEquals(mock.getMessage(), null);
		mock.setMessage("Nouveaux massage possible");
		assertEquals(mock.getMessage(), "Nouveaux massage possible");
	}


	/**
	 * Defini de Spy avec un comportement defini par when
	 */
	@Test
	public void DéfinitionDeSpy(){
		//Declaration de spy comme mock
		MyClass spy = Mockito.spy(new MyClass());
		Mockito.doReturn("Message").when(spy).getMessage();

		spy.setMessage("NewMessage");

		assertEquals(spy.getMessage(), "Message");
	}


	/**
	 * Pour Mock, si une fonction n'a pas de comportement donnée par when alors son resultat est null
	 */
	@Test
	public void mockVsSpy(){
		MyClass mock = mock(MyClass.class);
		mock.setMessage("NewMessage");
		assertEquals(mock.getMessage(), null);
	}

	/**
	 * Alors que pour Spy, si elle n'est pas defini par when alors c'est la fonction réel qui est appeler.
	 */
	@Test
	public void spyVsMock(){
		//Declaration de spy comme mock
		MyClass spy = Mockito.spy(new MyClass());
		spy.setMessage("NewMessage");
		assertEquals(spy.getMessage(), "NewMessage");
	}


	/**
	 * Grace a mockito ,on peut verfier le nombre d'execution d'une fonction, grace a times ou nevers (D'autre son possible comme : atLeastOnce() , atLeast() , atMost(), Only() , ...)
	 * verify permet de verifier une condition ici le nombre d'execution
	 */
	@Test
	public void testNombreAppel()
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



	/**
	 * Grace a mockito ,on peut verfier l'ordre d'execution des méthode
	 */
	@Test
	public void test_Order()
	{
		MyClass cMock = mock(MyClass.class);
		MyClass cMock2 = mock(MyClass.class);

		InOrder order = Mockito.inOrder(cMock);
		InOrder order2 = Mockito.inOrder(cMock2);

		//Premiere définition de l'ordre
		cMock.addToList(1);
		cMock.addToList(2);
		cMock.addToList(2);

		//Seconde définition de l'ordre
		cMock2.addToList(1);
		cMock2.addToList(1);
		cMock2.addToList(2);

		when(cMock.getFromList(0)).thenReturn(1);
		when(cMock.getFromList(3)).thenThrow(new RuntimeException());


		//Fusion des deux list d'ordre pour un donnée une plus grande
		InOrder order3 = inOrder(cMock, cMock2);

		//Verifie l'ordre et le nombres d'appels
		order3.verify(cMock, times(1)).addToList(1);
		order3.verify(cMock, times(2)).addToList(2);
		order3.verify(cMock2, times(2)).addToList(1);
		order3.verify(cMock2, times(1)).addToList(2);

		// times(0) equivalent a never
		verify(cMock, times(0)).addToList(0);
	}

	

	/**
	 * Mocjito donne aussi la possibilite de verifier que les condition sont faite dans un temps donnée
	 * Ici on verifie que la fonction est appeler deux fois sous 100 ms
	 */
	@Test
	public void test_timeOut()
	{
		MyClass cMock = mock(MyClass.class);

		cMock.addToList(2);
		cMock.addToList(2);

		verify(cMock, timeout(100).times(2)).addToList(2);
	}


	/**
	 * When est ce qui permet de définir le comportement de la fonction
	 * mais il existe plusieurs façon de le definir
	 */
	@Test
	public void test_AppelDirect_AppelSuccessifs(){
		MyClass cMock = mock(MyClass.class);

		//Premiere possibiliter l'appel direct
		//then Return defini directement la valeur retourner
		when(cMock.add(1,2)).thenReturn(3);
		assertEquals(cMock.add(1, 2) , 3);


		//Seconde solution l'appel successif
		//add return sussessivement 3 , 4 puis 3 lorsqu'il est appeler
		when(cMock.add(1,2)).thenReturn(3,4,3);

		assertEquals(cMock.add(1, 2) , 3);//3
		//Pour le second appeler le resultat attendu par when est 4 donc add ne sera pas egal a 3 ici
		assertNotEquals(cMock.add(1, 2) , 3);//4
		assertEquals(cMock.add(1, 2) , 3);//3
	}

	/**
	 * La troisième possibilite de définir when est avec un comportement Dynamique
	 */
	@Test
	public void test_AppelDynamique(){
		MyClass cMock = mock(MyClass.class);

		//Contrairement a thenReturn , thenAnswer retourne dynamiquement un resultat en fonction du contexte de la fonction
		when(cMock.add(1,2)).thenAnswer(new Answer<Object>() {
			public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
				return 3;
			}
		});
		assertEquals(cMock.add(1, 2) , 3);
	}

	/**
	 * Il est aussi possible d'avoir une erreur attendu lors de l'appelle d'une fonction (Sous Junit 5)
	 *//*
		@Test
		public void test_AppelError(){
			MyClass cMock = mock(MyClass.class);

			//Contrairement  thenReturn , thenAnswer retourne dynamiquement un resultat en fonction du contexte de la fonction
			when(cMock.add(1,2)).thenThrow(new NullPointerException());
			Assertions.assertThrows(NullPointerException.class, () ->  cMock.add(1,2));
		}
	 */



	/**
	 * Test ArgumentCaptor permet de verifier que le type des parametres de la fonction appeler sont les bon en terme de type
	 */
	@Test
	public void test_argumentCaptorADD() {
		MyClass cMock = mock(MyClass.class);
		
		when(cMock.add(1, 2)).thenReturn(3);

		//Capteur de type Integer
		ArgumentCaptor<Integer> addCaptor = ArgumentCaptor.forClass(Integer.class);
		//Autre exemple de capteur mais pour les String cette fois
		ArgumentCaptor<String> addCaptorBis = ArgumentCaptor.forClass(String.class);

		//Verification du resultat
		assertEquals(cMock.add(1, 2),3);

		//Verifie que les parametre de add sont des Integer grace a captor()
		verify(cMock).add(addCaptor.capture(), addCaptor.capture());
	}
	

	/**
	 * Second test avec captor mais cette fois-ci avec String
	 */
	@Test
	public void test_argumentCaptorString() {
		MyClass cMock = mock(MyClass.class);
		
		when(cMock.getMessage()).thenReturn("default");

		//Capteur de type String
		ArgumentCaptor<String> addCaptorBis = ArgumentCaptor.forClass(String.class);

		//Verification du resultat
		assertEquals(cMock.getMessage(),"default");

		doNothing().when(cMock).setMessage("test");

		cMock.setMessage("test");


		//Verifie que le parametre de setMessage est un String grace a captor()
		verify(cMock).setMessage(addCaptorBis.capture());
	}


	/**
	 * Enfin il est possible d'aller plus loin et de faire un Argument Captor sur une liste
	 */
	@Test
	public void test_argumentCaptorADDList() {
		MyClass cMock = mock(MyClass.class);
		
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

	




	//
	//	Test de Marius
	//

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
		 * Le but ici est de comparer le comportement d'un objet moquÃ© a celui d'une instance de l'objet
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
	 *  de mÃ©thodes Ã  l'intÃ©rieur de la classe Ã  tester.
	 * 
	 *  Ceci Ã©vite d'avoir Ã  tester en une seule fois les mÃ©thodes de la mÃªme classe qui 
	 *  seraient appelÃ©es entre elles. Ceci permet de diminuer cette complexitÃ© et d'avoir
	 *  Ã  gÃ©rer tous les cas de tests possibles.
	 */
	@Test
	public void defineMethodBehaviour()
	{
		MyClass myClass = Mockito.mock(MyClass.class);

		Mockito.doThrow(new IllegalArgumentException()).when(myClass).addToList(3);

		// myClass.addToList(3); L'appel a cette mÃ©thode avec 3 en paramÃ¨tre soulÃ¨vera une exception.

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
		// de leur paramÃ¨tres.
		Mockito.verify(spyList).add("one"); 
		Mockito.verify(spyList).add("two");

		assertEquals(2, spyList.size());
	}

	@Test
	public void methodOverride()
	{
		MyClass myClass = new MyClass();
		MyClass spyClass = Mockito.spy(myClass);

		/* DÃ©finit le resultat d'une mÃ©thode celon ces paramÃ¨tres. */
		Mockito.doReturn(100).when(spyClass).getIndex();

		assert(spyClass.getIndex() == 100);
	}
}
