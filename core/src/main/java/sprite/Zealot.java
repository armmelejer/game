package sprite;


import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import playn.core.*;
import playn.core.util.Callback;
import playn.core.util.Clock;
import sut.game01.core.GameS;


/**
 * Created by UM_COMPUTER on 28/1/2557.
 */
public class Zealot {
    private Sprite sprite;
    private int spriteIndex = 0;
    private boolean hasLoaded = false;
    public enum State{
        IDLE, RUN, ATTK
    };

    private State state = State.IDLE;

    private int e = 0;
    private int offset = 0;
    private Body body;



    public Zealot(final World world,final float x_px, final float y_px){

        this.sprite = SpriteLoader.getSprite("images/k.json");
        this.sprite.addCallback(new Callback<Sprite>() {
            @Override
            public void onSuccess(Sprite result) {
                sprite.setSprite(spriteIndex);
                sprite.layer().setOrigin(sprite.width() / 2f, sprite.height() / 2f);
                sprite.layer().setTranslation(x_px, y_px);
                body = initPhysicsBody(world, GameS.M_PER_PIXEL*x_px,GameS.M_PER_PIXEL*y_px);
                hasLoaded = true;
            }

            @Override
            public void onFailure(Throwable cause) {
                PlayN.log().error("Error loading picture!",cause);
            }

        });
        sprite.layer().addListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event){
                state = State.ATTK;
                spriteIndex = -1;
                e = 0;
            }
        });
        PlayN.keyboard().setListener(new Keyboard.Listener() {
            @Override
            public void onKeyDown(Keyboard.Event event) {
                if (event.key() == Key.A) {
                    state = State.RUN;
                    spriteIndex = -1;
                    e = 0;
                }
                if(event.key()== Key.SPACE){
                    body.applyLinearImpulse(new Vec2(0f, -100f), body.getPosition());
                }
                if(event.key()== Key.RIGHT){
                    body.applyLinearImpulse(new Vec2(100f,0f),body.getPosition());

                }
                if(event.key()== Key.LEFT){
                    body.applyLinearImpulse(new Vec2(-100f,0f),body.getPosition());

                }
                if(event.key()== Key.BACKSPACE){
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
    private Body initPhysicsBody(World world, float x, float y){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.position = new Vec2(0,0);
        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(56 * GameS.M_PER_PIXEL/2,sprite.layer().height()*GameS.M_PER_PIXEL/2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.5f;
        fixtureDef.friction = 0.1f;
        body.createFixture(fixtureDef);

        body.setLinearDamping(0.2f);
        body.setTransform(new Vec2(x,y),0f);
        return body;
    }
    public  void update(int delta){
        if(!hasLoaded) return;
        e += delta;

        if(e > 150){
            switch (state){
                case IDLE: offset = 1;
                     break;
                case RUN:
                            offset = 1;
                            break;
                case ATTK:
                            offset = 1;
                            if (spriteIndex == 1){
                                state = State.IDLE;
                            }
                            break;
            }
            spriteIndex = offset + ((spriteIndex + 1 )% 1);
            sprite.setSprite(spriteIndex);
            e = 0;
        }
        if (state == State.ATTK){
            spriteIndex = offset + ((spriteIndex + 1 ) % 10);
            sprite.setSprite(spriteIndex);
            e = 0;
        }
        if (state == State.RUN){
            spriteIndex = offset + ((spriteIndex + 1) % 10);
            sprite.setSprite(spriteIndex);
            e = 0;
        }
    }
    public void paint(Clock clock){
        if(!hasLoaded) return;
        sprite.layer().setTranslation((body.getPosition().x/ GameS.M_PER_PIXEL)-10,body.getPosition().y / GameS.M_PER_PIXEL);
    }
    public Layer layer(){
        return sprite.layer();
    }

}