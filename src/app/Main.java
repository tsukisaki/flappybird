package app;

import com.GameFrame;
import com.MusicPlayer;

public class Main
{
    public static void main(String[] args) {
        GameFrame frame = new GameFrame();
        MusicPlayer.playMusic(MusicPlayer.BGM);
        frame.initFrame();

    }
}
