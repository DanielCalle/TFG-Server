package es.ucm.fdi.tfg.app.sa;

import java.util.List;

import es.ucm.fdi.tfg.app.transfer.TPlan;

public interface SAPlan {
	public TPlan create(TPlan tPlan);
	
	public TPlan join(String Id, String userUuid);
	
	public String delete(String id);
	
	public TPlan read(String id);
	
	public List<TPlan> readAll();
}
