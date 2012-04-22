package com.couple.persistence;

import com.couple.model.Couple;

public interface CoupleDao {
	Couple find(long id);
}
