package com.simudyne.platform

import org.scalatest.{FlatSpec, Matchers}

class AgentTest extends FlatSpec with Matchers {
  private val agent = Agent(agentBreed = BreedType.BREED_C, policyId = "test policy td",
    1, 1, 250, 0.1, 1.9, .9, 1, 1)

  "AgentScala companion object " should "increment agent age by 1 and append the agent to the end of the previous agent list automatically" in {
    val agentInNext = Agent(agent)

    agentInNext.age should be(2)
    agentInNext.previousTwoAgents should be(List(agent))
  }
}
