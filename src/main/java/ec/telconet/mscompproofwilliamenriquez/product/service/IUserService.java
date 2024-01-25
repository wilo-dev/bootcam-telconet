package ec.telconet.mscompproofwilliamenriquez.product.service;

import ec.telconet.mscompproofwilliamenriquez.product.entity.model.UserEntity;

import java.util.List;

public interface IUserService {
    public List<UserEntity> findAll();

}
