package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.db.Database
import scala.collection.mutable.MutableList
import models.GamesDAO
import models.UpdateUsu
import models.Delete
import models.Login
import play.api.data._
import play.api.data.Forms._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class LoginController @Inject()(db: Database, cc: ControllerComponents) 
  extends AbstractController(cc) with play.api.i18n.I18nSupport {
  
  val loginForm: Form[Login] = Form (
        mapping(
            "email" -> text,
            "senha" -> text
    )(Login.apply)(Login.unapply))
    
    val upForm: Form[UpdateUsu] = Form (
        mapping(
            "id" -> number,
            "nome" -> text,
            "email" -> text,
            "senha" -> text,
        )(UpdateUsu.apply)(UpdateUsu.unapply))
    
    val delForm: Form[Delete] = Form (
        mapping(
            "id" -> number
        )(Delete.apply)(Delete.unapply))
  
  def form = Action {implicit request =>
    Ok(views.html.loginForm(loginForm))
  }
  
  def formUp = Action {implicit request =>
    Ok(views.html.usuUp(upForm))
  }
  
  def formDel = Action {implicit request =>
    Ok(views.html.usuDel(delForm))
  }
  
  def auth = Action {implicit request =>
    loginForm.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(views.html.loginForm(formWithErrors))
      },
      login => {
        val estaLogado = GamesDAO.autenticar(db,login)
        if(estaLogado){
           Redirect("/").withSession("games" -> login.email)   
        }else{
           Redirect("/login")
        }
      }
    )
  }
  
  def updateUsu = Action {implicit request =>
    upForm.bindFromRequest.fold(
      formWithErrors => {
        println(formWithErrors)
        BadRequest(views.html.usuUp(formWithErrors))
      },
      updateUsu => {
        GamesDAO.updateUsu(db,updateUsu)
        Redirect("/")
      }
    )
  }
  
  def deleteUsu = Action {implicit request =>
    delForm.bindFromRequest.fold(
      formWithErrors => {
        println(formWithErrors)
        BadRequest(views.html.usuDel(formWithErrors))
      },
      deleteUsu => {
        GamesDAO.deleteUsu(db,deleteUsu)
        Redirect("/")
      }
    )
  }
  
  def logout = Action {implicit request =>
    Redirect("/").withSession(request.session - "games")
  }

  
}
