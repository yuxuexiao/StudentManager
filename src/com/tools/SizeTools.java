package com.tools;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class SizeTools {

	public static void frameSize(JFrame jf)
	{
		Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
		int x=(int) (dim.getWidth()-jf.getWidth())/2;
		int y=(int) (dim.getHeight()-jf.getHeight())/2;
		jf.setLocation(x, y);
	}
	public static void dialogSize(JDialog jd)
	{
		Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
		int x=(int) (dim.getWidth()-jd.getWidth())/2;
		int y=(int) (dim.getHeight()-jd.getHeight())/2;
		jd.setLocation(x, y);
	}
}
