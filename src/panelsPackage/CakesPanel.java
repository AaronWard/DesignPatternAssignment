package panelsPackage;

import javax.swing.JPanel;
import main.PanelFactory;

public class CakesPanel extends PanelFactory {

	private JPanel mainCakesPanel;
	@Override
	public JPanel getPanel() {
		return mainCakesPanel;
	}

}
