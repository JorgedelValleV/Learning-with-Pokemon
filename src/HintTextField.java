

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;
import javax.swing.border.Border;

public class HintTextField extends JTextField implements FocusListener {

	private Color borderColor = Color.black;
	private Color hintColor = new Color(20, 88, 72), textColor = Color.black, backColor = Color.WHITE;
	private final String hint;
	private boolean showingHint;

	public HintTextField(final String hint) {
		super(hint);
		this.hint = hint;
		this.showingHint = true;
		super.addFocusListener(this);
		Font fuente = new Font("SansSerif", 0, 20);
		this.setFont(fuente);
		this.setForeground(hintColor);
		this.setBorder(null);
		this.setHorizontalAlignment(JTextField.CENTER);
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (this.getText().isEmpty()) {
			super.setText("");
			this.setForeground(textColor);
			showingHint = false;
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (this.getText().isEmpty()) {
			super.setText(hint);
			this.setForeground(hintColor);
			showingHint = true;
		}
	}

	@Override
	public String getText() {
		return showingHint ? "" : super.getText();
	}

	public void displayError(boolean err) {
		backColor = (err ? new Color(252, 214, 214) : Color.WHITE);
		repaint();

	}

	public void paint(Graphics g) {

		this.setBackground(backColor);

		super.paint(g);
		g.setColor(borderColor);
		g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);

	}
}