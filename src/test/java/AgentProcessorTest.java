import org.junit.Test;

import static org.junit.Assert.*;

public class AgentProcessorTest {
    private static AgentProcessor agentProcessor = new AgentProcessor();

    @Test
    public void generateNewAgentGivenByAnAgent() {
        //given
        final Agent testAgent = new Agent.AgentBuilder().withAgentBreed(Agent.BreedType.BREED_C)
                                                        .withPolicyId("132802001")
                                                        .withAge(66)
                                                        .withSocialGrade(3)
                                                        .withPaymentAtPurchase(250)
                                                        .withAttributeBrand(25.3)
                                                        .withAttributePrice(16.6)
                                                        .withAttributePromotion(5.1)
                                                        .withAutoRenew(0)
                                                        .withInertiaForSwitch(4)
                                                        .build();

        //when
        final Agent newAgent = agentProcessor.process(testAgent, 1.3);

        //then
        assertNotNull(newAgent);
        assertEquals(67, newAgent.getAge());
        assertEquals(newAgent.getPreviousTwoAgents().iterator().next(), testAgent);
    }
}
