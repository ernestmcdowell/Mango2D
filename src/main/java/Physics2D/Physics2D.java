package Physics2D;

import Physics2D.components.Box2DCollider;
import Physics2D.components.CircleCollider;
import Physics2D.components.Rigidbody2D;
import mango.GameObject;
import mango.Transform;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;
import org.joml.Vector2f;



public class Physics2D
{
    private Vec2 gravity = new Vec2(0, -9.81f);
    private World world = new World(gravity);

    private float physicsTime = 0.0f;
    private float physicsTimeStep = 1.0f / 60.0f;

    private int velocityIterations = 8;
    private int positionIterations = 3;

    public void add(GameObject go) {
        Rigidbody2D rb = go.getComponent(Rigidbody2D.class);
        if (rb != null && rb.getRawBody() == null) {
            Transform transform = go.transform;

            BodyDef bodyDef = new BodyDef();
            bodyDef.angle = (float) Math.toRadians(transform.rotation);
            bodyDef.position.set(transform.position.x, transform.position.y);
            bodyDef.angularDamping = rb.getAngularDamping();
            bodyDef.linearDamping = rb.getLinearDamping();
            bodyDef.fixedRotation = rb.isFixedRotation();
            bodyDef.bullet = rb.isContinuousCollisionDetection();

            switch (rb.getBodyType()) {
                case KINEMATIC: bodyDef.type = org.jbox2d.dynamics.BodyType.KINEMATIC; break;
                case STATIC: bodyDef.type = org.jbox2d.dynamics.BodyType.STATIC; break;
                case DYNAMIC: bodyDef.type = org.jbox2d.dynamics.BodyType.DYNAMIC; break;
            }

            PolygonShape shape = new PolygonShape();
            CircleCollider circleCollider = null;
            Box2DCollider box2DCollider = null;
            if ((circleCollider = go.getComponent(CircleCollider.class)) != null) {
                shape.setRadius(circleCollider.getRadius());
                bodyDef.position.set(transform.position.x - circleCollider.getRadius(), transform.position.y - circleCollider.getRadius());
            } else if ((box2DCollider = go.getComponent(Box2DCollider.class)) != null) {
                Vector2f halfSize = new Vector2f(box2DCollider.getHalfSize()).mul(0.5f);
                Vector2f offset = box2DCollider.getOffset();
                Vector2f origin = new Vector2f(box2DCollider.getOrigin());
                shape.setAsBox(halfSize.x, halfSize.y, new Vec2(origin.x, origin.y), 0);

                Vec2 pos = bodyDef.position;
                float xPos = pos.x + offset.x;
                float yPos = pos.y + offset.y;
                bodyDef.position.set(xPos, yPos);
            }

            Body body = this.world.createBody(bodyDef);
            rb.setRawBody(body);
            body.createFixture(shape, rb.getMass());
        }
    }

    public void update(float dt){
        physicsTime += dt;
        if(physicsTime >= 0.0f){
            physicsTime -= physicsTimeStep;
            world.step(physicsTimeStep, velocityIterations, positionIterations);
        }
    }
}
