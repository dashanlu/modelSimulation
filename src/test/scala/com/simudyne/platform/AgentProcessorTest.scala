package com.simudyne.platform

import org.scalatest.{FlatSpec, Matchers}

class AgentProcessorTest extends FlatSpec with Matchers {

  "Processor" should "just update age" in {
    val agentWithoutAutoRenew = Agent(agentBreed = BreedType.BREED_C, policyId = "test policy id",
      1, 1, 250, 0.1, 1.9, .9, 0, 1)

    val agent = AgentProcessor.process(agentWithoutAutoRenew, 1)

    agent.age should be(2)
  }

  "Processor" should "flip the breedType C to NC" in {
    val agentWithAutoRenewAndLargeAttributeBrand = Agent(agentBreed = BreedType.BREED_C, policyId = "test policy id",
      1, 1, 250, attributeBrand=400.1, 1.9, .9, 1, 1)

    val agent = AgentProcessor.process(agentWithAutoRenewAndLargeAttributeBrand, 1)

    agent.agentBreed should be(BreedType.BREED_NC)
  }

  "Processor" should "flip the breedType NC to C" in {
    val agentWithAutoRenewAndLargeAttributeBrand = Agent(agentBreed = BreedType.BREED_NC, policyId = "test policy id",
      1, 1, 250, attributeBrand=400.00001, 1.9, .9, 1, 1)

    val agent = AgentProcessor.process(agentWithAutoRenewAndLargeAttributeBrand, 1)

    agent.agentBreed should be(BreedType.BREED_C)
  }

}
