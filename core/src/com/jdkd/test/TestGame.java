package com.jdkd.test;

import com.artemis.World;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.jdkd.test.ecs.ECSInitialiser;
import com.jdkd.test.ecs.component.ComponentFactory;
import com.jdkd.test.util.UnitConverter;

public class TestGame extends ApplicationAdapter {

	private SpriteBatch batch;
	private World world;
	private ComponentFactory componentFactory;

	@Override
	public void create () {
		com.badlogic.gdx.physics.box2d.World box2D = new com.badlogic.gdx.physics.box2d.World(new Vector2(0, 0), true);
		UnitConverter unitConverter = new UnitConverter(100);
		componentFactory = new ComponentFactory(box2D, unitConverter);
		batch = new SpriteBatch();

		ECSInitialiser ecsInitialiser = new ECSInitialiser(batch, Texture::new, unitConverter);

		world = ecsInitialiser.init(box2D);
		world.createEntity().edit()
			.add(componentFactory.createSprite("badlogic.jpg", 256, 256))
			.add(componentFactory.createMovable())
			.add(componentFactory.createBody(0, 0, 256, 256, false));

		world.createEntity().edit()
				.add(componentFactory.createSprite("badlogic.jpg", 128, 256))
				.add(componentFactory.createBody(500, 500, 128, 256, true));
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
