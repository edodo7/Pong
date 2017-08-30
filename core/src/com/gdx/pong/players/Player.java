package com.gdx.pong.players;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.gdx.pong.Ball;


public abstract class Player {
    public final int WIDTH = 20;
    public final int HEIGHT = 100;
    protected Rectangle paddlePosition;
    protected final boolean isLeftPlayer;
    private static boolean isFirstInstance = true;
    protected final float MOVEMENT;


    protected Player(float MOVEMENT){
        if (isFirstInstance) {
            isLeftPlayer = true;
            isFirstInstance = false;
        }
        else
            isLeftPlayer = false;
        if(isLeftPlayer)
            paddlePosition =  new Rectangle(150, Gdx.graphics.getHeight()/2, WIDTH, HEIGHT);
        else
            paddlePosition = new Rectangle( Gdx.graphics.getWidth() - 150, Gdx.graphics.getHeight()/2, WIDTH, HEIGHT);
        this.MOVEMENT = MOVEMENT;
    }


    public boolean isCollisonDetected(Ball ball) {
        return paddlePosition.overlaps(ball.getBallPosition());
    }

    public float getX(){
        return paddlePosition.x;
    }

    public float getY(){
        return paddlePosition.y;
    }

    public abstract void move();

    public boolean isBallBehind(Ball ball){
        if (isLeftPlayer)
            return (ball.getX() + 80 ) < paddlePosition.x;
        else
            return (ball.getX() - 80) > paddlePosition.x;
    }

    protected void moveUp(){
        paddlePosition.y += MOVEMENT;
    }

    protected void moveDown(){
        paddlePosition.y -= MOVEMENT;
    }


    protected boolean isUpEdgeInsideScreen(){
        return (paddlePosition.y + HEIGHT) < Gdx.graphics.getHeight();
    }

    protected boolean isDownEdgeInsideScreen(){
        return paddlePosition.y > 0;
    }

}
