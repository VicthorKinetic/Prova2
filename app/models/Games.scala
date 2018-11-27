package models

case class Games(id: Int, nome: String, desenvolvedor: String, genero: String, plataforma: String, ano: Int)

case class Login(email: String, senha: String)

case class Update(id:Int, nome: String, desenvolvedor: String, genero: String, plataforma: String, ano: Int)

class GamesUsu(val _id: Int, val _nome: String, val _email: String, val _senha: String){
    def id = _id
    
    def nome = _nome
    
    def email = _email
    
    def senha = _senha
}

object Games{
    
    def criarGameId(id: Int, nome: String, desenvolvedor: String, genero: String, plataforma: String, ano: Int) = {
        new Games(id,nome,desenvolvedor,genero,plataforma,ano)
    }
    
    def criarGame(nome: String, desenvolvedor: String, genero: String, plataforma: String, ano: Int): Games= {
        return new Games(0,nome,desenvolvedor,genero,plataforma,ano)
    }
    
    def upGame(id: Int, nome: String, desenvolvedor: String, genero: String, plataforma: String, ano: Int): Update = {
        return new Update(id,nome,desenvolvedor,genero,plataforma,ano)
    }
    
    def applyGame(nome: String, desenvolvedor: String, genero: String, plataforma: String, ano: Int): (String,String,String,String,Int) = {
        return (nome,desenvolvedor,genero,plataforma,ano)
    }
    
    def unapplyGame(g: (String,String,String,String,Int)): Option[(String,String,String,String,Int)] = {
        return Some(g._1,g._2,g._3,g._4,g._5)
    }
    
    def criarUsuarioId(id: Int, nome: String, email: String, senha: String) = {
        new GamesUsu(id,nome,email,senha)
    }
    
    def criarUsuario(nome: String, email: String, senha: String): GamesUsu = {
        return new GamesUsu(0,nome,email,senha)
    }
    
    def applyUsu(nome: String, email: String, senha: String): (String,String,String) = {
        return (nome,email,senha)
    }
    
    def unapplyUsu(usu: (String,String,String)): Option[(String,String,String)] = {
        return Some(usu._1,usu._2,usu._3)
    }
}