//package com.hernsys.droolsJbpm;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.jbpm.workflow.core.node.WorkItemNode;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//
//
//import com.hernsys.utils.JbpmUtils;
//
//public class JbpmTest {
//	
//	protected List<String> triggeredWorkItemNodes = new ArrayList<String>();
//	private static String PROCESS_ID = "customer-relationships.with-rule";
//	
//
//	@Before
//	public void beforeTest() {
//		//triggeredWorkItemNodes.clear();
//	}
//
//	@Test
//	public void init() {
//		KieSession ksession = createSession();
//
//		registerWorkItemHandlers(ksession);
//
//		ksession.addEventListener(new TestProcessEventListener());
//		
//		Map<String, Object> params = new HashMap<String, Object>();
//		/*params.put("approvalType", ApprovalType.ROLLBACK_MANAGER);
//		params.put("requestType", LeaveRequest.Type.CANCEL);
//		params.put("hasTravel", Boolean.TRUE);
//		params.put("requiresHRDirector", Boolean.FALSE);*/
//		ProcessInstance processInstance = ksession.startProcess(PROCESS_ID,
//				params);
//		// Assert process created
//		Assert.assertNotNull(processInstance);
//
//		// Assert that the task "Travel" has been fired + task in subprocess:
//		// rollback form to direct manager
//		//assertWorkItemsTriggered(TRAVEL, ROLLBACK_FORM_TO_DIRECT_MANAGER);
//
//		// Assert that the Process is completed
////		Assert.assertEquals(ProcessInstance.STATE_COMPLETED,
////				processInstance.getState());
//
//		// Dispose the knowledge session
//		ksession.dispose();
//
//	}
//
//	private final class TestProcessEventListener implements
//			ProcessEventListener {
//
//		public void beforeVariableChanged(ProcessVariableChangedEvent event) {
//		}
//
//		public void beforeProcessStarted(ProcessStartedEvent event) {
//		}
//
//		public void beforeProcessCompleted(ProcessCompletedEvent event) {
//		}
//
//		public void beforeNodeTriggered(ProcessNodeTriggeredEvent event) {
//		}
//
//		public void beforeNodeLeft(ProcessNodeLeftEvent event) {
//		}
//
//		public void afterVariableChanged(ProcessVariableChangedEvent event) {
//		}
//
//		public void afterProcessStarted(ProcessStartedEvent event) {
//		}
//
//		public void afterProcessCompleted(ProcessCompletedEvent event) {
//		}
//
//		public void afterNodeTriggered(ProcessNodeTriggeredEvent event) {
//			/*if (event.getNodeInstance().getNode() instanceof WorkItemNode) {
//				triggeredWorkItemNodes.add(((WorkItemNode) event
//						.getNodeInstance().getNode()).getWork().getName());
//			}*/
//		}
//
//		public void afterNodeLeft(ProcessNodeLeftEvent event) {
//		}
//	}
//
//	private void registerWorkItemHandlers(KieSession ksession) {
//		ksession.getWorkItemManager().registerWorkItemHandler(
//				"WorkItemHandler1", new WorkItemHandler1());
//		ksession.getWorkItemManager().registerWorkItemHandler(
//				"WorkItemHandler2", new WorkItemHandler2());
//	}
//
//	private KieSession createSession() {
//		return JbpmUtils.createKieSession("DroolsJbpmTest.bpmn2");
//	}
//
//}
