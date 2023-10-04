package com.mad.capstone.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mad.capstone.MyGdxGame;
import com.mad.capstone.scene.Hud;

public class TitleScreen implements Screen {
  private MyGdxGame game;
  private OrthographicCamera gameCam;
  private Viewport gamePort;
  private Hud hud;

  // Tiled map
  private TmxMapLoader mapLoader;
  private TiledMap map;
  private OrthogonalTiledMapRenderer renderer;

  // Box2d
//  private Box2DDebugRenderer b2dRenderer;

  public TitleScreen(MyGdxGame game) {
    this.game = game;
//    texture = new Texture("badlogic.jpg");
    gameCam = new OrthographicCamera();
    gamePort = new FitViewport(MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT, gameCam);
    hud = new Hud(game.batch);

    mapLoader = new TmxMapLoader();
    map = mapLoader.load("test map/test map.tmx");
    renderer = new OrthogonalTiledMapRenderer(map);
    float halfWorldWidth = gamePort.getWorldWidth()/2;
    float halfWorldHeight = gamePort.getWorldHeight()/2;
    gameCam.position.set(halfWorldWidth, halfWorldHeight, 0);
  }

  @Override
  public void show() {

  }

  private void handleInput(float dt) {
    if(Gdx.input.isTouched()) {
      gameCam.position.x += 100 * dt;
    }
  }

  public void update(float dt) {
    handleInput(dt);
    gameCam.update();
    renderer.setView(gameCam);
  }

  @Override
  public void render(float dt) {
    update(dt);
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    renderer.render();

    game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
    hud.stage.draw();
//    game.batch.begin();
//    game.batch.draw(texture, 0, 0);
//    game.batch.end();
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

  }
}
