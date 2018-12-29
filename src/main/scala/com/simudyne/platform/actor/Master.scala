package com.simudyne.platform.actor

import akka.actor.{Actor, ActorLogging, PoisonPill, Props}
import akka.routing.{Broadcast, RoundRobinPool}
import com.simudyne.platform.Agent

class Master(agents: Seq[Agent], numOfYears: Int, factor: Double) extends Actor with ActorLogging {
  private val totalResps = agents.size
  private var currentRespsNum = 0

  private val workerRouter =
    context.actorOf(RoundRobinPool(4).props(Props[ProcessingActor]), "Agent-processing-worker-router")
  private val analyst = context.actorOf(Props(new AnalysisActor(agents.size, numOfYears, self)), "Agent-analysis-worker")

  override def receive: Receive = {
    case Start => {
      agents.foreach(x => workerRouter ! AgentMsgReq(x, factor, numOfYears))
    }
    case AgentMsgResp(result) => {
      currentRespsNum = currentRespsNum + 1

      analyst ! AgentAnalysisReq(result)
      if (totalResps == currentRespsNum) log.info("process all agents and it is time to kick off the analysis process")
    }
    case AnalysisResultAvailableMsg => {
      sender() ! ReportReqMsg
    }
    case ReportCompleteMsg => {
      log.info("Report is generated")
      analyst ! PoisonPill
      workerRouter ! Broadcast(PoisonPill)
      context.stop(self)
    }
  }
}
