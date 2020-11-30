package XuLyAnh;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ThreadGray extends Thread{
	BufferedImage img,img2;
	int i;
	List<Integer> list;
	int height,width;
	public ThreadGray(int i, BufferedImage img, BufferedImage img2) {
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
            
            int max = r;
            if (max < g) max = g;
            if (max < b) max = b;
            
            int min = r;
            if (min>g) min = g;
            if (min>b) min = b;
            int avg = (max + min) /2;

            p = (a<<24) | (avg<<16) | (avg<<8) | avg;

            img2.setRGB(i, j, p);		          
	}
		super.run();
		
}
}