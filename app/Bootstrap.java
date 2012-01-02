import models.Cell;
import models.EstateCell;
import models.Game;
import models.Player;
import models.Role;
import models.Player.Serializer;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import util.RichUtil;


/**
 * Initialize game data.
 * 
 * @author GuoLin
 *
 */
@OnApplicationStart
public class Bootstrap extends Job {

    @Override
    public void doJob() throws Exception {
        // Initialize roles 
        initJsonSerializer();
        new Role("蜗总", "role_wo.png").save();
        new Role("莎莎", "role_shasha.png").save();
        new Role("指甲", "role_lion.png").save();
        new Role("孙小结", "role_sunger1.png").save();
        new Role("xx", "role_xx.png").save();
        new Role("萌萌", "role_rabbit.png").save();
        new Role("安仔", "role_360.png").save();
    }

    private void initJsonSerializer() {
        RichUtil.builder.registerTypeAdapter(Player.class, new Game.Serializer());
        RichUtil.builder.registerTypeAdapter(Player.class, new Player.Serializer());
        RichUtil.builder.registerTypeAdapter(Player.class, new Cell.Serializer());
        RichUtil.builder.registerTypeAdapter(Player.class, new EstateCell.Serializer());
    }
}
