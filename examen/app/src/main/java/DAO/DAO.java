package DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import Entidades.Alumno;

@Dao
public interface DAO {

    //Altas
    @Insert
    public void insertarAlumno(Alumno alum);

    //Bajas
    @Query("DELETE FROM Alumno WHERE numControl=:nc")
    public void eliminarPorNumControl(String nc);

    //Cambios
    @Query("UPDATE Alumno SET nombre=:x,edad=:y WHERE numControl=:nc")
    public void modificarPorNumControl(String nc,String x,byte y);

    //Consultas
    @Query("SELECT * FROM Alumno")
    public List<Alumno> optenerTodos();

    @Query("SELECT * FROM Alumno WHERE numControl LIKE :n||'%'")
    public List<Alumno>  buscarPorNumControlFiltrado(String n);

    @Query("SELECT * FROM Alumno WHERE numControl=:c")
    public List<Alumno> buscarPorNumControl(String c);

    @Query("UPDATE Alumno SET nombre=:b, primerAp=:c, segundoAp=:d, edad=:e, semestre=:f, carrera=:g WHERE numControl=:a")
    public void actualizarAlumno(String a, String b, String c, String d, byte e, byte f, String g);

}
