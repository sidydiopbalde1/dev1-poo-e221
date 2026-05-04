package com.ecommerce.catalogue.repositories;

import java.util.Optional;

import com.ecommerce.catalogue.domain.entity.User;

public interface UserRepository{
    Optional<User> findById(String id);

}
