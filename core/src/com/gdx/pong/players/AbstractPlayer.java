package com.gdx.pong.players;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.gdx.pong.Ball;

public abstract class AbstractPlayer {
    public final int PADDLE_WIDTH = 20;
    public final int PADDLE_HEIGHT = 100;
    protected Vector2 paddlePositon;
    protected final boolean leftPlayer;

    public AbstractPlayer(boolean leftPlayer){
        this.leftPlayer = leftPlayer;
        if(leftPlayer)
            paddlePositon =  new Vector2(150, Gdx.graphics.getHeight()/2);
        else
            paddlePositon = new Vector2( Gdx.graphics.getWidth() - 150, Gdx.graphics.getHeight()/2);
    }

    public boolean isCollisonDetected(Ball ball) {
        float ballEdge = ball.getX() - ball.BALL_RADIUS;
        float paddleEdge = paddlePositon.x + PADDLE_WIDTH;
        return ((ballEdge <= paddleEdge) && ballWithinPaddleHeight(ball));
    }

    protected boolean ballWithinPaddleHeight(Ball ball){
        return ((paddlePositon.y + PADDLE_HEIGHT >= ball.getY()+ ball.BALL_RADIUS) &&
                (paddlePositon.y  <= ball.getY() - ball.BALL_RADIUS) );
    }

    public float getX(){
        return paddlePositon.x;
    }

    public float getY(){
        return paddlePositon.y;
    }

    public abstract void move();
}
