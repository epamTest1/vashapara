package com.couple.persistence;

import com.couple.model.Couple;

public interface CoupleDao {
	Couple find(long id);
	Couple findForPartners(String firstPartner, String secondPartner);
	
	Couple create(String firstPartner, String secondPartner);
}
