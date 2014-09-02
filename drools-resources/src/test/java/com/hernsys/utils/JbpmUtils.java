//package com.hernsys.utils;
//
//import org.kie.api.KieServices;
//import org.kie.api.builder.KieBuilder;
//import org.kie.api.builder.KieFileSystem;
//import org.kie.api.builder.KieModule;
//import org.kie.api.builder.Message;
//import org.kie.api.runtime.KieContainer;
//import org.kie.api.runtime.KieSession;
//import org.kie.internal.event.KnowledgeRuntimeEventManager;
//import org.kie.internal.io.ResourceFactory;
//import org.kie.internal.logger.KnowledgeRuntimeLoggerFactory;
//
//public class JbpmUtils {
//	
//	public static KieSession createKieSession(String... resources) {
//		KieServices ks = KieServices.Factory.get();
//		KieFileSystem kfs = ks.newKieFileSystem();
//		for (String r : resources)
//			kfs.write("src/main/resources/" + r,
//					ResourceFactory.newClassPathResource(r));
//
//		KieBuilder kbuilder = ks.newKieBuilder(kfs);
//		kbuilder.buildAll();
//
//		if (kbuilder.getResults().hasMessages(Message.Level.ERROR)) {
//			System.err.println(kbuilder.getResults());
//			throw new IllegalArgumentException("Could not parse knowledge.");
//		}
//		KieModule kmodule = kbuilder.getKieModule();
//		KieContainer kcontainer = ks.newKieContainer(kmodule.getReleaseId());
//
//		KieSession ksession = kcontainer.newKieSession();
//		// We can add a runtime logger to understand what is going on inside the
//		// Engine
//		KnowledgeRuntimeLoggerFactory
//				.newConsoleLogger((KnowledgeRuntimeEventManager) ksession);
//
//		return ksession;
//	}
//
//}
