package com.simudyne.platform

object AgentProcessor {
  def process(agent: AgentScala, brandFactor: Double): AgentScala = {
    if (agent.autoRenew == 0) {
      val random = Math.random() * 3
      val affinity = agent.paymentAtPurchase / agent.attributePrice + random * agent.attributePromotion * agent.inertiaForSwitch
      if (BreedType.BREED_C == agent.agentBreed
        && affinity < (agent.socialGrade * agent.attributeBrand)) {
        AgentScala(agent, Some(BreedType.BREED_NC))
      } else if (BreedType.BREED_NC == agent.agentBreed && affinity < (agent.socialGrade * agent.attributeBrand * brandFactor)) {
        AgentScala(agent, Some(BreedType.BREED_C))
      }
    }
    AgentScala(agent)
  }
}