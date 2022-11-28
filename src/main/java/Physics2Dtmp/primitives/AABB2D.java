package Physics2Dtmp.primitives;

import Physics2Dtmp.rigidbody.Rigidbody2D;
import org.joml.Vector2f;

//Axis aligned bounding box
public class AABB2D {

    private Vector2f size = new Vector2f();
    private Rigidbody2D rigidbody = null;
    private Vector2f halfSize = new Vector2f();

    public AABB2D(){
        this.halfSize = new Vector2f(size.mul(0.5f));
    }

    public AABB2D(Vector2f min, Vector2f max){
        this.size = new Vector2f(max).sub(min);
        this.halfSize = new Vector2f(size.mul(0.5f));

    }

    public Vector2f getMin(){
        return new Vector2f(this.rigidbody.getPosition()).sub(this.halfSize);
    }

    public Vector2f getMax(){
        return new Vector2f(this.rigidbody.getPosition()).add(this.halfSize);
    }


    public Vector2f getSize() {
        return size;
    }

    public void setSize(Vector2f size) {
        this.size = size;
    }
}
