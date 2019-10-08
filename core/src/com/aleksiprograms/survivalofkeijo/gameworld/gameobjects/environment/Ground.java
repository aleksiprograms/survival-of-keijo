package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment;

        import com.aleksiprograms.survivalofkeijo.TheGame;
        import com.badlogic.gdx.graphics.g3d.ModelInstance;
        import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;

public abstract class Ground extends SolidObject {

    public Ground(TheGame game, ModelInstance modelInstance, btCollisionShape shape) {
        super(game, modelInstance, shape);
        rigidBody.userData = this;
    }
}