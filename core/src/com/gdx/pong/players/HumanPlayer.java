package com.gdx.pong.players;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class HumanPlayer extends AbstractPlayer {

    public HumanPlayer(boolean leftPlayer){
        super(leftPlayer);
    }

    public void move() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            if( (paddlePositon.y + PADDLE_HEIGHT) < Gdx.graphics.getHeight() )
                paddlePositon.y += 7f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            if (paddlePositon.y > 0)
                paddlePositon.y -= 7f;
        }
    }
}
