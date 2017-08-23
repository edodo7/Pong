package com.gdx.pong.players;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.gdx.pong.Ball;


public abstract class AbstractPlayer {
    public final int WIDTH = 20;
    public final int HEIGHT = 100;
    protected Rectangle paddlePositon;
    protected final boolean leftPlayer;

    public AbstractPlayer(boolean leftPlayer){
        this.leftPlayer = leftPlayer;
        if(leftPlayer)
            paddlePositon =  new Rectangle(150, Gdx.graphics.getHeight()/2, WIDTH, HEIGHT);
        else
            paddlePositon = new Rectangle( Gdx.graphics.getWidth() - 150, Gdx.graphics.getHeight()/2, WIDTH, HEIGHT);
    }

    // TODO : manage collision detection for paddle's corners
    public boolean isCollisonDetected(Ball ball) {
        return paddlePositon.overlaps(ball.getBallPosition());
    }

    public float getX(){
        return paddlePositon.x;
    }

    public float getY(){
        return paddlePositon.y;
    }

    public abstract void move();

    public boolean isBallBehind(Ball ball){
        if (leftPlayer)
            return (ball.getX() + 80 ) < paddlePositon.x;
        else
            return (ball.getX() - 80) > paddlePositon.x;
    }
}
