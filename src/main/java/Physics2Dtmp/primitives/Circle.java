package Physics2Dtmp.primitives;

import Physics2Dtmp.rigidbody.Rigidbody2D;
import org.joml.Vector2f;

public class Circle {
    private float radius = 1.0f;
    private Rigidbody2D body = null;


    public float getRadius(){
        return  this.radius;
    }

    public Vector2f getCenter() {
        return body.getPosition();
    }

}
