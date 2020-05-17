import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stealmysheep.common.assets.Enemy;
import stealmysheep.common.assets.Player;
import stealmysheep.common.assets.Sheep;
import stealmysheep.common.assets.entityComponents.BoxCollider;
import stealmysheep.common.assets.entityComponents.Position;
import stealmysheep.common.game.GameData;
import stealmysheep.common.game.World;
import stealmysheep.enemy.EnemyControlSystem;

public class EnemyTest {

    private World world;
    private EnemyControlSystem enemyController = new EnemyControlSystem();
    private GameData gameData = new GameData();

    @BeforeEach
    public void setUp() {
        this.world = new World();
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void enemySheepTest() {
        Enemy enemy = createEnemy(false, true, 0, 10, 10);
        Sheep sheep = createSheep();
        this.world.addEntity(enemy);
        this.world.addEntity(sheep);
        this.enemyController.update(this.gameData, this.world);

        Assertions.assertEquals(sheep, enemy.getTarget());

    }

    @Test
    public void enemySwitchTargetTest() {
        Enemy enemy = createEnemy(true, true, 100, 10, 10);
        Sheep sheep = createSheep();
        Player player = createPlayer(150, 10);
        this.world.addEntity(enemy);
        this.world.addEntity(sheep);
        this.world.addEntity(player);

        this.enemyController.update(this.gameData, this.world);
        Assertions.assertEquals(sheep, enemy.getTarget());

        Position playerPosition = player.getComponent(Position.class);
        playerPosition.setX(50);

        this.enemyController.update(this.gameData, this.world);
        Assertions.assertEquals(player, enemy.getTarget());
    }

    private Enemy createEnemy(boolean targetPlayer, boolean targetSheep, float targetRadius, float x, float y) {
        float radians = 3.1415f / 2;
        float height = 70;
        float width = 30;

        Enemy thief = new Enemy(targetPlayer, targetSheep, targetRadius, "thief.png");
        thief.addComponent(new Position(x, y, radians));
        thief.addComponent(new BoxCollider(height, width));
        return thief;
    }

    private Sheep createSheep() {
        float x = 0;
        float y = 0;
        float radians = 3.1415f / 2;
        float radius = 125;

        Sheep sheep = new Sheep("sheep.png", radius);
        sheep.addComponent(new Position(x, y, radians));

        return sheep;
    }

    private Player createPlayer(float x, float y) {
        float radians = 3.1415f / 2;

        Player player = new Player("player.png");
        player.addComponent(new Position(x, y, radians));

        return player;
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
