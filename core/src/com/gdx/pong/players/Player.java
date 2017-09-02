package com.gdx.pong.players;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.gdx.pong.Ball;


public abstract class Player {
    protected Rectangle paddlePosition;
    protected final boolean isLeftPlayer;
    private static boolean isFirstInstance = true;
    protected final float MOVEMENT;


    protected Player(float MOVEMENT){
        int width = 20;
        int height = 100;
        if (isFirstInstance) {
            isLeftPlayer = true;
            isFirstInstance = false;
        }
        else
            isLeftPlayer = false;
        if(isLeftPlayer)
            paddlePosition =  new Rectangle(150, Gdx.graphics.getHeight()/2, width, height);
        else
            paddlePosition = new Rectangle( Gdx.graphics.getWidth() - 150, Gdx.graphics.getHeight()/2, width, height);
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
            return (ball.getX() + 5 ) < paddlePosition.x;
        else
            return (ball.getX() - 5) > paddlePosition.x;
    }

    protected void moveUp(){
        paddlePosition.y += MOVEMENT;
    }

    protected void moveDown(){
        paddlePosition.y -= MOVEMENT;
    }


    protected boolean isUpEdgeInsideScreen(){
        return (paddlePosition.y + getHeight()) < Gdx.graphics.getHeight();
    }

    protected boolean isDownEdgeInsideScreen(){
        return paddlePosition.y > 0;
    }

    public float getWidth(){
        return paddlePosition.width;
    }

    public float getHeight() {
        return paddlePosition.height;
    }

}
