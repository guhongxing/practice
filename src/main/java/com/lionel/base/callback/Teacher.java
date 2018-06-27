package com.lionel.base.callback;

public class Teacher implements Callback {
	
	private Student student;
	
	public Teacher(Student student) {
		
		this.student=student;
	}
	
	public void ask() {
		System.out.println("老师提问题了，请同学们回答。。。。。");
		student.resolveQuestion(this);
	}
	

	@Override
	public void tellAnswer(int answer) {
		System.out.println("知道了，你的答案是" + answer);

	}

}
