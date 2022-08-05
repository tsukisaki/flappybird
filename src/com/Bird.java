package com;

import utils.Constant;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static com.MusicPlayer.SE;


public class Bird {
    public BufferedImage img = null;// 小鸟图片
    public BufferedImage imgs[] = new BufferedImage[ Constant.BIRD_PIC_COUNT];// 数组，存储所有小鸟图案
    public static int index = 0;// 当前皮肤的序号
    public int x, y;// 初始坐标
    public int width = 0;// 小鸟的宽度
    public int height = 0;// 小鸟的高度
    public double g = Constant.GRAVITATION_ACCELERATION; // 重力加速度
    public double v = 0;// 下降速度
    public double t = Constant.DOWM_TIME;// 下降时间
    public double h;// 下降的距离

    public Bird()
    {
        try
        {
            for (int i = 0; i < 8; i++)
            {
                imgs[i] = ImageIO.read(getClass().getResource(Constant.PATH_PIC + i + ".png"));
            }
            img = imgs[0];
            // 获取小鸟的宽度和高度
            width = img.getWidth();
            height = img.getHeight();

            // 初始化小鸟的坐标位置
            x = Constant.BIRD_POSITION_X;
            y = Constant.BIRD_POSITION_Y;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 小鸟飞翔的图片切换
    public void fly()
    {
        index++;
        // 小鸟图形切换的频率，index/x,x越大，翅膀切换频率越慢
        img = imgs[index / 6 % Constant.BIRD_PIC_COUNT];
        if (index == 6 * Constant.BIRD_PIC_COUNT)
        {
            index = 0;
        }
    }

    // 小鸟自然下降
    public void down() {
        v = v - g * t; // 末速度Vt＝Vo-gt
        h = v * t - g * t * t / 2; // 位移h＝Vot-gt²/2
        y = y - (int) h;
    }

    // 上升
    public void up() {
        MusicPlayer.playMusic(SE);
        v = Constant.UP_SPEED;
    }

    // 碰撞检测
    // 掉落到地面时
    public boolean hitGround(BackGround bg, Ground ground) {
        if (y + height >= (bg.height - ground.height)) {
            return true;
        }
        return false;
    }

    // 碰撞到舞台顶部时
    public boolean hitSky() {
        if (y <= 0) {
            return true;
        }
        return false;
    }

     //碰到柱子时的检测
    public boolean hitPillar(Pillar p) {
        // x方向小鸟和柱子碰撞的条件
        if ((x + width) >= p.x && x <= p.x + p.width) {
            if ((y>p.y+p.height-526-height)||(y<p.y+526)) {
                return true;
            }
        }
        return false;
    }

    // 增加积分，通过柱子通道后调用该方法
    public boolean addScore(Pillar p) {
        // System.out.println("x=" + x + ",p.x=" + p.x);
        if (x == p.x + p.width) {
            return true;
        }
        return false;
    }
}
