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
	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;
	private ShapeRenderer dottedLineRenderer;
	private Music ballOutside;
	private Music hitPaddle;
	private Music hitWall;
	private AbstractPlayer leftPlayer;
	private int leftPlayerPoints;
	private AbstractPlayer rightPlayer;
	private int rightPLayerPoints;
	private Ball ball;
	private BitmapFont font;
	private SpriteBatch batch;

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
			ballOutside.play();
			ball.resetPosition();
			ball.chooseRandomDirection();
			rightPLayerPoints++;
		}
		if(rightPlayer.isBallBehind(ball)){
			ballOutside.play();
			ball.resetPosition();
			ball.chooseRandomDirection();
			leftPlayerPoints++;
		}
		if(leftPlayer.isCollisonDetected(ball)){
			hitPaddle.play();
			ball.reverseXDirection();
		}
		if(rightPlayer.isCollisonDetected(ball)){
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
