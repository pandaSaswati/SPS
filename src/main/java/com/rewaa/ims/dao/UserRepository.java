package com.rewaa.ims.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rewaa.ims.dto.UserDTO;

public interface UserRepository //extends JpaRepository<UserDTO, Integer>
{
	UserDTO findByLoginId(String loginId);
}
