import models.Role;
import play.jobs.Job;
import play.jobs.OnApplicationStart;


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
        new Role("蜗总", "role_wo.png").save();
        new Role("莎莎", "role_shasha.png").save();
        new Role("孙小结", "role_sunger1.png").save();
    }

}
