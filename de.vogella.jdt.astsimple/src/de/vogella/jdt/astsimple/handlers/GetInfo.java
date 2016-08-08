package de.vogella.jdt.astsimple.handlers;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;

import org.eclipse.core.runtime.CoreException;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.StructuralPropertyDescriptor;
import org.eclipse.jdt.internal.compiler.ast.Block;

/**
 * 
 * @author Romao
 * @date 29/07/16
 * @time 15:04
 * 
 *       Pega as informações dos métodos de TODAS as classes do projeto
 *       principal aberto na workspace Pega o nome do método e o seu tipo de
 *       retorno
 *
 */

public class GetInfo extends AbstractHandler {

	private static final String JDT_NATURE = "org.eclipse.jdt.core.javanature";
	private StructuralPropertyDescriptor property;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();

		// Pega todos os projetos na workspace
		IProject[] projects = root.getProjects();

		// Faz um loop sobre todos os projetos e executa o método analyseMethods
		for (IProject project : projects) {
			try {
				if (project.isNatureEnabled(JDT_NATURE)) {
					analyseMethods(project);
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/*
	 * private void analyseClass (IProject project) { //IProject class = root. }
	 * 
	 */
	private void analyseMethods(IProject project) throws JavaModelException {
		IPackageFragment[] packages = JavaCore.create(project).getPackageFragments();
		// parse(JavaCore.create(project));
		for (IPackageFragment mypackage : packages) {
			if (mypackage.getKind() == IPackageFragmentRoot.K_SOURCE) {
				createASTmethod(mypackage);
				createASTInvocation(mypackage);
			}
		}
	}

	private void createASTmethod(IPackageFragment mypackage) throws JavaModelException {
		for (ICompilationUnit unit : mypackage.getCompilationUnits()) {
			// now create the AST for the ICompilationUnits
			CompilationUnit parse = parse(unit);
			MethodVisitor visitor = new MethodVisitor();
			parse.accept(visitor);			
			
			// Imprime na tela o nome do método e o tipo de retorno
			for (MethodDeclaration method : visitor.getMethods()) {
				System.out.println("####### Informações do METHOD DECLARATION ######");
				System.out.println("Method name: " + method.getName());
				System.out.println("Return type: " + method.getReturnType2());
				System.out.println("Return body: "+ method.getBody());
			}

		}
	}

	private void createASTInvocation(IPackageFragment mypackage) throws JavaModelException {
		for (ICompilationUnit unit : mypackage.getCompilationUnits()) {
			// now create the AST for the ICompilationUnits
			CompilationUnit parse = parse(unit);
			MethodInvoke visitor = new MethodInvoke();
			parse.accept(visitor);			
			
			// Imprime na tela o nome do método e o tipo de retorno
			for (MethodInvocation method : visitor.getMethods()) {	
				System.out.println("####### Informações do METHOD INVOCATION ######");
				System.out.println("Name: " + method.getName());
				System.out.println("Parent: " + method.getParent());
				System.out.println("Class: " + method.getClass());
				System.out.println("Expression: "+ method.getExpression());
			}
		}
	}
	 

	/**
	 * Reads a ICompilationUnit and creates the AST DOM for manipulating the
	 * Java source file
	 *
	 * @param unit
	 * @return
	 */

	private static CompilationUnit parse(ICompilationUnit unit) {
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(unit);
		parser.setResolveBindings(true);
		return (CompilationUnit) parser.createAST(null); // parse
	}
}