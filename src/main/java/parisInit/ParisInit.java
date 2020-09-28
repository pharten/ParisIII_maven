package parisInit;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.swt.widgets.Text;


public class ParisInit extends Dialog {

	protected int result = 1;
	protected Shell shell;
	private Button startButton, cancelButton, techSupportButton, developersButton;
	private Developers developer;
	private TechSupport techSupport;

	/**
	 * Create the dialog.
	 * @param parent
	 */
	
    public ParisInit (Shell parent, int style) {
        super (parent, style);
        shell = new Shell(parent, SWT.CLOSE | SWT.TITLE | SWT.APPLICATION_MODAL);
        
        // Your code goes here (widget creation, set result, etc).
        createContents();
    }

    public ParisInit (Shell parent) {
        this (parent, 0); // your default style bits go here (not the Shell's style bits)
    }

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public int open() {
		start();
		return stop();
	}
	
	/**
	 * Open the dialog.
	 * @return the result
	 */
	public void start() {
		shell.open();
		shell.layout();
	}
	
	public int stop() {
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
		shell.setImage(getParent().getImage());
		shell.setText("PARIS III");
		shell.setSize(455, 335);
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		shell.setLayout(null);
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(5, 5, 316, 172);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		composite.setLayout(null);
		
		Text text = new Text(composite, SWT.CENTER | SWT.MULTI);
		text.setEditable(false);
		text.setText("Program for Assisting the Replacement of\r\nIndustrial Solvents");
		text.setBounds(10, 5, 296, 40);
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		
		Text text_1 = new Text(composite, SWT.CENTER | SWT.MULTI);
		text_1.setEditable(false);
		text_1.setText("PARIS III");
		text_1.setBounds(0, 51, 316, 78);
		text_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		text_1.setFont(SWTResourceManager.getFont("Arial", 40, SWT.BOLD));
		
		Text txtSolventDesignSoftware = new Text(composite, SWT.CENTER | SWT.MULTI);
		txtSolventDesignSoftware.setEditable(false);
		txtSolventDesignSoftware.setText("Solvent Design Software from US EPA\r\nVersion 1.4.1");
		txtSolventDesignSoftware.setBounds(10, 127, 296, 40);
		txtSolventDesignSoftware.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));

		Composite composite_1 = new Composite(shell, SWT.NONE);
		composite_1.setBounds(326, 5, 114, 172);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		composite_1.setLayout(null);
		
		ImageHyperlink parisImagehyperlink = new ImageHyperlink(composite_1, SWT.NONE);
		parisImagehyperlink.setBounds(10, 10, 92, 152);
		parisImagehyperlink.setImage(shell.getImage());
		parisImagehyperlink.setText("New ImageHyperlink");
		
		Composite composite_3 = new Composite(shell, SWT.NONE);
		composite_3.setBounds(5, 183, 297, 83);
		composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		composite_3.setLayout(null);
		
		techSupportButton = new Button(composite_3, SWT.NONE);
		techSupportButton.setText("Tech Support");
		techSupportButton.setBounds(30, 0, 250, 36);
		techSupportButton.setTouchEnabled(true);
		techSupportButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				techSupport = new TechSupport(shell);
				techSupport.open();
			}
		});
		
		developersButton = new Button(composite_3, SWT.NONE);
		developersButton.setText("Developers and Acknowledgements");
		developersButton.setBounds(30, 42, 250, 40);
		developersButton.setTouchEnabled(true);
		developersButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				developer = new Developers(shell);
				developer.open();
			}
		});
		
		Composite composite_2 = new Composite(shell, SWT.NONE);
		composite_2.setBounds(307, 183, 133, 83);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		composite_2.setLayout(null);
		
		startButton = new Button(composite_2, SWT.NONE);
		startButton.setText("Start");
		startButton.setBounds(0, 0, 83, 83);
		startButton.setTouchEnabled(true);
		startButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				result = SWT.OK;
				shell.dispose();
			}
		});
		
		cancelButton = new Button(composite_2, SWT.NONE);
		cancelButton.setText("Exit");
		cancelButton.setBounds(91, 40, 42, 43);
		cancelButton.setTouchEnabled(true);
		cancelButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				result = SWT.CANCEL;
				shell.dispose();
			}
		});
		
		Composite composite_4 = new Composite(shell, SWT.NONE);
		composite_4.setBounds(5, 272, 435, 29);
		composite_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		composite_4.setLayout(null);
		
		Label lblNewLabel = new Label(composite_4, SWT.CENTER);
		lblNewLabel.setBounds(78, 0, 260, 29);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setText("Paris III is a trademark of the US EPA");
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		
		shell.setTabList(new Control[]{composite, composite_1, composite_3, composite_2, composite_4});

	}

	public TechSupport getTechSupport() {
		return techSupport;
	}

	public Developers getDeveloper() {
		return developer;
	}

	public Button getStartButton() {
		return startButton;
	}
	
	public Button getCancelButton() {
		return cancelButton;
	}

	public Button getTechSupportButton() {
		return techSupportButton;
	}

	public Button getDevelopersButton() {
		return developersButton;
	}

	public Shell getShell() {
		return shell;
	}
	
	public int getResult() {
		return result;
	}
}
