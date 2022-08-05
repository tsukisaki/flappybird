package com;

import utils.Constant;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;


public class Pillar {
    public BufferedImage img;// 图片
    public int x, y;// 坐标
    public int width = 0;// 柱子宽度
    public int height = 0;// 柱子高度
    private Random random = new Random();// 一个生成随机数的对象

    public Pillar(BackGround bg, Ground ground) {
        try {
            img = ImageIO.read(getClass().getResource(Constant.PATH_PILLAR));
            width = img.getWidth();
            height = img.getHeight();
            //System.out.println("柱子width=" + width + ",height=" + height);
            x = bg.width;

            y=-(random.nextInt(200)+250);
             System.out.println("y=" + y);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 柱子移动，游戏一旦开始则柱子移动
    public void move(BackGround bg) {
        x--;
        if (x == -width) {
            x = bg.width;
            y=-(random.nextInt(200)+250);
            //System.out.println("y=" + y);
        }
    }
}
