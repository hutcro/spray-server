package com.rob.observable

import java.util.concurrent.Executors

import org.junit.Test
import rx.lang.scala.Observable
import rx.lang.scala.schedulers.ExecutionContextScheduler

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

class AsyncTest {

  @Test
  def shouldUseThreadPool: Unit = {
    val o = Observable.interval(200 millis).take(5)
    o.subscribe(n => println("n = " + n))

    // need to wait here because otherwise JUnit kills the thread created by interval()
    waitFor(o)

    println("done")
  }

  @Test
  def shouldDoInExecutor: Unit = {
    val e = ExecutionContext.fromExecutor(Executors.newSingleThreadExecutor)
    val s = ExecutionContextScheduler(e)

    val obser = Observable.just(1, 2, 3)
      .subscribeOn(s)

    obser.subscribe {
      n => println(s"Here on thread ${Thread.currentThread().getName}")
    }

    waitFor(obser)
  }

  def waitFor[T](obs: Observable[T]): Unit = {
    obs.toBlocking.toIterable.last
  }
}
