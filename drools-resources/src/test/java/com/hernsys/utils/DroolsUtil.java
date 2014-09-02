package com.hernsys.utils;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

public class DroolsUtil {
	
	public static KnowledgeBase readKnowledgeBase(String fileDrool) throws Exception {
		
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource(fileDrool),ResourceType.DRL);
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
	
	public static StatefulKnowledgeSession getKSession(String fileDrool){
		StatefulKnowledgeSession ksession = null;
		try {
			ksession =  readKnowledgeBase(fileDrool).newStatefulKnowledgeSession();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ksession;
	}	

}
