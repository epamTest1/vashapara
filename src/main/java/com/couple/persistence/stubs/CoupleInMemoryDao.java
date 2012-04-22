package com.couple.persistence.stubs;

import java.util.Random;

import org.springframework.stereotype.Repository;

import com.couple.model.Couple;
import com.couple.persistence.CoupleDao;

@Repository
public class CoupleInMemoryDao implements CoupleDao {
	
	@Override
	public Couple find(long id) {
		Couple couple = new Couple("168962961", "170020609");
		couple.setScore(new Random().nextInt(100));
		
		return couple;
	}
	
	@Override
	public Couple findForPartners(String firstPartner, String secondPartner) {
		return null;
	}
	
	@Override
	public Couple create(String firstPartner, String secondPartner) {
		Couple couple = new Couple("168962961", "170020609");
		couple.setId(100L);
		return couple;
	}
}
