package sut.game01.core;

import playn.core.*;
import playn.core.util.Clock;
import tripleplay.game.Screen;
import tripleplay.game.ScreenStack;
import tripleplay.ui.Root;

public class MyGame extends Game.Default {
    private Image bgImage;
    private ImageLayer bgLayer;


    private ScreenStack ss = new ScreenStack();
    private Clock.Source clock = new Clock.Source(33);
    private Root root;
    public MyGame() {
    super(30); // call update every 33ms (30 times per second)
  }

  @Override
  public void init() {
      final Screen home = new HomeScreen(ss);
      final Screen test = new testEx(ss);
      final Screen game = new GameS(ss);

//      bgImage = assets().getImage("images/bg.png");
//      bgLayer = graphics().createImageLayer(bgImage);
//      graphics().rootLayer().add(bgLayer);

      ss.push(home);
      PlayN.keyboard().setListener(new Keyboard.Listener() {
          @Override
          public void onKeyDown(Keyboard.Event event) {

              if(event.key()== Key.ESCAPE){
                  ss.popTo(home);
              }
              if(event.key()== Key.ENTER){
                  ss.push(game);
              }
          }

          @Override
          public void onKeyTyped(Keyboard.TypedEvent typedEvent) {

          }

          @Override
          public void onKeyUp(Keyboard.Event event) {

          }
      });
  }

  @Override
  public void update(int delta) {
      ss.update(delta);
  }

  @Override
  public void paint(float alpha) {
      clock.paint(alpha);
      ss.paint(clock);
  }
}