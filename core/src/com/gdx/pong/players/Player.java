package com.gdx.pong.players;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.gdx.pong.Ball;


public abstract class Player {
    private static boolean isFirstInstance = true;
    protected final boolean isLeftPlayer;
    protected final float MOVEMENT;
    protected Rectangle position;


    protected Player(float MOVEMENT) {
        int width = 20;
        int height = 100;
        if (isFirstInstance) {
            isLeftPlayer = true;
            isFirstInstance = false;
        } else
            isLeftPlayer = false;
        if (isLeftPlayer)
            position = new Rectangle(150, Gdx.graphics.getHeight() / 2, width, height);
        else
            position = new Rectangle(Gdx.graphics.getWidth() - 150, Gdx.graphics.getHeight() / 2, width, height);
        this.MOVEMENT = MOVEMENT;
    }


    public boolean isCollisonDetected(Ball ball) {
        return isBallOrdinateWithinPaddleHeight(ball) && isBallComingToPlayer(ball) &&
               isBallAbcissaWithinPaddleWidth(ball);
    }

    protected boolean isBallComingToPlayer(Ball ball) {
        return (isLeftPlayer) ? ball.getAbscissaDirection() < 0 : ball.getAbscissaDirection() > 0;
    }

    private boolean isBallAbcissaWithinPaddleWidth(Ball ball) {
        if (isLeftPlayer)
            return ball.getX() <= (position.x + position.width) && ball.getX() > position.x;
        else
            return ball.getX() >= position.x && ball.getX() < (position.x + position.width);

    }

    private boolean isBallOrdinateWithinPaddleHeight(Ball ball) {
        return ball.getY() >= position.y && ball.getY() <= (position.y + position.height);
    }

    public float getX() { return position.x; }

    public float getY() { return position.y; }

    public abstract void move();

    public boolean isBallBehind(Ball ball) {
        int margin = 80;
        if (isLeftPlayer)
            return (ball.getX() + margin) < position.x;
        else
            return (ball.getX() - margin) > position.x;
    }

    protected void moveUp() { position.y += MOVEMENT; }

    protected void moveDown() { position.y -= MOVEMENT; }

    protected boolean isUpEdgeInsideScreen() { return (position.y + getHeight()) < Gdx.graphics.getHeight(); }

    protected boolean isDownEdgeInsideScreen() { return position.y > 0; }

    public float getWidth() { return position.width; }

    public float getHeight() { return position.height; }

}
