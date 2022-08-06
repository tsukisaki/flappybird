package com;

import utils.Constant;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Ground
{
    public BufferedImage img = null;
    public int x ,y;
    public int width = 0;
    public int height = 0;
    private static Ground instance = null;

    private Ground()
    {
        try{
            //单例模式
            BackGround bg = BackGround.getInstance();
            img = ImageIO.read(this.getClass().getResource(Constant.PATH_GROUND));

            width = img.getWidth();
            height = img.getHeight();

            x=0;
            y = bg.height - height;//背景高度与地面图片高度差就是地面图片的起始y坐标
            System.out.println("widthGround = "+ width + ", heightGround =" + height);
            System.out.println("x = "+ x + ", y =" + y);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //懒汉式
    public static Ground getInstance()
    {
        if(instance ==null)
        {
            instance = new Ground();
        }
        return instance;
    }

    //移动
    public void move(BackGround bg)
    {
        x--;
        if(x == bg.width +18 - width)
        {
            x = 0;
        }
        //System.out.println("x =" + x);
    }
}
