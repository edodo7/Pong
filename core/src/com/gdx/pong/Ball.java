package com.gdx.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Ball {
    public final int RADIUS = 12;
    private Vector2 direction;
    private Rectangle ballPosition;
    private Random random;
    private float[] arrayOfDirections;

    public Ball(){
        random = new Random();
        arrayOfDirections = new float[]{5f,-5f};
        direction = new Vector2();
        ballPosition = new Rectangle(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2,0,0);
    }

    public void chooseRandomDirection(){
        float xDirection = arrayOfDirections[random.nextInt(2)];
        float yDirection = arrayOfDirections[random.nextInt(2)];
        direction.set(xDirection,yDirection);
    }

    public void resetPosition(){
        ballPosition.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
    }

    public void move(){
        ballPosition.setPosition(ballPosition.x + direction.x,ballPosition.y + direction.y);
    }

    public float getX(){
        return ballPosition.x;
    }

    public void reverseXDirection(){
        direction.x = -direction.x;
    }

    public void reverseYDirection(){
        direction.y = -direction.y;
    }

    public float getY(){
        return ballPosition.y;
    }

    public Rectangle getBallPosition() {
        return ballPosition;
    }
}
