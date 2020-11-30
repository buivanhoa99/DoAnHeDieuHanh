package XuLyAnh;

import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;



public class GiaoDien extends JFrame{
    JButton button,button1 ;
    JLabel label1,label2,label0,label4;
    int width = 500;
    int height = 500;
    String path;
    JRadioButton r1,r2,r3;
    public GiaoDien(){
    super("Chương trình xử lý ảnh");
    button = new JButton("Chọn ảnh");
    button.setBounds(100,100,100,40);
    label1 = new JLabel();
    label2 = new JLabel();
    label0 = new JLabel("Chương trình xử lý ảnh");
    label0.setFont(new Font("Arial",Font.BOLD,50));
    label0.setBounds(800, 50, 1000, 50);
    label1.setBounds(600,150,width,height);
    label2.setBounds(1300,150,width,height);
    this.add(label0);
    this.add(button);
    this.add(label1);
    this.add(label2);
    
    button1 = new JButton("Chuyển đổi");
    button1.setBounds(250,100,100,40);
    this.add(button1);
    label4 = new JLabel("Các chức năng xử lý:");
    label4.setBounds(100,200,400,50);
    label4.setFont(new Font("Arial",Font.BOLD,30));
    this.add(label4);
    Font f1 = new Font("Arial", Font.CENTER_BASELINE, 25);
    
    ButtonGroup p1 = new ButtonGroup();
    
    r1 = new JRadioButton("Gray convert");
    r1.setFont(f1);
    r1.setBounds(100, 250, 400, 50);
    p1.add(r1);
    this.add(r1);
    
    r2 = new JRadioButton("Binary convert");
    r2.setFont(f1);
    r2.setBounds(100, 300, 400, 50);
    p1.add(r2);
    this.add(r2);
    
    r3 = new JRadioButton("Negative convert");
    r3.setFont(f1);
    r3.setBounds(100, 400, 400, 50);
    p1.add(r3);
    this.add(r3);
    
    JSlider js = new JSlider(0, 255, 100);
    js.setBounds(100, 350, 200, 50);
    //js.setLocation(new Point(100,500));
    this.add(js);
    
   r2.addChangeListener(new ChangeListener() {
	
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		if (r2.isSelected()) {
			
		}
	}
});
    
    
    button1.addMouseListener(new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
			XuLyAnh xla = new XuLyAnh(path);
			String savedPath = "D://eclipse.png";
			 int i = getButtonSelected();
			 
			 
			 //set label
			 JLabel l1 = new JLabel("=>");
			 l1.setLocation(300, 300);
			 
			 if (i==1) {
				 xla.Gray();
			 }
			 else if (i==2) {
				 System.out.println(js.getValue());
				 xla.Binary(js.getValue());
			 }
			 else xla.Negative();
			 
			 xla.LuuAnh(savedPath);
			 BufferedImage img = xla.getImg();
			 label2.setIcon(ResizeImage(savedPath));
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
    	
    });
    
    button.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
        
          JFileChooser file = new JFileChooser();
          file.setCurrentDirectory(new File(System.getProperty("user.home")));
          //filter the files
          FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
          file.addChoosableFileFilter(filter);
          int result = file.showSaveDialog(null);
           //if the user click on save in Jfilechooser
          if(result == JFileChooser.APPROVE_OPTION){
              File selectedFile = file.getSelectedFile();
              path = selectedFile.getAbsolutePath();
              label1.setIcon(ResizeImage(path));
          }



          else if(result == JFileChooser.CANCEL_OPTION){
              System.out.println("No File Select");
          }
        }
    });
    
    setLayout(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setSize(700,400);
    setVisible(true);
    }
     
     private int getButtonSelected() {
		if (r1.isSelected()) return 1;
		if (r2.isSelected()) return 2;
		if (r3.isSelected()) return 3;
		return 0;
	}


    public ImageIcon ResizeImage(String ImagePath)
    {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(label1.getWidth(), label1.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
    
    public static void main(String[] args){
        new GiaoDien();
    }
   }