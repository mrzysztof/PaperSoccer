package km.ps.views

import scalatags.Text
import scalatags.Text.all._
import scalatags.Text.tags2.title

trait View {
  def bodyContents: Text.TypedTag[String]

  val render =
    "<!DOCTYPE html>" + html(lang:="en")(
      head(
        meta(charset:="UTF-8"),
        meta(name:="viewport", content:="width=device-width, initial-scale=1.0"),
        title("Paper Soccer"),
        link(rel:="icon", href:="static/images/favicon.jpg"),
        link(rel:="stylesheet", href:="static/styles/style.css")
      ),
      body(bodyContents)
    )
}

