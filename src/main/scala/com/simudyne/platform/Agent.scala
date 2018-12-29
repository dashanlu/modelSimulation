package com.simudyne.platform

import com.simudyne.platform.BreedType.BreedType


case class Agent(agentBreed: BreedType,
                 policyId: String,
                 age: Int,
                 socialGrade: Int,
                 paymentAtPurchase: Int,
                 attributeBrand: Double = .0,
                 attributePrice: Double = .0,
                 attributePromotion: Double = .0,
                 autoRenew: Int,
                 inertiaForSwitch: Int,
                 previousTwoAgents: List[Agent] = List.empty)

object Agent {
  def apply(agent: Agent) = {
    val previousAgents = agent.previousTwoAgents match {
      case Nil => agent :: Nil
      case x :: Nil => agent :: x :: Nil
      case x :: _ => agent :: x :: Nil
    }

    new Agent(agent.agentBreed,
      agent.policyId,
      agent.age + 1,
      agent.socialGrade,
      agent.paymentAtPurchase,
      agent.attributeBrand,
      agent.attributePrice,
      agent.attributePromotion,
      agent.autoRenew,
      agent.inertiaForSwitch,
      previousAgents
    )
  }

  def apply(agent: Agent, agentBreed: BreedType) = {
    val previousAgents = agent.previousTwoAgents match {
      case Nil => agent :: Nil
      case x :: Nil => agent :: x :: Nil
      case x :: _ => agent :: x :: Nil
    }

    new Agent(agentBreed,
      agent.policyId,
      agent.age + 1,
      agent.socialGrade,
      agent.paymentAtPurchase,
      agent.attributeBrand,
      agent.attributePrice,
      agent.attributePromotion,
      agent.autoRenew,
      agent.inertiaForSwitch,
      previousAgents
    )
  }

  def apply(agentBreed: BreedType,
            policyId: String,
            age: Int,
            socialGrade: Int,
            paymentAtPurchase: Int,
            attributeBrand: Double,
            attributePrice: Double,
            attributePromotion: Double,
            autoRenew: Int,
            inertiaForSwitch: Int,
            previousTwoAgents: List[Agent] = List.empty) = {
    new Agent(agentBreed, policyId, age,
      socialGrade, paymentAtPurchase, attributeBrand,
      attributePrice, attributePromotion, autoRenew,
      inertiaForSwitch, previousTwoAgents
    )
  }
}

object BreedType extends Enumeration {
  type BreedType = Value
  val BREED_C, BREED_NC = Value
}