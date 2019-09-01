package singtel.irshadillias.service.interview.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import singtel.irshadillias.service.interview.data.local.dao.MovieDao;
import singtel.irshadillias.service.interview.data.local.entity.SingtelServiceEntity;

/**
 * File Description
 * Author: irshad illias
 * Email: irshadillias@gmail.com
 */
@Database(entities = {SingtelServiceEntity.class}, version = 3)
public abstract class MovieDatabase extends RoomDatabase {
    public abstract MovieDao articleDao();
}