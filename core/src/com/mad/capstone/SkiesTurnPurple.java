package com.mad.capstone;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mad.capstone.screen.MainScreen;

public class SkiesTurnPurple extends Game {
	public static final float PPM = 100;
	public static final float V_WIDTH = 480/PPM;
	public static final float V_HEIGHT = 320/PPM;

	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new MainScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
