package com.lionel.utils.QRCode;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class QRCodeTest {

	public static void main(String[] args) throws Exception {
		String text = "17左边男厕所巡检点";
		BufferedImage bi = ImageIO.read(new File("e:/test/01.jpg")); 
		QRCodeUtils utils=new QRCodeUtils();
		utils.setLogoHight(100);
		utils.setLogoWidth(100);
		utils.setImageSize(500);
		utils.setQRcodeSize(450);
		utils.setContentFontSize(30);
		utils.encode(text,bi,"00100100008","00100100008","e:/test", true);
	}

}
