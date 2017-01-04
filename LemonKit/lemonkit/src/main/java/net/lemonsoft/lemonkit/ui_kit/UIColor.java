package net.lemonsoft.lemonkit.ui_kit;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;

import net.lemonsoft.lemonkit.core_graphics.CGColorRef;
import net.lemonsoft.lemonkit.core_native_tool.LKColorTool;

/**
 * LemonKit颜色描述对象
 * Created by LiuRi on 2016/12/29.
 */

public class UIColor {

    private Drawable drawable;

    public Drawable getDrawable() {
        return drawable;
    }

    public UIColor(float red, float green, float blue, float alpha) {
        this.drawable = new ColorDrawable(
                LKColorTool.getDefaultColorTool().colorWithARGBFloat(alpha, red, green, blue)
        );
    }

    public UIColor(String hexColor) {
        this.drawable = new ColorDrawable(Color.parseColor(hexColor));
    }

    /**
     * 获取CGColor颜色值描述对象
     *
     * @return CGColor颜色值描述对象
     */
    public CGColorRef cgColor() {
        int colorValue = Color.argb(0, 255, 255, 255);
        if (drawable instanceof ColorDrawable)
            colorValue = ((ColorDrawable) drawable).getColor();
        else if (drawable instanceof ShapeDrawable)
            colorValue = ((ShapeDrawable) drawable).getPaint().getColor();
        return new CGColorRef(colorValue);
    }

    /**
     * 根据红绿蓝
     *
     * @param red   红色颜色值
     * @param green 绿色颜色值
     * @param blue  蓝色颜色值
     * @param alpha 透明度颜色值
     * @return 生成的UIColor对象
     */
    public static UIColor colorWithRedGreenBlueAlpha(float red, float green, float blue, float alpha) {
        return new UIColor(alpha, red, green, blue);
    }

    /**
     * 透明颜色
     * #00ffffff
     * 0,255,255,255
     */
    public static UIColor clearColor() {
        return new UIColor(1f, 1f, 1f, 0f);
    }

    /**
     * aliceblue
     * #f0f8ff
     * 240,248,255
     */
    public static UIColor aliceblueColor() {
        return new UIColor("#f0f8ff");
    }

    /**
     * antiquewhite
     * #faebd7
     * 250,235,215
     */
    public static UIColor antiquewhiteColor() {
        return new UIColor("#faebd7");
    }

    /**
     * aqua
     * #00ffff
     * 0,255,255
     */
    public static UIColor aquaColor() {
        return new UIColor("#00ffff");
    }

    /**
     * aquamarine
     * #7fffd4
     * 127,255,212
     */
    public static UIColor aquamarineColor() {
        return new UIColor("#7fffd4");
    }

    /**
     * azure
     * #f0ffff
     * 240,255,255
     */
    public static UIColor azureColor() {
        return new UIColor("#f0ffff");
    }

    /**
     * beige
     * #f5f5dc
     * 245,245,220
     */
    public static UIColor beigeColor() {
        return new UIColor("#f5f5dc");
    }

    /**
     * bisque
     * #ffe4c4
     * 255,228,196
     */
    public static UIColor bisqueColor() {
        return new UIColor("#ffe4c4");
    }

    /**
     * black
     * #000000
     * 0,0,0
     */
    public static UIColor blackColor() {
        return new UIColor("#000000");
    }

    /**
     * blanchedalmond
     * #ffebcd
     * 255,235,205
     */
    public static UIColor blanchedalmondColor() {
        return new UIColor("#ffebcd");
    }

    /**
     * blue
     * #0000ff
     * 0,0,255
     */
    public static UIColor blueColor() {
        return new UIColor("#0000ff");
    }

    /**
     * blueviolet
     * #8a2be2
     * 138,43,226
     */
    public static UIColor bluevioletColor() {
        return new UIColor("#8a2be2");
    }

    /**
     * brown
     * #a52a2a
     * 165,42,42
     */
    public static UIColor brownColor() {
        return new UIColor("#a52a2a");
    }

    /**
     * burlywood
     * #deb887
     * 222,184,135
     */
    public static UIColor burlywoodColor() {
        return new UIColor("#deb887");
    }

    /**
     * cadetblue
     * #5f9ea0
     * 95,158,160
     */
    public static UIColor cadetblueColor() {
        return new UIColor("#5f9ea0");
    }

    /**
     * chartruse
     * #7fff00
     * 127,255,0
     */
    public static UIColor chartruseColor() {
        return new UIColor("#7fff00");
    }

    /**
     * chocolate
     * #d2691e
     * 210,105,30
     */
    public static UIColor chocolateColor() {
        return new UIColor("#d2691e");
    }

    /**
     * coral
     * #ff7f50
     * 255,127,80
     */
    public static UIColor coralColor() {
        return new UIColor("#ff7f50");
    }

    /**
     * cornflowerblue
     * #6495ed
     * 100,149,237
     */
    public static UIColor cornflowerblueColor() {
        return new UIColor("#6495ed");
    }

    /**
     * cornsilk
     * #fff8dc
     * 255,248,220
     */
    public static UIColor cornsilkColor() {
        return new UIColor("#fff8dc");
    }

    /**
     * crimson
     * #dc143c
     * 220,20,60
     */
    public static UIColor crimsonColor() {
        return new UIColor("#dc143c");
    }

    /**
     * cyan
     * #00ffff
     * 0,255,255
     */
    public static UIColor cyanColor() {
        return new UIColor("#00ffff");
    }

    /**
     * darkblue
     * #00008b
     * 0,0,139
     */
    public static UIColor darkblueColor() {
        return new UIColor("#00008b");
    }

    /**
     * darkcyan
     * #008b8b
     * 0,139,139
     */
    public static UIColor darkcyanColor() {
        return new UIColor("#008b8b");
    }

    /**
     * darkgoldenrod
     * #b8860b
     * 184,134,11
     */
    public static UIColor darkgoldenrodColor() {
        return new UIColor("#b8860b");
    }

    /**
     * darkgray
     * #a9a9a9
     * 169,169,169
     */
    public static UIColor darkgrayColor() {
        return new UIColor("#a9a9a9");
    }

    /**
     * darkgreen
     * #006400
     * 0,100,0
     */
    public static UIColor darkgreenColor() {
        return new UIColor("#006400");
    }

    /**
     * darkkhaki
     * #bdb76b
     * 189,183,107
     */
    public static UIColor darkkhakiColor() {
        return new UIColor("#bdb76b");
    }

    /**
     * darkmagenta
     * #8b008b
     * 139,0,139
     */
    public static UIColor darkmagentaColor() {
        return new UIColor("#8b008b");
    }

    /**
     * darkolivegreen
     * #556b2f
     * 85,107,47
     */
    public static UIColor darkolivegreenColor() {
        return new UIColor("#556b2f");
    }

    /**
     * darkorange
     * #ff8c00
     * 255,140,0
     */
    public static UIColor darkorangeColor() {
        return new UIColor("#ff8c00");
    }

    /**
     * darkorchid
     * #9932cc
     * 153,50,204
     */
    public static UIColor darkorchidColor() {
        return new UIColor("#9932cc");
    }

    /**
     * darksalmon
     * #e9967a
     * 233,150,122
     */
    public static UIColor darksalmonColor() {
        return new UIColor("#e9967a");
    }

    /**
     * darkseagreen
     * #8fbc8f
     * 143,188,143
     */
    public static UIColor darkseagreenColor() {
        return new UIColor("#8fbc8f");
    }

    /**
     * darkslateblue
     * #483d8b
     * 72,61,139
     */
    public static UIColor darkslateblueColor() {
        return new UIColor("#483d8b");
    }

    /**
     * darkslategray
     * #2f4f4f
     * 47,79,79
     */
    public static UIColor darkslategrayColor() {
        return new UIColor("#2f4f4f");
    }

    /**
     * darkturquoise
     * #00ced1
     * 0,206,209
     */
    public static UIColor darkturquoiseColor() {
        return new UIColor("#00ced1");
    }

    /**
     * darkviolet
     * #9400d3
     * 148,0,211
     */
    public static UIColor darkvioletColor() {
        return new UIColor("#9400d3");
    }

    /**
     * deeppink
     * #ff1493
     * 255,20,147
     */
    public static UIColor deeppinkColor() {
        return new UIColor("#ff1493");
    }

    /**
     * deepskyblue
     * #00bfff
     * 0,191,255
     */
    public static UIColor deepskyblueColor() {
        return new UIColor("#00bfff");
    }

    /**
     * dimgray
     * #696969
     * 105,105,105
     */
    public static UIColor dimgrayColor() {
        return new UIColor("#696969");
    }

    /**
     * dodgerblue
     * #le90ff
     * 30,144,255
     */
    public static UIColor dodgerblueColor() {
        return new UIColor("#le90ff");
    }

    /**
     * firebrick
     * #b22222
     * 178,34,34
     */
    public static UIColor firebrickColor() {
        return new UIColor("#b22222");
    }

    /**
     * floralwhite
     * #fffaf0
     * 255,250,240
     */
    public static UIColor floralwhiteColor() {
        return new UIColor("#fffaf0");
    }

    /**
     * forestgreen
     * #228b22
     * 34,193,34
     */
    public static UIColor forestgreenColor() {
        return new UIColor("#228b22");
    }

    /**
     * fuchsia
     * #ff00ff
     * 255,0,255
     */
    public static UIColor fuchsiaColor() {
        return new UIColor("#ff00ff");
    }

    /**
     * gainsboro
     * #dcdcdc
     * 220,220,220
     */
    public static UIColor gainsboroColor() {
        return new UIColor("#dcdcdc");
    }

    /**
     * ghostwhite
     * #f8f8ff
     * 248,248,255
     */
    public static UIColor ghostwhiteColor() {
        return new UIColor("#f8f8ff");
    }

    /**
     * gold
     * #ffd700
     * 255,215,0
     */
    public static UIColor goldColor() {
        return new UIColor("#ffd700");
    }

    /**
     * goldenrod
     * #daa520
     * 218,165,32
     */
    public static UIColor goldenrodColor() {
        return new UIColor("#daa520");
    }

    /**
     * gray
     * #808080
     * 128,128,128
     */
    public static UIColor grayColor() {
        return new UIColor("#808080");
    }

    /**
     * green
     * #008000
     * 0,128,0
     */
    public static UIColor greenColor() {
        return new UIColor("#008000");
    }

    /**
     * greenyellow
     * #adff2f
     * 173,255,47
     */
    public static UIColor greenyellowColor() {
        return new UIColor("#adff2f");
    }

    /**
     * honeydew
     * #f0fff0
     * 240,255,240
     */
    public static UIColor honeydewColor() {
        return new UIColor("#f0fff0");
    }

    /**
     * hotpink
     * #ff69b4
     * 255,105,180
     */
    public static UIColor hotpinkColor() {
        return new UIColor("#ff69b4");
    }

    /**
     * indianred
     * #cd5c5c
     * 205,92,92
     */
    public static UIColor indianredColor() {
        return new UIColor("#cd5c5c");
    }

    /**
     * indigo
     * #4b0082
     * 75,0,130
     */
    public static UIColor indigoColor() {
        return new UIColor("#4b0082");
    }

    /**
     * ivory
     * #fffff0
     * 255,255,240
     */
    public static UIColor ivoryColor() {
        return new UIColor("#fffff0");
    }

    /**
     * khaki
     * #f0e68c
     * 240,230,140
     */
    public static UIColor khakiColor() {
        return new UIColor("#f0e68c");
    }

    /**
     * lavender
     * #e6e6fa
     * 230,230,250
     */
    public static UIColor lavenderColor() {
        return new UIColor("#e6e6fa");
    }

    /**
     * lavenderblush
     * #fff0f5
     * 255,240,245
     */
    public static UIColor lavenderblushColor() {
        return new UIColor("#fff0f5");
    }

    /**
     * lawngreen
     * #7cfc00
     * 124,252,0
     */
    public static UIColor lawngreenColor() {
        return new UIColor("#7cfc00");
    }

    /**
     * lemonchiffon
     * #fffacd
     * 255,250,205
     */
    public static UIColor lemonchiffonColor() {
        return new UIColor("#fffacd");
    }

    /**
     * lightblue
     * #add8e6
     * 173,216,230
     */
    public static UIColor lightblueColor() {
        return new UIColor("#add8e6");
    }

    /**
     * lightcoral
     * #f08080
     * 240,128,128
     */
    public static UIColor lightcoralColor() {
        return new UIColor("#f08080");
    }

    /**
     * lightcyan
     * #e0ffff
     * 224,255,255
     */
    public static UIColor lightcyanColor() {
        return new UIColor("#e0ffff");
    }

    /**
     * lightgoldenrodyellow
     * #fafad2
     * 250,250,210
     */
    public static UIColor lightgoldenrodyellowColor() {
        return new UIColor("#fafad2");
    }

    /**
     * lightgreen
     * #90ee90
     * 144,238,144
     */
    public static UIColor lightgreenColor() {
        return new UIColor("#90ee90");
    }

    /**
     * lightgrey
     * #d3d3d3
     * 211,211,211
     */
    public static UIColor lightgreyColor() {
        return new UIColor("#d3d3d3");
    }

    /**
     * lightpink
     * #ffb6c1
     * 255,182,193
     */
    public static UIColor lightpinkColor() {
        return new UIColor("#ffb6c1");
    }

    /**
     * lightsalmon
     * #ffa07a
     * 255,160,122
     */
    public static UIColor lightsalmonColor() {
        return new UIColor("#ffa07a");
    }

    /**
     * lightseagreen
     * #20b2aa
     * 32,178,170
     */
    public static UIColor lightseagreenColor() {
        return new UIColor("#20b2aa");
    }

    /**
     * lightskyblue
     * #87cefa
     * 135,206,250
     */
    public static UIColor lightskyblueColor() {
        return new UIColor("#87cefa");
    }

    /**
     * lightslategray
     * #778899
     * 119,136,153
     */
    public static UIColor lightslategrayColor() {
        return new UIColor("#778899");
    }

    /**
     * lightsteelblue
     * #b0c4de
     * 176,196,222
     */
    public static UIColor lightsteelblueColor() {
        return new UIColor("#b0c4de");
    }

    /**
     * lightyellow
     * #ffffe0
     * 255,255,224
     */
    public static UIColor lightyellowColor() {
        return new UIColor("#ffffe0");
    }

    /**
     * lime
     * #00ff00
     * 0,255,0
     */
    public static UIColor limeColor() {
        return new UIColor("#00ff00");
    }

    /**
     * limegreen
     * #32cd32
     * 50,205,50
     */
    public static UIColor limegreenColor() {
        return new UIColor("#32cd32");
    }

    /**
     * linen
     * #faf0e6
     * 250,240,230
     */
    public static UIColor linenColor() {
        return new UIColor("#faf0e6");
    }

    /**
     * magenta
     * #ff00ff
     * 255,0,255
     */
    public static UIColor magentaColor() {
        return new UIColor("#ff00ff");
    }

    /**
     * maroon
     * #800000
     * 128,0,0
     */
    public static UIColor maroonColor() {
        return new UIColor("#800000");
    }

    /**
     * mediumaquamarine
     * #66cdaa
     * 102,205,170
     */
    public static UIColor mediumaquamarineColor() {
        return new UIColor("#66cdaa");
    }

    /**
     * mediumblue
     * #0000cd
     * 0,0,205
     */
    public static UIColor mediumblueColor() {
        return new UIColor("#0000cd");
    }

    /**
     * mediumorchid
     * #ba55d3
     * 186,85,211
     */
    public static UIColor mediumorchidColor() {
        return new UIColor("#ba55d3");
    }

    /**
     * mediumpurple
     * #9370d6
     * 147,112,219
     */
    public static UIColor mediumpurpleColor() {
        return new UIColor("#9370d6");
    }

    /**
     * mediumseagreen
     * #3cb371
     * 60,179,113
     */
    public static UIColor mediumseagreenColor() {
        return new UIColor("#3cb371");
    }

    /**
     * mediumslateblue
     * #7b68ee
     * 123,104,238
     */
    public static UIColor mediumslateblueColor() {
        return new UIColor("#7b68ee");
    }

    /**
     * mediumspringgreen
     * #00fa9a
     * 0,250,154
     */
    public static UIColor mediumspringgreenColor() {
        return new UIColor("#00fa9a");
    }

    /**
     * mediumturquoise
     * #48d1cc
     * 72,209,204
     */
    public static UIColor mediumturquoiseColor() {
        return new UIColor("#48d1cc");
    }

    /**
     * mediumvioletred
     * #c71585
     * 199,21,133
     */
    public static UIColor mediumvioletredColor() {
        return new UIColor("#c71585");
    }

    /**
     * midnightblue
     * #191970
     * 25,25,112
     */
    public static UIColor midnightblueColor() {
        return new UIColor("#191970");
    }

    /**
     * mintcream
     * #f5fffa
     * 245,255,250
     */
    public static UIColor mintcreamColor() {
        return new UIColor("#f5fffa");
    }

    /**
     * mistyrose
     * #ffe4e1
     * 255,228,225
     */
    public static UIColor mistyroseColor() {
        return new UIColor("#ffe4e1");
    }

    /**
     * moccasin
     * #ffe4b5
     * 255,228,181
     */
    public static UIColor moccasinColor() {
        return new UIColor("#ffe4b5");
    }

    /**
     * navajowhite
     * #ffdead
     * 255,222,173
     */
    public static UIColor navajowhiteColor() {
        return new UIColor("#ffdead");
    }

    /**
     * navy
     * #000080
     * 0,0,128
     */
    public static UIColor navyColor() {
        return new UIColor("#000080");
    }

    /**
     * oldlace
     * #fdf5e6
     * 253,245,230
     */
    public static UIColor oldlaceColor() {
        return new UIColor("#fdf5e6");
    }

    /**
     * olive
     * #808000
     * 128,128,0
     */
    public static UIColor oliveColor() {
        return new UIColor("#808000");
    }

    /**
     * olivedrab
     * #6b8e23
     * 107,142,35
     */
    public static UIColor olivedrabColor() {
        return new UIColor("#6b8e23");
    }

    /**
     * orange
     * #ffa500
     * 255,165,0
     */
    public static UIColor orangeColor() {
        return new UIColor("#ffa500");
    }

    /**
     * orangered
     * #ff4500
     * 255,69,0
     */
    public static UIColor orangeredColor() {
        return new UIColor("#ff4500");
    }

    /**
     * orchid
     * #da70d6
     * 218,112,214
     */
    public static UIColor orchidColor() {
        return new UIColor("#da70d6");
    }

    /**
     * palegoldenrod
     * #eee8aa
     * 238,232,170
     */
    public static UIColor palegoldenrodColor() {
        return new UIColor("#eee8aa");
    }

    /**
     * palegreen
     * #98fb98
     * 152,251,152
     */
    public static UIColor palegreenColor() {
        return new UIColor("#98fb98");
    }

    /**
     * paleturquoise
     * #afeeee
     * 175,238,238
     */
    public static UIColor paleturquoiseColor() {
        return new UIColor("#afeeee");
    }

    /**
     * palevioletred
     * #db7093
     * 219,112,147
     */
    public static UIColor palevioletredColor() {
        return new UIColor("#db7093");
    }

    /**
     * papayawhip
     * #ffefd5
     * 255,239,213
     */
    public static UIColor papayawhipColor() {
        return new UIColor("#ffefd5");
    }

    /**
     * peachpuff
     * #ffdab9
     * 255,218,185
     */
    public static UIColor peachpuffColor() {
        return new UIColor("#ffdab9");
    }

    /**
     * peru
     * #cd853f
     * 205,133,63
     */
    public static UIColor peruColor() {
        return new UIColor("#cd853f");
    }

    /**
     * pink
     * #ffc0cb
     * 255,192,203
     */
    public static UIColor pinkColor() {
        return new UIColor("#ffc0cb");
    }

    /**
     * plum
     * #dda0dd
     * 221,160,221
     */
    public static UIColor plumColor() {
        return new UIColor("#dda0dd");
    }

    /**
     * powderblue
     * #b0e0dd
     * 176,224,230
     */
    public static UIColor powderblueColor() {
        return new UIColor("#b0e0dd");
    }

    /**
     * purple
     * #800080
     * 128,0,128
     */
    public static UIColor purpleColor() {
        return new UIColor("#800080");
    }

    /**
     * red
     * #ff0000
     * 255,0,0
     */
    public static UIColor redColor() {
        return new UIColor("#ff0000");
    }

    /**
     * rosybrown
     * #bc8f8f
     * 188,0,143
     */
    public static UIColor rosybrownColor() {
        return new UIColor("#bc8f8f");
    }

    /**
     * royalblue
     * #41169e1
     * 65,105,225
     */
    public static UIColor royalblueColor() {
        return new UIColor("#41169e1");
    }

    /**
     * saddlebrown
     * #8b4513
     * 139,69,19
     */
    public static UIColor saddlebrownColor() {
        return new UIColor("#8b4513");
    }

    /**
     * salmon
     * #fa8072
     * 250,128,114
     */
    public static UIColor salmonColor() {
        return new UIColor("#fa8072");
    }

    /**
     * sandybrown
     * #f4a460
     * 244,164,96
     */
    public static UIColor sandybrownColor() {
        return new UIColor("#f4a460");
    }

    /**
     * seagreen
     * #2e8b57
     * 46,139,87
     */
    public static UIColor seagreenColor() {
        return new UIColor("#2e8b57");
    }

    /**
     * seashell
     * #fff5ee
     * 255,245,238
     */
    public static UIColor seashellColor() {
        return new UIColor("#fff5ee");
    }

    /**
     * sienna
     * #a0522d
     * 160,82,45
     */
    public static UIColor siennaColor() {
        return new UIColor("#a0522d");
    }

    /**
     * silver
     * #c0c0c0
     * 192,192,192
     */
    public static UIColor silverColor() {
        return new UIColor("#c0c0c0");
    }

    /**
     * skyblue
     * #87ceeb
     * 135,206,235
     */
    public static UIColor skyblueColor() {
        return new UIColor("#87ceeb");
    }

    /**
     * slateblue
     * #6a5acd
     * 106,90,205
     */
    public static UIColor slateblueColor() {
        return new UIColor("#6a5acd");
    }

    /**
     * slategray
     * #708090
     * 112,128,144
     */
    public static UIColor slategrayColor() {
        return new UIColor("#708090");
    }

    /**
     * snow
     * #fffafa
     * 255,250,250
     */
    public static UIColor snowColor() {
        return new UIColor("#fffafa");
    }

    /**
     * springgreen
     * #00ff7f
     * 0,255,127
     */
    public static UIColor springgreenColor() {
        return new UIColor("#00ff7f");
    }

    /**
     * steelblue
     * #4682b4
     * 70,130,180
     */
    public static UIColor steelblueColor() {
        return new UIColor("#4682b4");
    }

    /**
     * tan
     * #d2b48c
     * 210,180,140
     */
    public static UIColor tanColor() {
        return new UIColor("#d2b48c");
    }

    /**
     * teal
     * #008080
     * 0,128,128
     */
    public static UIColor tealColor() {
        return new UIColor("#008080");
    }

    /**
     * thistle
     * #d8bfd8
     * 216,191,216
     */
    public static UIColor thistleColor() {
        return new UIColor("#d8bfd8");
    }

    /**
     * tomato
     * #ff6347
     * 255,99,71
     */
    public static UIColor tomatoColor() {
        return new UIColor("#ff6347");
    }

    /**
     * turquoise
     * #40e0d0
     * 64,224,208
     */
    public static UIColor turquoiseColor() {
        return new UIColor("#40e0d0");
    }

    /**
     * violet
     * #ee82ee
     * 238,130,238
     */
    public static UIColor violetColor() {
        return new UIColor("#ee82ee");
    }

    /**
     * wheat
     * #f5deb3
     * 245,223,179
     */
    public static UIColor wheatColor() {
        return new UIColor("#f5deb3");
    }

    /**
     * white
     * #ffffff
     * 255,255,255
     */
    public static UIColor whiteColor() {
        return new UIColor("#ffffff");
    }

    /**
     * whitesmoke
     * #f5f5f5
     * 245,245,245
     */
    public static UIColor whitesmokeColor() {
        return new UIColor("#f5f5f5");
    }

    /**
     * yellow
     * #ffff00
     * 255,255,0
     */
    public static UIColor yellowColor() {
        return new UIColor("#ffff00");
    }

    /**
     * yellowgreen
     * #9acd32
     * 154,205,50
     */
    public static UIColor yellowgreenColor() {
        return new UIColor("#9acd32");
    }


    public Drawable getDRB() {
        return drawable;
    }

}
