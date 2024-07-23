package com.emarketshop.favourite_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emarketshop.favourite_service.domain.Favourite;
import com.emarketshop.favourite_service.domain.id.FavouriteId;

public interface FavouriteRepository extends JpaRepository<Favourite, FavouriteId> {
	
	
	
}
