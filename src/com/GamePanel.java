package com;

import utils.Constant;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.io.*;
import java.text.SimpleDateFormat;
import java.awt.event.*;



public class GamePanel extends JPanel
{
    //使用单例模式声明背景对象和地面对象
    private BackGround bg = null;
    private Ground ground = null;
    private int speed = 0;
    //游戏状态 0表示未开始 1表示正在运行 2表示游戏结束
    private static int state = 0;
    private BufferedImage imgTitle = null;
    private BufferedImage imgStart = null;
    private BufferedImage imgTutorial = null;
    private BufferedImage imgOver = null;
    private BufferedImage imgScore = null;

    private Bird bird = null;
    private Pillar p1 = null;
    private Pillar p2 = null;
    private int score = 0 ;

    public GamePanel()
    {
        bg = BackGround.getInstance();
        ground = Ground.getInstance();
        speed = Constant.MOVE_SPEED;
        state = 0;
        bird = new Bird();

        // 声明两个柱子，并分别设置柱子的起始X坐标
        p1 = new Pillar(bg, ground);
        p1.x = bg.width;
        p2 = new Pillar(bg, ground);
        p2.x = bg.width + Constant.PILLAR_DISTANCE;

        try{
            imgStart = ImageIO.read(this.getClass().getResource(Constant.PATH_START));
            imgTutorial = ImageIO.read(this.getClass().getResource(Constant.PATH_TUTORIAL));
            imgOver = ImageIO.read(this.getClass().getResource(Constant.PATH_GAMEOVER));
            imgTitle = ImageIO.read(this.getClass().getResource(Constant.PATH_TITLE));
            imgScore = ImageIO.read(this.getClass().getResource(Constant.PATH_SCORE));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 重写paint方法，会在初始化以及最大化最小化时调用方法
     * 绘制组件 加载图片等
     */

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        //System.out.println("paint方法被调用" + getCurrentTime());
        //绘制背景
        g.drawImage(bg.img, 0, 0,null);
        // 绘制文字
        Font font = new Font("微软雅黑", Font.ITALIC, Constant.FONT_SIZE);//字体，倾斜，大小
        g.setFont(font);
        g.setColor(Color.white);// 这里font和color导包都导java.awt
        g.drawString("SCORE：" + score, Constant.SCORE_X, Constant.SCORE_Y);
        if(state == 0)
        {

            g.drawImage(imgTitle,127,80,null);
            g.drawImage(imgStart,115,160,null);
            g.drawImage(bird.img, bird.x, bird.y, null);
            g.drawImage(imgTutorial,159,280,null);
        }
        else if(state == 1)
        {

            g.drawImage(bird.img, bird.x, bird.y,null);
            g.drawImage(p1.img, p1.x, p1.y, null);
            g.drawImage(p2.img, p2.x, p2.y, null);
        }
        else if(state == 2)
        {

            g.drawImage(imgOver,115,80,null);
            g.drawImage(imgScore,97 , 200,null);
            String str = Integer.toString(score);
            g.drawString(str, 280, 250);
        }
        //绘制地面
        g.drawImage(ground.img, ground.x, ground.y, null);

    }

    public String getCurrentTime(){
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return df.format(day);
    }
    private int getScore(){
        return score;
    }
    public void action()
    {
        //设置鼠标监听
        this.addMouseListener(new MouseAdapter() {
            //点击鼠标后
            @Override
            public void mousePressed(MouseEvent e) {
                super.mouseReleased(e);
                switch (state) {
                    case 0://游戏未开始
                        // 切换到状态1时的数据
                        state = 1;
                        bird.x = Constant.BIRD_FLY_POSITION_X;//开始时设置小鸟初始位置
                        break;

                    case 1://开始游戏
                        bird.up();

                        break;

                    case 2:
                        // 切换到状态0时的数据
                        state = 0;
                        score = 0;
                        speed = 40;
                        // 重置小鸟的位置
                        bird.x = Constant.BIRD_POSITION_X;
                        bird.y = Constant.BIRD_POSITION_Y;
                        bird.v = 0;
                        // 重置柱子的坐标
                        p1.x = bg.width;
                        p2.x = bg.width + Constant.PILLAR_DISTANCE;
                        break;

                    default:
                        break;
                }
            }
        });

        //设置键盘监听

        //监听鼠标事件，监听到state变化，无限循环
        while(true)
        {
            if(state == 0){
            //地面移动
                ground.move(bg);
                bird.fly();
            }else if(state == 1){
                ground.move(bg);
                bird.fly();
                bird.down();//小鸟下降
                p1.move(bg);//柱子移动
                p2.move(bg);
                if (bird.hitGround(bg, ground) || bird.hitSky() || bird.hitPillar(p1) || bird.hitPillar(p2)) {
                    state = 2;
                } else {
                    // 小鸟每通过一个竹子通道，累计积分，并提高柱子和地面移动速度。
                    if (bird.addScore(p1) || bird.addScore(p2)) {
                        score++;
                        // 每通过一个柱子，速度会递增
                        speed += 2;
                        // System.out.println("speed=" + speed);
                    }
                }
                }else if(state == 2){
                }


            //线程休眠
            try{
                Thread.sleep(1000 / speed);//调节游戏速度
                this.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
