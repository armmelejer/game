package sut.game01.core;

import playn.core.Font;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.PlayN;
import playn.core.util.Clock;
import sprite.IdelZ;
import sprite.Sprite;
import tripleplay.game.ScreenStack;
import tripleplay.game.UIScreen;
import tripleplay.ui.Root;

/**
 * Created by UM_COMPUTER on 21/1/2557.
 */
public class TestScreen extends UIScreen{

    public static final Font TITLE_FONT = PlayN.graphics().createFont(
        "Helvetica",Font.Style.BOLD,24);

    private final ScreenStack ss;
    private Root root;
    private Image bgImage;
    private ImageLayer bgLayer;
    private Sprite sprite;
    //Zealot z = new Zealot(300f,200f);
    IdelZ i = new IdelZ(300f,300f);

    public TestScreen(ScreenStack ss){
        this.ss = ss;
    }

//    public void wasShown(){
//        super.wasShown();
//        root = iface.createRoot(AxisLayout.vertical().gap(15),SimpleStyles.newSheet(),layer);
//        root.addStyles(Style.BACKGROUND.is(Background.bordered(0xFF99CCFF, 0xFF99CCFF,5).inset(5, 10)));
//        root.setSize(width(),height());
//        root.add(new Label("Welcome to game").addStyles(Style.FONT.is(TestScreen.TITLE_FONT)));
//        root.add(new Button("BACK").onClick(new UnitSlot() {
//
//            @Override
//            public void onEmit() {
//                ss.remove(ss.top());
//            }
//        }));
//    }

    @Override
    public void wasAdded(){
        super.wasAdded();
//        Image bgImage = PlayN.assets().getImage("images/bg.png");
//
//
//        bgImage.addCallback(new Callback<Image>() {
//            @Override
//            public void onSuccess(Image result) {
//
//            }
//
//            @Override
//            public void onFailure(Throwable cause) {
//
//            }
//        });
//

//
//        ImageLayer bgLayer = PlayN.graphics().createImageLayer(bgImage);
//        layer.add(bgLayer);

        //layer.add(z.layer());
        //layer.add(i.layer());
    }

    @Override
    public void update(int delta) {
        //z.update(delta);
        i.update(delta);
    }
    @Override
    public void paint(Clock clock){

    }
}
