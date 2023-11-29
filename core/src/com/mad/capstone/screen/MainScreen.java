package com.mad.capstone.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mad.capstone.SkiesTurnPurple;
import com.mad.capstone.scene.Hud;
import com.mad.capstone.sprite.Thane;
import com.mad.capstone.util.WorldCreator;

public class MainScreen implements Screen {

  private SkiesTurnPurple game;
  private TextureAtlas atlas;

  private OrthographicCamera gameCam;
  private Viewport gamePort;
  private Hud hud;

  // Tiled map
  private TmxMapLoader mapLoader;
  private TiledMap map;
  private OrthogonalTiledMapRenderer renderer;

  // Box2d
  private World world;
  private Box2DDebugRenderer b2dRenderer;
  private Thane player;

  public MainScreen(SkiesTurnPurple game) {
    this.atlas = new TextureAtlas("thane.atlas"); // use asset manager later
    this.game = game;
    gameCam = new OrthographicCamera();
    gamePort = new FitViewport(SkiesTurnPurple.V_WIDTH, SkiesTurnPurple.V_HEIGHT, gameCam);
    hud = new Hud(game.batch);

    mapLoader = new TmxMapLoader();
    map = mapLoader.load("map/map.tmx");
    renderer = new OrthogonalTiledMapRenderer(map, 1 / SkiesTurnPurple.PPM);
    float halfWorldWidth = gamePort.getWorldWidth() / 2;
    float halfWorldHeight = gamePort.getWorldHeight() / 2;
    gameCam.position.set(halfWorldWidth, halfWorldHeight, 0);

    world = new World(new Vector2(0, -9.8f), false);
    b2dRenderer = new Box2DDebugRenderer();

    new WorldCreator(world, map);

    player = new Thane(world, this);

    // temp location

  }

  public TextureAtlas getAtlas() {
    return atlas;
  }

  @Override
  public void show() {

  }

  private void handleInput(float dt) {
    int horizontalForce = 0;
    if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && player.isOnGround) {
      player.setOnGroundFalse();
      player.b2Body.applyForceToCenter(0, 200, false);
    }
    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
//      player.b2Body.setLinearVelocity(1, player.b2Body.getAngularVelocity());
//      player.b2Body.applyLinearImpulse(new Vector2(0.05f, 0), player.b2Body.getLocalCenter(), false);
      horizontalForce += 1;
    }
    if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
//      player.b2Body.setLinearVelocity(-1, player.b2Body.getAngularVelocity());
//      player.b2Body.applyLinearImpulse(new Vector2(-0.05f, 0), player.b2Body.getLocalCenter(), false);
      horizontalForce -= 1;
    }

    if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
      horizontalForce = horizontalForce * 2;
    }

    player.b2Body.setLinearVelocity(horizontalForce, player.b2Body.getLinearVelocity().y);

  }

  public void update(float dt) {
    handleInput(dt);
    world.step(1 / 60f, 6, 2);

    player.update(dt);
    gameCam.position.x = player.b2Body.getPosition().x;
    gameCam.position.y = player.b2Body.getPosition().y;
    gameCam.update();
    renderer.setView(gameCam);
  }

  @Override
  public void render(float dt) {
    update(dt);
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    renderer.render();
    b2dRenderer.render(world, gameCam.combined);
//    game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
    game.batch.begin();
    player.draw(game.batch);
    game.batch.end();

    game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
    hud.stage.draw();
  }

  @Override
  public void resize(int width, int height) {
    gamePort.update(width, height);
  }

  @Override
  public void pause() {

  }

  @Override
  public void resume() {

  }

  @Override
  public void hide() {

  }

  @Override
  public void dispose() {
    map.dispose();
    renderer.dispose();
    world.dispose();
    hud.dispose();
    b2dRenderer.dispose();
player.dispose();
  }


}