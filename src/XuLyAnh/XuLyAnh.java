package XuLyAnh;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


public class XuLyAnh
{
	String input = "D://output//3.jpg";
    BufferedImage imgNative;
    BufferedImage img;
    File f = null;
    int width; 
    int height;
    
    public BufferedImage getImg() {
    	return this.imgNative;
    }
    
    public XuLyAnh (String path) {
    	this.input = path;
    	try
        {
            f = new File(input);
            imgNative = ImageIO.read(f);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    	
    	this.width = imgNative.getWidth();
    	this.height = imgNative.getHeight();
    	
	}
    
    public BufferedImage clone() {
    	BufferedImage bi = new BufferedImage(width, height, imgNative.getType());
    	for (int i=0;i<width;i++)
    		for (int j=0;j<height;j++) {
    			bi.setRGB(i, j, imgNative.getRGB(i, j));
    		}
    	return bi;
    }
    
	public void Negative()  {
		this.img = this.clone();
		List<ThreadNegative> thread = new ArrayList<>();
		for (int i = 0; i < width; i++) {
			thread.add(new ThreadNegative(i, imgNative, img));
			thread.get(thread.size() - 1).run();
		
		}
		
		while (true) {
			for (int i = 0; i < thread.size(); i++) {
				if (thread.get(i).isAlive()) i--;
			}
			break;
		}
	}
    
	public void Gray()  {
		this.img = this.clone();
		List<ThreadGray> thread = new ArrayList<>();
		for (int i = 0; i < width; i++) {
			thread.add(new ThreadGray(i, imgNative, img));
			thread.get(thread.size() - 1).run();
		}

		while (true) {
			for (int i = 0; i < thread.size(); i++) {
				if (thread.get(i).isAlive()) i--;
			}
			break;
		}
	}
	
	public void Binary(int nguong)  {
		this.img = this.clone();
		List<ThreadBinary> thread = new ArrayList<>();
		for (int i = 0; i < width; i++) {
			thread.add(new ThreadBinary(i, imgNative, img,nguong));
			thread.get(thread.size() - 1).run();
		}


		while (true) {
			for (int i = 0; i < thread.size(); i++) {
				if (thread.get(i).isAlive()) i--;
			}
			break;
		}

	}


	public void LuuAnh(String output) {
    	try
        {
            f = new File(output);
            ImageIO.write(img, "jpg", f);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    	System.out.println("SAVED");
	}
	/*
 public static void main(String args[])throws IOException, InterruptedException{
	 XuLyAnh xla = new XuLyAnh();
	 xla.Negative();
	 xla.LuuAnh("D://output//negative.png");
	 
	 xla.Gray();
	 xla.LuuAnh("D://output//gray.png");
	 
	 xla.Binary();
	 xla.LuuAnh("D://output//binary.png");
 }*/
 
}

