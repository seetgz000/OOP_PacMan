package OOP_PacMan.component

import java.io.File

import OOP_PacMan.PacmanMap
import scalafx.Includes._
import scalafx.beans.property.IntegerProperty
import scalafx.scene.Node
import scalafx.scene.image.{Image, ImageView}
import OOP_PacMan.controller.PlayGameController

class Coin extends ImageView{
  val imageW = 23// set image Width
  val imageH = imageW // set image Height
  var coinfile = new File("src/main/resource/OOP_PacMan/image/coin.gif").toURI.toURL.toString
  var coin = new Image(coinfile,imageW,imageH,true,false)
  val coinStyle: Iterable[String] = List("coin")


  fitWidth = imageW
  image = coin
  styleClass= coinStyle
}

object Coin {
  var score = IntegerProperty(0)

  def checkCoinCollision(node: Node): Unit = {

    var thisCoin: Node = PacmanMap.coinList.head


    val localNode = node.localToScene(node.getBoundsInLocal())

    for (row <- 1 until PacmanMap.coinList.size + 1){
      thisCoin = PacmanMap.coinList.take(row).last

      val localCoin = thisCoin.localToScene(thisCoin.getBoundsInLocal())

      /** Get center of the coins to make Pacman need to be more to eat the coins */
      val localCoinX = ((localCoin.getMaxX + localCoin.getMinX)/2)
      val localCoinY = ((localCoin.getMaxY + localCoin.getMinY)/2)
      /** make the coin virtually smaller */
      val localCoinW = localCoin.getWidth/4
      val localCoinH = localCoin.getHeight/4

      if ((localNode.intersects(localCoinX, localCoinY, localCoinW, localCoinH))
        && thisCoin.isVisible){
        /** "Remove" the coin when collide with Pacman */
        score.set(score.get + 10)
        thisCoin.visible = false
      }else{
      }
        thisCoin = PacmanMap.coinList.take(row).last

    }
  }
}