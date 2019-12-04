package com.aleksiprograms.survivalofkeijo.toolbox;

public class BodyDef {

    public float mass;
    public float friction;
    public float restitution;
    public float linearDamping;
    public float angularDamping;
    public short categoryBits;
    public short maskBits;
    public boolean useMotionState;
    public boolean lockAxisZ;

    private BodyDef(BodyDefBuilder builder) {
        mass = builder.mass;
        friction = builder.friction;
        restitution = builder.restitution;
        linearDamping = builder.linearDamping;
        angularDamping = builder.angularDamping;
        categoryBits = builder.categoryBits;
        maskBits = builder.maskBits;
        useMotionState = builder.useMotionState;
        lockAxisZ = builder.lockAxisZ;
    }

    public static class BodyDefBuilder {
        private float mass;
        private float friction;
        private float restitution;
        private float linearDamping;
        private float angularDamping;
        private short categoryBits;
        private short maskBits;
        private boolean useMotionState;
        private boolean lockAxisZ;

        public BodyDefBuilder() {
            mass = 1f;
            friction = 0f;
            restitution = 0f;
            linearDamping = 0f;
            angularDamping = 0f;
            categoryBits = 0;
            maskBits = 0;
            useMotionState = true;
            lockAxisZ = true;
        }

        public BodyDefBuilder mass(float mass) {
            this.mass = mass;
            return this;
        }

        public BodyDefBuilder friction(float friction) {
            this.friction = friction;
            return this;
        }

        public BodyDefBuilder restitution(float restitution) {
            this.restitution = restitution;
            return this;
        }

        public BodyDefBuilder linearDamping(float linearDamping) {
            this.linearDamping = linearDamping;
            return this;
        }

        public BodyDefBuilder angularDamping(float angularDamping) {
            this.angularDamping = angularDamping;
            return this;
        }

        public BodyDefBuilder categoryBits(short categoryBits) {
            this.categoryBits = categoryBits;
            return this;
        }

        public BodyDefBuilder maskBits(short maskBits) {
            this.maskBits = maskBits;
            return this;
        }

        public BodyDefBuilder useMotionState(boolean useMotionState) {
            this.useMotionState = useMotionState;
            return this;
        }

        public BodyDefBuilder lockAxisZ(boolean lockAxisZ) {
            this.lockAxisZ = lockAxisZ;
            return this;
        }

        public BodyDef build() {
            return new BodyDef(this);
        }
    }
}