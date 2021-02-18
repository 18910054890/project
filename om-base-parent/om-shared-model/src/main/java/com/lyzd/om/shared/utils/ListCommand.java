package com.lyzd.om.shared.utils;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Http request command of list as root element should be wrapped in this 
 * class in order to be validated by JSR-303.
 *  
 * @author Thinker
 *
 * @param <T>
 */
public class ListCommand<T> extends ArrayList<T> {
    /**
	 * 
	 */
	private static final long serialVersionUID = -407172465673840465L;

	@Valid
    public List<T> getList() {
        return this;
    }
}
