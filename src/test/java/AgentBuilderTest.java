import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class AgentBuilderTest {

    @Test
    public void buildAgentGivenAllFields() {

        Agent.AgentBuilder agentBuilder = new Agent.AgentBuilder();
        Agent agent = agentBuilder.withAge(10)
                .withAgentBreed(Agent.BreedType.BREED_C)
                .withAttributeBrand(9.9)
                .withAttributePrice(9.9)
                .withAttributePromotion(0.99)
                .withAutoRenew(0)
                .withInertiaForSwitch(1)
                .withPaymentAtPurchase(250)
                .withPolicyId("1000001")
                .withSocialGrade(3).build();


        assertTrue(agent.getPreviousTwoAgents().isEmpty());
        assertEquals(Agent.BreedType.BREED_C, agent.getAgentBreed());
        assertEquals(0, agent.getAutoRenew());
        assertEquals(1, agent.getInertiaForSwitch());
        assertEquals(250, agent.getPaymentAtPurchase());
        assertEquals("1000001", agent.getPolicyId());
        assertEquals(3, agent.getSocialGrade());
    }

    @Test
    public void agentTakesTwoPreviousAgentAtMost(){
        Agent mockAgent1 = mock(Agent.class);
        Agent mockAgent2 = mock(Agent.class);
        Agent mockAgent3 = mock(Agent.class);

        Agent.AgentBuilder agentBuilder = new Agent.AgentBuilder();
        Agent agent = agentBuilder.withAge(10)
                .addPreviousAgent(mockAgent1)
                .addPreviousAgent(mockAgent2)
                .addPreviousAgent(mockAgent3)
               .build();

        assertEquals(2, agent.getPreviousTwoAgents().size());
        final Iterator<Agent> agentIterator = agent.getPreviousTwoAgents().iterator();
        assertEquals(mockAgent2, agentIterator.next());
        assertEquals(mockAgent3, agentIterator.next());

    }
}