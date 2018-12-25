public class AgentProcessor {
    public Agent process(final Agent agent, final double brandFactor) {
        final Agent.AgentBuilder builder = new Agent.AgentBuilder().withAgent(agent);

        builder.withAge(agent.getAge() + 1);

        if (agent.getAutoRenew() == 0) {
            double random = Math.random() * 3;
            double affinity = agent.getPaymentAtPurchase() / agent.getAttributePrice()
                    + random * agent.getAttributePromotion() * agent.getInertiaForSwitch();
            if (Agent.BreedType.BREED_C == agent.getAgentBreed()
                    && affinity < (agent.getSocialGrade() * agent.getAttributeBrand())) {
                builder.withAgentBreed(Agent.BreedType.BREED_NC);
            } else if (Agent.BreedType.BREED_NC == agent.getAgentBreed()
                    && affinity < (agent.getSocialGrade() * agent.getAttributeBrand() * brandFactor)) {
                builder.withAgentBreed(Agent.BreedType.BREED_C);
            }
        }
        return builder.addPreviousAgent(agent).build();
    }
}
