package com.hernsys;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.Test;

public class DiscountTest {

    private static String fileDrool = "Descuentos.drl";

    @Test
    public void init() throws Exception {
        // the rules are loaded
        KnowledgeBase kbase = readKnowledgeBase(fileDrool);
        StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();

        // expense less than 1000
        Customer customer1 = new Customer("Cliente 1", false, 1200);
        // expense greater than 1000
        Customer customer2 = new Customer("Cliente 2", true, 800);
        // it is a customer registered that spend greater than 1000 euros
        Customer customer3 = new Customer("Cliente 3", true, 1600);

        ksession.insert(customer1);
        ksession.insert(customer2);
        ksession.insert(customer3);
        ksession.fireAllRules();

        // logger.close();

        System.out.println("The customer1 has a discount of " + customer1.getDiscount() + " euros.");
        System.out.println("The customer2 has a discount of " + customer2.getDiscount() + " euros.");
        System.out.println("The customer3 has a discount of " + customer3.getDiscount() + " euros.");

    }

    private static KnowledgeBase readKnowledgeBase(String fileDrool) throws Exception {

        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource(fileDrool), ResourceType.DRL);
        KnowledgeBuilderErrors errors = kbuilder.getErrors();

        if (errors.size() > 0) {
            for (KnowledgeBuilderError error : errors) {
                System.err.println(error);
            }
            throw new IllegalArgumentException("Could not parse knowledge.");
        }

        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());

        return kbase;
    }

}
