package es.ucm.fdi.tfg.app.sa;

import java.util.List;

import es.ucm.fdi.tfg.app.transfer.TPlan;
import es.ucm.fdi.tfg.app.transfer.TUser;

/**
 * Application Service pattern
 */
public interface SAPlan {
	public TPlan create(TPlan tPlan);

	public TPlan join(Long id, Long userId);

	public TPlan quit(Long id, Long userId);

	public Long delete(Long id);
	
	public TPlan read(Long id);
	
	public List<TPlan> readAll();

	public List<TUser> getJoinedUsers(Long id);

	public List<TPlan> searchLikeByTitle(String title);

	public List<TPlan> getPlansByUserId(Long userId);
}
