package userss.mapper;

import userss.entity.Users;

import java.util.List;
import java.util.Optional;

public interface UsersMapper {

    Optional<Users> getOneUsers(String userName);

}
