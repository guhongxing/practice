package com.lionel.base.callback;

public class Tom implements Student {

	@Override
	public void resolveQuestion(Callback callback) {
		// 模拟解决问题
		try {
			System.out.println("学生正在认真思考这个问题，可能需要几秒时间");
			Thread.sleep(3000);
		} catch (InterruptedException e) {

		}

		System.out.println("老师，我已经做出来了");
		// 回调，告诉老师作业写了多久
		callback.tellAnswer(3);

	}

}
