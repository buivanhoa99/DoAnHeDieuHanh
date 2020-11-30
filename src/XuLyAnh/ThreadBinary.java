package XuLyAnh;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ThreadBinary extends Thread{
	BufferedImage img,img2;
	int i;
	List<Integer> list;
	int height,width;
	int nguong;
	public ThreadBinary(int i, BufferedImage img, BufferedImage img2,int nguong) {
		list = new ArrayList<Integer>();
		this.height = img.getHeight();
		this.width = img.getWidth();
		this.img = img;
		this.img2 = img2;
		this.i=i;
		this.nguong = nguong;
	}
	@Override
	public void run() {
		for (int j = 0; j < height; j++) {
			int p = img.getRGB(i,j);
			 
            int a = (p>>24)&0xff;
            int r = (p>>16)&0xff;
            int g = (p>>8)&0xff;
            int b = p&0xff;

            int nhiphan = (int) (0.2126*r + 0.7152*g + 0.0722*b);
            if (nhiphan >= nguong) p = 0xffffff;
            else p = 0x0;

            img2.setRGB(i, j, p);		          
	}
		super.run();
		
}
}