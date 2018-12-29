package com.simudyne.platform.actor

import akka.actor.{Actor, ActorLogging}
import com.simudyne.platform.{AgentProcessor, Agent}

import scala.collection.mutable

class ProcessingActor extends Actor with ActorLogging {
  val agentProcessor: AgentProcessor = AgentProcessor

  override def receive: Receive = {
    case AgentMsgReq(agent, factor, year) => {
      val result = process(agent, factor, year)
      sender() ! AgentMsgResp(result)
    }
  }

  private def process(agent: Agent, factor: Double, year: Int): Map[Int, Agent] = {
    def processHelper(agent: Agent, factor: Double, counter: Int, accResult: mutable.Map[Int, Agent]): Map[Int, Agent] = {
      if (counter > year) accResult.toMap
      else {
        val agentInNext = agentProcessor.process(agent, factor)
        accResult.put(counter, agentInNext)
        processHelper(agentInNext, factor, counter+1, accResult)
      }
    }
    processHelper(agent, factor, 1, mutable.Map[Int, Agent]())
  }
}
