package com.emarketshop.user_service.service;

import java.util.List;
import java.util.Locale;

import com.emarketshop.user_service.dto.UserDto;

public interface UserService {

	List<UserDto> findAll();

	// UserDto findById(final Integer userId);

	UserDto findById(final Integer userId, final Locale locale);

	UserDto save(final UserDto userDto);

	UserDto update(final UserDto userDto);

	// UserDto update(final Integer userId, final UserDto userDto);
	
	UserDto update(final Integer userId, final UserDto userDto, final Locale locale);

	void deleteById(final Integer userId);

	UserDto findByUsername(final String username);

}
