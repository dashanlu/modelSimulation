package com.simudyne.platform

trait AgentProcessor {
  def process(agent: Agent, brandFactor: Double): Agent = {
    if (agent.autoRenew == 1) {
      val random = Math.random() * 3
      val affinity = agent.paymentAtPurchase / agent.attributePrice + random * agent.attributePromotion * agent.inertiaForSwitch
      if (BreedType.BREED_C == agent.agentBreed
        && affinity < (agent.socialGrade * agent.attributeBrand)) {
        Agent(agent, BreedType.BREED_NC)
      } else if (BreedType.BREED_NC == agent.agentBreed && affinity < (agent.socialGrade * agent.attributeBrand * brandFactor)) {
        Agent(agent, BreedType.BREED_C)
      } else {
        Agent(agent)
      }
    } else {
      Agent(agent)
    }
  }
}

object AgentProcessor extends AgentProcessor