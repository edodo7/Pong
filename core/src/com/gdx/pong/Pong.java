package com.gdx.pong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class Pong extends ApplicationAdapter {
	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;
	private Vector2 direction;
	private Vector2 position;
	private Music ballOutside;
	private Music hitPaddle;
	private Music hitWall;

	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false);
		shapeRenderer = new ShapeRenderer();
		direction = new Vector2(5f,5f);
		position = new Vector2(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
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
		shapeRenderer.circle(position.x,position.y, 20);
		shapeRenderer.end();
		if(position.x >= Gdx.graphics.getWidth() || position.x <= 0) {
			hitWall.play();
			direction.x = -direction.x;
		}
		if (position.y >= Gdx.graphics.getHeight() || position.y <= 0 ) {
			hitWall.play();
			direction.y = -direction.y;
		}
		position.add(direction);
	}
	
	@Override
	public void dispose () {

	}
}
