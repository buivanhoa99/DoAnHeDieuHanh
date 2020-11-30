package XuLyAnh;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ThreadNegative extends Thread{
	BufferedImage img,img2;
	int i;
	List<Integer> list;
	int height,width;
	public ThreadNegative(int i, BufferedImage img, BufferedImage img2) {
		list = new ArrayList<Integer>();
		this.height = img.getHeight();
		this.width = img.getWidth();
		this.img = img;
		this.img2 = img2;
		this.i=i;
	}
	@Override
	public void run() {
		for (int j = 0; j < height; j++) {
			int p = img.getRGB(i,j);
			 
            int a = (p>>24)&0xff;
            int r = (p>>16)&0xff;
            int g = (p>>8)&0xff;
            int b = p&0xff;

            r = 255- r;
            g = 255 - g;
            b = 255 - b;
            p = (a<<24) | (r<<16) | (r<<8) | b;

            img2.setRGB(i, j, p);		          
	}
		super.run();
		
}
}