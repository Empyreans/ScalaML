package utils

import java.io.ObjectInputStream

import models.Movie

import scala.util.{Failure, Success, Using}

object Data {
  def readFromResources(): List[Movie] = {
    Using(new ObjectInputStream(getClass.getClassLoader.getResourceAsStream("data.ser"))) {
      ois => ois.readObject().asInstanceOf[List[Movie]];
    } match {
      case Success(value) => value
      case Failure(exception) => throw exception
    }
  }
}
