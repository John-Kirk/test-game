package com.jdkd.test;

import com.artemis.World;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jdkd.test.ecs.ECSInitialiser;
import com.jdkd.test.ecs.component.Body;
import com.jdkd.test.ecs.component.Movable;
import com.jdkd.test.ecs.component.Position;
import com.jdkd.test.ecs.component.TextureReference;
import com.jdkd.test.gfx.TextureLoader;

public class TestGame extends ApplicationAdapter {

	private SpriteBatch batch;
	private World world;

	@Override
	public void create () {
		batch = new SpriteBatch();
		ECSInitialiser ecsInitialiser = new ECSInitialiser(batch, textureReference -> new Texture(textureReference));

		world = ecsInitialiser.init();
		world.createEntity().edit()
			.add(new Position(0, 0))
			.add(new TextureReference("badlogic.jpg"))
			.add(new Body(64, 64))
			.add(new Movable());
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		world.process();
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
