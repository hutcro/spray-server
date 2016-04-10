package com.rob.http.spray

import akka.actor.{Actor, ActorRefFactory}
import spray.routing._
import spray.http._
import MediaTypes._
import akka.actor.Actor.Receive
import com.rob.http.spray.get.routes.Secret

class MasterGetServiceActor extends Actor with MasterGetService{
   def receive: Receive = runRoute(allRoutes)

   def actorRefFactory: ActorRefFactory = context
}

trait MasterGetService extends HttpService{



  val masterGetRoute =
    path("") {
      get {
        respondWithMediaType(`text/html`) { // XML is marshalled to `text/xml` by default, so we simply override here
          complete {
            <html>
              <body>
                <h1>Hi Everybody! Say hello to <i>spray-routing</i> on <i>spray-can</i>!</h1>
              </body>
            </html>
          }
        }
      }
    }

  val allRoutes = Secret.route ~ masterGetRoute
}
