package com.simudyne.platform

import com.simudyne.platform.AgentGroup.AgentGroup

import scala.collection.mutable

trait AgentAnalyst {
  def analyse(agent: Agent): AnalysisReport = {
    val result = mutable.Set[AgentGroup]()

    if (agent.agentBreed == BreedType.BREED_C) {
      result.add(AgentGroup.BREED_C_AGENT_GROUP)
      agent.previousTwoAgents match {
        case x :: Nil =>
          if (x.agentBreed == BreedType.BREED_NC)
            result.add(AgentGroup.BREED_C_GAINED_AGENT_GROUP)

        case x :: y :: Nil =>
          if (x.agentBreed == BreedType.BREED_NC) {
            result.add(AgentGroup.BREED_C_GAINED_AGENT_GROUP)
            if (y.agentBreed == BreedType.BREED_C) {
              result.add(AgentGroup.BREED_C_REGAINED_AGENT_GROUP)
            }
          }
      }
    }

    else if (agent.agentBreed == BreedType.BREED_NC) {
      result.add(AgentGroup.BREED_NC_AGENT_GROUP)

      agent.previousTwoAgents match {
        case x :: _ =>
          if (x.agentBreed == BreedType.BREED_C) result.add(AgentGroup.BREED_C_LOST_AGENT_GROUP)
      }
    }

    AnalysisReport(agent.policyId, result.toSet)
  }

}

object AgentAnalyst extends AgentAnalyst
