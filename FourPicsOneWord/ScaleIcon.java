//********************************************************************** 
// ScaleIcon.java, Yuxiang Wang, Gj4912oy@metrostate.edu, Midterm, July .9 
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.Icon;

/**
 * 
 * @author
 * 
 */
public class ScaleIcon implements Icon {

    private Icon icon;

    private int scaleWidth;

    private int scaleHeight;

    public ScaleIcon(Icon icon, int scaleWidth, int scaleHeight) {
	this.icon = icon;
	this.scaleHeight = scaleHeight;
	this.scaleWidth = scaleWidth;
    }

    public int getIconHeight() {
	return icon.getIconHeight();
    }

    @Override
    public int getIconWidth() {
	return icon.getIconWidth();
    }

    /**
 * 
 * 
 */
    public void paintIcon(Component c, Graphics g, int x, int y) {
	Graphics2D g2d = (Graphics2D) g;
	int iconWid = icon.getIconWidth();
	int iconHei = icon.getIconHeight();
	g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
		RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	g2d.scale(this.scaleWidth * 1.0 / iconWid, this.scaleHeight * 1.0
		/ iconHei);
	icon.paintIcon(c, g2d, 0, 0);
    }

    public int getScaleWidth() {
	return scaleWidth;
    }

    public void setScaleWidth(int scaleWidth) {
	this.scaleWidth = scaleWidth;
    }

    public int getScaleHeight() {
	return scaleHeight;
    }

    public void setScaleHeight(int scaleHeight) {
	this.scaleHeight = scaleHeight;
    }
}