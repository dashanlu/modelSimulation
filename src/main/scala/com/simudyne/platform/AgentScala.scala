package com.simudyne.platform

import com.simudyne.platform.BreedType.BreedType


case class AgentScala(agentBreed: BreedType,
                      policyId: String,
                      age: Int = 0,
                      socialGrade: Int = 0,
                      paymentAtPurchase: Int = 0,
                      attributeBrand: Double = .0,
                      attributePrice: Double = .0,
                      attributePromotion: Double = .0,
                      autoRenew: Int = 0,
                      inertiaForSwitch: Int = 0,
                      previousTwoAgents: List[AgentScala] = List.empty)

object AgentScala {
  def apply(agent: AgentScala, agentBreed: Option[BreedType] = Option.empty) = {
    val previousAgents = agent.previousTwoAgents match {
      case List.empty => agent :: List.empty
      case x :: List.empty => agent :: agent.previousTwoAgents
      case x :: _ => agent :: x :: List.empty
    }
    val newAgentBreed: BreedType.BreedType = agentBreed match {
      case Option.empty => agent.agentBreed
      case Some(x) => x
    }
    new AgentScala(newAgentBreed,
      agent.policyId,
      agent.age + 1,
      agent.socialGrade,
      agent.paymentAtPurchase,
      agent.attributeBrand,
      agent.attributePrice,
      agent.attributePromotion,
      agent.autoRenew,
      agent.inertiaForSwitch,
      agent :: previousAgents
    )
  }
}

object BreedType extends Enumeration {
  type BreedType = Value
  val BREED_C, BREED_NC = Value
}