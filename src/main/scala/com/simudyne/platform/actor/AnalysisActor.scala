package com.simudyne.platform.actor

import akka.actor.{Actor, ActorLogging, ActorRef}
import com.simudyne.platform.AgentGroup.AgentGroup
import com.simudyne.platform.{AgentAnalyst, AgentReportGenerator}

import scala.collection.mutable


class AnalysisActor(numOfAgents: Int, years: Int, masterRef: ActorRef) extends Actor with ActorLogging {
  val agentAnalyst: AgentAnalyst = AgentAnalyst
  val agentReportGenerator: AgentReportGenerator = AgentReportGenerator

  val intermediaryResult = mutable.Set[(Int, String, AgentGroup)]()
  var respCounter = 0

  override def receive: Receive = {
    case AgentAnalysisReq(result) => {
      result.toList foreach { x =>
        val (year, agent) = x
        val report = AgentAnalyst.analyse(agent)
        report.categorisedGroups map { categorisedGroup => (year, report.agentPolicyId, categorisedGroup) } foreach intermediaryResult.add
      }

      respCounter = respCounter + 1
      if (respCounter == numOfAgents) {
        masterRef ! AnalysisResultAvailableMsg
      }
    }
    case ReportReqMsg => {
      val reports = intermediaryResult.groupBy(row => (row._1, row._3)) map { entry =>
        (entry._1, entry._2.map(x => x._2).mkString(","))
      } toList

      val lines = reports.sorted.map { row =>
        s"In year ${row._1._1}: ${row._1._2} has ${row._2}\n"
      }

      agentReportGenerator.generate(lines)

      sender() ! ReportCompleteMsg
    }
  }
}


