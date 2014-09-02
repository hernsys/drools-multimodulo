package com.hernsys.droolsJbpm;

import org.drools.process.instance.WorkItemHandler;
import org.drools.runtime.process.WorkItem;
import org.drools.runtime.process.WorkItemManager;

public class WorkItemHandler2  implements WorkItemHandler {

	@Override
	public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
		System.out.println("** WorkItemHandler2 was called");
		manager.completeWorkItem(workItem.getId(), null);
	}

	@Override
	public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
		System.out.println("** WorkItemHandler2 was aborted");
		manager.abortWorkItem(workItem.getId());
		
	}

	
	
	
}
