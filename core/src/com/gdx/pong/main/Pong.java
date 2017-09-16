package com.gdx.pong.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.gdx.pong.Ball;
import com.gdx.pong.players.AIPlayer;
import com.gdx.pong.players.HumanPlayer;
import com.gdx.pong.players.Player;

public class Pong extends ApplicationAdapter {
    private static OrthographicCamera camera;
    private static ShapeRenderer shapeRenderer;
    private static ShapeRenderer dottedLineRenderer;
    private static Music ballOutside;
    private static Music hitPaddle;
    private static Music hitWall;
    private static Player leftPlayer;
    private static int leftPlayerPoints;
    private static Player rightPlayer;
    private static int rightPLayerPoints;
    private static Ball ball;
    private static BitmapFont font;
    private static SpriteBatch batch;

    public static Ball getBall() { return ball; }

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        shapeRenderer = new ShapeRenderer();
        dottedLineRenderer = new ShapeRenderer();
        ball = new Ball();
        leftPlayer = new AIPlayer();
        rightPlayer = new HumanPlayer();
        ball.chooseRandomDirection();
        ballOutside = Gdx.audio.newMusic(Gdx.files.internal("ballOutside.mp3"));
        hitPaddle = Gdx.audio.newMusic(Gdx.files.internal("hitPaddle.wav"));
        hitWall = Gdx.audio.newMusic(Gdx.files.internal("hitWall.wav"));
        leftPlayerPoints = 0;
        rightPLayerPoints = 0;
        font = new BitmapFont();
        batch = new SpriteBatch();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(255, 255, 255, 1);
        drawDottedLine(dottedLineRenderer, 20);
        shapeRenderer.circle(ball.getX(), ball.getY(), ball.getRadius());
        shapeRenderer.rect(leftPlayer.getX(), leftPlayer.getY(), leftPlayer.getWidth(), leftPlayer.getHeight());
        shapeRenderer.rect(rightPlayer.getX(), rightPlayer.getY(), rightPlayer.getWidth(), rightPlayer.getHeight());
        shapeRenderer.end();
        batch.begin();

        font.setColor(255, 255, 255, 1);
        font.draw(batch, leftPlayerPoints + "", (Gdx.graphics.getWidth()/2) - 100, Gdx.graphics.getHeight() - 100);
        font.draw(batch, rightPLayerPoints + "", (Gdx.graphics.getWidth()/2) + 100, Gdx.graphics.getHeight() - 100);
        batch.end();
        if (isBallHitRightEdgeOfScreen() || isBallHitLeftEgdeOfScreen()) {
            hitWall.play();
            ball.reverseAbscissaDirection();
        }
        if (isBallHitUpEdgeOfScreen() || isBallHitDownEdgeOfScreen()) {
            hitWall.play();
            ball.reverseOrdinateDirection();
        }
        leftPlayer.move();
        rightPlayer.move();
        if (leftPlayer.isBallBehind(ball)) {
            ballMissed();
            rightPLayerPoints++;
        }
        if (rightPlayer.isBallBehind(ball)) {
            ballMissed();
            leftPlayerPoints++;
        }
        if (leftPlayer.isCollisonDetected(ball) || rightPlayer.isCollisonDetected(ball)) {
            hitPaddle.play();
            ball.reverseAbscissaDirection();
        }
        ball.move();
    }

    private boolean isBallHitLeftEgdeOfScreen() { return ball.getX() <= 0; }

    private boolean isBallHitRightEdgeOfScreen() { return ball.getX() >= Gdx.graphics.getWidth(); }

    private boolean isBallHitUpEdgeOfScreen() { return ball.getY() >= Gdx.graphics.getHeight(); }

    private boolean isBallHitDownEdgeOfScreen() { return ball.getY() <= 0; }

    private void ballMissed() {
        ballOutside.play();
        ball.resetPosition();
        ball.chooseRandomDirection();
    }

    private void drawDottedLine(ShapeRenderer shapeRenderer, int dotDist) {
        shapeRenderer.begin(ShapeType.Line);
        for (float i = 0; i < Gdx.graphics.getHeight(); i += dotDist + 15) {
            shapeRenderer.line(Gdx.graphics.getWidth() / 2, i, Gdx.graphics.getWidth() / 2, i + 15);
        }
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        ballOutside.dispose();
        hitWall.dispose();
        hitPaddle.dispose();
        dottedLineRenderer.dispose();
        font.dispose();
        batch.dispose();
    }
}
