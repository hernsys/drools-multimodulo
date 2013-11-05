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


public class CalculadorDescuento {
	
	@Test
	public void init() {
		try {
			// Cargar las reglas
			KnowledgeBase kbase = readKnowledgeBase();
			StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
			//KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "test");

			// Cliente no registrado que gasta más de 1.000 euros
			Cliente cliente1 = new Cliente();
			cliente1.setNombre("Cliente 1");
			cliente1.setRegistrado(false);
			cliente1.setGasto(1200);
			ksession.insert(cliente1);

			// Cliente registrado que gasta menos de 1.000 euros
			Cliente cliente2 = new Cliente();
			cliente2.setNombre("Cliente 2");
			cliente2.setRegistrado(true);
			cliente2.setGasto(800);
			ksession.insert(cliente2);

			// Cliente registrado que gasta más de 1.000 euros
			Cliente cliente3 = new Cliente();
			cliente3.setNombre("Cliente 3");
			cliente3.setRegistrado(true);
			cliente3.setGasto(1600);
			ksession.insert(cliente3);

			ksession.fireAllRules();
			//logger.close();

			System.out.println("El cliente 1 tiene un descuento de " + cliente1.getDescuento() + " euros.");
			System.out.println("El cliente 2 tiene un descuento de " + cliente2.getDescuento() + " euros.");
			System.out.println("El cliente 3 tiene un descuento de " + cliente3.getDescuento() + " euros.");

		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	private static KnowledgeBase readKnowledgeBase() throws Exception {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource("Descuentos.drl"),ResourceType.DRL);
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
