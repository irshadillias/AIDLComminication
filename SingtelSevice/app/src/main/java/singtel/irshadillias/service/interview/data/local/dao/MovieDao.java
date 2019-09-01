package singtel.irshadillias.service.interview.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import singtel.irshadillias.service.interview.data.local.entity.SingtelServiceEntity;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM singtel LIMIT 10")
    LiveData<List<SingtelServiceEntity>> loadPopularMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveArticles(List<SingtelServiceEntity> movieEntities);

}

