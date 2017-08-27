package com.gdx.pong.players;

import com.badlogic.gdx.Gdx;
import com.gdx.pong.Ball;
import com.gdx.pong.main.Pong;

public class AIPlayer  extends AbstractPlayer{

    private static Ball ball;
    private final float deplacement = 5f;

    public AIPlayer(boolean isLeftPlayer) {
        super(isLeftPlayer);
        ball = Pong.getBall();
    }

    public void move (){
        if (isBallOnAIPlayerSide()) {
            if (ball.getY() > (getY() + HEIGHT/2) )
                moveUp();
            else if (ball.getY() < (getY() + HEIGHT/2))
                moveDown();
        }
    }

    private boolean isBallOnAIPlayerSide() {
        return (Math.abs(paddlePositon.x - ball.getX()) < Gdx.graphics.getWidth()/2);
    }

    private void moveUp(){
        paddlePositon.y += deplacement;
    }

    private void moveDown(){
        paddlePositon.y -= deplacement;
    }
}