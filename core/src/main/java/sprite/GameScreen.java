package sprite;

import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;
import playn.core.CanvasImage;
import playn.core.util.Clock;
import tripleplay.game.Screen;
import tripleplay.game.ScreenStack;

import static playn.core.PlayN.graphics;
public class GameScreen extends Screen{

    public static float M_PER_PIXEL = 1/26.666667f;
    private static int width = 24;
    private static int height = 18;

    private Zealot z;
    private boolean showDebugDraw = true;
    private final ScreenStack ss;
    private World world;
    private DebugDrawBox2D debugDraw;

    public GameScreen(ScreenStack ss) {
        this.ss = ss;
    }

    public enum State{
        KICK
    };

    @Override
    public void wasAdded(){
        super.wasAdded();

        Vec2 gravity = new Vec2(0.0f, 10.0f);
        world = new World(gravity,true);
        world.setWarmStarting(true);
        world.setAutoClearForces(true);

        if(showDebugDraw){
            CanvasImage image = graphics().createImage(
                    (int)(width/GameScreen.M_PER_PIXEL),
                    (int)(height/GameScreen.M_PER_PIXEL));
            layer.add(graphics().createImageLayer(image));
            debugDraw = new DebugDrawBox2D();
            debugDraw.setCanvas(image);
            debugDraw.setFlipY(false);
            debugDraw.setStrokeAlpha(150);
            debugDraw.setFillAlpha(75);
            debugDraw.setStrokeWidth(2.0f);
            debugDraw.setFlags(DebugDraw.e_aabbBit | DebugDraw.e_jointBit | DebugDraw.e_aabbBit);
            debugDraw.setCamera(0,0,1f/ GameScreen.M_PER_PIXEL);
            world.setDebugDraw(debugDraw);
        }

        Body ground = world.createBody(new BodyDef());
        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsEdge(new Vec2(2f, height-2),new Vec2(width-2f,height-2));
        ground.createFixture(groundShape, 0.0f);
    }
    @Override
    public void update(int delta){
        super.update(delta);
        world.step(0.033f,10,10);
        z.update(delta);

    }

    @Override
    public void paint(Clock clock){
        super.paint(clock);
        //z.paint(clock);
        if(showDebugDraw){
            debugDraw.getCanvas().clear();
            world.drawDebugData();
        }
    }
}
