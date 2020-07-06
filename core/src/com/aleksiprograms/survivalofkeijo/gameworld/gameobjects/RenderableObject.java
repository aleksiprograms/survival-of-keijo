package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.utils.Pool;

public abstract class RenderableObject extends ModelInstance implements Pool.Poolable {

    protected TheGame game;
    protected Vector3 objectPosition;
    protected Quaternion objectQuaternion;
    protected Vector3 objectScale;
    protected Matrix4 objectTransform;
    protected Vector3 center;
    protected Vector3 dimensions;
    protected BoundingBox boundingBox;
    protected boolean free;

    public RenderableObject(
            TheGame game,
            ModelInstance modelInstance) {

        super(modelInstance);

        this.game = game;
        objectPosition = new Vector3();
        objectQuaternion = new Quaternion();
        objectScale = new Vector3(1, 1, 1);
        objectTransform = new Matrix4();
        center = new Vector3();
        dimensions = new Vector3();
        boundingBox = new BoundingBox();
        calculateBoundingBox(boundingBox);
        boundingBox.getCenter(center);
        boundingBox.getDimensions(dimensions);
        free = false;
        for (Material material : materials) {
            material.remove(ColorAttribute.Emissive);
        }
    }

    public void init() {
    }

    public void update(float deltaTime) {
    }

    @Override
    public void reset() {
        free = false;
    }

    public Vector3 getCenter() {
        return center;
    }

    public Vector3 getDimensions() {
        return dimensions;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }
}