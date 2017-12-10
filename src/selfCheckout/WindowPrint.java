package selfCheckout;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Toolkit;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class WindowPrint extends JFrame
{
	private static JTextArea textArea = new JTextArea();

	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	public static JTextArea getTextArea()
	{
		return textArea;
	}
	
	public static void appendTextArea(Item x)
	{
		textArea.append( x.getName()+ " $" + x.getPrice() + " \n");
	}
	
	public static void appendTextArea(String s)
	{
		textArea.append(s);
	}
	
	public static void setTextAreaText(String s)
	{
		textArea.setText(s);
	}


	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public WindowPrint()
	{
		setAlwaysOnTop(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(WindowPrint.class.getResource("/com/sun/javafx/scene/control/skin/modena/HTMLEditor-Justify-Yellow.png")));
		setResizable(false);
		setTitle("Screen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 254, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		textArea.setBounds(10, 11, 228, 366);
		contentPane.add(textArea);
	}

}
