package com.gdx.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Ball {
    private Vector2 direction;
    private Circle position;
    private Random random;
    private float[] arrayOfDirections;

    public Ball(){
        random = new Random();
        arrayOfDirections = new float[]{5f,-5f};
        direction = new Vector2();
        position = new Circle(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2,12f);
    }

    public void chooseRandomDirection(){
        float xDirection = arrayOfDirections[random.nextInt(2)];
        float yDirection = arrayOfDirections[random.nextInt(2)];
        direction.set(xDirection,yDirection);
    }

    public void resetPosition(){
        position.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
    }

    public void move(){
        position.setPosition(position.x + direction.x, position.y + direction.y);
    }

    public float getX(){
        return position.x;
    }

    public void reverseAbscissaDirection(){
        direction.x = -direction.x;
    }

    public void reverseOrdinateDirection(){
        direction.y = -direction.y;
    }

    public float getY(){
        return position.y;
    }

    public Circle getPosition() {
        return new Circle(position);
    }

    public float getAbscissaDirection(){
        return  direction.x;
    }

    public float getRadius() {
        return position.radius;
    }

}
