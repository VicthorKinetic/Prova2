package models

case class Games(id:Int, nome: String, desenvolvedor: String, genero: String, plataforma: String, ano: Int)

object Games{
    
    def criarGameId(id: Int, nome: String, desenvolvedor: String, genero: String, plataforma: String, ano: Int) = {
        new Games(id,nome,desenvolvedor,genero,plataforma,ano)
    }
    
    def criarGame(nome: String, desenvolvedor: String, genero: String, plataforma: String, ano: Int): Games= {
        return new Games(0,nome,desenvolvedor,genero,plataforma,ano)
    }
    
    def applyGame(nome: String, desenvolvedor: String, genero: String, plataforma: String, ano: Int): (String,String,String,String,Int) = {
        return (nome,desenvolvedor,genero,plataforma,ano)
    }
    
    def unapplyGame(g: (String,String,String,String,Int)): Option[(String,String,String,String,Int)] = {
        return Some(g._1,g._2,g._3,g._4,g._5)
    }
}