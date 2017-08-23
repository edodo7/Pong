package com.gdx.pong.players;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class AIPlayer  extends AbstractPlayer{
    public AIPlayer(boolean isLeft) {
        super(isLeft);
    }
    public void move (){
        if (Gdx.input.isKeyPressed(Input.Keys.H)){
            if( (paddlePositon.y + PADDLE_HEIGHT) < Gdx.graphics.getHeight() )
                paddlePositon.y += 7f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.B)){
            if (paddlePositon.y > 0)
                paddlePositon.y -= 7f;
        }
    }
}
