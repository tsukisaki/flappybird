package com;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

import static utils.Constant.PATH_BGM;
import static utils.Constant.PATH_SE;

public class MusicPlayer {
    public static final String BGM = PATH_BGM;//bgm文件路径
    public static final String SE = PATH_SE;//bgm文件路径
    public static Clip music = null; //声明Clip接口
    public static File sourceFile = null; //声明文件变量

    /**
     * 音乐播放方法
     */
    public static void playMusic(String path){
        try {
            music = AudioSystem.getClip(); // 获取可用于播放音频文件或音频流的数据流
            sourceFile = new File(path);//获取文件
            AudioInputStream ais = AudioSystem.getAudioInputStream(sourceFile);//获得指示格式的音频输入流
            music.open(ais); //打开数据流
            music.start();    //开始播放音乐

        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * 关闭音乐
     */
    public static void closeMusic(){
        if (music!=null)    //需要判断music是否为null，避免出现空指针异常
            music.stop();//暂停音乐
    }

}
