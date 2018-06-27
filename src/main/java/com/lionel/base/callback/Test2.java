package com.lionel.base.callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test2 {

	public static void main(String[] args) {
		Map<String, String> relation=new HashMap<String, String>();
		List<String> list=new ArrayList<String>();
		relation.put("01", "0");
		list.add("01");
		relation.put("02", "0");
		list.add("02");
		relation.put("01H", "01");
		list.add("01H");
		relation.put("01B", "01");
		list.add("01B");
		relation.put("01HOA", "01H");
		list.add("01HOA");
		relation.put("01BBM", "01B");
		list.add("01BBM");
		relation.put("02H", "02");
		list.add("02H");

		Node root=new Node("0");
		//Set<String> set=relation.keySet();
		for(String str : list) {
			String prentNode=relation.get(str);
			Node childNode=new Node(str);
			root.addNewNOde(prentNode, childNode);
		}
		root.print();
		/*String name=root.getChildNode().get(0).getChildNode().get(1).getName();
		int size=root.getChildNode().get(0).getChildNode().get(0).getChildNode().size();
		System.out.println(name);
		System.out.println(size);*/
	}

}
