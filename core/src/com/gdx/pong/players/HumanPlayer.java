package com.gdx.pong.players;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class HumanPlayer extends Player {

    private final int UP;
    private final int DOWN;

    public HumanPlayer(){
        super(7f);
        if (isLeftPlayer){
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
            if(isUpEdgeInsideScreen())
                moveUp();
        }
        if (Gdx.input.isKeyPressed(DOWN)){
            if (isUpEdgeInsideScreen())
                moveDown();
        }
    }
}
