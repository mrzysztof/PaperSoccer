package km.ps.views

import scalatags.Text
import scalatags.Text.all._

case class Index() extends View {
  def bodyContents: Text.TypedTag[String] =
    div(cls:="root")(
      h1("Paper Soccer"),
      img(src:="static/images/ball.jpg", alt:="ball"),
      hr,
      div(cls:="menu")(
        button(onclick:="window.location.href='match'")("Single Match"),
        button(onclick:="window.location.href='league'", disabled)("League"),
        button(onclick:="window.location.href='tournament'", disabled)("Tournament"),
      )
    )
}
