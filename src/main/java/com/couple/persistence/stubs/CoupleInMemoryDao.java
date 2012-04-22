package com.couple.persistence.stubs;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.couple.model.Couple;
import com.couple.persistence.CoupleDao;

@Repository
public class CoupleInMemoryDao implements CoupleDao {
	private AtomicLong idCounter = new AtomicLong();
	private Map<Long, Couple> couplesById = new ConcurrentHashMap<Long, Couple>();
	private Map<String, Couple> couplesByPartners = new ConcurrentHashMap<String, Couple>();
	
	
	@Override
	public Couple find(long id) {
		return couplesById.get(id);
	}
	
	@Override
	public Couple findForPartners(String firstPartner, String secondPartner) {
		return null;
	}
	
	@Override
	public Couple create(String firstPartner, String secondPartner) {
		Couple couple = new Couple(firstPartner, secondPartner);
		couple.setId(idCounter.incrementAndGet());
		couple.setScore(new Random().nextInt(100));
		
		couplesById.put(couple.getId(), couple);
		couplesByPartners.put(firstPartner, couple);
		couplesByPartners.put(secondPartner, couple);
		
		return couple;
	}
}
