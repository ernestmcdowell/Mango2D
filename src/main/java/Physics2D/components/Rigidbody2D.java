package Physics2D.components;

import Physics2D.enums.BodyType;
import components.Component;
import org.jbox2d.dynamics.Body;
import org.joml.Vector2f;

public class Rigidbody2D extends Component {

    private Vector2f velocity = new Vector2f();
    private float angularDamping = 0.8f;
    private float linearDamping = 0.9f;
    private float mass = 0.0f;
    private BodyType bodyType = BodyType.DYNAMIC;

    private boolean fixedRotation = false;
    private boolean continuousCollisionDetection = true;

    private Body rawBody = null;

    @Override
    public void update(float dt){
        Collider collider = gameObject.getComponent(Collider.class);
        if (rawBody != null && collider != null) {
            this.gameObject.transform.position.set(this.rawBody.getPosition().x - collider.getOffset().x, this.rawBody.getPosition().y - collider.getOffset().y);
            this.gameObject.transform.rotation = (float) Math.toDegrees(this.rawBody.getAngle());
        }
    }

    public Vector2f getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2f velocity) {
        this.velocity = velocity;
    }

    public float getAngularDamping() {
        return angularDamping;
    }

    public void setAngularDamping(float angularDamping) {
        this.angularDamping = angularDamping;
    }

    public float getLinearDamping() {
        return linearDamping;
    }

    public void setLinearDamping(float linearDamping) {
        this.linearDamping = linearDamping;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    public boolean isFixedRotation() {
        return fixedRotation;
    }

    public void setFixedRotation(boolean fixedRotation) {
        this.fixedRotation = fixedRotation;
    }

    public boolean isContinuousCollisionDetection() {
        return continuousCollisionDetection;
    }

    public void setContinuousCollisionDetection(boolean continuousCollisionDetection) {
        this.continuousCollisionDetection = continuousCollisionDetection;
    }

    public Body getRawBody() {
        return rawBody;
    }

    public void setRawBody(Body rawBody) {
        this.rawBody = rawBody;
    }
}
