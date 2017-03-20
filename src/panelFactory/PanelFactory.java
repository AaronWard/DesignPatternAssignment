package panelFactory;
import javax.swing.JPanel;

/**
 * A factory for panels to be returned to the main GUI driver program
 * the getPanel() method is implemented across all subclasses to return
 * the appropriate panel to displayed in the view
 * @author aaron
 *
 */
public abstract class PanelFactory {
		public abstract JPanel getPanel();
}
