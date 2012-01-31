package com.farata.cleardatabuilder.js.facet.sample.ui;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.farata.cleardatabuilder.js.facet.sample.SampleInstallWizardPage;

public class SampleInstallWizardPageUI {

	private Composite				parent					= null; // @jve:decl-index=0:visual-constraint="46,23"
	private Label					label					= null; // @jve:decl-index=0:
	private Text					sampleDBInstallFolder	= null;
	private Button					button					= null;
	private SampleInstallWizardPage	installWizardPage;

	/**
	 * This method initializes parent
	 * 
	 */
	public void createParent() {
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = false;
		gridData.verticalAlignment = GridData.CENTER;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		gridLayout.makeColumnsEqualWidth = false;
		if (parent == null) {
			parent = new Shell();
			parent.setSize(new Point(463, 288));
		}
		parent.setLayout(gridLayout);
		label = new Label(parent, SWT.NONE);
		label.setText("Install sample database to folder:");
		sampleDBInstallFolder = new Text(parent, SWT.BORDER);
		sampleDBInstallFolder.setLayoutData(gridData);
		sampleDBInstallFolder.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				boolean valid = installWizardPage.validateInstallationFolder(new File(sampleDBInstallFolder.getText()));
				installWizardPage.setPageComplete(valid);
				if (valid) {
					installWizardPage.setErrorMessage(null);
					installWizardPage.getConfig().setSampleDBInstallFolder(new File(sampleDBInstallFolder.getText()));
				} else {
					installWizardPage.setErrorMessage("Installtion folder is not valid.");
				}
			}
		});
		button = new Button(parent, SWT.NONE);
		button.setText("Browse...");
		button.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				DirectoryDialog dialog = new DirectoryDialog(parent.getShell());
				if (!"".equals(sampleDBInstallFolder.getText())) {
					String initialDir = sampleDBInstallFolder.getText();
					dialog.setFilterPath(initialDir);
				}
				String result = dialog.open();
				if (result != null) {
					sampleDBInstallFolder.setText(result.toString());
				}
			}
		});
	}

	public SampleInstallWizardPageUI(
			SampleInstallWizardPage sampleInstallWizardPage, Composite parent) {
		this.parent = parent;
		this.installWizardPage = sampleInstallWizardPage;
	}
}