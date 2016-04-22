package com.gc.temple;

public class Temple {

	public static void main(String[] args) {
		
		int input = 0;
		Visitor test = new Visitor();
		test.setPhoneNumber("8108887654");
		test.setFirstName("Ralph");
		test.setLastName("Jubroni");
		test.setAddress("123 Who Cares st");

		
		
		if (input == 1) {
			VisitorDao.saveEntry(test);
		} else if (input == 2) {
			VisitorDao.listVisitors();
		}
	}
}
