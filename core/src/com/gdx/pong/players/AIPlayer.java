package com.gdx.pong.players;

import com.badlogic.gdx.Gdx;
import com.gdx.pong.Ball;
import com.gdx.pong.main.Pong;

public class AIPlayer  extends Player {

    private static Ball ball;


    public AIPlayer() {
        super(4.35f);
        ball = Pong.getBall();
    }

    public void move (){
        if (isBallOnAIPlayerSide() && isBallComingToAIPLayer()) {
            if (isBallAboveMiddle()) {
                if(isUpEdgeInsideScreen())
                    moveUp();
            } else if (isBallBelowMiddle()) {
                if (isDownEdgeInsideScreen())
                    moveDown();
            }
        }
    }

    private boolean isBallOnAIPlayerSide() {
        return (Math.abs((paddlePosition.x + 160 ) - ball.getX()) < Gdx.graphics.getWidth()/2);
    }

    private boolean isBallComingToAIPLayer() {
        if(isLeftPlayer)
            return ball.getAbscissaDirection() < 0;
        else
            return ball.getAbscissaDirection() > 0;
    }

    private boolean isBallAboveMiddle(){
        return ball.getY() > (getY() + getHeight() / 2);
    }

    private boolean isBallBelowMiddle(){
        return ball.getY() < (getY() + getHeight() / 2);
    }

}