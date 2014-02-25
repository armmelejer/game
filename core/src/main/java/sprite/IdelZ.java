package sprite;


import playn.core.Layer;
import playn.core.PlayN;
import playn.core.Pointer;
import playn.core.util.Callback;

/**
 * Created by UM_COMPUTER on 28/1/2557.
 */
public class IdelZ {
    private Sprite sprite;
    private int spriteIndex = 0;
    private boolean hasLoaded = false;
    public enum State{
        IDLE, RUN, ATTK, DIA
    };

    private State state = State.IDLE;

    private int e = 0;
    private int offset = 0;

    public IdelZ(final float x,final float y){
        this.sprite = SpriteLoader.getSprite("images/idel.json");
        this.sprite.addCallback(new Callback<Sprite>() {
            @Override
            public void onSuccess(Sprite result) {
                sprite.setSprite(spriteIndex);
                sprite.layer().setOrigin(sprite.width()/2f,sprite.height()/2f);
                sprite.layer().setTranslation(x,y);
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
                state = State.DIA;
                spriteIndex = -1;
                e = 0;
            }
        });
    }

    public  void update(int delta){
        if(!hasLoaded) return;
        e += delta;

        if(e > 150){
            switch (state){
                case IDLE: offset = 0;
                    break;
                case RUN:
                    offset = 4;
                    break;
                case DIA:
                    offset = 2;
                    if (spriteIndex == 10){
                        state = State.DIA;
                    }
                    break;
            }
            spriteIndex = offset + ((spriteIndex + 1 )% 4);
            sprite.setSprite(spriteIndex);
            e = 0;
        }
        if (state == State.DIA){
            spriteIndex = offset + ((spriteIndex + 1 ) % 2);
            sprite.setSprite(spriteIndex);
            e = 0;
        }
    }

    public Layer layer(){
        return sprite.layer();
    }

}