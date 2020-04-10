package stealmysheep.collision;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stealmysheep.common.assets.Entity;
import stealmysheep.common.game.GameData;
import stealmysheep.common.game.World;

import java.util.Collection;
import java.util.Collections;

public class CollisionTests {

    private World world = null;
    private GameData gameData = new GameData();
    private CollisionPlugin plugin = new CollisionPlugin();

    @BeforeEach
    public void setup() {
        world = new World();
    }

    @Test
    public void testNoEntities() {
        plugin.postUpdate(gameData, world);
    }

    @Test
    public void testNoCollide() {
        MockEntity a = mock(1, 1);
        MockEntity b = mock(1, 3);
        MockEntity c = mock(3, 1);

        plugin.postUpdate(gameData, world);

        assertProperties(a, Collections.emptyList(), false, false, false, false);
        assertProperties(b, Collections.emptyList(), false, false, false, false);
        assertProperties(c, Collections.emptyList(), false, false, false, false);
    }

    @Test
    public void testCollides() {
        MockEntity a = mock(1, 1);
        MockEntity b = mock(2, 1);

        plugin.postUpdate(gameData, world);

        assertProperties(a, Collections.singleton(b), false, true, false, false);
        assertProperties(b, Collections.singleton(a), false, false, false, true);
    }

    /**
     * @return a 1x1 entity with the given position
     */
    private MockEntity mock(float x, float y) {
        MockEntity mock = new MockEntity(x, y);
        world.addEntity(mock);
        return mock;
    }

    private void assertProperties(MockEntity mock, Collection<Entity> collides,
                                  boolean top, boolean right, boolean bottom, boolean left) {
        Object[] expected = {collides.toArray(), top, right, bottom, left};
        Object[] actual = {
                mock.box.getCollidesWith().toArray(),
                mock.box.collidesTop(),
                mock.box.collidesRight(),
                mock.box.collidesBottom(),
                mock.box.collidesLeft()
        };

        Assertions.assertArrayEquals(expected, actual);
    }
}
