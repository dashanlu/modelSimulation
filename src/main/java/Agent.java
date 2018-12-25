import org.apache.commons.collections4.queue.CircularFifoQueue;

import java.util.Collection;
import java.util.Objects;

public class Agent {
    private BreedType agentBreed;
    private String policyId;
    private int age;
    private int socialGrade;
    private int paymentAtPurchase;
    private double attributeBrand;
    private double attributePrice;
    private double attributePromotion;
    private int autoRenew;
    private int inertiaForSwitch;
    private Collection<Agent> previousTwoAgents;

    public Agent(BreedType agentBreed, String policyId,
                 int age, int socialGrade, int paymentAtPurchase,
                 double attributeBrand, double attributePrice, double attributePromotion,
                 int autoRenew, int inertiaForSwitch,
                 Collection<Agent> previousTwoAgents) {
        this.agentBreed = agentBreed;
        this.policyId = policyId;
        this.age = age;
        this.socialGrade = socialGrade;
        this.paymentAtPurchase = paymentAtPurchase;
        this.attributeBrand = attributeBrand;
        this.attributePrice = attributePrice;
        this.attributePromotion = attributePromotion;
        this.autoRenew = autoRenew;
        this.inertiaForSwitch = inertiaForSwitch;
        this.previousTwoAgents = previousTwoAgents;
    }

    public BreedType getAgentBreed() {
        return agentBreed;
    }

    public String getPolicyId() {
        return policyId;
    }

    public int getAge() {
        return age;
    }

    public int getSocialGrade() {
        return socialGrade;
    }

    public int getPaymentAtPurchase() {
        return paymentAtPurchase;
    }

    public double getAttributeBrand() {
        return attributeBrand;
    }

    public double getAttributePrice() {
        return attributePrice;
    }

    public double getAttributePromotion() {
        return attributePromotion;
    }

    public int getAutoRenew() {
        return autoRenew;
    }

    public int getInertiaForSwitch() {
        return inertiaForSwitch;
    }

    public Collection<Agent> getPreviousTwoAgents() {
        return previousTwoAgents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agent agent = (Agent) o;
        return age == agent.age &&
                socialGrade == agent.socialGrade &&
                paymentAtPurchase == agent.paymentAtPurchase &&
                Double.compare(agent.attributeBrand, attributeBrand) == 0 &&
                Double.compare(agent.attributePrice, attributePrice) == 0 &&
                Double.compare(agent.attributePromotion, attributePromotion) == 0 &&
                autoRenew == agent.autoRenew &&
                inertiaForSwitch == agent.inertiaForSwitch &&
                agentBreed == agent.agentBreed &&
                policyId.equals(agent.policyId) &&
                previousTwoAgents.equals(agent.previousTwoAgents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(agentBreed, policyId, age, socialGrade, paymentAtPurchase, attributeBrand, attributePrice,
                attributePromotion, autoRenew, inertiaForSwitch, previousTwoAgents);
    }

    public static class AgentBuilder {
        private BreedType agentBreed;
        private String policyId;
        private int age;
        private int socialGrade;
        private int paymentAtPurchase;
        private double attributeBrand;
        private double attributePrice;
        private double attributePromotion;
        private int autoRenew;
        private int inertiaForSwitch;
        private Collection<Agent> previousTwoAgents = new CircularFifoQueue(2);

        public AgentBuilder withAgent(Agent agent) {
            agentBreed = agent.getAgentBreed();
            policyId = agent.getPolicyId();
            age = agent.getAge();
            socialGrade = agent.getSocialGrade();
            paymentAtPurchase = agent.getPaymentAtPurchase();
            attributeBrand = agent.getAttributeBrand();
            attributePrice = agent.getAttributePrice();
            attributePromotion = agent.getAttributePromotion();
            autoRenew = agent.getAutoRenew();
            inertiaForSwitch = agent.getInertiaForSwitch();
            previousTwoAgents = agent.getPreviousTwoAgents();
            return this;
        }

        public AgentBuilder withAgentBreed(BreedType agentBreed) {
            this.agentBreed = agentBreed;
            return this;
        }

        public AgentBuilder withPolicyId(String policyId) {
            this.policyId = policyId;
            return this;
        }

        public AgentBuilder withAge(int age) {
            this.age = age;
            return this;
        }

        public AgentBuilder withSocialGrade(int socialGrade) {
            this.socialGrade = socialGrade;
            return this;
        }

        public AgentBuilder withPaymentAtPurchase(int paymentAtPurchase) {
            this.paymentAtPurchase = paymentAtPurchase;
            return this;
        }

        public AgentBuilder withAttributeBrand(double attributeBrand) {
            this.attributeBrand = attributeBrand;
            return this;
        }

        public AgentBuilder withAttributePrice(double attributePrice) {
            this.attributePrice = attributePrice;
            return this;
        }

        public AgentBuilder withAttributePromotion(double attributePromotion) {
            this.attributePromotion = attributePromotion;
            return this;
        }

        public AgentBuilder withAutoRenew(int autoRenew) {
            this.autoRenew = autoRenew;
            return this;
        }

        public AgentBuilder withInertiaForSwitch(int inertiaForSwitch) {
            this.inertiaForSwitch = inertiaForSwitch;
            return this;
        }

        public AgentBuilder addPreviousAgent(Agent previousAgent) {
            this.previousTwoAgents.add(previousAgent);
            return this;
        }

        public Agent build() {
            return new Agent(agentBreed, policyId, age,
                    socialGrade, paymentAtPurchase, attributeBrand,
                    attributePrice, attributePromotion, autoRenew,
                    inertiaForSwitch, previousTwoAgents);

        }
    }

    public static enum BreedType {
        BREED_C,
        BREED_NC;
    }
}



