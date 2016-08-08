package de.vogella.jdt.astsimple.handlers;

import java.util.ArrayList;

public class MethodInvocation {
	private String name;
	private String classOrg;
	
	private ArrayList<MethodInvocation> methodsInvocates;
	
	public MethodInvocation() {
		this.methodsInvocates = new ArrayList<MethodInvocation>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassOrg() {
		return classOrg;
	}
	public void setClassOrg(String classOrg) {
		this.classOrg = classOrg;
	}
	public ArrayList<MethodInvocation> getMethodsInvocates() {
		return methodsInvocates;
	}
	public void setMethodsInvocates(MethodInvocation methodsInvocates) {
		this.methodsInvocates.add(methodsInvocates);
	}

	
}