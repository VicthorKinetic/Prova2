package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.db.Database
import scala.collection.mutable.MutableList
import models.Games
import models.GamesDAO
import play.api.data._
import play.api.data.Forms._
import play.api.data.format.Formats._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class GamesController @Inject()(db: Database, cc: ControllerComponents) 
  extends AbstractController(cc) with play.api.i18n.I18nSupport {
    
  val form: Form[(String, String, String, String, Int)] = Form (
        mapping(
            "nome" -> text,
            "desenvolvedor" -> text,
            "genero" -> text,
            "plataforma" -> text,
            "ano" -> number
        )(Games.applyGame)(Games.unapplyGame)
    )
    
  def create = Action {implicit request =>
    form.bindFromRequest.fold(
      formWithErrors => {
        println(formWithErrors)
        BadRequest(views.html.games(formWithErrors))
      },
      games => {
        GamesDAO.create(db,Games.criarGame(games._1,games._2,games._3,games._4,games._5))
        Redirect("/games")
      }
    )
  }
  
  def formGames = Action {implicit request =>
    Ok(views.html.games(form))
  }
  
  def info(id: Int) = Action {
    val g = GamesDAO.getGame(db,id)
    Ok(views.html.info(g))
  }
 
    def games = Action {
 
      val list = MutableList[Games]()
      //conn representa a conexao de fato com o bd
      db.withConnection { conn =>
        val stm = conn.createStatement()
        val res = stm.executeQuery("""
        select 
           * 
        from 
           games 
        order by 
            games.nome 
        limit 10""")
        while (res.next()) {
          list.+=(Games(res.getInt(1)
                    ,res.getString(2)
                    ,res.getString(3)
                    ,res.getString(4)
                    ,res.getString(5)
                    ,res.getInt(6)))
        }
      }
    Ok(views.html.listaGames(list))
  }
}
