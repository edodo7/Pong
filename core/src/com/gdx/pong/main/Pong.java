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
import com.gdx.pong.players.AbstractPlayer;
import com.gdx.pong.players.HumanPlayer;

public class Pong extends ApplicationAdapter {
	private static OrthographicCamera camera;
	private static ShapeRenderer shapeRenderer;
	private static ShapeRenderer dottedLineRenderer;
	private static Music ballOutside;
	private static Music hitPaddle;
	private static Music hitWall;
	private static AbstractPlayer leftPlayer;
	private static int leftPlayerPoints;
	private static AbstractPlayer rightPlayer;
	private static int rightPLayerPoints;
	private static Ball ball;
	private static BitmapFont font;
	private static SpriteBatch batch;

	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false);
		shapeRenderer = new ShapeRenderer();
		dottedLineRenderer = new ShapeRenderer();
		ball = new Ball();
		leftPlayer = new HumanPlayer(true);
		rightPlayer = new AIPlayer(false);
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
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(255,255,255,1);
		drawDottedLine(dottedLineRenderer, 20);
		shapeRenderer.circle(ball.getX(),ball.getY(), ball.RADIUS);
		shapeRenderer.rect(leftPlayer.getX(),leftPlayer.getY(),leftPlayer.WIDTH,leftPlayer.HEIGHT);
		shapeRenderer.rect(rightPlayer.getX(),rightPlayer.getY(),rightPlayer.WIDTH,rightPlayer.HEIGHT);
		shapeRenderer.end();
		batch.begin();

		font.setColor(255,255,255,1);
		font.draw(batch,leftPlayerPoints + "",650,750);
		font.draw(batch,rightPLayerPoints + "", 800,750);
		batch.end();
		if(ball.getX() >= Gdx.graphics.getWidth() || ball.getX() <= 0) {
			hitWall.play();
			ball.reverseXDirection();
		}
		if (ball.getY() >= Gdx.graphics.getHeight() || ball.getY() <= 0 ) {
			hitWall.play();
			ball.reverseYDirection();
		}
		leftPlayer.move();
		rightPlayer.move();
		if (leftPlayer.isBallBehind(ball)){
			ballMissed();
			rightPLayerPoints++;
		}
		if(rightPlayer.isBallBehind(ball)){
			ballMissed();
			leftPlayerPoints++;
		}
		if(leftPlayer.isCollisonDetected(ball) || rightPlayer.isCollisonDetected(ball)){
			hitPaddle.play();
			ball.reverseXDirection();
		}
		ball.move();
	}

	private void ballMissed() {
		ballOutside.play();
		ball.resetPosition();
		ball.chooseRandomDirection();
	}


	private void drawDottedLine(ShapeRenderer shapeRenderer, int dotDist) {
		shapeRenderer.begin(ShapeType.Line);
		for(float i = 0; i < Gdx.graphics.getHeight(); i += dotDist + 15) {
			shapeRenderer.line(Gdx.graphics.getWidth()/2,i,Gdx.graphics.getWidth()/2,i + 15);
		}
		shapeRenderer.end();
	}

	public static Ball getBall(){
		return ball;
	}

	@Override
	public void dispose () {
		shapeRenderer.dispose();
		ballOutside.dispose();
		hitWall.dispose();
		hitPaddle.dispose();
	}
}
