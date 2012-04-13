package si1project2.tests;

import java.util.ArrayList;
import java.util.List;

import si1project2.logic.SistemaCaronas;
import easyaccept.EasyAcceptFacade;

public class SistemaCaronasTest {
	public static void main(String[] args) {
		 List<String> files = new ArrayList<String>();
		 
		 //Put the testScript1 file into the "test scripts" list
         files.add("scripts/US01.txt");
         files.add("scripts/US02.txt");
         files.add("scripts/US03.txt");
         files.add("scripts/US04.txt");
         files.add("scripts/US05.txt");
         files.add("scripts/US06.txt");
         files.add("scripts/US07.txt");
//         files.add("scripts/US08.txt");
//         files.add("scripts/US09.txt");
//         files.add("scripts/US10.txt");
//         files.add("scripts/US11.txt");
//         files.add("scripts/US12.txt");
         
         //Instantiate your software facade
	     SistemaCaronas sis1 = new SistemaCaronas();
	     
	     //Instantiate EasyAccept facade
	     EasyAcceptFacade eaFacade = new EasyAcceptFacade(sis1, files);
	     
	     //Execute the tests
	     eaFacade.executeTests();
	     
	     //Print the tests execution results
         System.out.println(eaFacade.getCompleteResults());
	}
}
