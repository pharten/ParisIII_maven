package parisWork;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Vector;

import parisInit.States;

import org.eclipse.swt.widgets.Composite;

public class Screen extends Composite {
	States states = ParisWork.states;
	Chemicals allChemicals = ParisWork.allChemicals;
	Chemicals chemicals = ParisWork.chemicals;
	Replacements replacements = ParisWork.replacements;
	Vector<Mixture> bestMixtures = ParisWork.bestMixtures;
	
	DecimalFormat df = (DecimalFormat)NumberFormat.getInstance();
	DecimalFormat ef = (DecimalFormat)NumberFormat.getInstance();
	DecimalFormat pf = (DecimalFormat)NumberFormat.getInstance();

	public Screen(Composite parent, int style) throws Exception {
		super(parent, style);
		df.applyPattern("##0.00");
		ef.applyPattern("0.00E0");
		pf.applyPattern("#0.0#");
	}
	
	public void begin() throws Exception {
		restore();
		int screenNum = Integer.parseInt(this.getClass().getSimpleName().substring(6));
		states.getActiveState().setOpenScreen(screenNum);
//		System.out.println("at Screen"+screenNum);  // no longer used
	}
	
	public boolean finishUp() throws Exception {
		updateShared();
		return true;
	}
	
	public void updateShared() throws Exception {
		ParisWork.states = states;
		ParisWork.allChemicals = allChemicals;
		ParisWork.chemicals = chemicals;
		ParisWork.replacements = replacements;
		ParisWork.bestMixtures = bestMixtures;
	}

	public void restore() throws Exception {
		states = ParisWork.states;
		allChemicals = ParisWork.allChemicals;
		chemicals = ParisWork.chemicals;
		replacements = ParisWork.replacements;
		bestMixtures = ParisWork.bestMixtures;
	}

}
