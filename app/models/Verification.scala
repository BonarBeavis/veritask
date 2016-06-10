package models

import java.util.UUID

import config.ConfigBanana
import org.w3.banana.binder.PGBinder
import play.api.libs.json.Json
import scala.language.implicitConversions

/**
  * Created by beavis on 03.12.15.
  */
case class Verification(_id: UUID,
                        verifier: UUID,
                        task_id: UUID,
                        value: Option[Boolean]
                       ) extends MongoEntity

object Verification extends ConfigBanana {

  implicit def UUIDToString(id: UUID): String = id.toString
  implicit def StringToUUID(id: String): UUID = UUID.fromString(id)

  implicit val verificationFormat = Json.format[Verification]

//  import ops._
//  import recordBinder._
//  import org.w3.banana.syntax._
//
//  val clazz = URI("http://example.com/City#class")
//  implicit val classUris = classUrisFor[Verification](clazz)
//
//  val verifier = property[String](foaf("veri"))
//  val task_id = property[String](foaf("task"))
//  val value = optional[Boolean](foaf("value"))
//
//  implicit val binder: PGBinder[Rdf, Verification] =
//    pgbWithId[Verification](t => URI("http://example.com/" + t._id))
//      .apply(verifier,task_id,value)(Verification.apply, Verification.unapply) withClasses classUris

}

case class VerificationDump(
                          _id: String,
                          verifier: String,
                          linkSubject: String,
                          predicate: String,
                          linkObject: String,
                          value: Option[Boolean]
                           )

object VerificationDump extends ConfigBanana {

    import ops._
    import recordBinder._
    import org.w3.banana.syntax._

    val clazz = URI("http://example.com/City#class")
    implicit val classUris = classUrisFor[VerificationDump](clazz)

    val _id = property[String](foaf("id"))
    val verifier = property[String](foaf("veri"))
    val linkSubject = property[String](foaf("subject"))
    val predicate = property[String](foaf("predi"))
    val linkObject = property[String](foaf("obj"))
    val value = optional[Boolean](foaf("val"))

    implicit val binder: PGBinder[Rdf, VerificationDump] =
      pgbWithId[VerificationDump](t => URI("http://example.com/" + t._id))
        .apply(_id, verifier, linkSubject, predicate, linkObject, value)(VerificationDump.apply, VerificationDump.unapply) withClasses classUris
}

//object UUID extends ConfigBanana{
//    import ops._
//    import recordBinder._
//    import org.w3.banana.syntax._
//
//    implicit val rsaClassUri = classUrisFor[RSAPublicKey](cert.RSAPublicKey)
//    val factory = KeyFactory.getInstance("RSA")
//    val exponent = property[BigInteger](cert.exponent)
//    val modulus = property[Array[Byte]](cert.modulus)
//
//    implicit val binder: PGBinder[Rdf, RSAPublicKey] =
//      pgb[RSAPublicKey](modulus, exponent)(
//        (m, e) => factory.generatePublic(new RSAPublicKeySpec(new BigInteger(m), e)).asInstanceOf[RSAPublicKey],
//        key => Some((key.getModulus.toByteArray, key.getPublicExponent))
//      ) // withClasses rsaClassUri
//
//  }
//  import ops._
//  import recordBinder._
//  val clazz = URI("http://example.com/Taskset#class")
//  implicit val classUris = classUrisFor[Verification](clazz)
//
//  val _id = property[Option[String]](vt("_id"))
//  val tasks = set[Task](vt.Tasks)
//
//  //ToDo: Find out for what container is
//  implicit val container = URI("http://example.com/tasksets")
//  implicit val binder: PGBinder[Rdf, Taskset] =
//    pgbWithId[Taskset](t => URI("http://example.com/tasksets" + t._id))
//      .apply(_id, tasks)(Taskset.apply, Taskset.unapply) withClasses classUris
//case class City(cityName: String, otherNames: Set[String] = Set.empty)
//
//object City {
//
//  val clazz = URI("http://example.com/City#class")
//  implicit val classUris = classUrisFor[City](clazz)
//
//  val cityName = property[String](foaf("cityName"))
//  val otherNames = set[String](foaf("otherNames"))
//
//  implicit val binder: PGBinder[Rdf, City] =
//    pgbWithId[City](t => URI("http://example.com/" + t.cityName))
//      .apply(cityName, otherNames)(City.apply, City.unapply) withClasses classUris
//
//}