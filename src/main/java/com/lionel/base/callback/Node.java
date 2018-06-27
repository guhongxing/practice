package com.lionel.base.callback;

import java.util.ArrayList;
import java.util.List;



public class Node {
	
	private String name;
	
	private List<Node> childNode=new ArrayList<Node>();
	
	private String parentName;
	
	public Node(String name) {
		this.name=name;
	}

	public void addChild(Node node) {
		childNode.add(node);
		node.setParentName(this.name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public List<Node> getChildNode() {
		return childNode;
	}
	
	public boolean isPrent(String name) {
		if(this.name.equals(name)) {
			return true;
		}
		else
			return false;
	}
	public void addNewNOde(String name,Node node) {
		if(isPrent(name)) {
			this.addChild(node);
		}
		else {
			for (Node nod : childNode) {
				nod.addNewNOde(name,node);
			}
		}
			
	}
	
	public void print() {
		System.out.println("父节点："+name);
		if(childNode.size()>0) {
			for (Node nod : childNode) {
				System.out.println(name+"的子节点："+nod.name);
				nod.print();
			}
			
		}
	}
	
}
