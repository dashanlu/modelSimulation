package com.simudyne.platform

import akka.actor.{ActorSystem, Props}
import com.simudyne.platform.actor.{Master, Start}

import scala.io.Source

object Simulator extends App {

  // Create an Akka system
  val system = ActorSystem("Simudyne-modelling-simulation-System")

  val bufferedSource = Source.fromResource("SimudynePlatformTestData.csv")
  val iter = bufferedSource.getLines().drop(1).map(_.split(","))
  val agentList = iter map { line =>
    val breedType = if (line(0) == "Breed_C") BreedType.BREED_C else BreedType.BREED_NC
    val policy = line(1)
    val age = line(2).toInt
    val socialGrade = line(3).toInt
    val paymentAtPurchase = line(4).toInt
    val attributBrand = line(5).toDouble
    val attributePrice = line(6).toDouble
    val attributePromotion = line(7).toDouble
    val autoRenew = line(8).toInt
    val intertiaForSwitch = line(9).toInt
    Agent(breedType
      , policy
      , age
      , socialGrade
      , paymentAtPurchase
      , attributBrand
      , attributePrice
      , attributePromotion
      , autoRenew
      , intertiaForSwitch)
  } toList

  val master = system.actorOf(Props(new Master(agentList, 15, factor = 1.23)), name = "master")

  //  start the simulation
  master ! Start
}
