package com.gdx.pong.players;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class HumanPlayer extends AbstractPlayer {

    private final int UP;
    private final int DOWN;

    public HumanPlayer(boolean isleftPlayer){
        super(isleftPlayer);
        if (isleftPlayer){
            UP = Input.Keys.A;
            DOWN = Input.Keys.Q;
        }
        else{
            UP = Input.Keys.UP;
            DOWN = Input.Keys.DOWN;
        }
    }

    public void move() {
        if (Gdx.input.isKeyPressed(UP)){
            if( (paddlePositon.y + HEIGHT) < Gdx.graphics.getHeight() )
                paddlePositon.y += 7f;
        }
        if (Gdx.input.isKeyPressed(DOWN)){
            if (paddlePositon.y > 0)
                paddlePositon.y -= 7f;
        }
    }
}
