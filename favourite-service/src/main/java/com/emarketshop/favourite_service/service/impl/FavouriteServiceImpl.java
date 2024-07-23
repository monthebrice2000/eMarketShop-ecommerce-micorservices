package com.emarketshop.favourite_service.service.impl;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.emarketshop.favourite_service.constant.AppConstant;
import com.emarketshop.favourite_service.domain.id.FavouriteId;
import com.emarketshop.favourite_service.dto.FavouriteDto;
import com.emarketshop.favourite_service.dto.ProductDto;
import com.emarketshop.favourite_service.dto.UserDto;
import com.emarketshop.favourite_service.exception.wrapper.FavouriteNotFoundException;
import com.emarketshop.favourite_service.helper.FavouriteMappingHelper;
import com.emarketshop.favourite_service.repository.FavouriteRepository;
import com.emarketshop.favourite_service.service.FavouriteService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class FavouriteServiceImpl implements FavouriteService {

	MessageSource messages;

	private final FavouriteRepository favouriteRepository;
	private final RestTemplate restTemplate;

	@Override
	public List<FavouriteDto> findAll() {
		log.info("*** FavouriteDto List, service; fetch all favourites *");
		return this.favouriteRepository.findAll()
				.stream()
				.map(FavouriteMappingHelper::map)
				.map(f -> {
					f.setUserDto(this.restTemplate
							.getForObject(AppConstant.DiscoveredDomainsApi.USER_SERVICE_API_URL + "/" + f.getUserId(),
									UserDto.class));
					f.setProductDto(this.restTemplate
							.getForObject(
									AppConstant.DiscoveredDomainsApi.PRODUCT_SERVICE_API_URL + "/" + f.getProductId(),
									ProductDto.class));
					return f;
				})
				.distinct()
				.collect(Collectors.toUnmodifiableList());
	}

	@Override
	public FavouriteDto findById(final FavouriteId favouriteId, final Locale locale) {
		log.info("*** FavouriteDto, service; fetch favourite by id *");
		return this.favouriteRepository.findById(favouriteId)
				.map(FavouriteMappingHelper::map)
				.map(f -> {
					f.setUserDto(this.restTemplate
							.getForObject(AppConstant.DiscoveredDomainsApi.USER_SERVICE_API_URL + "/" + f.getUserId(),
									UserDto.class));
					f.setProductDto(this.restTemplate
							.getForObject(
									AppConstant.DiscoveredDomainsApi.PRODUCT_SERVICE_API_URL + "/" + f.getProductId(),
									ProductDto.class));
					return f;
				})
				.orElseThrow(() -> new FavouriteNotFoundException(
						String.format(messages.getMessage("favourite.findbyid.error.message", null, locale),
								favouriteId)));

	}

	@Override
	public FavouriteDto save(final FavouriteDto favouriteDto) {
		return FavouriteMappingHelper.map(this.favouriteRepository
				.save(FavouriteMappingHelper.map(favouriteDto)));
	}

	@Override
	public FavouriteDto update(final FavouriteDto favouriteDto) {
		return FavouriteMappingHelper.map(this.favouriteRepository
				.save(FavouriteMappingHelper.map(favouriteDto)));
	}

	@Override
	public void deleteById(final FavouriteId favouriteId) {
		this.favouriteRepository.deleteById(favouriteId);
	}

}
