package sut.game01.core;

import react.UnitSlot;
import tripleplay.game.ScreenStack;
import tripleplay.game.UIScreen;
import tripleplay.ui.*;
import tripleplay.ui.layout.AxisLayout;

/**
 * Created by UM_COMPUTER on 11/2/2557.
 */
public class TestExam extends UIScreen{
    private final ScreenStack ss;

    public TestExam(ScreenStack ss) {
        this.ss = ss;
    }
    private Root root;

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

        root.add(new Label("The End")
                .addStyles(Style.FONT.is(testEx.TITLE_FONT)));

        root.add(new Label("Please press ESC")
                .addStyles(Style.FONT.is(testEx.TITLE_FONT2)));

        root.add(new Button("Back").onClick(new UnitSlot() {
            @Override
            public void onEmit() {
                ss.remove(ss.top());
            }
        }));
    }
}
