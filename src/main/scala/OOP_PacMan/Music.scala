package OOP_PacMan

import java.io.File
import scalafx.scene.media.{Media, MediaPlayer}

trait Music {

  val dieSound =new Media (new File("src/main/resource/OOP_PacMan/audio/die.wav").toURI.toURL.toString)
  val ahh =new Media (new File("src/main/resource/OOP_PacMan/audio/AHH.wav").toURI.toURL.toString)
  val background =new Media (new File("src/main/resource/OOP_PacMan/audio/retrobackground.mp3").toURI.toURL.toString)

  var backgroundmusic =  new MediaPlayer(background)
}
