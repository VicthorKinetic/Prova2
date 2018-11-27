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
    
    def createUsu(db: Database, usu: GamesUsu): Unit = {
        db.withConnection{ conn =>
            val ps = conn.prepareStatement("insert into usuario(nome,email,senha) values (?,?,?)")
            ps.setString(1,usu.nome)
            ps.setString(2,usu.email)
            ps.setString(3,usu.senha)
            ps.execute()
        }
    }
    
    def update(db: Database, jog: Update): Unit = {
        db.withConnection{ conn =>
            val ps = conn.prepareStatement("update games set nome= ?, desenvolvedor= ?, genero= ?, plataforma= ?, ano= ? where id= ?")
            ps.setString(1,jog.nome)
            ps.setString(2,jog.desenvolvedor)
            ps.setString(3,jog.genero)
            ps.setString(4,jog.plataforma)
            ps.setInt(5,jog.ano)
            ps.setInt(6,jog.id)
            ps.execute()
        }
    }
    
    def updateUsu(db: Database, usu: UpdateUsu): Unit = {
        db.withConnection{ conn =>
            val ps = conn.prepareStatement("update usuario set nome= ?, email= ?, senha= ? where id= ?")
            ps.setString(1,usu.nome)
            ps.setString(2,usu.email)
            ps.setString(3,usu.senha)
            ps.setInt(4,usu.id)
            ps.execute()
        }
    }
    
    def delete(db: Database, jog: Delete): Unit = {
        db.withConnection{ conn =>
            val ps = conn.prepareStatement("delete from games where id= ?")
            ps.setInt(1,jog.id)
            ps.execute()
        }
    }
    
    def deleteUsu(db: Database, usu: Delete): Unit = {
        db.withConnection{ conn =>
            val ps = conn.prepareStatement("delete from usuario where id= ?")
            ps.setInt(1,usu.id)
            ps.execute()
        }
    }
    
    def getGame(db: Database, id: Int): Games = {
        db.withConnection{conn =>
            val ps = conn.prepareStatement("select * from games where id=?")
            ps.setInt(1,id)
            val res = ps.executeQuery()
            if(res.next())
                Games(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getInt(6))
            else
                Games(0,"","","","",0)
        }
    }
    
    def autenticar (db: Database, login: Login): Boolean = {
        db.withConnection{ conn =>
            val ps = conn.prepareStatement("select * from usuario where email= ? and senha= ?")
            ps.setString(1,login.email)
            ps.setString(2,login.senha)
            val rs = ps.executeQuery()
            rs.next()
        }
    }
}