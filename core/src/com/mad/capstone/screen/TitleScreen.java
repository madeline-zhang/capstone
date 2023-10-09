package com.mad.capstone.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
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
import com.mad.capstone.MyGdxGame;
import com.mad.capstone.scene.Hud;

public class TitleScreen implements Screen {
  private static final int TILE_LENGTH = 20;

  private MyGdxGame game;
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

  public TitleScreen(MyGdxGame game) {
    this.game = game;
    gameCam = new OrthographicCamera();
    gamePort = new FitViewport(MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT, gameCam);
    hud = new Hud(game.batch);

    mapLoader = new TmxMapLoader();
    map = mapLoader.load("realtest/realtest.tmx");
    renderer = new OrthogonalTiledMapRenderer(map);
    float halfWorldWidth = gamePort.getWorldWidth() / 2;
    float halfWorldHeight = gamePort.getWorldHeight() / 2;
    gameCam.position.set(halfWorldWidth, halfWorldHeight, 0);

    world = new World(new Vector2(0, -9.8f), true);
    b2dRenderer = new Box2DDebugRenderer();
    // temp location
    BodyDef bDef = new BodyDef();
    PolygonShape shape = new PolygonShape();
    FixtureDef fDef = new FixtureDef();
    Body body;

    for (MapObject object : map.getLayers().get("object").getObjects()) {
      Shape tempShape;
      //        Rectangle rect = ((RectangleMapObject) object).getRectangle();
//        bDef.type = BodyDef.BodyType.StaticBody;
//        float xPos = rect.getX() + rect.getWidth() / 2;
//        float yPos = rect.getY() + rect.getWidth() / 2;
//        bDef.position.set(xPos, yPos);
//        body = world.createBody(bDef);
//        fDef.shape = shape;
//        body.createFixture(fDef);
//        shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2);
      if (object instanceof PolylineMapObject) {
        tempShape = createPolyline((PolylineMapObject) object);
      } else {
        continue;
      }
      bDef.type = BodyDef.BodyType.StaticBody;
      body = world.createBody(bDef);
      body.createFixture(tempShape, 1.0f);
      shape.dispose();
    }
  }

  @Override
  public void show() {

  }

  private void handleInput(float dt) {
    if (Gdx.input.isTouched()) {
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
    b2dRenderer.render(world, gameCam.combined);

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

  private static ChainShape createPolyline(PolylineMapObject polyline) {
    float[] vertices = polyline.getPolyline().getTransformedVertices();
    Vector2[] worldVertices = new Vector2[vertices.length/2];

    for(int i = 0; i<worldVertices.length; i++) {
      worldVertices[i] = new Vector2(vertices[i*2], vertices[i*2+1]);
    }

    ChainShape cs = new ChainShape();
    cs.createChain(worldVertices);
    return cs;
  }
}
