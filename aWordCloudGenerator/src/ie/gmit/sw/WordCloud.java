package ie.gmit.sw;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class WordCloud {
	public static void main(String args[]) throws Exception {
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 62);
		BufferedImage image = new BufferedImage(600, 300, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics graphics = image.getGraphics();
		graphics.setColor(Color.red);
		graphics.setFont(font);
		graphics.drawString("Object-Oriented", 0, 100);
		font = new Font(Font.SANS_SERIF, Font.ITALIC, 42);
		graphics.setFont(font);
		graphics.setColor(Color.yellow);
		graphics.drawString("Software Development", 10, 150);
		font = new Font(Font.MONOSPACED, Font.PLAIN, 22);
		graphics.setFont(font);
		graphics.setColor(Color.blue);
		graphics.drawString("2012 Assignment", 40, 180);
		graphics.dispose();
		ImageIO.write(image, "png", new File("image.png"));
	}
}