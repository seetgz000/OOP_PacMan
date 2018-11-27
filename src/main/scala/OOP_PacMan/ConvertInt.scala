package OOP_PacMan
import java.lang.Integer.parseInt

    trait ConvertInt[A] {
      def fromString(string: String): A


    implicit object IntCastFromString extends ConvertInt[Int] {
      override def fromString(string: String): Int =
        parseInt(string)
    }

    def convertInt[A](string: String)(cast: ConvertInt[A]): A = {
      cast.fromString(string)
    }

  }

