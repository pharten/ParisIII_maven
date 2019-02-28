package parisEvents;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import parisInit.Units;

public class FocusListenerAdapter extends FocusAdapter {

	private Text[] vText;
	private DecimalFormat df;
	private static DecimalFormat pf = (DecimalFormat)NumberFormat.getInstance();
	private Color defaultColor;
	private Units unit = null;
	private double[] tolerance = null;
	private boolean textChanged = false;
	private Text focusText;
	private String textSav = "";
	private Color swtRed = SWTResourceManager.getColor(SWT.COLOR_RED);
	private Color swtGreen = SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN);
	
	public FocusListenerAdapter(Text[][] aText, int row, NumberFormat df, Units unit, double[] tolerance) {
		this.vText = aText[row];
		this.df = (DecimalFormat)df;
		this.unit = unit;
		this.tolerance = tolerance;
		
		try {

			if (vText.length!=5) {
				throw new Exception("Error: wrong number of Text Fields");
			} else if (vText[0]== null || vText[1]==null || vText[2]==null || vText[3]==null || vText[4]==null) {
				throw new Exception("Error: Text Field has null address");
			} else {
				pf.applyPattern("#0.0#");
			}

		} catch (ParseException e) {
			MessageBox dialog = new MessageBox(focusText.getShell(), SWT.ICON_ERROR);
			dialog.setText("Error");
			dialog.setMessage("Illegal number format");
			dialog.open();
			focusText.forceFocus();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		Text focusText = (Text)arg0.widget;
		this.setDefaultColor(focusText.getForeground());
		focusText.setForeground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		textSav = focusText.getText();
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		focusText = (Text)arg0.widget;		
		double tol, lb, val, ub, rep;
		
		try {
			
			focusText.setForeground(this.getDefaultColor());
			if (focusText.getText().equals(textSav)) {
				// no text change this time
			} else if (focusText==vText[1] && !vText[2].isEnabled()) { // Flash Point case
				lb = df.parse(vText[1].getText()).doubleValue();
				vText[1].setText(df.format(lb));
				rep = df.parse(vText[4].getText()).doubleValue();
				if (lb <= rep) vText[4].setForeground(swtGreen);
				else vText[4].setForeground(swtRed);
				textChanged = true;
			} else {
				
				lb = df.parse(vText[1].getText()).doubleValue();
				val = df.parse(vText[2].getText()).doubleValue();
				ub = df.parse(vText[3].getText()).doubleValue();
				rep = df.parse(vText[4].getText()).doubleValue();

				if (focusText==vText[0] || focusText==vText[2]) {// tolerance or desired fields
					tol = 0.01 * pf.parse(vText[0].getText()).doubleValue();
				} else if (focusText==vText[1] || focusText==vText[3]) { // bounds fields
					tol = (ub-val>=val-lb ? ub-val : val-lb) / val;
				} else {
					throw new Exception("Error: Incorrect Text Field");
				}

				lb = val*(1.0-tol);
				ub = val*(1.0+tol);
				if (unit==null && lb < 0.0) lb = 0.0;  // change this from unit==null to negativeIsAllowed()

				vText[0].setText(pf.format(tol*100.0));
				vText[1].setText(df.format(lb));
				vText[2].setText(df.format(val));
				vText[3].setText(df.format(ub));
				if (lb <= rep && rep <= ub) vText[4].setForeground(swtGreen);
				else vText[4].setForeground(swtRed);
				
				textChanged = true;
			}
			
		} catch (ParseException e) {
			MessageBox dialog = new MessageBox(focusText.getShell(), SWT.ICON_ERROR);
			dialog.setText("Error");
			dialog.setMessage("Illegal number format");
			dialog.open();
			focusText.forceFocus();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	public Color getDefaultColor() {
		return defaultColor;
	}

	public void setDefaultColor(Color defaultColor) {
		this.defaultColor = defaultColor;
	}

	public boolean isTextChanged() {
		return textChanged;
	}

}
