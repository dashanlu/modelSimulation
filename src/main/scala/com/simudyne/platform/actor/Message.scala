package com.simudyne.platform.actor

import com.simudyne.platform.Agent

sealed trait SimudyneMessage

object Start extends SimudyneMessage

case class AgentMsgReq(agent: Agent, brandFactor: Double, years: Int) extends SimudyneMessage

case class AgentMsgResp(result: Map[Int, Agent]) extends SimudyneMessage

case class AgentAnalysisReq(result: Map[Int, Agent]) extends SimudyneMessage

object ReportReqMsg extends SimudyneMessage

object AnalysisResultAvailableMsg extends SimudyneMessage

object ReportCompleteMsg extends SimudyneMessage
