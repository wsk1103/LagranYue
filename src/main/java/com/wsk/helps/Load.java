package com.wsk.helps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

/**
 * @author wsk1103
 * @date 2019/3/9
 * @description 描述
 */
public class Load {
    public static TextureAtlas atlas;
    public static AtlasRegion bg;
    public static TextureAtlas atlas5;
    public static AtlasRegion bg5;
    public static TextureAtlas atlas_plate;
    public static AtlasRegion plate;
    public static TextureAtlas atlas3;
    public static AtlasRegion bg3;
    public static TextureAtlas atlas2;
    public static AtlasRegion bg2;
    public static TextureAtlas atlas4;
    public static AtlasRegion bg4;
    public static TextureAtlas atlaso;
    public static AtlasRegion bgo;
    public static int times;
    public static Texture DulePlate;
    public static int TrapActivated;

    public Load() {
    }

    static {
        //        atlas = new TextureAtlas(Gdx.files.internal("MyEndingScene/scene.atlas"));
//        bg = atlas.findRegion("bg");
//        atlaso = new TextureAtlas(Gdx.files.internal("EndingSS/scene.atlas"));
//        bgo = atlaso.findRegion("bg");
        atlas5 = new TextureAtlas(Gdx.files.internal("ValleyScene/scene.atlas"));
        bg5 = atlas5.findRegion("bg");
//        atlas_plate = new TextureAtlas(Gdx.files.internal("Plate/scene.atlas"));
//        plate = atlas_plate.findRegion("bg");
//        atlas3 = new TextureAtlas(Gdx.files.internal("MyForestScene/scene.atlas"));
//        bg3 = atlas3.findRegion("bg");
//        atlas2 = new TextureAtlas(Gdx.files.nternal("EndlessPalaceScene/scene.atlas"));
//        bg2 = atlas2.findRegion("bg");
//        DulePlate = ImageMaster.loadImage("orbs/Plate.png");
//        atlas4 = new TextureAtlas(Gdx.files.internal("DarkSideScene/scene.atlas"));
//        bg4 = atlas4.findRegion("bg");
//        atlas = new TextureAtlas(Gdx.files.internal("MyEndingScene/scene.atlas"));
//        bg = atlas.findRegion("bg");
        times = 1;
        TrapActivated = 0;
    }

}
