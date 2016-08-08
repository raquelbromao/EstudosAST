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
				System.out.println("Method name: " + method.getName() + "\nReturn type: " + method.getReturnType2()
						+ "\nAnother GetClass information: " + method.getClass()
						+ "\nAnother2 ReceiverType Informarion: " + method.getReceiverType() + "\nReturn body:"
						+ method.getBody() + "\n\n");
			}

			// Chamada em loop para análise do corpo dos métodos
			for (IPackageFragment mypackage : packages) {
				IType[] allTypes = unit.getAllTypes();

				// Lista todas as classes do projeto, independente se estiver no
				// mesmo arquivo ou não
				for (IType type : allTypes) {
					System.out.println("\nClass Name: " + type.getElementName() + " / " + type.getFullyQualifiedName());
					String sourceCode = type.getSource();

					ASTParse(sourceCode, type);
				}
			}
		}
	}

	private void ASTParse(String sourceCode, IType type) {
		ASTParser parser = ASTParser.newParser(AST.JLS3);

		parser.setSource(sourceCode.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);

		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);

		cu.accept(new ASTVisitor() {

			Set names = new HashSet();

			public boolean visit(MethodDeclaration node) {
				SimpleName name = node.getName();
				this.names.add(name.getIdentifier());
				System.out.println("    Declaration of '" + name.getFullyQualifiedName() + "' at line"
						+ cu.getLineNumber(name.getStartPosition()));
				
				
				methodsDec.add(node);

				/*
				 * MethodDec metodoDec = new MethodDec();
				 * metodoDec.setName(name.getFullyQualifiedName());
				 * metodoDec.setClassOrg(type.getElementName());
				 */

				Block body = node.getBody();
				body.accept(new ASTVisitor() {

					Set names = new HashSet();

					public boolean visit(MethodInvocation node) {
						SimpleName name = node.getName();

						this.names.add(name.getIdentifier());
						System.out.println("     Invocation of '" + name.getFullyQualifiedName() + "' at line"
								+ cu.getLineNumber(name.getStartPosition()));

						/*
						 * MethodDec metodoInv = new MethodDec();
						 * metodoInv.setName(name.getFullyQualifiedName());
						 * metodoInv.setClassOrg(name.getParent().toString());
						 * metodoDec.setMethodsInvocates(metodoInv);
						 */
						return false; // do not continue
					}
				});

				// methodsDec.add(metodoDec);

				return false; // do not continue
			}

			public boolean visit(SimpleName node) {
				if (this.names.contains(node.getIdentifier())) {
					System.out.println("Usage of '" + node + "' at line " + cu.getLineNumber(node.getStartPosition()));
				}
				return true;
			}
		});

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