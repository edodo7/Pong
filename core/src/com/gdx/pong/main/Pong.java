package com.gdx.pong.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.gdx.pong.Ball;
import com.gdx.pong.players.AIPlayer;
import com.gdx.pong.players.AbstractPlayer;
import com.gdx.pong.players.HumanPlayer;

import java.util.Random;

public class Pong extends ApplicationAdapter {
	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;
	private ShapeRenderer dottedLineRenderer;
	private Music ballOutside;
	private Music hitPaddle;
	private Music hitWall;
	private AbstractPlayer leftPlayer;
	private AbstractPlayer rightPlayer;
	private Ball ball;

	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false);
		shapeRenderer = new ShapeRenderer();
		dottedLineRenderer = new ShapeRenderer();
		leftPlayer = new HumanPlayer(true);
		rightPlayer = new AIPlayer(false);
		ball = new Ball();
		ball.chooseRandomDirection();
		ballOutside = Gdx.audio.newMusic(Gdx.files.internal("ballOutside.mp3"));
		hitPaddle = Gdx.audio.newMusic(Gdx.files.internal("hitPaddle.wav"));
		hitWall = Gdx.audio.newMusic(Gdx.files.internal("hitWall.wav"));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(255,255,255,1);
		drawDottedLine(dottedLineRenderer, 20);
		shapeRenderer.circle(ball.getX(),ball.getY(), ball.BALL_RADIUS);
		shapeRenderer.rect(leftPlayer.getX(),leftPlayer.getY(),leftPlayer.PADDLE_WIDTH,leftPlayer.PADDLE_HEIGHT);
		shapeRenderer.rect(rightPlayer.getX(),rightPlayer.getY(),rightPlayer.PADDLE_WIDTH,rightPlayer.PADDLE_HEIGHT);
		shapeRenderer.end();
		if(ball.getX()>= Gdx.graphics.getWidth() || ball.getX() <= 0) {
			hitWall.play();
			ball.reverseXDirection();
		}
		if (ball.getY() >= Gdx.graphics.getHeight() || ball.getY() <= 0 ) {
			hitWall.play();
			ball.reverseYDirection();
		}
		leftPlayer.move();
		rightPlayer.move();
		if (( ball.getX() + 40 ) < leftPlayer.getX()  ){
			ballOutside.play();
			ball.resetPosition();
			ball.chooseRandomDirection();
		}
		if(leftPlayer.isCollisonDetected(ball)){
			hitPaddle.play();
			ball.reverseXDirection();
		}
		ball.move();
	}





	private void drawDottedLine(ShapeRenderer shapeRenderer, int dotDist) {
		shapeRenderer.begin(ShapeType.Line);
		for(float i = 0; i < Gdx.graphics.getHeight(); i += dotDist + 15) {
			shapeRenderer.line(Gdx.graphics.getWidth()/2,i,Gdx.graphics.getWidth()/2,i + 15);
		}
		shapeRenderer.end();
	}

	@Override
	public void dispose () {
		shapeRenderer.dispose();
		ballOutside.dispose();
		hitWall.dispose();
		hitPaddle.dispose();
	}
}
