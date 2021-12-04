package BaseDatos;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import DAO.*;
import Entidades.Alumno;

@Database(entities = {Alumno.class},version = 1,exportSchema = false)

public abstract class BDEscuela extends RoomDatabase {

    public abstract DAO alumnoDAO();

    private static BDEscuela INSTANCE;

    public static BDEscuela gettAppDatabase(Context context){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    BDEscuela.class,"@escuela").build();
        }
        return  INSTANCE;
        }

        public static void destroyInstance(){
        INSTANCE=null;
        }


}


