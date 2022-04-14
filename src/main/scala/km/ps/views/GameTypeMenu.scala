package km.ps.views

import scalatags.Text
import scalatags.Text.all._

case class GameTypeMenu(headerTitle: String) extends View {
  def bodyContents: Text.TypedTag[String] =
    div(cls:="root")(
      h1(headerTitle),
      img(src:="static/images/ball.jpg", alt:="ball"),
      hr,
      div(cls:="menu")(
        button(id:="offline")("Offline"),
        button(id:="online", disabled)("Online"),
      ),
      div(id:="game-params-modal", cls:="modal")(
        form(action:="game")(
          div(cls:="modal-content")(
            div(cls:="radio-group")(
              button(`type`:="button", id:="time-mode-button")("Time"),
              button(`type`:="button", id:="bof-mode-button")("Best of"),
              input(`type`:="hidden", name:="mode", id:="mode-input")
            ),
            div(id:="len-adjustment-group")(
              div(id:="len-adjustment-time", cls:="len-adjustment")(
                label(`for`:="game-len-ui-time", display:="block")("Game duration"),
                div(cls:="spinbutton-group")(
                  button(`type`:="button", cls:="time-adj-button", value:="-")("-"),
                  input(`type`:="text", id:="game-len-ui-time", value:="5:00"),
                  button(`type`:="button", cls:="time-adj-button", value:="+")("+")
                )
              ),
              div(id:="len-adjustment-bof", cls:="len-adjustment", display:="none")(
                label(`for`:="game-len-ui-bof", display:="block")("Best of"),
                div(cls:="spinbutton-group")(
                  button(`type`:="button", cls:="bof-adj-button", value:="-")("-"),
                  input(`type`:="text", id:="game-len-ui-bof", value:="1"),
                  button(`type`:="button", cls:="bof-adj-button", value:="+")("+")
                )
              ),
                input(`type`:="hidden", name:="len", id:="game-len-input")
            ),
            div(cls:="radio-group")(
              button(`type`:="button", cls:="size-button", value:="S")("Small (4x5)"),
              button(`type`:="button", cls:="size-button", value:="M", id:="def-size-button")("Classic (8x10)"),
              button(`type`:="button", cls:="size-button", value:="L")("Large (16x20)"),
              input(`type`:="hidden", name:="size", id:="size-input")
            ),
            button(`type`:="submit")("Play")
          )
        )
      ),
      script(src:="static/scripts/game-type-menu.js")
    )
}
