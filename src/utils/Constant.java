package utils;

public class Constant {
    //图片路径
    public static String PATH_PIC = "/pics/";

    //背景路径设置
    public static String PATH_BACKGROUND = PATH_PIC + "bg_day1.png";

    //界面参数
    public static String GAME_TITLE = "Flappy Bird";
    public static int WINDOW_WIDTH = 432;
    public static int WINDOW_HEIGHT = 644;

    // 地面图片路径
    public static String PATH_GROUND = PATH_PIC + "land1.png";

    //地面和柱子移动初始速度
    public static int MOVE_SPEED = 40;

    //设置开始和结束图片的路径
    public static String PATH_TITLE = PATH_PIC + "title.png";
    public static String PATH_START = PATH_PIC + "start.png";
    public static String PATH_TUTORIAL = PATH_PIC + "tutorial1.png";

    public static String PATH_GAMEOVER = PATH_PIC + "gameover.png";

    public static int BIRD_PIC_COUNT = 8;// 小鸟皮肤个数
    public static int BIRD_POSITION_X = 190;// 小鸟初始化坐标
    public static int BIRD_POSITION_Y = 240;

    //小鸟初始位置
    public static int BIRD_FLY_POSITION_X = 120;

    //设置重力加速度和自然下降的时间
    public static double GRAVITATION_ACCELERATION = 9.8;
    public static double DOWM_TIME = 0.18;

    //上升速度
    public static double UP_SPEED = 30;

    // 柱子参数
    public static String PATH_PILLAR = PATH_PIC + "pillar.png";
    public static int PILLAR_GAP = 150;// 柱子通道距离
    public static int PILLAR_DISTANCE = 244;// 柱子间距

    // 得分信息的字体大小及坐标
    public static int FONT_SIZE = 20;
    public static int SCORE_X = 20;
    public static int SCORE_Y = 40;

    //音乐路径
    public static String PATH_MUSIC = "/musics/";
    public static String PATH_SE =  "D:\\Work\\javaproject\\flappybird2\\src\\musics\\se.wav";
    public static String PATH_BGM = "D:\\Work\\javaproject\\flappybird2\\src\\musics\\Funky.wav";

    public static String PATH_SCORE = PATH_PIC + "score_panel.png";
}
