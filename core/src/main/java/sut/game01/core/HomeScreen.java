package sut.game01.core;

import playn.core.Font;
import playn.core.PlayN;
import react.UnitSlot;
import tripleplay.game.ScreenStack;
import tripleplay.game.UIScreen;
import tripleplay.ui.*;
import tripleplay.ui.layout.AxisLayout;


/**
 * Created by UM_COMPUTER on 21/1/2557.
 */
public class HomeScreen extends UIScreen{

    public static final Font TITLE_FONT = PlayN.graphics().createFont(
            "Helvetica",Font.Style.BOLD,24);

    public static final Font TITLE_FONT2 = PlayN.graphics().createFont(
            "Helvetica",Font.Style.PLAIN,15);

    private final ScreenStack ss;
    private Root root;

    public HomeScreen(ScreenStack ss){
        this.ss = ss;
    }


    @Override
    public void wasShown() {
        super.wasShown();

        root = iface.createRoot(
            AxisLayout.vertical().gap(15),
            SimpleStyles.newSheet(),layer);
        root.addStyles(Style.BACKGROUND
            .is(Background.bordered(0xFFCCCCCC, 0xFF99CCFF, 5)
                    .inset(5, 10)));
        root.setSize(width(),height());

        root.add(new Label("Event Driven Programming")
            .addStyles(Style.FONT.is(testEx.TITLE_FONT)));

        root.add(new Label("Please press Enter or Button Start")
            .addStyles(Style.FONT.is(testEx.TITLE_FONT2)));

        root.add(new Button("Start").onClick(new UnitSlot() {
            @Override
            public void onEmit() {
                ss.push(new GameS(ss));
            }
        }));
    }
}
