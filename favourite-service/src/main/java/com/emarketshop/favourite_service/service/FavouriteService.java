package com.emarketshop.favourite_service.service;

import java.util.List;
import java.util.Locale;

import com.emarketshop.favourite_service.domain.id.FavouriteId;
import com.emarketshop.favourite_service.dto.FavouriteDto;

public interface FavouriteService {
	
	List<FavouriteDto> findAll();
	// FavouriteDto findById(final FavouriteId favouriteId);
	FavouriteDto findById(final FavouriteId favouriteId, final Locale locale);
	FavouriteDto save(final FavouriteDto favouriteDto);
	FavouriteDto update(final FavouriteDto favouriteDto);
	void deleteById(final FavouriteId favouriteId);
	
}
