package com.simudyne.platform

import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{FlatSpec, Matchers}


class AgentAnalystTest extends FlatSpec with Matchers with MockitoSugar {


  "Analyst" should "generate a report with Breed_C, Breed_C Gained and Breed_C Regained " in {
    val mockAgent1 = mock[Agent]
    val mockAgentInLastYear = mock[Agent]
    val mockAgent1InTheYearBeforeLastYear = mock[Agent]
    when(mockAgent1.policyId) thenReturn "test policy id"
    when(mockAgent1.agentBreed) thenReturn (BreedType.BREED_C)
    when(mockAgentInLastYear.agentBreed) thenReturn (BreedType.BREED_NC)
    when(mockAgent1InTheYearBeforeLastYear.agentBreed) thenReturn (BreedType.BREED_C)
    when(mockAgent1.previousTwoAgents) thenReturn (mockAgentInLastYear :: mockAgent1InTheYearBeforeLastYear :: Nil)

    val analysisReport = AgentAnalyst.analyse(mockAgent1)

    analysisReport.agentPolicyId should be("test policy id")
    analysisReport.categorisedGroups should contain(AgentGroup.BREED_C_AGENT_GROUP)
    analysisReport.categorisedGroups should contain(AgentGroup.BREED_C_GAINED_AGENT_GROUP)
    analysisReport.categorisedGroups should contain(AgentGroup.BREED_C_REGAINED_AGENT_GROUP)

    verify(mockAgent1, times(1)).policyId
    verify(mockAgent1, times(1)).previousTwoAgents
    verify(mockAgent1, times(1)).agentBreed
    verify(mockAgentInLastYear, times(1)).agentBreed
    verify(mockAgent1InTheYearBeforeLastYear, times(1)).agentBreed
  }

  "Analyst" should "generate a report with Breed_NC, Breed_C Lost" in {
    val mockAgent1 = mock[Agent]
    val mockAgentInLastYear = mock[Agent]
    when(mockAgent1.policyId) thenReturn "test policy id"
    when(mockAgent1.agentBreed) thenReturn (BreedType.BREED_NC)
    when(mockAgentInLastYear.agentBreed) thenReturn (BreedType.BREED_C)
    when(mockAgent1.previousTwoAgents) thenReturn (mockAgentInLastYear :: Nil)

    val analysisReport = AgentAnalyst.analyse(mockAgent1)

    analysisReport.agentPolicyId should be("test policy id")
    analysisReport.categorisedGroups should contain(AgentGroup.BREED_NC_AGENT_GROUP)
    analysisReport.categorisedGroups should contain(AgentGroup.BREED_C_LOST_AGENT_GROUP)
    verify(mockAgent1, times(1)).policyId
    verify(mockAgent1, times(1)).previousTwoAgents
    verify(mockAgent1, times(2)).agentBreed
    verify(mockAgentInLastYear, times(1)).agentBreed

  }
}