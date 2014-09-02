package com.hernsys.droolsJbpm;

import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hernsys.Person;
import com.hernsys.utils.DroolsUtil;

import org.drools.event.ActivationCreatedEvent;
import org.drools.WorkingMemory;
import org.drools.event.RuleFlowGroupActivatedEvent;
import org.drools.event.ActivationCancelledEvent;
import org.drools.event.AfterActivationFiredEvent;
import org.drools.event.AgendaGroupPoppedEvent;
import org.drools.event.AgendaGroupPushedEvent;
import org.drools.event.BeforeActivationFiredEvent;
import org.drools.event.RuleFlowGroupDeactivatedEvent;
import org.drools.impl.StatefulKnowledgeSessionImpl;
import org.drools.runtime.process.ProcessInstance;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.io.impl.ClassPathResource;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.ResourceType;



public class DroolsTest {

    private static String fileDrool = "DroolsJbpmTest.drl";
    private static String fileJbpm = "DroolsJbpmTest.bpmn2";
    
    
    private static final String ID_BPMN2 = "com.sample.bpmn";
    
    
    private StatefulKnowledgeSession ksession;
    
    @Before
    public void beforeInicialitation(){
        this.ksession = this.createKnowledgeSession();
    }
    
    @After
    public void afterExecution(){
        this.ksession.dispose();
    }
    

    @Test
    public void init() {
        try {
            final org.drools.event.AgendaEventListener agendaEventListener = new RuleFlowGroupAgendaEventListener();
            // Add the AgendaEventListener to the Session
            ((StatefulKnowledgeSessionImpl) ksession).session.addEventListener(agendaEventListener);
            
            // ===============================
            // Start the process using its id
            // ===============================
            //ProcessInstance process = ksession.startProcess(ID_BPMN2);
            
            
            // KnowledgeRuntimeLogger logger =
            // KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "test");

            Person person1 = new Person("Horacio", "Antar", 32);
            Person person2 = new Person("Maria", "Lamas", 30);
            Person person3 = new Person("Muny", "perez", 26);

            ksession.insert(person1);
            ksession.insert(person2);
            ksession.insert(person3);

            ksession.fireAllRules();

            // logger.close();

            System.out.println(person1.getName() + " " + person1.getLastName() + " status:" + person1.getStatus());
            System.out.println(person2.getName() + " " + person2.getLastName() + " status:" + person2.getStatus());
            System.out.println(person3.getName() + " " + person3.getLastName() + " status:" + person3.getStatus());
        } catch (Exception e) {
            System.out.println("We have a exception");
            this.ksession.dispose();
        }

    }
    
    public StatefulKnowledgeSession createKnowledgeSession() {
        //Create the kbuilder
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

        // Add simpleRuleFlowGroupProcess.bpmn2 to kbuilder
        // Add simpleRuleFlowGroupRules.drl to kbuilder
        //kbuilder.add(new ClassPathResource(fileJbpm), ResourceType.BPMN2);
        kbuilder.add(new ClassPathResource(fileDrool), ResourceType.DRL);
        
        //Check for errors
        if (kbuilder.hasErrors()) {
            if (kbuilder.getErrors().size() > 0) {
                for (KnowledgeBuilderError error : kbuilder.getErrors()) {
                    System.out.println("Error building kbase: " + error.getMessage());
                }
            }
            throw new RuntimeException("Error building kbase!");
        }

        //Create a knowledge base and add the generated package
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());

        //return a new stateful session
        return kbase.newStatefulKnowledgeSession();
    }
    
    
    private class RuleFlowGroupAgendaEventListener implements org.drools.event.AgendaEventListener {
        /**
         * Fire all rules when an activation has happened
         */
        public void activationCreated(ActivationCreatedEvent event, WorkingMemory workingMemory){
            ksession.fireAllRules();
        }
        
        /**
         * Fire all rules after a RuleFlowGroup has been activated
         */
        public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event, WorkingMemory workingMemory) {
            System.out.println("\n### After RuleFlowGroup '" + event.getRuleFlowGroup().getName() + "' was activated, we fire all rules again\n");
            workingMemory.fireAllRules();
        }
        // =========================
        // Methods without behavior
        // =========================
        public void activationCancelled(ActivationCancelledEvent event, WorkingMemory workingMemory){ }
        public void beforeActivationFired(BeforeActivationFiredEvent event, WorkingMemory workingMemory) { }
        public void afterActivationFired(AfterActivationFiredEvent event, WorkingMemory workingMemory) { }
        public void agendaGroupPopped(AgendaGroupPoppedEvent event, WorkingMemory workingMemory) { }
        public void agendaGroupPushed(AgendaGroupPushedEvent event, WorkingMemory workingMemory) { }
        public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event, WorkingMemory workingMemory) { }
        public void beforeRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event, WorkingMemory workingMemory) { }
        public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event, WorkingMemory workingMemory) { }
    };

}
