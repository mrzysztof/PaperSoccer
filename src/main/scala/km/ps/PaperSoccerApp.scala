package km.ps

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._

import scala.util.Failure
import scala.util.Success

object PaperSoccerApp {
  private def startHttpServer(routes: Route)(implicit system: ActorSystem[_]): Unit = {
    // Akka HTTP still needs a classic ActorSystem to start
    import system.executionContext

    val port: Int = sys.env.getOrElse("PORT", "8080").toInt

    val futureBinding = Http().newServerAt("0.0.0.0", port).bind(routes)
    futureBinding.onComplete {
      case Success(binding) =>
        val address = binding.localAddress
        system.log.info("Server online at http://{}:{}/", address.getHostString, address.getPort)
      case Failure(ex) =>
        system.log.error("Failed to bind HTTP endpoint, terminating system", ex)
        system.terminate()
    }
  }

  def main(args: Array[String]): Unit = {
    val rootBehavior = Behaviors.setup[Nothing] { context =>
      val userRegistryActor = context.spawn(UserRegistry(), "UserRegistryActor")
      context.watch(userRegistryActor)

      val frontendRoutes = new FrontendRoutes()(context.system).route
      startHttpServer(frontendRoutes)(context.system)

      Behaviors.empty
    }
    val system = ActorSystem[Nothing](rootBehavior, "PaperSoccerServer")
  }
}
