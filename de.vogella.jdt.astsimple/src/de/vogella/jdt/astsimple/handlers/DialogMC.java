package de.vogella.jdt.astsimple.handlers;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import de.vogella.jdt.astsimple.handlers.GetInfo;

public class DialogMC extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text results;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public DialogMC(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(451, 348);
		shell.setText(getText());
		
		results = new Text(shell, SWT.BORDER);
		results.setBounds(24, 10, 396, 233);
		results.setText(createASTInvocation);
		
		
		Button btnApply = new Button(shell, SWT.NONE);
		btnApply.setBounds(24, 261, 75, 25);
		btnApply.setText("Apply");
		
		Button btnCancel = new Button(shell, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		btnCancel.setBounds(107, 261, 75, 25);
		btnCancel.setText("Cancel");

	}
}
