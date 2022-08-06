package com;

import utils.Constant;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BackGround
{
    //背景图片
    public BufferedImage img = null;
    public int width = 0;
    public int height = 0;

    //懒汉式单例
    private  static BackGround instance = null;

    private BackGround(){
        try{
            //使用ImageIO加载图片资源
            img = ImageIO.read(this.getClass().getResource(Constant.PATH_BACKGROUND));
            //获取背景高度和长度
            width = img.getWidth();
            height = img.getHeight();

            System.out.println("widthBg = " + width + ", heightBg = " + height);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //实现线程安全的懒汉式
    public static BackGround getInstance(){
        if( instance == null)
        {
            instance = new BackGround();
        }
        return instance;
    }
}
