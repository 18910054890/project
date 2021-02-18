package com.lyzd.om.shared.model;



import javax.inject.Inject;

import com.lyzd.om.shared.event.DomainEventDao;

/**
 * @author Thinker
 *
 * @param <AR>
 */
public abstract class BaseRepository<AR extends BaseAggregate> {
	
    @Inject
    private DomainEventDao eventDao;

    public final void save(AR aggregate) {
    	if(aggregate != null && eventDao != null) {
    		 eventDao.save(aggregate.getEvents());
    	     aggregate.clearEvents();
    	}
       
        doSave(aggregate);
    }
    

    protected abstract void doSave(AR aggregate);
}
