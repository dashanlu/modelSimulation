package com.simudyne.platform

import com.simudyne.platform.AgentGroup.AgentGroup

case class AnalysisReport(agentPolicyId: String, categorisedGroups: Set[AgentGroup])

object AgentGroup extends Enumeration {
  type AgentGroup = Value
  val BREED_C_AGENT_GROUP,
  BREED_NC_AGENT_GROUP,
  BREED_C_LOST_AGENT_GROUP,
  BREED_C_GAINED_AGENT_GROUP,
  BREED_C_REGAINED_AGENT_GROUP = Value
}

