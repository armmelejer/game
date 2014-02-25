package sut.game01.core;

import playn.core.*;
import tripleplay.game.Screen;
import tripleplay.game.ScreenStack;
import tripleplay.ui.Root;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

/**
 * Created by UM_COMPUTER on 11/2/2557.
 */
public class testEx extends Screen {
    public static final Font TITLE_FONT = PlayN.graphics().createFont(
            "Helvetica",Font.Style.BOLD,24);
    public static final Font TITLE_FONT2 = PlayN.graphics().createFont(
            "Helvetica",Font.Style.PLAIN,15);
    private final ScreenStack ss;

    public testEx(ScreenStack ss) {
        this.ss = ss;
    }
    private Root root;

    @Override
    public void wasShown() {
        super.wasShown();
        create1();
        create2();
    }

    public void create1(){
        Image image = assets().getImage("images/cloud1.png");
        ImageLayer imageLayer = graphics().createImageLayer(image);
        graphics().rootLayer().add(imageLayer);
        imageLayer.setTranslation(0f,0f);

        imageLayer.addListener(new Pointer.Listener() {
            @Override
            public void onPointerStart(Pointer.Event event) {
                ss.remove(ss.top());
            }

            @Override
            public void onPointerEnd(Pointer.Event event) {

            }

            @Override
            public void onPointerDrag(Pointer.Event event) {

            }

            @Override
            public void onPointerCancel(Pointer.Event event) {

            }
        });
    }

    public void create2(){
        Image image = assets().getImage("images/cloud2.png");
        ImageLayer imageLayer = graphics().createImageLayer(image);
        graphics().rootLayer().add(imageLayer);
        imageLayer.setTranslation(510f,-10f);

        imageLayer.addListener(new Pointer.Listener() {
            @Override
            public void onPointerStart(Pointer.Event event) {
                ss.push(new TestExam(ss));
            }

            @Override
            public void onPointerEnd(Pointer.Event event) {

            }

            @Override
            public void onPointerDrag(Pointer.Event event) {

            }

            @Override
            public void onPointerCancel(Pointer.Event event) {

            }
        });
    }

    @Override
    public void wasAdded() {
        Image image2 = assets().getImage("images/bg.png");
        ImageLayer imageLayer2 = graphics().createImageLayer(image2);
        graphics().rootLayer().add(imageLayer2);



    }
}

