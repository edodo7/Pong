package com.gdx.pong.players;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.gdx.pong.Ball;


public abstract class Player {
    protected Rectangle position;
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
            position =  new Rectangle(150, Gdx.graphics.getHeight()/2, width, height);
        else
            position = new Rectangle( Gdx.graphics.getWidth() - 150, Gdx.graphics.getHeight()/2, width, height);
        this.MOVEMENT = MOVEMENT;
    }


    public boolean isCollisonDetected(Ball ball) {
        return Intersector.overlaps(ball.getPosition(), position);
    }

    public float getX(){
        return position.x;
    }

    public float getY(){
        return position.y;
    }

    public abstract void move();

    public boolean isBallBehind(Ball ball){
        int margin = 80;
        if (isLeftPlayer)
            return (ball.getX() + margin ) < position.x;
        else
            return (ball.getX() - margin) > position.x;
    }

    protected void moveUp(){
        position.y += MOVEMENT;
    }

    protected void moveDown(){
        position.y -= MOVEMENT;
    }


    protected boolean isUpEdgeInsideScreen(){
        return (position.y + getHeight()) < Gdx.graphics.getHeight();
    }

    protected boolean isDownEdgeInsideScreen(){
        return position.y > 0;
    }

    public float getWidth(){
        return position.width;
    }

    public float getHeight() {
        return position.height;
    }

}
