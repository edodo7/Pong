package com.gdx.pong.players;

import com.badlogic.gdx.Gdx;
import com.gdx.pong.Ball;
import com.gdx.pong.main.Pong;

public class AIPlayer  extends AbstractPlayer{

    private static Ball ball;
    private final float deplacement = 5f;

    public AIPlayer() {
        super();
        ball = Pong.getBall();
    }

    public void move (){
        if (isBallOnAIPlayerSide() && isBallComingToAIPLayer()) {
            if (ball.getY() > (getY() + HEIGHT / 2)) {
                if( (paddlePositon.y + HEIGHT) < Gdx.graphics.getHeight() )
                    moveUp();
            } else if (ball.getY() < (getY() + HEIGHT / 2)) {
                if (paddlePositon.y > 0)
                    moveDown();
            }
        }
    }

    private boolean isBallOnAIPlayerSide() {
        return (Math.abs((paddlePositon.x + 160 )- ball.getX()) < Gdx.graphics.getWidth()/2);
    }

    private boolean isBallComingToAIPLayer() {
        return( (!isLeftPlayer && ball.getDirection().x > 0) || (isLeftPlayer && ball.getDirection().x < 0));
    }

    private void moveUp(){
        paddlePositon.y += deplacement;
    }

    private void moveDown(){
        paddlePositon.y -= deplacement;
    }
}