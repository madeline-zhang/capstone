package com.mad.capstone.util;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.mad.capstone.SkiesTurnPurple;

public class WorldCreator {
  public WorldCreator(World world, TiledMap map) {
    BodyDef bDef = new BodyDef();
    PolygonShape shape = new PolygonShape();
    FixtureDef fDef = new FixtureDef();
    Body body;

    for (MapObject object : map.getLayers().get("object").getObjects()) {
      Shape tempShape;
      if (object instanceof PolylineMapObject) {
        tempShape = createPolyline((PolylineMapObject) object);
      } else {
        continue;
      }
      bDef.type = BodyDef.BodyType.StaticBody;
      body = world.createBody(bDef);
      body.createFixture(tempShape, 1.0f).setUserData("ground");
    }
    shape.dispose();
  }

  private static ChainShape createPolyline(PolylineMapObject polyline) {
    float[] vertices = polyline.getPolyline().getTransformedVertices();
    Vector2[] worldVertices = new Vector2[vertices.length / 2];

    for (int i = 0; i < worldVertices.length; i++) {
      worldVertices[i] = new Vector2(vertices[i * 2] / SkiesTurnPurple.PPM, vertices[i * 2 + 1] / SkiesTurnPurple.PPM);
    }

    ChainShape cs = new ChainShape();
    cs.createChain(worldVertices);
    return cs;
  }
}
