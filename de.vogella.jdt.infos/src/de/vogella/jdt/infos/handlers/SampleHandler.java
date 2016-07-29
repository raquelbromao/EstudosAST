package de.vogella.jdt.infos.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.text.Document;

/**
 * 
 *	ORDEM DAS CHAMADAS DOS MÉTODOS
 * 1. execute(ExecutionEvent event)
 * 2. printProjectInfo(project)
 * 3. printPackageInfos(javaProject)
 * 4. printICompilationUnitInfo(mypackage)
 * 5. printCompilationUnitDetails(unit)
 * 6. printIMethods(unit)
 * 7. printIMethodDetails(type)
 *
 */

public class SampleHandler extends AbstractHandler {

        public Object execute(ExecutionEvent event) throws ExecutionException {
                // Pega a raiz da workspace
                IWorkspace workspace = ResourcesPlugin.getWorkspace();
                IWorkspaceRoot root = workspace.getRoot();
                // Pega todos os projetos da workspace
                IProject[] projects = root.getProjects();
                // Faz um loop sobre todos os projetos
                for (IProject project : projects) {
                        try {
							printProjectInfo(project);
						} catch (JavaModelException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (CoreException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                }
                return null;
        }

        private void printProjectInfo(IProject project) throws CoreException,
                        JavaModelException {
        		// Imprime o nome dos projetos
                System.out.println("Working in project " + project.getName());
                // Checa se há um projeto Java
                IJavaProject javaProject = JavaCore.create(project);
                printPackageInfos(javaProject);
        }

        private void printPackageInfos(IJavaProject javaProject)
                        throws JavaModelException {
                IPackageFragment[] packages = javaProject.getPackageFragments();
                for (IPackageFragment mypackage : packages) {
                        // Package fragments include all packages in the
                        // classpath
                        // We will only look at the package from the source
                        // folder
                        // K_BINARY would include also included JARS, e.g.
                        // rt.jar
                        if (mypackage.getKind() == IPackageFragmentRoot.K_SOURCE) {
                        		// Imprime nome do pacote
                                System.out.println("Package " + mypackage.getElementName());
                                printICompilationUnitInfo(mypackage);
                        }
                }
        }

        private void printICompilationUnitInfo(IPackageFragment mypackage)
                        throws JavaModelException {
                for (ICompilationUnit unit : mypackage.getCompilationUnits()) {
                        printCompilationUnitDetails(unit);
                }
        }

        private void printCompilationUnitDetails(ICompilationUnit unit)
                        throws JavaModelException {
        		// Imprime nome do arquivo fonte java
                System.out.println("Source file " + unit.getElementName());
                Document doc = new Document(unit.getSource());
                // Imprime o número de linhas do documento java
                System.out.println("Has number of lines: " + doc.getNumberOfLines() + "\n");
                printIMethods(unit);
        }
        
        private void printIMethods(ICompilationUnit unit) throws JavaModelException {
            IType[] allTypes = unit.getAllTypes();
            for (IType type : allTypes) {
                    printIMethodDetails(type);
            }
        }

        private void printIMethodDetails(IType type) throws JavaModelException {
                IMethod[] methods = type.getMethods();
                for (IMethod method : methods) {
                        System.out.println("Method name " + method.getElementName());
                        System.out.println("Signature " + method.getSignature());
                        System.out.println("Return Type " + method.getReturnType() + "\n");
                }
        }
}