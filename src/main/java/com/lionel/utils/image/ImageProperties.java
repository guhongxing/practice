package com.lionel.utils.image;

import java.io.File;
import java.net.InetAddress;

public class ImageProperties {

	/**
	 * 商品图片
	 */
	public static final String imageHead = "item";
	
	/**
	 * 商品长图
	 */
	public static final String imageLongHead = "pt";
	
	public static enum dns {
		
		//long picture
		LONGIMAGE("pt", null), 
		
		//detail image contain 800*800 500*500 100*100 50*50 
		ORIGINAL("n0", "800*800"), N1("n1", "500*500"), N2("n2", "100*100"), N3("n3", "50*50"),


		
		;

		private String name;
		
		private String specification;

		dns(String name) {
			this.name = name;
			this.specification = null;
		}
		
		dns(String name, String specification) {
			this.name = name;
			this.specification = specification;
		}

		public String getName() {
			return this.name;
		}
		
		public String getSpecification() {
			return this.specification;
		}

		public static boolean detailImage(dns type) {
			if(type == null) return false;
			if(type.ordinal() >= ORIGINAL.ordinal() && type.ordinal() <= N3.ordinal()) {
				return true;
			}
			return false;
		}
		public static boolean valid(dns type) {
			if(type == null) return false;
			boolean result = false;
			for(dns TYPE : dns.values()) {
				if(TYPE.equals(type)) {
					result = true;
					break;
				}
			}
			return result;
		}
		
		public static dns getDns(Integer status) {
			if(status == null) return null;
			if(status < 0 || status >= dns.values().length) return null;
			return dns.values()[status];
		}
		public static String getNameByType(dns type) {
			if(!valid(type)) return null;
			for(dns TYPE : dns.values()) {
				if(TYPE.equals(type)) {
					return TYPE.getName();
				}
			}
			return null;
		}
	}

	public static String SEPARATOR = File.separator;
	
	public static String URL_SEPARATOR = "/";
	
	public static String URI_SEPARATOR = "\\|";

	private static final String[] ONLINE_PRODUCT_IMAGE_SERVER = {

	};

	private static final String[] TEST_PRODUCT_IMAGE_SERVER = {
			"182.140.195.22", "172.16.10.92", 
	};
	
	private static final String[] TEST_IMAGE_UPLOAD_SERVER = {
			"172.16.10.92",  "182.140.195.22", 
	};

	//default local file host img.localhost.com:8080 need to add hosts file 
	//like 127.0.0.1      img.localhost.com
	public static String FILE_HOST = "http://182.140.195.22";

	public static String FILE_UPLOAD_HOST = "http://182.140.195.22:82/upload";
	
	public static boolean isOnline = false;

	public static boolean isTest = false;
	
	public static final String ip;
	
	static {
		
        String initIp = "";
        try {
            InetAddress addr = InetAddress.getLocalHost();
            initIp = addr.getHostAddress().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ip = initIp;
        
        for(String server : ONLINE_PRODUCT_IMAGE_SERVER) {
        		if(server.equals(ip)) {
        			isOnline = true;
        			FILE_HOST = "http://img.meixuan.com";
        		}
        }
        
        for(String server : TEST_PRODUCT_IMAGE_SERVER) {
        		if(server.equals(ip)) {
        			isTest = true;
        			FILE_HOST = "http://182.140.195.22";
        		}
        }
        
        for(String server : TEST_IMAGE_UPLOAD_SERVER) {
    		if(server.equals(ip)) {
    			isTest = true;
    			FILE_UPLOAD_HOST = "http://172.16.10.92:82/upload";
    		}
    }
	}
	
	public static String LONGIMAGE_DNS_PATH = dns.LONGIMAGE.getName(); 
	
	public static String itemDNSPath(ImageProperties.dns type) {
		return ImageProperties.dns.getNameByType(type);
	}
	
	public static void main(String[] args) {
		System.out.println(ImageProperties.LONGIMAGE_DNS_PATH);
	}
}
