package km.ps

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}

import akka.actor.typed.ActorSystem
import akka.util.Timeout
import km.ps.views._

class FrontendRoutes(implicit val system: ActorSystem[_]) {

  // If ask takes more time than this to complete the request is failed
  private implicit val timeout = Timeout.create(system.settings.config.getDuration("app.routes.ask-timeout"))

  val frontendPrefix = "static"
  val htmlType = ContentTypes.`text/html(UTF-8)`

  val route =
    get {
      concat(
        pathEndOrSingleSlash {
          complete(HttpEntity(htmlType, Index().render))
        },
        pathPrefix("match"){
          pathEndOrSingleSlash{
            complete(HttpEntity(htmlType, GameTypeMenu("Single Match").render))
          }
        },
        pathPrefix("game"){
          complete(HttpEntity(htmlType, Game().render))
        },
        path(frontendPrefix / Remaining) { resource =>
          getFromResource(resource)
        })
    }
}
