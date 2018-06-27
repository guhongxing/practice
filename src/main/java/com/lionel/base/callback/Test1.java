package com.lionel.base.callback;

public class Test1 {

	public static void main(String[] args) {
		
		Student tom=new Tom();
		Teacher teacher=new Teacher(tom);
		teacher.ask();
				

	}

}
