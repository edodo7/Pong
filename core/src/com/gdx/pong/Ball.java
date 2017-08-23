package com.gdx.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Ball {
    public final int BALL_RADIUS = 12;
    private Vector2 direction;
    private Vector2 ballPosition;
    private Random random;
    private float[] arrayOfDirections;

    public Ball(){
        random = new Random();
        arrayOfDirections = new float[]{6f,-6f};
        direction = new Vector2();
        ballPosition = new Vector2(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
    }

    public void chooseRandomDirection(){
        float xDirection = arrayOfDirections[random.nextInt(2)];
        float yDirection = arrayOfDirections[random.nextInt(2)];
        direction.set(xDirection,yDirection);
    }

    public void resetPosition(){
        ballPosition.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
    }

    public void move(){
        ballPosition.add(direction);
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
}
