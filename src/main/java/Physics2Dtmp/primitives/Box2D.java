package Physics2Dtmp.primitives;

import Physics2Dtmp.rigidbody.Rigidbody2D;
import org.joml.Vector2f;

public class Box2D {

    private Vector2f size = new Vector2f();
    private Vector2f halfSize = new Vector2f();
    private Rigidbody2D rigidbody = null;

    public Box2D() {
        this.halfSize = new Vector2f(size.mul(0.5f));
    }

    public Box2D(Vector2f min, Vector2f max) {
        this.size = new Vector2f(max).sub(min);
        this.halfSize = new Vector2f(size.mul(0.5f));
    }

    public Vector2f getMin() {
        return new Vector2f(this.rigidbody.getPosition()).sub(this.halfSize);
    }

    public Vector2f getMax() {
        return new Vector2f(this.rigidbody.getPosition()).add(this.halfSize);
    }

    public Vector2f[] getVertices() {
        Vector2f min = getMin();
        Vector2f max = getMax();

        Vector2f[] vertices = {
                new Vector2f(min.x, min.y), new Vector2f(min.x, min.y),
                new Vector2f(max.x, min.y), new Vector2f(max.x, max.y)
        };
        if (rigidbody.getRotation() != 0.0f) {
            for (Vector2f vert : vertices) {
                //TODO: implement me
                //Rotates point(Vector2f) about center(Vector2f) by rotation(float in degrees)
                //MMath.rotate(vert, this.rigidbody.getPosition(), this.rigidbody.getRotation());
            }
        }
        return vertices;
    }

    public Rigidbody2D getRigidbody(){
        return this.rigidbody;
    }
}
