package com;

import utils.Constant;

import javax.swing.*;

public class GameFrame  extends JFrame
{
    //初始化窗体
    public void initFrame()
    {

        setTitle(Constant.GAME_TITLE);
        setSize(Constant.WINDOW_WIDTH,Constant.WINDOW_HEIGHT);

        //添加panel
        GamePanel panel = new GamePanel();
        add(panel);

        //设置窗口坐标
        setLocationRelativeTo(null);

        //设置窗口可见
        setVisible(true);

        //设置窗口大小不可调整
        setResizable(false);

        //监听窗口关闭
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.action();
    }

}
