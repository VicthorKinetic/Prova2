package models;
import play.api.db.Database;
import scala.collection.mutable.MutableList;

object GamesDAO{
    
    def create(db: Database, jog: Games): Unit = {
        db.withConnection{ conn =>
            val ps = conn.prepareStatement("insert into games(nome,desenvolvedor,genero,plataforma,ano) values (?,?,?,?,?)")
            ps.setString(1,jog.nome)
            ps.setString(2,jog.desenvolvedor)
            ps.setString(3,jog.genero)
            ps.setString(4,jog.plataforma)
            ps.setInt(5,jog.ano)
            ps.execute()
        }
    }
}