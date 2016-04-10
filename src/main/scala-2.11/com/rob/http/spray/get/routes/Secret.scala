package com.rob.http.spray.get.routes

import spray.http.MediaTypes._
import spray.routing._
import Directives._

object Secret {

  val route = path("secret" / IntNumber) { num =>
    get {
      respondWithMediaType(`text/html`) { // XML is marshalled to `text/xml` by default, so we simply override here
        complete {
          <html>
            <body>
              <h1>Sssh! This is a secret place! ${num}</h1>
            </body>
          </html>
        }
      }
    }
  }

}
