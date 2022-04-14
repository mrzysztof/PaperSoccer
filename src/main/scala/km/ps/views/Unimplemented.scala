package km.ps.views

import scalatags.Text
import scalatags.Text.all._

case class Unimplemented() extends View {
  def bodyContents: Text.TypedTag[String] =
    div(cls:="root")(
      h1("Paper Soccer"),
      img(src:="static/images/ball.jpg", alt:="ball"),
      hr,
      h2("Coming soon!"),
      p("This feature is not ready yet. Come back soon!")
    )
}
