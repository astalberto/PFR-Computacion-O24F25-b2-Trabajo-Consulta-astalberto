package codigo

import java.sql.{Connection, DriverManager, SQLException}

object TrabajoConsulta {
  def main(args: Array[String]): Unit = {
    val url = "jdbc:mysql://localhost:3306/examen"
    val user = "root"
    val password = "1537"
    try {
      Class.forName("com.mysql.cj.jdbc.Driver")
      val connection: Connection = DriverManager.getConnection(url, user, password)
      val statement = connection.createStatement()
      val resultSet = statement.executeQuery("SELECT * FROM clientes")
      while (resultSet.next()) {
        val id_Cliente = resultSet.getInt("ID_Cliente")
        val nombre = resultSet.getString("nombre")
        val ciudad = resultSet.getString("ciudad")
        println(s"ID: $id_Cliente, Nombre: $nombre, Ciudad: $ciudad")
      }
      resultSet.close()
      statement.close()
      connection.close()
    } catch {
      case e: SQLException => e.printStackTrace()
      case e: Exception => e.printStackTrace()
    }
  }
}