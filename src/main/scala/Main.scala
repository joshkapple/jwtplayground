import com.nimbusds.jose.JWSAlgorithm
import com.nimbusds.jose.jwk._
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator
import java.util.UUID
import scala.io.StdIn.readLine

object Main extends App {

  def usage = println(
    "Usage: java -jar <path to sbt-assembly jar> <command> <commandargs> \n" +
    s"Example: java -jar target/JWTPlayground-assembly-0.1.0-SNAPSHOT.jar help \n" +
    s"where command: ${Commands.values.toList.mkString(" | ")}\n" +
    s"\t${Commands.GenerateJWK} has no <commandarg>\n" +
    s"\t${Commands.SignJWT} <payload> <key> - where <payload> is a json string and <key> is the key made during ${Commands.GenerateJWK}"
  )

  args.toList.headOption.map(Commands.withName) match {
    case Some(Commands.GenerateJWK) => {
      println("signing JWT")
      generateJwk()
    }
    case Some(Commands.SignJWT) if args.length > 2 => {
      println("signing JWT")
      println(args(1))
      println(args(2))
    }
    case Some(Commands.Help) => usage
    case _ => {
      println("invalid usage\n")
      usage
    }

  }


  def generateJwk(): Unit ={
    val jwk = new RSAKeyGenerator(2048)
      .keyUse(KeyUse.SIGNATURE)
      .algorithm(JWSAlgorithm.RS256)
      .keyID(UUID.randomUUID().toString)
      .generate()

    println(s"\nSecret ${jwk}")
    println(s"Public ${jwk.toPublicKey}\n\n")
  }
}

object Commands extends Enumeration {
  type Commands = Value

  val GenerateJWK = Value("generatejwk")
  val SignJWT = Value("signjwt")
  val Help = Value("help")
}
