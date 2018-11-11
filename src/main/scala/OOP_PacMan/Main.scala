package OOP_PacMan

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.{Parent, Scene, image}
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import scalafx.Includes._
import javafx.{scene => jfxs}
import scalafx.scene.media.Media
import scalafx.scene.text.Font
import scalafx.stage.{Modality, Stage}

object Main extends JFXApp {

  val rootResource = getClass.getResourceAsStream("view/MainMenu.fxml")
  val loader = new FXMLLoader(null, NoDependencyResolver)
  loader.load(rootResource)
  val roots = loader.getRoot[jfxs.layout.BorderPane]
  stage = new PrimaryStage {
    title = "Pac Man"
    maxWidth = 400
    maxHeight = 700
    scene = new Scene {
      root = roots
      Font.loadFont(getClass.getResourceAsStream("font/Fleftex_M.ttf"), 16)
      stylesheets add getClass.getResource("style/Style.css").toExternalForm
    }
  }

  //introduction page
  val resource = getClass.getResourceAsStream("view/Introduction.fxml")
  val introLoader = new FXMLLoader(null, NoDependencyResolver)
  introLoader.load(resource);
  val introRoot = introLoader.getRoot[jfxs.Parent]

  def showIntroduction():Unit ={
    roots.setCenter(introRoot)
  }

  def closeIntroduction()={
    roots.getChildren.remove(introRoot)
  }

  //playGame page
  def playGame(): Unit = {
    val resource = getClass.getResourceAsStream("view/PlayGame.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource);
    val roots2 = loader.getRoot[jfxs.layout.AnchorPane]
    stage.scene().setRoot(roots2)
  }


}