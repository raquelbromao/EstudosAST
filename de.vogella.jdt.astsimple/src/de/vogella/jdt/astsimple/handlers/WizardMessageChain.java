package de.vogella.jdt.astsimple.handlers;

import org.eclipse.jface.wizard.Wizard;

public class WizardMessageChain extends Wizard {

	public WizardMessageChain() {
		setWindowTitle("New Wizard");
	}

	@Override
	public void addPages() {
	}

	@Override
	public boolean performFinish() {
		return false;
	}

}
