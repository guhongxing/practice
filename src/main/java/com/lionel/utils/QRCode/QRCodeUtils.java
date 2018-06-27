package com.lionel.utils.QRCode;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.UUID;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.lionel.utils.QRCode.demo1.BufferedImageLuminanceSource;

public class QRCodeUtils {
	
	private String CHARSET = "utf-8";    
    private String FORMAT_NAME = "JPG";    
    // 二维码尺寸    
    private int QRcodeSize =600;    
    // LOGO宽度    
    private int logoWidth=100;    
    // LOGO高度    
    private int logoHight =100; 
    //二维码颜色
    private int QRcodeColor=0xFF000000;
    //二维码外边颜色
    private int QRcodeBackGround=0xFFFFFFFF;
    //下边文字颜色
    private int contentColor=0xFF000000;
    //图片大小
    private int imageSize=700;
    //文字区域宽
    private int contentWidth=600;
    //文字区域高
    private int contentHight=50;
    //下边文字字体
    private String contentFontname="Serif";
    //下边文字大小
    private int contentFontSize=30;
    
    public QRCodeUtils() {
    	
    }

	public int getQRcodeSize() {
		return QRcodeSize;
	}

	public void setQRcodeSize(int qRcodeSize) {
		QRcodeSize = qRcodeSize;
	}

	public int getLogoWidth() {
		return logoWidth;
	}

	public void setLogoWidth(int logoWidth) {
		this.logoWidth = logoWidth;
	}

	public int getLogoHight() {
		return logoHight;
	}

	public void setLogoHight(int logoHight) {
		this.logoHight = logoHight;
	}

	public int getQRcodeColor() {
		return QRcodeColor;
	}

	public void setQRcodeColor(int qRcodeColor) {
		QRcodeColor = qRcodeColor;
	}

	public int getQRcodeBackGround() {
		return QRcodeBackGround;
	}

	public void setQRcodeBackGround(int qRcodeBackGround) {
		QRcodeBackGround = qRcodeBackGround;
	}

	public int getContentColor() {
		return contentColor;
	}

	public void setContentColor(int contentColor) {
		this.contentColor = contentColor;
	}

	public int getImageSize() {
		return imageSize;
	}

	public void setImageSize(int imageSize) {
		this.imageSize = imageSize;
	}
	
	public int getContentWidth() {
		if(contentWidth>imageSize)
			return imageSize;
		return contentWidth;
	}

	public void setContentWidth(int contentWidth) {
		this.contentWidth = contentWidth;
	}

	public int getContentHight() {
		if(contentHight>(imageSize-QRcodeSize)/2)
			return (imageSize-QRcodeSize)/2;
		return contentHight;
	}

	public void setContentHight(int contentHight) {
		this.contentHight = contentHight;
	}
	
	public String getContentFontname() {
		return contentFontname;
	}

	public void setContentFontname(String contentFontname) {
		this.contentFontname = contentFontname;
	}

	public int getContentFontSize() {
		return contentFontSize;
	}

	public void setContentFontSize(int contentFontSize) {
		this.contentFontSize = contentFontSize;
	}

	/**  
     *  检查路径 图片是否存在  
     * @param imgPath  
     * @return  
     * @throws IOException  
     */    
    private static BufferedImage CheckImageExist(String imgPath) throws IOException{    
        File file = new File(imgPath);    
        if (!file.exists()) {    
            System.err.println(""+imgPath+"   该文件不存在！");    
            return null;    
        }    
        BufferedImage src = ImageIO.read(new File(imgPath));    
        return src;    
    }    
        
      
    /**  
     * 创建 二维码所需图片   
     * @param content   内容  
     * @param imgPath   Logo图片地址  
     * @param needCompress 是否压缩Logo大小 
     * @param needDescription 是否需要底部描述  
     * @return    
     * @throws Exception  
     */    
    private  BufferedImage createImage(String content,BufferedImage logoImage, String bottomDes,  
            boolean needCompress) throws Exception {    
        Hashtable hints = new Hashtable();    
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H); //容错级别 H->30%    
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);    
        hints.put(EncodeHintType.MARGIN, 1);    
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,    
                BarcodeFormat.QR_CODE, QRcodeSize, QRcodeSize, hints);    
        int width = bitMatrix.getWidth();    
        int height = bitMatrix.getHeight();   
        int tempHeight = height;  
        boolean needDescription=(null==bottomDes&&"".equals(bottomDes));  
        if (needDescription) {  
            tempHeight += 30;  
        }
        
        BufferedImage image = new BufferedImage(imageSize, imageSize ,BufferedImage.TYPE_INT_RGB);   
       
        int blankSize=(imageSize-QRcodeSize)/2;
        for (int x = 0; x < imageSize; x++) {    
            for (int y = 0; y < imageSize; y++) {
            	if(x<=blankSize || x>=(imageSize-blankSize) || y>=(imageSize-blankSize) || y<=blankSize)
            		image.setRGB(x,y,QRcodeBackGround);
            	else{
            		image.setRGB(x, y, bitMatrix.get(x-blankSize, y-blankSize) ? QRcodeColor   
                                : QRcodeBackGround);//fff白色。000黑色
            	}
            		   
            }    
        }
          
        if(null==logoImage)    
             return image;    
            
        // 插入图片    
        insertImage(image, logoImage, needCompress);  
          
        if(needDescription)  
             return image;  
        //下边文字
        addFontImage(image, bottomDes);  
          
        return image;    
    }    
    
    /** 
     * 添加 底部图片文字 
     * @param source   图片源 
     * @param declareText 文字本文 
     */  
    private  void addFontImage(BufferedImage source, String declareText) {
    	int cblankSize=(imageSize-contentWidth)/2;
    	/*int bankSize=(imageSize-QRcodeSize)/2;
    	if(contentHight>bankSize)
    		contentHight=bankSize;
    	if(contentWidth>imageSize)
    		contentWidth=imageSize;
    	if(contentFontSize>bankSize)
    		contentFontSize=bankSize+10;*/

    	Font font=font = new Font(contentFontname, Font.BOLD, contentFontSize);
    	BufferedImage textImage = createStringImage(declareText, font, contentWidth, contentHight, 
    			new Color(QRcodeBackGround), new Color(contentColor));
        Graphics2D graph = source.createGraphics();  
          
        int width = textImage.getWidth(null);    
        int height = textImage.getHeight(null);    
            
        Image src =textImage; 
        
        graph.drawImage(src, cblankSize, imageSize-contentHight, width, height, null);     
        graph.dispose();    
    }  
    
    /** 
     * 创建文字图片 
     * @param content 内容 
     * @param font  字体 
     * @param width 宽 
     * @param height 高 
     * @return 
     */  
   public BufferedImage createStringImage(String content,Font font,Integer width,Integer height,Color backColor,Color paintColor){    
          BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);     
          Graphics2D g2 = (Graphics2D)bi.getGraphics();     
          g2.setBackground(backColor);     
          g2.clearRect(0, 0, width, height);     
          g2.setPaint(paintColor);     
               
          FontRenderContext context = g2.getFontRenderContext();     
          Rectangle2D bounds = font.getStringBounds(content, context);     
          double x = (width - bounds.getWidth()) / 2;     
          double y = (height - bounds.getHeight()) / 2;     
          double ascent = -bounds.getY();     
          double baseY = y + ascent;     
          
          g2.setFont(font);
          g2.drawString(content, (int)x, (int)(baseY-bounds.getHeight()-bounds.getY()));     
            
          return bi;  
   }

    /**  
     *  插入Logo图片  
     * @param source    图片操作对象  
     * @param imgPath   Logo图片地址  
     * @param needCompress 是否压缩Logo大小  
     * @throws Exception  
     */    
    private void insertImage(BufferedImage source,BufferedImage logoImage,    
            boolean needCompress) throws Exception {    
        int width = logoImage.getWidth(null);    
        int height = logoImage.getHeight(null);    
            
        Image src =logoImage;    
        if (needCompress) {   
            if (width > logoWidth) {    
                width = logoWidth;    
            }    
            if (height > logoHight) {    
                height = logoHight;    
            }    

            Image  image = logoImage.getScaledInstance(width, height,    
            Image.SCALE_SMOOTH);    
            BufferedImage tag = new BufferedImage(width, height,    
                    BufferedImage.TYPE_INT_RGB);    
            Graphics g = tag.getGraphics();    
            g.drawImage(image, 0, 0, null);   
            g.dispose();    
            src = image;    
        }    
        Graphics2D graph = source.createGraphics(); 
        int blankSize=(imageSize-QRcodeSize)/2;
        int x = (QRcodeSize - width) / 2+blankSize;    
        int y = (QRcodeSize - height) / 2+blankSize;    
        graph.drawImage(src, x, y, width, height, null);     
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);    
        graph.setStroke(new BasicStroke(3f));    
        graph.draw(shape);    
        graph.dispose();    
    }    

    /**  
     * 创建 目录  
     * @param destPath  
     */    
    public void mkdirs(String destPath) {    
            File file =new File(destPath);       
            //当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)    
            if (!file.exists() && !file.isDirectory()) {    
                file.mkdirs();    
            }    
        }    
        
      
    /**  
     *  生成二维码 (以Logo路径)  
     * @param content  内容 (若带http:// 会自动跳转)  
     * @param imgPath  Logo 图片地址  
     * @param destPath   保存二维码 地址 (没有该目录会自动创建)  
     * @param bottomDes 底部文字描述 
     * @param needCompress 是否压缩Logo大小  
     * @throws Exception  
     */    
    public void encodeOfPath(String content, String imgPath,String bottomDes,String destPath,    
            boolean needCompress) throws Exception {    
            
        BufferedImage image = createImage(content, CheckImageExist(imgPath),    
                bottomDes,needCompress);    
        mkdirs(destPath);    
        String file = UUID.randomUUID().toString()+".jpg";    
        ImageIO.write(image, FORMAT_NAME, new File(destPath+"/"+file));    
    }    
        

    /** 
     *  
     * @param content 内容     
     * @param logoImage  图片源 LOGO 
     * @param bottomDes 底部描述文字 
     * @param destPath   保存二维码 地址 (没有该目录会自动创建) 
     * @param needCompress  是否压缩logo 
     * @throws Exception 
     */  
    public void encode(String content, BufferedImage logoImage,String bottomDes, String destPath,    
            boolean needCompress) throws Exception {    
            
        BufferedImage image = createImage(content, logoImage, bottomDes,   
                needCompress);    
        mkdirs(destPath);    
        String file = UUID.randomUUID().toString()+".jpg";    
        ImageIO.write(image, FORMAT_NAME, new File(destPath+"/"+file));    
    }    
      
    /** 
     *  
     * @param content  内容     
     * @param logoImage  图片源LOGO 
     * @param bottomDes 底部描述文字 
     * @param picName   图片名 
     * @param destPath     保存二维码 地址 (没有该目录会自动创建) 
     * @param needCompress 是否压缩logo 
     * @throws Exception 
     */  
    public void encode(String content, BufferedImage logoImage,String bottomDes,String picName,String destPath,    
            boolean needCompress) throws Exception {            
        BufferedImage image=createImage(content, logoImage,bottomDes,   
                    needCompress);    
  
        mkdirs(destPath);    
        String file = picName+".jpg";    
        ImageIO.write(image, FORMAT_NAME, new File(destPath+"/"+file));    
    }   
      

    /** 
     *  
     * @param content  内容 
     * @param destPath     保存二维码 地址 (没有该目录会自动创建) 
     * @throws Exception 
     */  
    public void encode(String content, String destPath) throws Exception {    
        encode(content, null,null,destPath, false);    
    }    

    /** 
     *    
     * @param content   内容 
     * @param imgPath    图片源地址LOGO 
     * @param bottomDes 底部描述文字 
     * @param output    输出流 
     * @param needCompress 是否压缩logo 
     * @throws Exception 
     */  
    public void encodeOfPath(String content, String imgPath,String bottomDes,    
            OutputStream output, boolean needCompress) throws Exception {    
        BufferedImage image = createImage(content, CheckImageExist(imgPath), bottomDes,   
                needCompress);    
        ImageIO.write(image, FORMAT_NAME, output);    
    }    
        
    /** 
     *  
     * @param content  内容 
     * @param Image        图片源LOGO 
     * @param bottomDes 底部描述文字 
     * @param output    输出流 
     * @param needCompress 是否压缩 
     * @throws Exception 
     */  
    public void encode(String content, BufferedImage Image,String bottomDes,    
            OutputStream output, boolean needCompress) throws Exception {    
        BufferedImage image = createImage(content, Image, bottomDes,    
                needCompress);    
        ImageIO.write(image, FORMAT_NAME, output);    
    }    
        
    /** 
     *  
     * @param content 内容 
     * @param output    输出流 
     * @throws Exception 
     */  
    public void encode(String content, OutputStream output)    
            throws Exception {    
        encode(content, null,null, output, false);    
    }    

    /**  
     * 解析 二维码  
     * @param file 图片文件  
     * @return  
     * @throws Exception  
     */    
    public String decode(File file) throws Exception {    
        BufferedImage image;    
        image = ImageIO.read(file);    
        if (image == null) {    
            return null;    
        }    
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(    
                image);    
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));    
        Result result;    
        Hashtable hints = new Hashtable();    
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);    
        result = new MultiFormatReader().decode(bitmap, hints);    
        String resultStr = result.getText();    
        return resultStr;    
    }    
 
    /**  
     *  解析二维码  
     * @param path 图片地址  
     * @return  
     * @throws Exception  
     */   
    public String decode(String path) throws Exception {    
        return decode(new File(path));    
    }
    
    /**
     *@throws IOException 
     * @Description ：
     *@date：2018年6月14日
     *@author:guhongxing
     */
    public static void createQRcode(String text,String logoPath,String content,String picName,String savePath) throws Exception {
		BufferedImage bi = ImageIO.read(new File(logoPath)); 
		QRCodeUtils utils=new QRCodeUtils();
		utils.setLogoHight(100);
		utils.setLogoWidth(100);
		utils.setImageSize(500);
		utils.setQRcodeSize(450);
		utils.setContentFontSize(30);
		utils.encode(text,bi,content,picName,savePath, true);
    }
    
    

}
