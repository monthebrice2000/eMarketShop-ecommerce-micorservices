package com.emarketshop.user_service.service.impl;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.emarketshop.user_service.dto.UserDto;
import com.emarketshop.user_service.exception.wrapper.UserObjectNotFoundException;
import com.emarketshop.user_service.helper.UserMappingHelper;
import com.emarketshop.user_service.repository.UserRepository;
import com.emarketshop.user_service.service.UserService;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	@Autowired
	MessageSource messages;

	private final UserRepository userRepository;

	@Override
	@CircuitBreaker(name = "userService")
	@Bulkhead(name = "bulkheadUserService", type = Bulkhead.Type.THREADPOOL)
	@Retry(name = "retryUserService")
	public List<UserDto> findAll() {
		log.info("*** UserDto List, service; fetch all users *");
		return this.userRepository.findAll()
				.stream()
				.map(UserMappingHelper::map)
				.distinct()
				.collect(Collectors.toUnmodifiableList());
	}

	@Override
	public UserDto findById(final Integer userId, final Locale locale) {
		log.info("*** UserDto, service; fetch user by id *");
		return this.userRepository.findById(userId)
				.map(UserMappingHelper::map)
				.orElseThrow(() -> new UserObjectNotFoundException(
						String.format(messages.getMessage("user.findbyid.error.message", null, locale), userId)));
	}

	@Override
	public UserDto save(final UserDto userDto) {
		log.info("*** UserDto, service; save user *");
		return UserMappingHelper.map(this.userRepository.save(UserMappingHelper.map(userDto)));
	}

	@Override
	public UserDto update(final UserDto userDto) {
		log.info("*** UserDto, service; update user *");
		return UserMappingHelper.map(this.userRepository.save(UserMappingHelper.map(userDto)));
	}

	@Override
	public UserDto update(final Integer userId, final UserDto userDto, final Locale locale) {
		log.info("*** UserDto, service; update user with userId *");
		return UserMappingHelper.map(this.userRepository.save(
				UserMappingHelper.map(this.findById(userId,locale))));
	}

	@Override
	public void deleteById(final Integer userId) {
		log.info("*** Void, service; delete user by id *");
		this.userRepository.deleteById(userId);
	}

	@Override
	public UserDto findByUsername(final String username) {
		log.info("*** UserDto, service; fetch user with username *");
		return UserMappingHelper.map(this.userRepository.findByCredentialUsername(username)
				.orElseThrow(() -> new UserObjectNotFoundException(
						String.format("User with username: %s not found", username))));
	}

}
