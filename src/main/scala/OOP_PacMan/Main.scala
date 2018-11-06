package OOP_PacMan
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import scalafx.Includes._
import javafx.{scene => jfxs}
import scalafx.scene.text.Font

object Main extends JFXApp{
  val rootResource = getClass.getResourceAsStream("view/MainMenu.fxml")

  val loader = new FXMLLoader(null, NoDependencyResolver)
  loader.load(rootResource)
  val roots = loader.getRoot[jfxs.layout.BorderPane]
  stage = new PrimaryStage {
    title = "Pac Man"
    maxWidth = 400
    maxHeight =600
    scene = new Scene {
      root = roots
      stylesheets add getClass.getResource("style/Style.css").toExternalForm
      Font.loadFont(getClass.getResourceAsStream("font/Fleftex_M.ttf"), 16)
    }
  }
}
