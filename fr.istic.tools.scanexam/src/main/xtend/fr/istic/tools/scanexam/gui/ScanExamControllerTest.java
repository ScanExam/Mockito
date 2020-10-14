package fr.istic.tools.scanexam.gui;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
//import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Dimension;
import java.awt.List;
import java.awt.image.BufferedImage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.stubbing.Answer;

import fr.istic.tools.scanexam.GradingData;
import fr.istic.tools.scanexam.Question;
import fr.istic.tools.scanexam.QuestionGrade;
import fr.istic.tools.scanexam.StudentGrade;
//import test.MyClass;


public class ScanExamControllerTest {


	//ScanExamController controlTest;
	GradingData data2 =Mockito.mock(GradingData.class, Mockito.RETURNS_DEEP_STUBS );
	ScanExamController controlTest2 = new ScanExamController(data2); 
	
	//controlTest2.currentStudentIndex.
	
	@Mock
	//GradingData data;
	BufferedImage image;
	ScanExamPanel panel;
	ExcelTableViewer tableView;
	Question prevQuestion;
	//GradingData data =Mockito.mock(GradingData.class);
	ScanExamController controlTest =Mockito.mock(ScanExamController.class, Mockito.RETURNS_DEEP_STUBS);

	/*
	@Spy
	ScanExamController spyController = new ScanExamController(data2);
*/
	
	
	@Before
	public void setUp() throws Exception {
		GradingData data =Mockito.mock(GradingData.class);
		controlTest = new ScanExamController(data); 
		
	}

	 public void createData() {
		 	data2 =Mockito.mock(GradingData.class, Mockito.RETURNS_DEEP_STUBS );
		 	controlTest2 =Mockito.mock(ScanExamController.class, Mockito.RETURNS_DEEP_STUBS );
		 	//ScanExamController spy = Mockito.spy(ScanExamController.class);
		 	//controlTest2.setGrade(1);
	       // item2.setSomeOtherValue("test");

	        //item2.setSomeValue("val");
	       // item2.setSomeOtherValue("value");

	        //allData.add(item1);
	        //allData.add(item2);


	}
	
	@Test
	public void test_Init() {
		assertEquals(true,true);
	}

	
	
	@Test
	public void test_setPanel() {
		GradingData data2 =Mockito.mock(GradingData.class, Mockito.RETURNS_DEEP_STUBS);
		ScanExamController instance = new ScanExamController(data2);
		
		ScanExamController mock = mock(ScanExamController.class, Mockito.RETURNS_DEEP_STUBS);
		ScanExamPanel mockPanel = mock(ScanExamPanel.class, Mockito.RETURNS_DEEP_STUBS);
		
		
		when(mock.setPanel(mockPanel)).thenReturn(mockPanel);
		
		ScanExamPanel res = mock.setPanel(mockPanel);
		
		verify(mock, times(1)).setPanel(mockPanel);
		assertEquals(res,instance.setPanel(mockPanel));
	}
	
	
	@Test
	public void test_setTableView() {
		GradingData data2 =Mockito.mock(GradingData.class, Mockito.RETURNS_DEEP_STUBS);
		ScanExamController instance = new ScanExamController(data2);
		
		ScanExamController mock = mock(ScanExamController.class, Mockito.RETURNS_DEEP_STUBS);
		ExcelTableViewer mockExcel = mock(ExcelTableViewer.class, Mockito.RETURNS_DEEP_STUBS);
		
		
		when(mock.setTableView(mockExcel)).thenReturn(mockExcel);
		
		ExcelTableViewer res = mock.setTableView(mockExcel);
		
		verify(mock, times(1)).setTableView(mockExcel);
		assertEquals(res,instance.setTableView(mockExcel));
	}
	
	
	@Test
	public void test_getGradingData() {
		GradingData data2 =Mockito.mock(GradingData.class, Mockito.RETURNS_DEEP_STUBS);
		ScanExamController instance = new ScanExamController(data2);
		GradingData data3 =Mockito.mock(GradingData.class, Mockito.RETURNS_DEEP_STUBS);
		ScanExamController instanceF = new ScanExamController(data3);
		ScanExamController mock = mock(ScanExamController.class, Mockito.RETURNS_DEEP_STUBS);

		when(mock.getGradingData()).thenReturn(data2);
		
		GradingData res = mock.getGradingData();
		
		verify(mock, times(1)).getGradingData();
		assertEquals(res,instance.getGradingData());
		assertNotEquals(res,instanceF.getGradingData());
	}

	
	
	
	@Test
	public void extractQuestionZone() {
		GradingData data2 =Mockito.mock(GradingData.class, Mockito.RETURNS_DEEP_STUBS);
		ScanExamController instance = new ScanExamController(data2);
		ScanExamController mock = mock(ScanExamController.class, Mockito.RETURNS_DEEP_STUBS);

		BufferedImage mockBuffered = mock(BufferedImage.class, Mockito.RETURNS_DEEP_STUBS);

		
		when(mock.extractQuestionZone()).thenReturn(mockBuffered);
		
		BufferedImage res = mock.extractQuestionZone();
		
		verify(mock, times(1)).extractQuestionZone();
		assertEquals(res,instance.extractQuestionZone());
	}
	
	
	
	
	@Test
	public void getCurrentQuestion() {
		GradingData data2 =Mockito.mock(GradingData.class, Mockito.RETURNS_DEEP_STUBS);
		ScanExamController instance = new ScanExamController(data2);
		ScanExamController mock = mock(ScanExamController.class, Mockito.RETURNS_DEEP_STUBS);

		Question mockQuestion = mock(Question.class, Mockito.RETURNS_DEEP_STUBS);

		when(data2.getExam().getQuestions().get(0)).thenReturn(mockQuestion);
		when(mock.getCurrentQuestion()).thenReturn(mockQuestion);
		
		Question res = mock.getCurrentQuestion();
		
		verify(mock, times(1)).getCurrentQuestion();
		assertEquals(res,instance.getCurrentQuestion());
	}
	
	
	
	@Test
	public void getCurrentPageIndex_nextExam_prevExam() {
		GradingData data2 =Mockito.mock(GradingData.class, Mockito.RETURNS_DEEP_STUBS);
		ScanExamController instance = new ScanExamController(data2);
		ScanExamController mock = mock(ScanExamController.class, Mockito.RETURNS_DEEP_STUBS);

		Question mockQuestion = mock(Question.class, Mockito.RETURNS_DEEP_STUBS);

		when(data2.getExam().getQuestions().size()).thenReturn(10);
		int debut = instance.getCurrentPageIndex();
		instance.nextExam();
		instance.prevExam();
		int fin = instance.getCurrentPageIndex();
		instance.nextExam();
		
		when(data2.getExam().getQuestions().get(0)).thenReturn(mockQuestion);
		when(mock.getCurrentPageIndex()).thenReturn(1);
		
		int res = mock.getCurrentPageIndex();
		
		verify(mock, times(1)).getCurrentPageIndex();
		assertEquals(res,instance.getCurrentPageIndex());
		assertEquals(debut,fin);
	}
	
	
	
	@Test
	public void test_loadCurrentPage() {
		GradingData data2 =Mockito.mock(GradingData.class, Mockito.RETURNS_DEEP_STUBS);

		ScanExamController instance = new ScanExamController(data2);

		ScanExamController mock = mock(ScanExamController.class, Mockito.RETURNS_DEEP_STUBS);
		
		int value = 2;
		
		//instance.setGrade(value);
		//instance.increaseGrade();
		when(mock.loadCurrentPage()).thenReturn("");
		mock.loadCurrentPage();
		verify(mock, times(1)).loadCurrentPage();
		//assertEquals(mock.getCurrentGradeIndex(),instance.getCurrentGradeIndex());
	}
	
	
	//////updateQuestionGradeIndex()
	
	
	@Test
	public void updateQuestionInfo() {
		GradingData data2 =Mockito.mock(GradingData.class, Mockito.RETURNS_DEEP_STUBS);
		ScanExamController instance = new ScanExamController(data2);
		ScanExamController mock = mock(ScanExamController.class, Mockito.RETURNS_DEEP_STUBS);

		Question mockQuestion = mock(Question.class, Mockito.RETURNS_DEEP_STUBS);

		when(data2.getExam().getQuestions().get(0)).thenReturn(mockQuestion);
		doNothing().when(mock).updateQuestionInfo();
		mock.updateQuestionInfo();
		
		verify(mock, times(1)).updateQuestionInfo();
	}
	
	
	
	
	
	@Test
	public void getNextQuestion_and_getPrevQuestion() {
		GradingData data2 =Mockito.mock(GradingData.class, Mockito.RETURNS_DEEP_STUBS);
		ScanExamController instance = new ScanExamController(data2);
		ScanExamController mock = mock(ScanExamController.class, Mockito.RETURNS_DEEP_STUBS);

		Question mockQuestion = mock(Question.class, Mockito.RETURNS_DEEP_STUBS);

		when(data2.getExam().getQuestions().get(0)).thenReturn(mockQuestion);
		//when(mock.getNextQuestion()).thenReturn(mockQuestion);
		
		Question debut = instance.getCurrentQuestion();
		instance.getNextQuestion();
		mock.getNextQuestion();
		
		instance.getPrevQuestion();
		Question fin = instance.getCurrentQuestion();
		instance.getNextQuestion();
		
		Question res = mock.getCurrentQuestion();
		
		verify(mock, times(1)).getCurrentQuestion();
		assertEquals(res,instance.getCurrentQuestion());
		assertEquals(debut,fin);
	}
	

	
	@Test
	public void test_increaseGrade() {
		GradingData data2 =Mockito.mock(GradingData.class, Mockito.RETURNS_DEEP_STUBS);

		ScanExamController instance = new ScanExamController(data2);

		ScanExamController mock = mock(ScanExamController.class, Mockito.RETURNS_DEEP_STUBS);
		Question mockQues = mock(Question.class, Mockito.RETURNS_DEEP_STUBS);
		
		
		int value = 2;
		when(data2.getExam().getQuestions().size()).thenReturn(10);
		
		instance.setGrade(value);
		instance.increaseGrade();
		
		//doNothing().when(mock).increaseGrade();
		//when(mock.getCurrentGradeIndex()).thenReturn(value+1);
		
		verify(mock, times(1)).increaseGrade();
		assertEquals(mock.getCurrentGradeIndex(),value+1);
	}
	
	
	@Test
	public void test_SetGrade() {
		//Mock
		data2 =Mockito.mock(GradingData.class, Mockito.RETURNS_DEEP_STUBS );
	 	controlTest2 =Mockito.mock(ScanExamController.class, Mockito.RETURNS_DEEP_STUBS );
	 	//Spy
		ScanExamController spyController = Mockito.spy(controlTest2);
		
		int val = 2;
		spyController.setGrade(val);
		
		//When
		when(spyController.getCurrentGradeIndex()).thenReturn(val);
		int resultatInc = spyController.getCurrentGradeIndex();
			
		//Verify & Assert
		verify(spyController, times(1)).setGrade(val);;
		assertEquals(spyController.getCurrentGradeIndex(),resultatInc);
	}
	
	
	
	
	@Test
	public void test_SetGrade2() {
		GradingData data2 =Mockito.mock(GradingData.class, Mockito.RETURNS_DEEP_STUBS);
		//GradingData data3 = new GradingData();
		ScanExamController instance = new ScanExamController(data2);
		GradingData data3 =Mockito.mock(GradingData.class, Mockito.RETURNS_DEEP_STUBS );
		
		
		data2.setExcelFileName("/fr.istic.tools.scanexam/backups/PFO_december_19_2020-09-15-16-12-28.xls");
		System.out.println();
		
		//instance.loadExcel();
		//instance.
		//instance.getGradingData().getExam().getQuestions().add(l);
		data2.getExam().getQuestions().add(null);
		data2.getExam().getQuestions().add(null);
		data2.getExam().getQuestions().add(null);
		data2.getExam().getQuestions().add(null);
		data2.getExam().getQuestions().add(null);
		System.out.println("Nb element : " + instance.getGradingData().getExam().getQuestions().size());
		
		
		ScanExamController mock = mock(ScanExamController.class, Mockito.RETURNS_DEEP_STUBS);
		//when(data2.exam.questions.get(currentQuestionIndex))
		int value = 0;
		//instance.currentQuestionIndex=1;
		//instance.getPrevQuestion();
		//System.out.println(instance.getCurrentQuestion());
		instance.setGrade(value);
		when(mock.getCurrentGradeIndex()).thenReturn(value);
		verify(instance, times(1)).setGrade(value);
		assertEquals(mock.getCurrentGradeIndex(),instance.getCurrentGradeIndex());
	}
	
	
	
	@Test
	public void test_decreaseGrade() {
		GradingData data2 =Mockito.mock(GradingData.class, Mockito.RETURNS_DEEP_STUBS);

		ScanExamController instance = new ScanExamController(data2);

		ScanExamController mock = mock(ScanExamController.class, Mockito.RETURNS_DEEP_STUBS);
		
		int value = 2;
		
		mock.setGrade(value);
		instance.setGrade(value);
		instance.decreaseGrade();
		
		doNothing().when(mock).increaseGrade();
		when(mock.getCurrentGradeIndex()).thenReturn(value);
	
		verify(instance, times(1)).increaseGrade();
		assertEquals(mock.getCurrentGradeIndex(),instance.getCurrentGradeIndex());
	}
	
	
	
	
	
	@Test
	public void test_getNbStudent() {
		GradingData data2 =Mockito.mock(GradingData.class, Mockito.RETURNS_DEEP_STUBS);

		ScanExamController instance = new ScanExamController(data2);

		ScanExamController mock = mock(ScanExamController.class, Mockito.RETURNS_DEEP_STUBS);
		
		int value = 2;

		doNothing().when(mock).increaseGrade();
		when(mock.getNbStudent()).thenReturn(value);
	
		//Ajouter NumberPage
		
		assertEquals(mock.getNbStudent(),instance.getNbStudent());
	}
	
	
	
	
	@Test
	public void gotoStudent_nextExam_prevExam() {
		GradingData data2 =Mockito.mock(GradingData.class, Mockito.RETURNS_DEEP_STUBS);
		ScanExamController instance = new ScanExamController(data2);
		ScanExamController mock = mock(ScanExamController.class, Mockito.RETURNS_DEEP_STUBS);

		Question mockQuestion = mock(Question.class, Mockito.RETURNS_DEEP_STUBS);

		when(data2.getExam().getQuestions().size()).thenReturn(10);
		long val =0;
		instance.gotoStudent(val);
		StudentGrade debut = instance.getCurrentStudent();
		instance.nextExam();
		instance.prevExam();
		StudentGrade fin = instance.getCurrentStudent();
		
		
		doNothing().when(mock).gotoStudent(val);
		
		mock.gotoStudent(val);
		
		verify(mock, times(1)).gotoStudent(val);
		assertEquals(mock.getCurrentStudent(),instance.getCurrentStudent());
		assertEquals(debut,fin);
	}
	


	@Test
	public void test_getNextQuestion() {
		createData();
		ArgumentCaptor<ScanExamController> captor = ArgumentCaptor.forClass(ScanExamController.class);
		
		
		//verify(controlTest2).update(captor.capture());
		
		ScanExamController spyController = Mockito.spy(controlTest2);
		spyController.setGrade(1);
		spyController.increaseGrade();
		
		int resultatIncSpy = (
				spyController.getCurrentGradeIndex()
				
				);
		//ScanExamController controlTest =Mockito.mock(ScanExamController.class);

		//controlTest2.setGrade(1);
		//int previousIndex = controlTest.getCurrentGradeIndex();
		//int currentStudentIndex =0;
		int val = 2;
		spyController.setGrade(val);
		when(spyController.getCurrentGradeIndex()).thenReturn(val);
		int resultatInc = spyController.getCurrentGradeIndex();
				
		
		//int resultatInc = Mockito.doAnswer(invocation -> controlTest2.getCurrentGradeIndex()).when(controlTest2).increaseGrade();
		//controlTest.currentGradeValueIndex++;
		//int newIndex = controlTest.getCurrentGradeIndex();

		controlTest2.decreaseGrade();
		System.out.println(resultatInc);
		verify(spyController, times(1)).setGrade(val);;
		assertEquals(spyController.getCurrentGradeIndex(),resultatInc);
		
		
		//doNothing().when(controlTest).getNextQuestion();


		//verify(controlTest2, times(1)).decreaseGrade();
		//assertEquals(previousIndex,newIndex-1);
	}
	
	
	
	
	
	@Test
	public void test_getCurrentStudent() {
		GradingData data2 =Mockito.mock(GradingData.class, Mockito.RETURNS_DEEP_STUBS);

		ScanExamController instance = new ScanExamController(data2);

		ScanExamController mock = mock(ScanExamController.class, Mockito.RETURNS_DEEP_STUBS);
		
		
		StudentGrade val = instance.getCurrentStudent();

		when(mock.getCurrentStudent()).thenReturn(val);
	    
		StudentGrade res = mock.getCurrentStudent();
		
		verify(mock, times(1)).getCurrentStudent();
		assertEquals(res,val);
	}
	
	
	@Test
	public void test_getCurrentQuestionGrade() {
		GradingData data2 =Mockito.mock(GradingData.class, Mockito.RETURNS_DEEP_STUBS);

		ScanExamController instance = new ScanExamController(data2);

		ScanExamController mock = mock(ScanExamController.class, Mockito.RETURNS_DEEP_STUBS);
		
		
		QuestionGrade val = instance.getCurrentQuestionGrade();

		when(mock.getCurrentQuestionGrade()).thenReturn(val);
	    
		QuestionGrade res = mock.getCurrentQuestionGrade();
		
		verify(mock, times(1)).getCurrentQuestionGrade();
		assertEquals(res,val);
	}
	
	
	
	@Test
	public void test_getCurrentGradeValue() {
		GradingData data2 =Mockito.mock(GradingData.class, Mockito.RETURNS_DEEP_STUBS);

		ScanExamController instance = new ScanExamController(data2);

		ScanExamController mock = mock(ScanExamController.class, Mockito.RETURNS_DEEP_STUBS);
		
		
		String val = instance.getCurrentGradeValue();

		when(mock.getCurrentGradeValue()).thenReturn(val);
	    
		String res = mock.getCurrentGradeValue();
		
		verify(mock, times(1)).getCurrentGradeValue();
		assertEquals(res,val);
	}
	
	
	
	@Test
	public void test_getCurrentGradeIndex() {
		GradingData data2 =Mockito.mock(GradingData.class, Mockito.RETURNS_DEEP_STUBS);

		ScanExamController instance = new ScanExamController(data2);

		ScanExamController mock = mock(ScanExamController.class, Mockito.RETURNS_DEEP_STUBS);
		
		
		int val = instance.getCurrentGradeIndex();
		
		when(mock.getCurrentGradeIndex()).thenReturn(val);
	    
		int res = mock.getCurrentGradeIndex();
		
		verify(mock, times(1)).getCurrentGradeIndex();
		assertEquals(res,val);
	}

	
	

	@After
	public void tearDown() throws Exception {
		//System.out.println("Fin des test");
		controlTest2 = null;
		data2 = null;
		assertNull(controlTest2);
		assertNull(data2);
	}



}
