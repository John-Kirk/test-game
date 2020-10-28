package com.jdkd.test;

import com.artemis.World;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector2;
import com.jdkd.test.ecs.ECSInitialiser;
import com.jdkd.test.ecs.component.ComponentFactory;
import com.jdkd.test.ecs.component.Position3D;
import com.jdkd.test.ecs.component.Rotation3D;
import com.jdkd.test.ecs.component.Sprite;
import com.jdkd.test.util.UnitConverter;

public class TestGame extends ApplicationAdapter {

	private SpriteBatch batch;
	private World world;
	private ComponentFactory componentFactory;
	private IsoViewCamera camera;

	@Override
	public void create () {
		com.badlogic.gdx.physics.box2d.World box2D = new com.badlogic.gdx.physics.box2d.World(new Vector2(0, 0), true);
		UnitConverter unitConverter = new UnitConverter(100);
		componentFactory = new ComponentFactory(box2D, unitConverter);
		batch = new SpriteBatch();

		ECSInitialiser ecsInitialiser = new ECSInitialiser(batch, Texture::new, unitConverter);

		camera = new IsoViewCamera(10, 10 * (Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth()));
		camera.position.set(10f, 10f, 10f);
		camera.lookAt(0,0,0);
		camera.near = 1f;
		camera.far = 300f;
		camera.update();

		InputHandler inputHandler = new InputHandler(camera);

		world = ecsInitialiser.init(box2D, camera, inputHandler);
//		world.createEntity().edit()
//			.add(componentFactory.createSprite("badlogic.jpg", 256, 256))
//			.add(componentFactory.createMovable())
//			.add(componentFactory.createBody(0, 0, 256, 256, false));
//
//		world.createEntity().edit()
//				.add(componentFactory.createSprite("badlogic.jpg", 128, 256))
//				.add(componentFactory.createBody(500, 500, 128, 256, true));

		for (int z = 0; z < 10; z += 2) {
			for (int x = 0; x < 10; x += 2) {
				world.createEntity().edit()
				.add(new Position3D(x, 0, z))
				.add(new Rotation3D(90, 0, 0))
				.add(new Sprite("floor-tile.png", 2, 2));
			}

			world.createEntity().edit()
					.add(new Position3D(z, 1, -1))
					.add(new Rotation3D(0, 0, 0))
					.add(new Sprite("brick-wall.png", 2, 2));

			world.createEntity().edit()
					.add(new Position3D(-1, 1, z))
					.add(new Rotation3D(0, 90, 0))
					.add(new Sprite("brick-wall.png", 2, 2));

			world.createEntity().edit()
					.add(new Position3D(z, 1, 9))
					.add(new Rotation3D(0, 0, 0))
					.add(new Sprite("brick-wall.png", 2, 2));

			world.createEntity().edit()
					.add(new Position3D(9, 1, z))
					.add(new Rotation3D(0, 90, 0))
					.add(new Sprite("brick-wall.png", 2, 2));
		}

		Gdx.input.setInputProcessor(inputHandler);
	}

	@Override
	public void render () {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT | GL30.GL_DEPTH_BUFFER_BIT);
		camera.update();
		batch.begin();
		world.process();
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
