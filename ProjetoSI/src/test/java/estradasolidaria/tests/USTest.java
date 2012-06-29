package estradasolidaria.tests;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


import easyaccept.EasyAcceptFacade;
import estradasolidaria.ui.server.logic.*;

public class USTest extends TestCase {

	public USTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(USTest.class);
	}

	public void testarEasyAcceptScript() {

		List<String> files = new ArrayList<String>();
		// Put the us1.txt file into the "test scripts" list
//		files.add("scripts/US01.txt");
//		files.add("scripts/US02.txt");
//		files.add("scripts/US03.txt");
//		files.add("scripts/US04.txt");
//		files.add("scripts/US05.txt");
//		files.add("scripts/US06.txt");
//        files.add("scripts/US07.txt");
//        files.add("scripts/US08.txt");
//        files.add("scripts/US09.txt");
//	    files.add("scripts/US10.txt");
	    files.add("scripts/US11.txt");
//	    files.add("scripts/US12.txt");
//	    files.add("scripts/US15.txt");
	    files.add("scripts/US16.txt");
	    
		// Instantiate the SistemaCaronas
		EasyacceptAdapterInterface sistemaAdapter = EasyacceptEstradaSolidariaAdapter.getInstance();
		// Instantiate EasyAccept facade
		EasyAcceptFacade eaFacade = new EasyAcceptFacade(sistemaAdapter, files);

		// Execute the tests
		eaFacade.executeTests();

		// Print the tests execution results
		System.out.println(eaFacade.getCompleteResults());
		
		assertTrue(eaFacade.getTotalNumberOfNotPassedTests() == 0);
	}
}
