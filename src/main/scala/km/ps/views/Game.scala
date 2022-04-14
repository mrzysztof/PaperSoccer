package km.ps.views

import scalatags.Text
import scalatags.Text.all._

case class Game() extends View{
  def bodyContents: Text.TypedTag[String] = {
    div(cls:="root")(
      h1(id:="game-header")(),
      hr,
      div(id:="score-section")(
        div(cls:="score-info")(
          p(cls:="score", id:="red-score")("0"),
          div(cls:="color-label", style:="background-color: red;")
        ),
        div(cls:="score-info")(
          p(cls:="score", id:="blue-score")("0"),
          div(cls:="color-label", style:="background-color: blue;")
        )
      ),
      canvas(id:="board"),
      script(src:="static/scripts/game.js")
    )
  }

}
