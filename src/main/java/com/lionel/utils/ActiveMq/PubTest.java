package com.lionel.utils.ActiveMq;

public class PubTest {

	public static void main(String[] args) {
		
		/*for(int i=0;i<5;i++) {
			PubReceiverThread thread=new PubReceiverThread("線程"+i);
			thread.start();
		}*/

		for(int i=0;i<5;i++) {
			AppReceiverThread thread=new AppReceiverThread("線程"+i);
			thread.start();
		}
	}

}
